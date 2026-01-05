package com.facebook.drawee.backends.pipeline.info.internal;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.Supplier;
import com.facebook.common.time.MonotonicClock;
import com.facebook.fresco.ui.common.BaseControllerListener2;
import com.facebook.fresco.ui.common.ControllerListener2;
import com.facebook.fresco.ui.common.DimensionsInfo;
import com.facebook.fresco.ui.common.ImageLoadStatus;
import com.facebook.fresco.ui.common.ImagePerfNotifier;
import com.facebook.fresco.ui.common.ImagePerfNotifierHolder;
import com.facebook.fresco.ui.common.ImagePerfState;
import com.facebook.fresco.ui.common.OnDrawControllerListener;
import com.facebook.fresco.ui.common.VisibilityState;
import com.facebook.imagepipeline.image.ImageInfo;
import java.io.Closeable;
import javax.annotation.Nullable;

/* loaded from: classes.dex */
public class ImagePerfControllerListener2 extends BaseControllerListener2<ImageInfo> implements ImagePerfNotifierHolder, OnDrawControllerListener<ImageInfo>, Closeable {
    private static final int WHAT_STATUS = 1;
    private static final int WHAT_VISIBILITY = 2;

    @Nullable
    private static LogHandler sHandler;
    private final Supplier<Boolean> mAsyncLogging;
    private final MonotonicClock mClock;
    private final ImagePerfNotifier mImagePerfNotifier;
    private final ImagePerfState mImagePerfState;

    @Nullable
    private ImagePerfNotifier mLocalImagePerfNotifier = null;

    static class LogHandler extends Handler implements ImagePerfNotifierHolder {

        @Nullable
        private ImagePerfNotifier mLocalNotifier;
        private final ImagePerfNotifier mNotifier;

        @Override // com.facebook.fresco.ui.common.ImagePerfNotifierHolder
        public void setImagePerfNotifier(@Nullable ImagePerfNotifier imagePerfNotifier) {
            this.mLocalNotifier = imagePerfNotifier;
        }

        public LogHandler(Looper looper, ImagePerfNotifier imagePerfNotifier, @Nullable ImagePerfNotifier imagePerfNotifier2) {
            super(looper);
            this.mNotifier = imagePerfNotifier;
            this.mLocalNotifier = imagePerfNotifier2;
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            ImagePerfState imagePerfState = (ImagePerfState) Preconditions.checkNotNull(message.obj);
            ImagePerfNotifier imagePerfNotifier = this.mLocalNotifier;
            int i = message.what;
            if (i == 1) {
                ImageLoadStatus fromInt = ImageLoadStatus.INSTANCE.fromInt(message.arg1);
                if (fromInt == null) {
                    throw new IllegalArgumentException("Invalid ImageLoadStatus value: " + message.arg1);
                }
                this.mNotifier.notifyStatusUpdated(imagePerfState, fromInt);
                if (imagePerfNotifier != null) {
                    imagePerfNotifier.notifyStatusUpdated(imagePerfState, fromInt);
                    return;
                }
                return;
            }
            if (i != 2) {
                return;
            }
            VisibilityState fromInt2 = VisibilityState.INSTANCE.fromInt(message.arg1);
            if (fromInt2 == null) {
                throw new IllegalArgumentException("Invalid VisibilityState value: " + message.arg1);
            }
            this.mNotifier.notifyListenersOfVisibilityStateUpdate(imagePerfState, fromInt2);
            if (imagePerfNotifier != null) {
                imagePerfNotifier.notifyListenersOfVisibilityStateUpdate(imagePerfState, fromInt2);
            }
        }
    }

    public ImagePerfControllerListener2(MonotonicClock monotonicClock, ImagePerfState imagePerfState, ImagePerfNotifier imagePerfNotifier, Supplier<Boolean> supplier) {
        this.mClock = monotonicClock;
        this.mImagePerfState = imagePerfState;
        this.mImagePerfNotifier = imagePerfNotifier;
        this.mAsyncLogging = supplier;
    }

    @Override // com.facebook.fresco.ui.common.ImagePerfNotifierHolder
    public void setImagePerfNotifier(@Nullable ImagePerfNotifier imagePerfNotifier) {
        this.mLocalImagePerfNotifier = imagePerfNotifier;
        LogHandler logHandler = sHandler;
        if (logHandler != null) {
            logHandler.setImagePerfNotifier(imagePerfNotifier);
        }
    }

    @Override // com.facebook.fresco.ui.common.BaseControllerListener2, com.facebook.fresco.ui.common.ControllerListener2
    public void onSubmit(String str, @Nullable Object obj, @Nullable ControllerListener2.Extras extras) {
        long now = this.mClock.now();
        ImagePerfState imagePerfState = this.mImagePerfState;
        imagePerfState.resetPointsTimestamps();
        imagePerfState.setControllerSubmitTimeMs(now);
        imagePerfState.setControllerId(str);
        imagePerfState.setCallerContext(obj);
        imagePerfState.setExtraData(extras);
        updateStatus(imagePerfState, ImageLoadStatus.REQUESTED);
        reportViewVisible(imagePerfState, now);
    }

    @Override // com.facebook.fresco.ui.common.BaseControllerListener2, com.facebook.fresco.ui.common.ControllerListener2
    public void onIntermediateImageSet(String str, @Nullable ImageInfo imageInfo) {
        long now = this.mClock.now();
        ImagePerfState imagePerfState = this.mImagePerfState;
        imagePerfState.setControllerIntermediateImageSetTimeMs(now);
        imagePerfState.setControllerId(str);
        imagePerfState.setImageInfo(imageInfo);
        updateStatus(imagePerfState, ImageLoadStatus.INTERMEDIATE_AVAILABLE);
    }

    @Override // com.facebook.fresco.ui.common.BaseControllerListener2, com.facebook.fresco.ui.common.ControllerListener2
    public void onFinalImageSet(String str, @Nullable ImageInfo imageInfo, @Nullable ControllerListener2.Extras extras) {
        long now = this.mClock.now();
        ImagePerfState imagePerfState = this.mImagePerfState;
        imagePerfState.setExtraData(extras);
        imagePerfState.setControllerFinalImageSetTimeMs(now);
        imagePerfState.setImageRequestEndTimeMs(now);
        imagePerfState.setControllerId(str);
        imagePerfState.setImageInfo(imageInfo);
        updateStatus(imagePerfState, ImageLoadStatus.SUCCESS);
    }

    @Override // com.facebook.fresco.ui.common.BaseControllerListener2, com.facebook.fresco.ui.common.ControllerListener2
    public void onFailure(String str, @Nullable Throwable th, @Nullable ControllerListener2.Extras extras) {
        long now = this.mClock.now();
        ImagePerfState imagePerfState = this.mImagePerfState;
        imagePerfState.setExtraData(extras);
        imagePerfState.setControllerFailureTimeMs(now);
        imagePerfState.setControllerId(str);
        imagePerfState.setErrorThrowable(th);
        updateStatus(imagePerfState, ImageLoadStatus.ERROR);
        reportViewInvisible(imagePerfState, now);
    }

    @Override // com.facebook.fresco.ui.common.BaseControllerListener2, com.facebook.fresco.ui.common.ControllerListener2
    public void onRelease(String str, @Nullable ControllerListener2.Extras extras) {
        long now = this.mClock.now();
        ImagePerfState imagePerfState = this.mImagePerfState;
        imagePerfState.setExtraData(extras);
        imagePerfState.setControllerId(str);
        ImageLoadStatus imageLoadStatus = imagePerfState.getImageLoadStatus();
        if (imageLoadStatus != ImageLoadStatus.SUCCESS && imageLoadStatus != ImageLoadStatus.ERROR && imageLoadStatus != ImageLoadStatus.DRAW) {
            imagePerfState.setControllerCancelTimeMs(now);
            updateStatus(imagePerfState, ImageLoadStatus.CANCELED);
        }
        reportViewInvisible(imagePerfState, now);
    }

    @Override // com.facebook.fresco.ui.common.OnDrawControllerListener
    public void onImageDrawn(String str, ImageInfo imageInfo, DimensionsInfo dimensionsInfo) {
        ImagePerfState imagePerfState = this.mImagePerfState;
        imagePerfState.setControllerId(str);
        imagePerfState.setImageDrawTimeMs(this.mClock.now());
        imagePerfState.setDimensionsInfo(dimensionsInfo);
        updateStatus(imagePerfState, ImageLoadStatus.DRAW);
    }

    public void reportViewVisible(ImagePerfState imagePerfState, long j) {
        imagePerfState.setVisible(true);
        imagePerfState.setVisibilityEventTimeMs(j);
        updateVisibility(imagePerfState, VisibilityState.VISIBLE);
    }

    public void resetState() {
        this.mImagePerfState.reset();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        resetState();
    }

    private void reportViewInvisible(ImagePerfState imagePerfState, long j) {
        imagePerfState.setVisible(false);
        imagePerfState.setInvisibilityEventTimeMs(j);
        updateVisibility(imagePerfState, VisibilityState.INVISIBLE);
    }

    private void updateStatus(ImagePerfState imagePerfState, ImageLoadStatus imageLoadStatus) {
        imagePerfState.setImageLoadStatus(imageLoadStatus);
        if (shouldDispatchAsync()) {
            Message obtainMessage = ((LogHandler) Preconditions.checkNotNull(sHandler)).obtainMessage();
            obtainMessage.what = 1;
            obtainMessage.arg1 = imageLoadStatus.getValue();
            obtainMessage.obj = imagePerfState;
            sHandler.sendMessage(obtainMessage);
            return;
        }
        this.mImagePerfNotifier.notifyStatusUpdated(imagePerfState, imageLoadStatus);
        ImagePerfNotifier imagePerfNotifier = this.mLocalImagePerfNotifier;
        if (imagePerfNotifier != null) {
            imagePerfNotifier.notifyStatusUpdated(imagePerfState, imageLoadStatus);
        }
    }

    private void updateVisibility(ImagePerfState imagePerfState, VisibilityState visibilityState) {
        if (shouldDispatchAsync()) {
            Message obtainMessage = ((LogHandler) Preconditions.checkNotNull(sHandler)).obtainMessage();
            obtainMessage.what = 2;
            obtainMessage.arg1 = visibilityState.getValue();
            obtainMessage.obj = imagePerfState;
            sHandler.sendMessage(obtainMessage);
            return;
        }
        this.mImagePerfNotifier.notifyListenersOfVisibilityStateUpdate(imagePerfState, visibilityState);
        ImagePerfNotifier imagePerfNotifier = this.mLocalImagePerfNotifier;
        if (imagePerfNotifier != null) {
            imagePerfNotifier.notifyListenersOfVisibilityStateUpdate(imagePerfState, visibilityState);
        }
    }

    private synchronized void initHandler() {
        if (sHandler != null) {
            return;
        }
        HandlerThread handlerThread = new HandlerThread("ImagePerfControllerListener2Thread");
        handlerThread.start();
        sHandler = new LogHandler((Looper) Preconditions.checkNotNull(handlerThread.getLooper()), this.mImagePerfNotifier, this.mLocalImagePerfNotifier);
    }

    private boolean shouldDispatchAsync() {
        boolean booleanValue = this.mAsyncLogging.get().booleanValue();
        if (booleanValue && sHandler == null) {
            initHandler();
        }
        return booleanValue;
    }

    @Override // com.facebook.fresco.ui.common.BaseControllerListener2, com.facebook.fresco.ui.common.ControllerListener2
    public void onEmptyEvent(Object obj) {
        ImagePerfState imagePerfState = this.mImagePerfState;
        imagePerfState.setImageLoadStatus(ImageLoadStatus.EMPTY_EVENT);
        this.mImagePerfNotifier.notifyStatusUpdated(imagePerfState, ImageLoadStatus.EMPTY_EVENT);
        ImagePerfNotifier imagePerfNotifier = this.mLocalImagePerfNotifier;
        if (imagePerfNotifier != null) {
            imagePerfNotifier.notifyStatusUpdated(imagePerfState, ImageLoadStatus.EMPTY_EVENT);
        }
    }
}
