package com.google.firebase.messaging;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import androidx.collection.ArrayMap;
import androidx.exifinterface.media.ExifInterface;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.installations.FirebaseInstallationsApi;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* compiled from: com.google.firebase:firebase-messaging@@22.0.0 */
/* loaded from: classes2.dex */
class TopicsSubscriber {
    private static final long MAX_DELAY_SEC = TimeUnit.HOURS.toSeconds(8);
    private final Context context;
    private final FirebaseInstallationsApi firebaseInstallationsApi;
    private final FirebaseMessaging firebaseMessaging;
    private final Metadata metadata;
    private final GmsRpc rpc;
    private final TopicsStore store;
    private final ScheduledExecutorService syncExecutor;
    private final Map<String, ArrayDeque<TaskCompletionSource<Void>>> pendingOperations = new ArrayMap();
    private boolean syncScheduledOrRunning = false;

    private TopicsSubscriber(FirebaseMessaging firebaseMessaging, FirebaseInstallationsApi firebaseInstallationsApi, Metadata metadata, TopicsStore topicsStore, GmsRpc gmsRpc, Context context, ScheduledExecutorService scheduledExecutorService) {
        this.firebaseMessaging = firebaseMessaging;
        this.firebaseInstallationsApi = firebaseInstallationsApi;
        this.metadata = metadata;
        this.store = topicsStore;
        this.rpc = gmsRpc;
        this.context = context;
        this.syncExecutor = scheduledExecutorService;
    }

    private void addToPendingOperations(TopicOperation topicOperation, TaskCompletionSource<Void> taskCompletionSource) {
        ArrayDeque<TaskCompletionSource<Void>> arrayDeque;
        synchronized (this.pendingOperations) {
            String serialize = topicOperation.serialize();
            if (this.pendingOperations.containsKey(serialize)) {
                arrayDeque = this.pendingOperations.get(serialize);
            } else {
                ArrayDeque<TaskCompletionSource<Void>> arrayDeque2 = new ArrayDeque<>();
                this.pendingOperations.put(serialize, arrayDeque2);
                arrayDeque = arrayDeque2;
            }
            arrayDeque.add(taskCompletionSource);
        }
    }

    private static <T> T awaitTask(Task<T> task) throws IOException {
        try {
            return (T) Tasks.await(task, 30L, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e = e;
            throw new IOException("SERVICE_NOT_AVAILABLE", e);
        } catch (ExecutionException e2) {
            Throwable cause = e2.getCause();
            if (cause instanceof IOException) {
                throw ((IOException) cause);
            }
            if (cause instanceof RuntimeException) {
                throw ((RuntimeException) cause);
            }
            throw new IOException(e2);
        } catch (TimeoutException e3) {
            e = e3;
            throw new IOException("SERVICE_NOT_AVAILABLE", e);
        }
    }

    private void blockingSubscribeToTopic(String str) throws IOException {
        awaitTask(this.rpc.subscribeToTopic((String) awaitTask(this.firebaseInstallationsApi.getId()), this.firebaseMessaging.blockingGetToken(), str));
    }

    private void blockingUnsubscribeFromTopic(String str) throws IOException {
        awaitTask(this.rpc.unsubscribeFromTopic((String) awaitTask(this.firebaseInstallationsApi.getId()), this.firebaseMessaging.blockingGetToken(), str));
    }

    static Task<TopicsSubscriber> createInstance(final FirebaseMessaging firebaseMessaging, final FirebaseInstallationsApi firebaseInstallationsApi, final Metadata metadata, final GmsRpc gmsRpc, final Context context, final ScheduledExecutorService scheduledExecutorService) {
        return Tasks.call(scheduledExecutorService, new Callable(context, scheduledExecutorService, firebaseMessaging, firebaseInstallationsApi, metadata, gmsRpc) { // from class: com.google.firebase.messaging.TopicsSubscriber$$Lambda$0
            private final Context arg$1;
            private final ScheduledExecutorService arg$2;
            private final FirebaseMessaging arg$3;
            private final FirebaseInstallationsApi arg$4;
            private final Metadata arg$5;
            private final GmsRpc arg$6;

            {
                this.arg$1 = context;
                this.arg$2 = scheduledExecutorService;
                this.arg$3 = firebaseMessaging;
                this.arg$4 = firebaseInstallationsApi;
                this.arg$5 = metadata;
                this.arg$6 = gmsRpc;
            }

            @Override // java.util.concurrent.Callable
            public Object call() {
                return TopicsSubscriber.lambda$createInstance$0$TopicsSubscriber(this.arg$1, this.arg$2, this.arg$3, this.arg$4, this.arg$5, this.arg$6);
            }
        });
    }

    static boolean isDebugLogEnabled() {
        if (Log.isLoggable(Constants.TAG, 3)) {
            return true;
        }
        return Build.VERSION.SDK_INT == 23 && Log.isLoggable(Constants.TAG, 3);
    }

    static final /* synthetic */ TopicsSubscriber lambda$createInstance$0$TopicsSubscriber(Context context, ScheduledExecutorService scheduledExecutorService, FirebaseMessaging firebaseMessaging, FirebaseInstallationsApi firebaseInstallationsApi, Metadata metadata, GmsRpc gmsRpc) throws Exception {
        return new TopicsSubscriber(firebaseMessaging, firebaseInstallationsApi, metadata, TopicsStore.getInstance(context, scheduledExecutorService), gmsRpc, context, scheduledExecutorService);
    }

    private void markCompletePendingOperation(TopicOperation topicOperation) {
        synchronized (this.pendingOperations) {
            String serialize = topicOperation.serialize();
            if (this.pendingOperations.containsKey(serialize)) {
                ArrayDeque<TaskCompletionSource<Void>> arrayDeque = this.pendingOperations.get(serialize);
                TaskCompletionSource<Void> poll = arrayDeque.poll();
                if (poll != null) {
                    poll.setResult(null);
                }
                if (arrayDeque.isEmpty()) {
                    this.pendingOperations.remove(serialize);
                }
            }
        }
    }

    private void startSync() {
        if (isSyncScheduledOrRunning()) {
            return;
        }
        syncWithDelaySecondsInternal(0L);
    }

    boolean hasPendingOperation() {
        return this.store.getNextTopicOperation() != null;
    }

    synchronized boolean isSyncScheduledOrRunning() {
        return this.syncScheduledOrRunning;
    }

    boolean performTopicOperation(TopicOperation topicOperation) throws IOException {
        char c;
        try {
            String operation = topicOperation.getOperation();
            int hashCode = operation.hashCode();
            if (hashCode != 83) {
                if (hashCode == 85 && operation.equals("U")) {
                    c = 1;
                }
                c = 65535;
            } else {
                if (operation.equals(ExifInterface.LATITUDE_SOUTH)) {
                    c = 0;
                }
                c = 65535;
            }
            if (c == 0) {
                blockingSubscribeToTopic(topicOperation.getTopic());
                if (isDebugLogEnabled()) {
                    String topic = topicOperation.getTopic();
                    StringBuilder sb = new StringBuilder(String.valueOf(topic).length() + 31);
                    sb.append("Subscribe to topic: ");
                    sb.append(topic);
                    sb.append(" succeeded.");
                    Log.d(Constants.TAG, sb.toString());
                }
            } else if (c == 1) {
                blockingUnsubscribeFromTopic(topicOperation.getTopic());
                if (isDebugLogEnabled()) {
                    String topic2 = topicOperation.getTopic();
                    StringBuilder sb2 = new StringBuilder(String.valueOf(topic2).length() + 35);
                    sb2.append("Unsubscribe from topic: ");
                    sb2.append(topic2);
                    sb2.append(" succeeded.");
                    Log.d(Constants.TAG, sb2.toString());
                }
            } else if (isDebugLogEnabled()) {
                String valueOf = String.valueOf(topicOperation);
                StringBuilder sb3 = new StringBuilder(String.valueOf(valueOf).length() + 24);
                sb3.append("Unknown topic operation");
                sb3.append(valueOf);
                sb3.append(".");
                Log.d(Constants.TAG, sb3.toString());
            }
            return true;
        } catch (IOException e) {
            if (!"SERVICE_NOT_AVAILABLE".equals(e.getMessage()) && !"INTERNAL_SERVER_ERROR".equals(e.getMessage())) {
                if (e.getMessage() != null) {
                    throw e;
                }
                Log.e(Constants.TAG, "Topic operation failed without exception message. Will retry Topic operation.");
                return false;
            }
            String message = e.getMessage();
            StringBuilder sb4 = new StringBuilder(String.valueOf(message).length() + 53);
            sb4.append("Topic operation failed: ");
            sb4.append(message);
            sb4.append(". Will retry Topic operation.");
            Log.e(Constants.TAG, sb4.toString());
            return false;
        }
    }

    void scheduleSyncTaskWithDelaySeconds(Runnable runnable, long j) {
        this.syncExecutor.schedule(runnable, j, TimeUnit.SECONDS);
    }

    Task<Void> scheduleTopicOperation(TopicOperation topicOperation) {
        this.store.addTopicOperation(topicOperation);
        TaskCompletionSource<Void> taskCompletionSource = new TaskCompletionSource<>();
        addToPendingOperations(topicOperation, taskCompletionSource);
        return taskCompletionSource.getTask();
    }

    synchronized void setSyncScheduledOrRunning(boolean z) {
        this.syncScheduledOrRunning = z;
    }

    void startTopicsSyncIfNecessary() {
        if (hasPendingOperation()) {
            startSync();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Task<Void> subscribeToTopic(String str) {
        Task<Void> scheduleTopicOperation = scheduleTopicOperation(TopicOperation.subscribe(str));
        startTopicsSyncIfNecessary();
        return scheduleTopicOperation;
    }

    /* JADX WARN: Code restructure failed: missing block: B:14:0x000d, code lost:
    
        if (isDebugLogEnabled() == false) goto L8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x000f, code lost:
    
        android.util.Log.d(com.google.firebase.messaging.Constants.TAG, "topic sync succeeded");
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0017, code lost:
    
        return true;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    boolean syncTopics() throws java.io.IOException {
        /*
            r2 = this;
        L0:
            monitor-enter(r2)
            com.google.firebase.messaging.TopicsStore r0 = r2.store     // Catch: java.lang.Throwable -> L2b
            com.google.firebase.messaging.TopicOperation r0 = r0.getNextTopicOperation()     // Catch: java.lang.Throwable -> L2b
            if (r0 != 0) goto L19
            boolean r0 = isDebugLogEnabled()     // Catch: java.lang.Throwable -> L2b
            if (r0 == 0) goto L16
            java.lang.String r0 = "FirebaseMessaging"
            java.lang.String r1 = "topic sync succeeded"
            android.util.Log.d(r0, r1)     // Catch: java.lang.Throwable -> L2b
        L16:
            monitor-exit(r2)     // Catch: java.lang.Throwable -> L2b
            r0 = 1
            return r0
        L19:
            monitor-exit(r2)     // Catch: java.lang.Throwable -> L2b
            boolean r1 = r2.performTopicOperation(r0)
            if (r1 != 0) goto L22
            r0 = 0
            return r0
        L22:
            com.google.firebase.messaging.TopicsStore r1 = r2.store
            r1.removeTopicOperation(r0)
            r2.markCompletePendingOperation(r0)
            goto L0
        L2b:
            r0 = move-exception
            monitor-exit(r2)     // Catch: java.lang.Throwable -> L2b
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.messaging.TopicsSubscriber.syncTopics():boolean");
    }

    void syncWithDelaySecondsInternal(long j) {
        scheduleSyncTaskWithDelaySeconds(new TopicsSyncTask(this, this.context, this.metadata, Math.min(Math.max(30L, j + j), MAX_DELAY_SEC)), j);
        setSyncScheduledOrRunning(true);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Task<Void> unsubscribeFromTopic(String str) {
        Task<Void> scheduleTopicOperation = scheduleTopicOperation(TopicOperation.unsubscribe(str));
        startTopicsSyncIfNecessary();
        return scheduleTopicOperation;
    }
}
