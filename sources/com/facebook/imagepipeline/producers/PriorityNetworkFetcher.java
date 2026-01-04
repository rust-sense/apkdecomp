package com.facebook.imagepipeline.producers;

import com.facebook.common.logging.FLog;
import com.facebook.common.time.MonotonicClock;
import com.facebook.common.time.RealtimeSinceBootClock;
import com.facebook.imagepipeline.common.Priority;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.producers.FetchState;
import com.facebook.imagepipeline.producers.NetworkFetcher;
import io.sentry.ProfilingTraceData;
import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.net.ConnectException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;

/* loaded from: classes.dex */
public class PriorityNetworkFetcher<FETCH_STATE extends FetchState> implements NetworkFetcher<PriorityFetchState<FETCH_STATE>> {
    static final int INFINITE_REQUEUE = -1;
    static final int NO_DELAYED_REQUESTS = -1;
    public static final String TAG = "PriorityNetworkFetcher";
    private final boolean doNotCancelRequests;
    private long firstDelayedRequestEnqueuedTimeStamp;
    private final int immediateRequeueCount;
    private final boolean inflightFetchesCanBeCancelled;
    private volatile boolean isRunning;
    private final MonotonicClock mClock;
    private final HashSet<PriorityFetchState<FETCH_STATE>> mCurrentlyFetching;
    private final LinkedList<PriorityFetchState<FETCH_STATE>> mDelayedQueue;
    private final NetworkFetcher<FETCH_STATE> mDelegate;
    private final LinkedList<PriorityFetchState<FETCH_STATE>> mHiPriQueue;
    private final boolean mIsHiPriFifo;
    private final Object mLock;
    private final LinkedList<PriorityFetchState<FETCH_STATE>> mLowPriQueue;
    private final int mMaxOutstandingHiPri;
    private final int mMaxOutstandingLowPri;
    private final int maxAttemptCount;
    private final int maxConnectAttemptCount;
    private final int maxNumberOfRequeue;
    private final boolean multipleDequeue;
    private final boolean nonRecoverableExceptionPreventsRequeue;
    private final long requeueDelayTimeInMillis;
    private final boolean retryLowPriAll;
    private final boolean retryLowPriConnectionException;
    private final boolean retryLowPriUnknownHostException;

    HashSet<PriorityFetchState<FETCH_STATE>> getCurrentlyFetching() {
        return this.mCurrentlyFetching;
    }

    List<PriorityFetchState<FETCH_STATE>> getDelayedQeueue() {
        return this.mDelayedQueue;
    }

    List<PriorityFetchState<FETCH_STATE>> getHiPriQueue() {
        return this.mHiPriQueue;
    }

    List<PriorityFetchState<FETCH_STATE>> getLowPriQueue() {
        return this.mLowPriQueue;
    }

    public void pause() {
        this.isRunning = false;
    }

    @Override // com.facebook.imagepipeline.producers.NetworkFetcher
    public /* bridge */ /* synthetic */ FetchState createFetchState(Consumer consumer, ProducerContext producerContext) {
        return createFetchState((Consumer<EncodedImage>) consumer, producerContext);
    }

    public PriorityNetworkFetcher(NetworkFetcher<FETCH_STATE> networkFetcher, boolean z, int i, int i2, boolean z2, int i3, boolean z3, int i4, int i5, boolean z4, boolean z5, int i6, int i7, boolean z6, boolean z7, boolean z8) {
        this(networkFetcher, z, i, i2, z2, i3, z3, i4, i5, z4, z5, i6, i7, z6, z7, z8, RealtimeSinceBootClock.get());
    }

    public PriorityNetworkFetcher(NetworkFetcher<FETCH_STATE> networkFetcher, boolean z, int i, int i2, boolean z2, int i3, boolean z3, int i4, int i5, boolean z4, boolean z5, int i6, int i7, boolean z6, boolean z7, boolean z8, MonotonicClock monotonicClock) {
        this.mLock = new Object();
        this.mHiPriQueue = new LinkedList<>();
        this.mLowPriQueue = new LinkedList<>();
        this.mCurrentlyFetching = new HashSet<>();
        this.mDelayedQueue = new LinkedList<>();
        this.isRunning = true;
        this.mDelegate = networkFetcher;
        this.mIsHiPriFifo = z;
        this.mMaxOutstandingHiPri = i;
        this.mMaxOutstandingLowPri = i2;
        if (i <= i2) {
            throw new IllegalArgumentException("maxOutstandingHiPri should be > maxOutstandingLowPri");
        }
        this.inflightFetchesCanBeCancelled = z2;
        this.maxNumberOfRequeue = i3;
        this.doNotCancelRequests = z3;
        this.immediateRequeueCount = i4;
        this.requeueDelayTimeInMillis = i5;
        this.multipleDequeue = z4;
        this.nonRecoverableExceptionPreventsRequeue = z5;
        this.maxConnectAttemptCount = i6;
        this.maxAttemptCount = i7;
        this.retryLowPriAll = z6;
        this.retryLowPriUnknownHostException = z7;
        this.retryLowPriConnectionException = z8;
        this.mClock = monotonicClock;
    }

    public void resume() {
        this.isRunning = true;
        dequeueIfAvailableSlots();
    }

    @Override // com.facebook.imagepipeline.producers.NetworkFetcher
    public void fetch(final PriorityFetchState<FETCH_STATE> priorityFetchState, final NetworkFetcher.Callback callback) {
        priorityFetchState.getContext().addCallbacks(new BaseProducerContextCallbacks() { // from class: com.facebook.imagepipeline.producers.PriorityNetworkFetcher.1
            @Override // com.facebook.imagepipeline.producers.BaseProducerContextCallbacks, com.facebook.imagepipeline.producers.ProducerContextCallbacks
            public void onCancellationRequested() {
                if (PriorityNetworkFetcher.this.doNotCancelRequests) {
                    return;
                }
                if (PriorityNetworkFetcher.this.inflightFetchesCanBeCancelled || !PriorityNetworkFetcher.this.mCurrentlyFetching.contains(priorityFetchState)) {
                    PriorityNetworkFetcher.this.removeFromQueue(priorityFetchState, "CANCEL");
                    callback.onCancellation();
                }
            }

            @Override // com.facebook.imagepipeline.producers.BaseProducerContextCallbacks, com.facebook.imagepipeline.producers.ProducerContextCallbacks
            public void onPriorityChanged() {
                PriorityNetworkFetcher priorityNetworkFetcher = PriorityNetworkFetcher.this;
                PriorityFetchState priorityFetchState2 = priorityFetchState;
                priorityNetworkFetcher.changePriority(priorityFetchState2, priorityFetchState2.getContext().getPriority() == Priority.HIGH);
            }
        });
        synchronized (this.mLock) {
            if (this.mCurrentlyFetching.contains(priorityFetchState)) {
                FLog.e(TAG, "fetch state was enqueued twice: " + priorityFetchState);
                return;
            }
            boolean z = priorityFetchState.getContext().getPriority() == Priority.HIGH;
            FLog.v(TAG, "enqueue: %s %s", z ? "HI-PRI" : "LOW-PRI", priorityFetchState.getUri());
            priorityFetchState.callback = callback;
            putInQueue(priorityFetchState, z);
            dequeueIfAvailableSlots();
        }
    }

    @Override // com.facebook.imagepipeline.producers.NetworkFetcher
    public void onFetchCompletion(PriorityFetchState<FETCH_STATE> priorityFetchState, int i) {
        removeFromQueue(priorityFetchState, "SUCCESS");
        this.mDelegate.onFetchCompletion(priorityFetchState.delegatedState, i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void removeFromQueue(PriorityFetchState<FETCH_STATE> priorityFetchState, String str) {
        synchronized (this.mLock) {
            FLog.v(TAG, "remove: %s %s", str, priorityFetchState.getUri());
            this.mCurrentlyFetching.remove(priorityFetchState);
            if (!this.mHiPriQueue.remove(priorityFetchState)) {
                this.mLowPriQueue.remove(priorityFetchState);
            }
        }
        dequeueIfAvailableSlots();
    }

    private void moveDelayedRequestsToPriorityQueues() {
        if (this.mDelayedQueue.isEmpty() || this.mClock.now() - this.firstDelayedRequestEnqueuedTimeStamp <= this.requeueDelayTimeInMillis) {
            return;
        }
        Iterator<PriorityFetchState<FETCH_STATE>> it = this.mDelayedQueue.iterator();
        while (it.hasNext()) {
            PriorityFetchState<FETCH_STATE> next = it.next();
            putInQueue(next, next.getContext().getPriority() == Priority.HIGH);
        }
        this.mDelayedQueue.clear();
    }

    private void putInDelayedQueue(PriorityFetchState<FETCH_STATE> priorityFetchState) {
        if (this.mDelayedQueue.isEmpty()) {
            this.firstDelayedRequestEnqueuedTimeStamp = this.mClock.now();
        }
        priorityFetchState.delayCount++;
        this.mDelayedQueue.addLast(priorityFetchState);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void retry(PriorityFetchState<FETCH_STATE> priorityFetchState) {
        FLog.v(TAG, "retry: %s", priorityFetchState.getUri());
        synchronized (this.mLock) {
            priorityFetchState.delegatedState = this.mDelegate.createFetchState(priorityFetchState.getConsumer(), priorityFetchState.getContext());
            this.mCurrentlyFetching.remove(priorityFetchState);
            if (!this.mHiPriQueue.remove(priorityFetchState)) {
                this.mLowPriQueue.remove(priorityFetchState);
            }
            this.mHiPriQueue.addFirst(priorityFetchState);
        }
        dequeueIfAvailableSlots();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void requeue(PriorityFetchState<FETCH_STATE> priorityFetchState) {
        synchronized (this.mLock) {
            FLog.v(TAG, "requeue: %s", priorityFetchState.getUri());
            boolean z = true;
            priorityFetchState.requeueCount++;
            priorityFetchState.delegatedState = this.mDelegate.createFetchState(priorityFetchState.getConsumer(), priorityFetchState.getContext());
            this.mCurrentlyFetching.remove(priorityFetchState);
            if (!this.mHiPriQueue.remove(priorityFetchState)) {
                this.mLowPriQueue.remove(priorityFetchState);
            }
            if (this.immediateRequeueCount != -1 && priorityFetchState.requeueCount > this.immediateRequeueCount) {
                putInDelayedQueue(priorityFetchState);
            } else {
                if (priorityFetchState.getContext().getPriority() != Priority.HIGH) {
                    z = false;
                }
                putInQueue(priorityFetchState, z);
            }
        }
        dequeueIfAvailableSlots();
    }

    private void dequeueIfAvailableSlots() {
        if (this.isRunning) {
            synchronized (this.mLock) {
                moveDelayedRequestsToPriorityQueues();
                int size = this.mCurrentlyFetching.size();
                PriorityFetchState<FETCH_STATE> pollFirst = size < this.mMaxOutstandingHiPri ? this.mHiPriQueue.pollFirst() : null;
                if (pollFirst == null && size < this.mMaxOutstandingLowPri) {
                    pollFirst = this.mLowPriQueue.pollFirst();
                }
                if (pollFirst == null) {
                    return;
                }
                pollFirst.dequeuedTimestamp = this.mClock.now();
                this.mCurrentlyFetching.add(pollFirst);
                FLog.v(TAG, "fetching: %s (concurrent: %s hi-pri queue: %s low-pri queue: %s)", pollFirst.getUri(), Integer.valueOf(size), Integer.valueOf(this.mHiPriQueue.size()), Integer.valueOf(this.mLowPriQueue.size()));
                delegateFetch(pollFirst);
                if (this.multipleDequeue) {
                    dequeueIfAvailableSlots();
                }
            }
        }
    }

    private void delegateFetch(final PriorityFetchState<FETCH_STATE> priorityFetchState) {
        try {
            NetworkFetcher.Callback callback = new NetworkFetcher.Callback() { // from class: com.facebook.imagepipeline.producers.PriorityNetworkFetcher.2
                @Override // com.facebook.imagepipeline.producers.NetworkFetcher.Callback
                public void onResponse(InputStream inputStream, int i) throws IOException {
                    NetworkFetcher.Callback callback2 = priorityFetchState.callback;
                    if (callback2 != null) {
                        callback2.onResponse(inputStream, i);
                    }
                }

                @Override // com.facebook.imagepipeline.producers.NetworkFetcher.Callback
                public void onFailure(Throwable th) {
                    if (PriorityNetworkFetcher.shouldRetry(th, priorityFetchState.attemptCount, PriorityNetworkFetcher.this.maxConnectAttemptCount, PriorityNetworkFetcher.this.maxAttemptCount, PriorityNetworkFetcher.this.retryLowPriAll, PriorityNetworkFetcher.this.retryLowPriUnknownHostException, PriorityNetworkFetcher.this.retryLowPriConnectionException, priorityFetchState.getContext().getPriority())) {
                        PriorityNetworkFetcher.this.retry(priorityFetchState);
                        return;
                    }
                    if ((PriorityNetworkFetcher.this.maxNumberOfRequeue == -1 || priorityFetchState.requeueCount < PriorityNetworkFetcher.this.maxNumberOfRequeue) && (!PriorityNetworkFetcher.this.nonRecoverableExceptionPreventsRequeue || !(th instanceof NonrecoverableException))) {
                        PriorityNetworkFetcher.this.requeue(priorityFetchState);
                        return;
                    }
                    PriorityNetworkFetcher.this.removeFromQueue(priorityFetchState, "FAIL");
                    NetworkFetcher.Callback callback2 = priorityFetchState.callback;
                    if (callback2 != null) {
                        callback2.onFailure(th);
                    }
                }

                @Override // com.facebook.imagepipeline.producers.NetworkFetcher.Callback
                public void onCancellation() {
                    PriorityNetworkFetcher.this.removeFromQueue(priorityFetchState, "CANCEL");
                    NetworkFetcher.Callback callback2 = priorityFetchState.callback;
                    if (callback2 != null) {
                        callback2.onCancellation();
                    }
                }
            };
            priorityFetchState.attemptCount++;
            this.mDelegate.fetch(priorityFetchState.delegatedState, callback);
        } catch (Exception unused) {
            removeFromQueue(priorityFetchState, "FAIL");
        }
    }

    private void changePriorityInDelayedQueue(PriorityFetchState<FETCH_STATE> priorityFetchState) {
        if (this.mDelayedQueue.remove(priorityFetchState)) {
            priorityFetchState.priorityChangedCount++;
            this.mDelayedQueue.addLast(priorityFetchState);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void changePriority(PriorityFetchState<FETCH_STATE> priorityFetchState, boolean z) {
        synchronized (this.mLock) {
            if (!(z ? this.mLowPriQueue : this.mHiPriQueue).remove(priorityFetchState)) {
                changePriorityInDelayedQueue(priorityFetchState);
                return;
            }
            FLog.v(TAG, "change-pri: %s %s", z ? "HIPRI" : "LOWPRI", priorityFetchState.getUri());
            priorityFetchState.priorityChangedCount++;
            putInQueue(priorityFetchState, z);
            dequeueIfAvailableSlots();
        }
    }

    private void putInQueue(PriorityFetchState<FETCH_STATE> priorityFetchState, boolean z) {
        if (!z) {
            this.mLowPriQueue.addLast(priorityFetchState);
        } else if (this.mIsHiPriFifo) {
            this.mHiPriQueue.addLast(priorityFetchState);
        } else {
            this.mHiPriQueue.addFirst(priorityFetchState);
        }
    }

    public static class PriorityFetchState<FETCH_STATE extends FetchState> extends FetchState {
        int attemptCount;

        @Nullable
        NetworkFetcher.Callback callback;
        final int currentlyFetchingCountWhenCreated;
        int delayCount;
        public FETCH_STATE delegatedState;
        long dequeuedTimestamp;
        final long enqueuedTimestamp;
        final int hiPriCountWhenCreated;
        final boolean isInitialPriorityHigh;
        final int lowPriCountWhenCreated;
        int priorityChangedCount;
        int requeueCount;

        private PriorityFetchState(Consumer<EncodedImage> consumer, ProducerContext producerContext, FETCH_STATE fetch_state, long j, int i, int i2, int i3) {
            super(consumer, producerContext);
            this.requeueCount = 0;
            this.attemptCount = 0;
            this.delayCount = 0;
            this.priorityChangedCount = 0;
            this.delegatedState = fetch_state;
            this.enqueuedTimestamp = j;
            this.hiPriCountWhenCreated = i;
            this.lowPriCountWhenCreated = i2;
            this.isInitialPriorityHigh = producerContext.getPriority() == Priority.HIGH;
            this.currentlyFetchingCountWhenCreated = i3;
        }
    }

    public static class NonrecoverableException extends Throwable {
        public NonrecoverableException(String str) {
            super(str);
        }
    }

    public static class RetriableIOException extends IOException {
        public RetriableIOException(Throwable th) {
            super(th);
        }

        @Override // java.lang.Throwable
        public String toString() {
            Throwable cause = getCause();
            return cause != null ? cause.toString() : toString();
        }
    }

    @Override // com.facebook.imagepipeline.producers.NetworkFetcher
    public PriorityFetchState<FETCH_STATE> createFetchState(Consumer<EncodedImage> consumer, ProducerContext producerContext) {
        return new PriorityFetchState<>(consumer, producerContext, this.mDelegate.createFetchState(consumer, producerContext), this.mClock.now(), this.mHiPriQueue.size(), this.mLowPriQueue.size(), this.mCurrentlyFetching.size());
    }

    @Override // com.facebook.imagepipeline.producers.NetworkFetcher
    public boolean shouldPropagate(PriorityFetchState<FETCH_STATE> priorityFetchState) {
        return this.mDelegate.shouldPropagate(priorityFetchState.delegatedState);
    }

    @Override // com.facebook.imagepipeline.producers.NetworkFetcher
    @Nullable
    public Map<String, String> getExtraMap(PriorityFetchState<FETCH_STATE> priorityFetchState, int i) {
        Map<String, String> extraMap = this.mDelegate.getExtraMap(priorityFetchState.delegatedState, i);
        HashMap hashMap = extraMap != null ? new HashMap(extraMap) : new HashMap();
        hashMap.put("pri_queue_time", "" + (priorityFetchState.dequeuedTimestamp - priorityFetchState.enqueuedTimestamp));
        hashMap.put("hipri_queue_size", "" + priorityFetchState.hiPriCountWhenCreated);
        hashMap.put("lowpri_queue_size", "" + priorityFetchState.lowPriCountWhenCreated);
        hashMap.put("requeueCount", "" + priorityFetchState.requeueCount);
        hashMap.put("priority_changed_count", "" + priorityFetchState.priorityChangedCount);
        hashMap.put("request_initial_priority_is_high", "" + priorityFetchState.isInitialPriorityHigh);
        hashMap.put("currently_fetching_size", "" + priorityFetchState.currentlyFetchingCountWhenCreated);
        hashMap.put("delay_count", "" + priorityFetchState.delayCount);
        return hashMap;
    }

    private static boolean shouldRetryLowPrioritySpecificExceptions(Throwable th, boolean z, boolean z2) {
        if (th instanceof UnknownHostException) {
            return z;
        }
        if (th instanceof ConnectException) {
            return z2;
        }
        return false;
    }

    public static boolean shouldRetry(Throwable th, int i, int i2, int i3, boolean z, boolean z2, boolean z3, Priority priority) {
        boolean z4 = th instanceof ConnectException;
        if ((z4 && i >= i2) || i >= i3) {
            return false;
        }
        boolean z5 = priority == Priority.HIGH;
        if (!z && !z5) {
            return shouldRetryLowPrioritySpecificExceptions(th, z2, z3);
        }
        if ((z5 && (th instanceof NonrecoverableException)) || (th instanceof SocketTimeoutException) || (th instanceof UnknownHostException) || z4 || (th instanceof RetriableIOException)) {
            return true;
        }
        String message = th.getMessage();
        if (message == null) {
            return false;
        }
        boolean z6 = th instanceof IOException;
        return (z6 && message.contains("Canceled")) || (z6 && message.contains("unexpected end of stream on null")) || (((th instanceof SocketException) && message.contains("Socket closed")) || (z5 && (th instanceof InterruptedIOException) && message.contains(ProfilingTraceData.TRUNCATION_REASON_TIMEOUT)));
    }
}
