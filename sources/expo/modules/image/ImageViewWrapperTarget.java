package expo.modules.image;

import android.graphics.drawable.Drawable;
import android.util.Log;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.Request;
import com.bumptech.glide.request.ThumbnailRequestCoordinator;
import com.bumptech.glide.request.target.SizeReadyCallback;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.facebook.react.views.textinput.ReactTextInputShadowNode;
import expo.modules.image.enums.ContentFit;
import io.sentry.SentryBaseEvent;
import java.lang.ref.WeakReference;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ImageViewWrapperTarget.kt */
@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0013\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0002\u0010\u0006J\u000e\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020,J\b\u0010-\u001a\u00020*H\u0002J\n\u0010.\u001a\u0004\u0018\u00010 H\u0016J\u0010\u0010/\u001a\u00020*2\u0006\u00100\u001a\u000201H\u0016J\b\u00102\u001a\u00020*H\u0016J\u0012\u00103\u001a\u00020*2\b\u00104\u001a\u0004\u0018\u00010\u0002H\u0016J\u0012\u00105\u001a\u00020*2\b\u00106\u001a\u0004\u0018\u00010\u0002H\u0016J\u0012\u00107\u001a\u00020*2\b\u00104\u001a\u0004\u0018\u00010\u0002H\u0016J\"\u00108\u001a\u00020*2\u0006\u00109\u001a\u00020\u00022\u0010\u0010:\u001a\f\u0012\u0006\b\u0000\u0012\u00020\u0002\u0018\u00010;H\u0016J\b\u0010<\u001a\u00020*H\u0016J\b\u0010=\u001a\u00020*H\u0016J\u0010\u0010>\u001a\u00020*2\u0006\u00100\u001a\u000201H\u0016J\u000e\u0010?\u001a\u00020*2\u0006\u0010@\u001a\u00020\bJ\u0012\u0010A\u001a\u00020*2\b\u0010\u001f\u001a\u0004\u0018\u00010 H\u0016R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\t\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u000f\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\f\"\u0004\b\u0010\u0010\u000eR\u001c\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u001a\u0010\u0017\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u001a\u0010\u001c\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u0019\"\u0004\b\u001e\u0010\u001bR\u0010\u0010\u001f\u001a\u0004\u0018\u00010 X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\"X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010#\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010\u0019\"\u0004\b%\u0010\u001bR\u001a\u0010&\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010\u0019\"\u0004\b(\u0010\u001b¨\u0006B"}, d2 = {"Lexpo/modules/image/ImageViewWrapperTarget;", "Lcom/bumptech/glide/request/target/Target;", "Landroid/graphics/drawable/Drawable;", "imageViewHolder", "Ljava/lang/ref/WeakReference;", "Lexpo/modules/image/ExpoImageViewWrapper;", "(Ljava/lang/ref/WeakReference;)V", "cookie", "", "hasSource", "", "getHasSource", "()Z", "setHasSource", "(Z)V", "isUsed", "setUsed", "placeholderContentFit", "Lexpo/modules/image/enums/ContentFit;", "getPlaceholderContentFit", "()Lexpo/modules/image/enums/ContentFit;", "setPlaceholderContentFit", "(Lexpo/modules/image/enums/ContentFit;)V", "placeholderHeight", "getPlaceholderHeight", "()I", "setPlaceholderHeight", "(I)V", "placeholderWidth", "getPlaceholderWidth", "setPlaceholderWidth", SentryBaseEvent.JsonKeys.REQUEST, "Lcom/bumptech/glide/request/Request;", "sizeDeterminer", "Lexpo/modules/image/SizeDeterminer;", "sourceHeight", "getSourceHeight", "setSourceHeight", "sourceWidth", "getSourceWidth", "setSourceWidth", "clear", "", "requestManager", "Lcom/bumptech/glide/RequestManager;", "endLoadingNewImageTraceBlock", "getRequest", "getSize", "cb", "Lcom/bumptech/glide/request/target/SizeReadyCallback;", "onDestroy", "onLoadCleared", ReactTextInputShadowNode.PROP_PLACEHOLDER, "onLoadFailed", "errorDrawable", "onLoadStarted", "onResourceReady", "resource", "transition", "Lcom/bumptech/glide/request/transition/Transition;", "onStart", "onStop", "removeCallback", "setCookie", "newValue", "setRequest", "expo-image_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class ImageViewWrapperTarget implements Target<Drawable> {
    private int cookie;
    private boolean hasSource;
    private final WeakReference<ExpoImageViewWrapper> imageViewHolder;
    private boolean isUsed;
    private ContentFit placeholderContentFit;
    private int placeholderHeight;
    private int placeholderWidth;
    private Request request;
    private SizeDeterminer sizeDeterminer;
    private int sourceHeight;
    private int sourceWidth;

    public final boolean getHasSource() {
        return this.hasSource;
    }

    public final ContentFit getPlaceholderContentFit() {
        return this.placeholderContentFit;
    }

    public final int getPlaceholderHeight() {
        return this.placeholderHeight;
    }

    public final int getPlaceholderWidth() {
        return this.placeholderWidth;
    }

    @Override // com.bumptech.glide.request.target.Target
    public Request getRequest() {
        return this.request;
    }

    public final int getSourceHeight() {
        return this.sourceHeight;
    }

    public final int getSourceWidth() {
        return this.sourceWidth;
    }

    /* renamed from: isUsed, reason: from getter */
    public final boolean getIsUsed() {
        return this.isUsed;
    }

    @Override // com.bumptech.glide.manager.LifecycleListener
    public void onDestroy() {
    }

    @Override // com.bumptech.glide.request.target.Target
    public void onLoadCleared(Drawable placeholder) {
    }

    @Override // com.bumptech.glide.request.target.Target
    public void onLoadStarted(Drawable placeholder) {
    }

    @Override // com.bumptech.glide.manager.LifecycleListener
    public void onStart() {
    }

    @Override // com.bumptech.glide.manager.LifecycleListener
    public void onStop() {
    }

    public final void setHasSource(boolean z) {
        this.hasSource = z;
    }

    public final void setPlaceholderContentFit(ContentFit contentFit) {
        this.placeholderContentFit = contentFit;
    }

    public final void setPlaceholderHeight(int i) {
        this.placeholderHeight = i;
    }

    public final void setPlaceholderWidth(int i) {
        this.placeholderWidth = i;
    }

    @Override // com.bumptech.glide.request.target.Target
    public void setRequest(Request request) {
        this.request = request;
    }

    public final void setSourceHeight(int i) {
        this.sourceHeight = i;
    }

    public final void setSourceWidth(int i) {
        this.sourceWidth = i;
    }

    public final void setUsed(boolean z) {
        this.isUsed = z;
    }

    public ImageViewWrapperTarget(WeakReference<ExpoImageViewWrapper> imageViewHolder) {
        Intrinsics.checkNotNullParameter(imageViewHolder, "imageViewHolder");
        this.imageViewHolder = imageViewHolder;
        this.sourceHeight = -1;
        this.sourceWidth = -1;
        this.placeholderHeight = -1;
        this.placeholderWidth = -1;
        this.cookie = -1;
        this.sizeDeterminer = new SizeDeterminer(imageViewHolder);
    }

    public final void setCookie(int newValue) {
        endLoadingNewImageTraceBlock();
        synchronized (this) {
            this.cookie = newValue;
            Unit unit = Unit.INSTANCE;
        }
    }

    private final void endLoadingNewImageTraceBlock() {
        synchronized (this) {
            if (this.cookie >= 0) {
                String tag = Trace.INSTANCE.getTag();
                String loadNewImageBlock = Trace.INSTANCE.getLoadNewImageBlock();
                androidx.tracing.Trace.endAsyncSection("[" + tag + "] " + loadNewImageBlock, this.cookie);
                this.cookie = -1;
            }
            Unit unit = Unit.INSTANCE;
        }
    }

    @Override // com.bumptech.glide.request.target.Target
    public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
        Request privateFullRequest;
        Intrinsics.checkNotNullParameter(resource, "resource");
        ExpoImageViewWrapper expoImageViewWrapper = this.imageViewHolder.get();
        if (expoImageViewWrapper != null) {
            Intrinsics.checkNotNullExpressionValue(expoImageViewWrapper, "ifNull(...)");
            ExpoImageViewWrapper expoImageViewWrapper2 = expoImageViewWrapper;
            Request request = this.request;
            boolean z = false;
            if (request instanceof ThumbnailRequestCoordinator) {
                ThumbnailRequestCoordinator thumbnailRequestCoordinator = request instanceof ThumbnailRequestCoordinator ? (ThumbnailRequestCoordinator) request : null;
                if (thumbnailRequestCoordinator != null && (privateFullRequest = ThumbnailRequestCoordinatorExtensionKt.getPrivateFullRequest(thumbnailRequestCoordinator)) != null && !privateFullRequest.isComplete()) {
                    z = true;
                }
            }
            if (!z) {
                endLoadingNewImageTraceBlock();
            }
            expoImageViewWrapper2.onResourceReady(this, resource, z);
            return;
        }
        endLoadingNewImageTraceBlock();
        Log.w("ExpoImage", "The `ExpoImageViewWrapper` was deallocated, but the target wasn't canceled in time.");
    }

    @Override // com.bumptech.glide.request.target.Target
    public void onLoadFailed(Drawable errorDrawable) {
        endLoadingNewImageTraceBlock();
    }

    @Override // com.bumptech.glide.request.target.Target
    public void getSize(SizeReadyCallback cb) {
        Intrinsics.checkNotNullParameter(cb, "cb");
        if (this.imageViewHolder.get() == null) {
            cb.onSizeReady(Integer.MIN_VALUE, Integer.MIN_VALUE);
        } else {
            this.sizeDeterminer.getSize(cb);
        }
    }

    @Override // com.bumptech.glide.request.target.Target
    public void removeCallback(SizeReadyCallback cb) {
        Intrinsics.checkNotNullParameter(cb, "cb");
        this.sizeDeterminer.removeCallback(cb);
    }

    public final void clear(RequestManager requestManager) {
        Intrinsics.checkNotNullParameter(requestManager, "requestManager");
        this.sizeDeterminer.clearCallbacksAndListener();
        requestManager.clear(this);
    }
}
