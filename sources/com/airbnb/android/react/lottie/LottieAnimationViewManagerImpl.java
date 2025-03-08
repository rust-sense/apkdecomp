package com.airbnb.android.react.lottie;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.ImageView;
import androidx.core.app.NotificationCompat;
import androidx.core.view.ViewCompat;
import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.RenderMode;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.UIManagerHelper;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.uimanager.events.EventDispatcher;
import com.google.firebase.messaging.Constants;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: LottieAnimationViewManagerImpl.kt */
@Metadata(d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010$\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0003\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u0007\n\u0002\b\u000e\n\u0002\u0010\u0006\n\u0002\b\u0003\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0007J\u0014\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00010\u000fH\u0007J\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u000bH\u0007J \u0010\u0013\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u000b2\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0015H\u0007J\u0010\u0010\u0017\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u000bH\u0007J\u0010\u0010\u0018\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u000bH\u0007J\u0018\u0010\u0019\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u000b2\u0006\u0010\u001a\u001a\u00020\u001bH\u0007J\u0010\u0010\u001c\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u000bH\u0007J\u0018\u0010\u001d\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u000b2\u0006\u0010\u001e\u001a\u00020\u001fH\u0007J\u0018\u0010 \u001a\u00020\u00112\u0006\u0010!\u001a\u00020\u001f2\u0006\u0010\"\u001a\u00020#H\u0007J\u0018\u0010$\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u000b2\u0006\u0010%\u001a\u00020\u001fH\u0007J\u001a\u0010&\u001a\u00020\u00112\b\u0010'\u001a\u0004\u0018\u00010(2\u0006\u0010\"\u001a\u00020#H\u0007J\u0018\u0010)\u001a\u00020\u00112\u0006\u0010*\u001a\u00020\u001f2\u0006\u0010\"\u001a\u00020#H\u0007J\u0018\u0010+\u001a\u00020\u00112\u0006\u0010,\u001a\u00020\u001f2\u0006\u0010\"\u001a\u00020#H\u0007J\u001a\u0010-\u001a\u00020\u00112\b\u0010.\u001a\u0004\u0018\u00010\u00042\u0006\u0010\"\u001a\u00020#H\u0007J\u0018\u0010/\u001a\u00020\u00112\u0006\u00100\u001a\u00020\u001f2\u0006\u0010\"\u001a\u00020#H\u0007J\u0018\u00101\u001a\u00020\u00112\u0006\u00102\u001a\u0002032\u0006\u0010\"\u001a\u00020#H\u0007J\u001a\u00104\u001a\u00020\u00112\b\u00105\u001a\u0004\u0018\u00010\u00042\u0006\u0010\"\u001a\u00020#H\u0007J\u001a\u00106\u001a\u00020\u00112\b\u00107\u001a\u0004\u0018\u00010\u00042\u0006\u0010\"\u001a\u00020#H\u0007J\u001a\u00108\u001a\u00020\u00112\b\u00109\u001a\u0004\u0018\u00010\u00042\u0006\u0010\"\u001a\u00020#H\u0007J\u001a\u0010:\u001a\u00020\u00112\b\u0010;\u001a\u0004\u0018\u00010\u00042\u0006\u0010\"\u001a\u00020#H\u0007J\u001a\u0010<\u001a\u00020\u00112\b\u0010=\u001a\u0004\u0018\u00010\u00042\u0006\u0010\"\u001a\u00020#H\u0007J\u001a\u0010>\u001a\u00020\u00112\b\u0010?\u001a\u0004\u0018\u00010\u00042\u0006\u0010\"\u001a\u00020#H\u0007J\u0018\u0010@\u001a\u00020\u00112\u0006\u0010A\u001a\u00020B2\u0006\u0010\"\u001a\u00020#H\u0007J\u001a\u0010C\u001a\u00020\u00112\b\u0010D\u001a\u0004\u0018\u00010(2\u0006\u0010\"\u001a\u00020#H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R&\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00010\u00068FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u0007\u0010\u0002\u001a\u0004\b\b\u0010\t¨\u0006E"}, d2 = {"Lcom/airbnb/android/react/lottie/LottieAnimationViewManagerImpl;", "", "()V", "REACT_CLASS", "", "exportedViewConstants", "", "getExportedViewConstants$annotations", "getExportedViewConstants", "()Ljava/util/Map;", "createViewInstance", "Lcom/airbnb/lottie/LottieAnimationView;", "context", "Lcom/facebook/react/uimanager/ThemedReactContext;", "getExportedCustomDirectEventTypeConstants", "", "pause", "", "view", "play", "startFrame", "", "endFrame", "reset", "resume", "sendAnimationFailureEvent", Constants.IPC_BUNDLE_KEY_SEND_ERROR, "", "sendAnimationLoadedEvent", "sendOnAnimationFinishEvent", "isCancelled", "", "setAutoPlay", "autoPlay", "viewManager", "Lcom/airbnb/android/react/lottie/LottieAnimationViewPropertyManager;", "setCacheComposition", "cacheComposition", "setColorFilters", "colorFilters", "Lcom/facebook/react/bridge/ReadableArray;", "setEnableMergePaths", "enableMergePaths", "setHardwareAcceleration", "hardwareAccelerationAndroid", "setImageAssetsFolder", "imageAssetsFolder", "setLoop", "loop", "setProgress", NotificationCompat.CATEGORY_PROGRESS, "", "setRenderMode", "renderMode", "setResizeMode", ViewProps.RESIZE_MODE, "setSourceDotLottieURI", "uri", "setSourceJson", "json", "setSourceName", "name", "setSourceURL", "urlString", "setSpeed", "speed", "", "setTextFilters", "textFilters", "lottie-react-native_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes.dex */
public final class LottieAnimationViewManagerImpl {
    public static final LottieAnimationViewManagerImpl INSTANCE = new LottieAnimationViewManagerImpl();
    public static final String REACT_CLASS = "LottieAnimationView";

    @JvmStatic
    public static /* synthetic */ void getExportedViewConstants$annotations() {
    }

    private LottieAnimationViewManagerImpl() {
    }

    public static final Map<String, Object> getExportedViewConstants() {
        Map<String, Object> build = MapBuilder.builder().put("VERSION", 1).build();
        Intrinsics.checkNotNullExpressionValue(build, "build(...)");
        return build;
    }

    @JvmStatic
    public static final LottieAnimationView createViewInstance(ThemedReactContext context) {
        Intrinsics.checkNotNullParameter(context, "context");
        LottieAnimationView lottieAnimationView = new LottieAnimationView(context);
        lottieAnimationView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        return lottieAnimationView;
    }

    @JvmStatic
    public static final void sendOnAnimationFinishEvent(LottieAnimationView view, boolean isCancelled) {
        Intrinsics.checkNotNullParameter(view, "view");
        Context context = view.getContext();
        Intrinsics.checkNotNull(context, "null cannot be cast to non-null type com.facebook.react.uimanager.ThemedReactContext");
        ThemedReactContext themedReactContext = (ThemedReactContext) context;
        EventDispatcher eventDispatcherForReactTag = UIManagerHelper.getEventDispatcherForReactTag(themedReactContext, view.getId());
        if (eventDispatcherForReactTag != null) {
            eventDispatcherForReactTag.dispatchEvent(new OnAnimationFinishEvent(themedReactContext.getSurfaceId(), view.getId(), isCancelled));
        }
    }

    @JvmStatic
    public static final void sendAnimationFailureEvent(LottieAnimationView view, Throwable error) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(error, "error");
        Context context = view.getContext();
        Intrinsics.checkNotNull(context, "null cannot be cast to non-null type com.facebook.react.uimanager.ThemedReactContext");
        ThemedReactContext themedReactContext = (ThemedReactContext) context;
        EventDispatcher eventDispatcherForReactTag = UIManagerHelper.getEventDispatcherForReactTag(themedReactContext, view.getId());
        if (eventDispatcherForReactTag != null) {
            eventDispatcherForReactTag.dispatchEvent(new OnAnimationFailureEvent(themedReactContext.getSurfaceId(), view.getId(), error));
        }
    }

    @JvmStatic
    public static final void sendAnimationLoadedEvent(LottieAnimationView view) {
        Intrinsics.checkNotNullParameter(view, "view");
        Context context = view.getContext();
        Intrinsics.checkNotNull(context, "null cannot be cast to non-null type com.facebook.react.uimanager.ThemedReactContext");
        ThemedReactContext themedReactContext = (ThemedReactContext) context;
        EventDispatcher eventDispatcherForReactTag = UIManagerHelper.getEventDispatcherForReactTag(themedReactContext, view.getId());
        if (eventDispatcherForReactTag != null) {
            eventDispatcherForReactTag.dispatchEvent(new OnAnimationLoadedEvent(themedReactContext.getSurfaceId(), view.getId()));
        }
    }

    @JvmStatic
    public static final Map<String, Object> getExportedCustomDirectEventTypeConstants() {
        Map<String, Object> of = MapBuilder.of(OnAnimationFinishEvent.EVENT_NAME, MapBuilder.of("registrationName", "onAnimationFinish"), OnAnimationFailureEvent.EVENT_NAME, MapBuilder.of("registrationName", "onAnimationFailure"), OnAnimationLoadedEvent.EVENT_NAME, MapBuilder.of("registrationName", "onAnimationLoaded"));
        Intrinsics.checkNotNullExpressionValue(of, "of(...)");
        return of;
    }

    @JvmStatic
    public static final void play(final LottieAnimationView view, final int startFrame, final int endFrame) {
        Intrinsics.checkNotNullParameter(view, "view");
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.airbnb.android.react.lottie.LottieAnimationViewManagerImpl$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                LottieAnimationViewManagerImpl.play$lambda$1(startFrame, endFrame, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void play$lambda$1(int i, int i2, LottieAnimationView view) {
        Intrinsics.checkNotNullParameter(view, "$view");
        if (i != -1 && i2 != -1) {
            if (i > i2) {
                view.setMinAndMaxFrame(i2, i);
                if (view.getSpeed() > 0.0f) {
                    view.reverseAnimationSpeed();
                }
            } else {
                view.setMinAndMaxFrame(i, i2);
                if (view.getSpeed() < 0.0f) {
                    view.reverseAnimationSpeed();
                }
            }
        }
        if (ViewCompat.isAttachedToWindow(view)) {
            view.setProgress(0.0f);
            view.playAnimation();
        } else {
            view.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() { // from class: com.airbnb.android.react.lottie.LottieAnimationViewManagerImpl$play$1$1
                @Override // android.view.View.OnAttachStateChangeListener
                public void onViewAttachedToWindow(View v) {
                    Intrinsics.checkNotNullParameter(v, "v");
                    LottieAnimationView lottieAnimationView = (LottieAnimationView) v;
                    lottieAnimationView.setProgress(0.0f);
                    lottieAnimationView.playAnimation();
                    lottieAnimationView.removeOnAttachStateChangeListener(this);
                }

                @Override // android.view.View.OnAttachStateChangeListener
                public void onViewDetachedFromWindow(View v) {
                    Intrinsics.checkNotNullParameter(v, "v");
                    ((LottieAnimationView) v).removeOnAttachStateChangeListener(this);
                }
            });
        }
    }

    @JvmStatic
    public static final void reset(final LottieAnimationView view) {
        Intrinsics.checkNotNullParameter(view, "view");
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.airbnb.android.react.lottie.LottieAnimationViewManagerImpl$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                LottieAnimationViewManagerImpl.reset$lambda$2(LottieAnimationView.this);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void reset$lambda$2(LottieAnimationView view) {
        Intrinsics.checkNotNullParameter(view, "$view");
        if (ViewCompat.isAttachedToWindow(view)) {
            view.cancelAnimation();
            view.setProgress(0.0f);
        }
    }

    @JvmStatic
    public static final void pause(final LottieAnimationView view) {
        Intrinsics.checkNotNullParameter(view, "view");
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.airbnb.android.react.lottie.LottieAnimationViewManagerImpl$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                LottieAnimationViewManagerImpl.pause$lambda$3(LottieAnimationView.this);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void pause$lambda$3(LottieAnimationView view) {
        Intrinsics.checkNotNullParameter(view, "$view");
        if (ViewCompat.isAttachedToWindow(view)) {
            view.pauseAnimation();
        }
    }

    @JvmStatic
    public static final void resume(final LottieAnimationView view) {
        Intrinsics.checkNotNullParameter(view, "view");
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.airbnb.android.react.lottie.LottieAnimationViewManagerImpl$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                LottieAnimationViewManagerImpl.resume$lambda$4(LottieAnimationView.this);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void resume$lambda$4(LottieAnimationView view) {
        Intrinsics.checkNotNullParameter(view, "$view");
        if (ViewCompat.isAttachedToWindow(view)) {
            view.resumeAnimation();
        }
    }

    @JvmStatic
    public static final void setSourceName(String name, LottieAnimationViewPropertyManager viewManager) {
        Intrinsics.checkNotNullParameter(viewManager, "viewManager");
        if (name != null && !StringsKt.contains$default((CharSequence) name, (CharSequence) ".", false, 2, (Object) null)) {
            name = name + ".json";
        }
        viewManager.setAnimationName(name);
        viewManager.commitChanges();
    }

    @JvmStatic
    public static final void setSourceJson(String json, LottieAnimationViewPropertyManager viewManager) {
        Intrinsics.checkNotNullParameter(viewManager, "viewManager");
        viewManager.setAnimationJson(json);
        viewManager.commitChanges();
    }

    @JvmStatic
    public static final void setSourceURL(String urlString, LottieAnimationViewPropertyManager viewManager) {
        Intrinsics.checkNotNullParameter(viewManager, "viewManager");
        viewManager.setAnimationURL(urlString);
        viewManager.commitChanges();
    }

    @JvmStatic
    public static final void setSourceDotLottieURI(String uri, LottieAnimationViewPropertyManager viewManager) {
        Intrinsics.checkNotNullParameter(viewManager, "viewManager");
        viewManager.setSourceDotLottie(uri);
        viewManager.commitChanges();
    }

    @JvmStatic
    public static final void setCacheComposition(LottieAnimationView view, boolean cacheComposition) {
        Intrinsics.checkNotNullParameter(view, "view");
        view.setCacheComposition(cacheComposition);
    }

    @JvmStatic
    public static final void setResizeMode(String resizeMode, LottieAnimationViewPropertyManager viewManager) {
        ImageView.ScaleType scaleType;
        Intrinsics.checkNotNullParameter(viewManager, "viewManager");
        if (resizeMode != null) {
            int hashCode = resizeMode.hashCode();
            if (hashCode != -1364013995) {
                if (hashCode != 94852023) {
                    if (hashCode == 951526612 && resizeMode.equals("contain")) {
                        scaleType = ImageView.ScaleType.FIT_CENTER;
                    }
                } else if (resizeMode.equals("cover")) {
                    scaleType = ImageView.ScaleType.CENTER_CROP;
                }
            } else if (resizeMode.equals("center")) {
                scaleType = ImageView.ScaleType.CENTER_INSIDE;
            }
            viewManager.setScaleType(scaleType);
        }
        scaleType = null;
        viewManager.setScaleType(scaleType);
    }

    @JvmStatic
    public static final void setRenderMode(String renderMode, LottieAnimationViewPropertyManager viewManager) {
        RenderMode renderMode2;
        Intrinsics.checkNotNullParameter(viewManager, "viewManager");
        if (renderMode != null) {
            int hashCode = renderMode.hashCode();
            if (hashCode != 165298699) {
                if (hashCode != 899536360) {
                    if (hashCode == 2101957031 && renderMode.equals("SOFTWARE")) {
                        renderMode2 = RenderMode.SOFTWARE;
                    }
                } else if (renderMode.equals("HARDWARE")) {
                    renderMode2 = RenderMode.HARDWARE;
                }
            } else if (renderMode.equals("AUTOMATIC")) {
                renderMode2 = RenderMode.AUTOMATIC;
            }
            viewManager.setRenderMode(renderMode2);
        }
        renderMode2 = null;
        viewManager.setRenderMode(renderMode2);
    }

    @JvmStatic
    public static final void setHardwareAcceleration(boolean hardwareAccelerationAndroid, LottieAnimationViewPropertyManager viewManager) {
        Intrinsics.checkNotNullParameter(viewManager, "viewManager");
        int i = 1;
        if (hardwareAccelerationAndroid) {
            i = 2;
        }
        viewManager.setLayerType(i);
    }

    @JvmStatic
    public static final void setProgress(float progress, LottieAnimationViewPropertyManager viewManager) {
        Intrinsics.checkNotNullParameter(viewManager, "viewManager");
        viewManager.setProgress(Float.valueOf(progress));
    }

    @JvmStatic
    public static final void setSpeed(double speed, LottieAnimationViewPropertyManager viewManager) {
        Intrinsics.checkNotNullParameter(viewManager, "viewManager");
        viewManager.setSpeed(Float.valueOf((float) speed));
    }

    @JvmStatic
    public static final void setLoop(boolean loop, LottieAnimationViewPropertyManager viewManager) {
        Intrinsics.checkNotNullParameter(viewManager, "viewManager");
        viewManager.setLoop(Boolean.valueOf(loop));
    }

    @JvmStatic
    public static final void setAutoPlay(boolean autoPlay, LottieAnimationViewPropertyManager viewManager) {
        Intrinsics.checkNotNullParameter(viewManager, "viewManager");
        viewManager.setAutoPlay(Boolean.valueOf(autoPlay));
    }

    @JvmStatic
    public static final void setEnableMergePaths(boolean enableMergePaths, LottieAnimationViewPropertyManager viewManager) {
        Intrinsics.checkNotNullParameter(viewManager, "viewManager");
        viewManager.setEnableMergePaths(Boolean.valueOf(enableMergePaths));
    }

    @JvmStatic
    public static final void setImageAssetsFolder(String imageAssetsFolder, LottieAnimationViewPropertyManager viewManager) {
        Intrinsics.checkNotNullParameter(viewManager, "viewManager");
        viewManager.setImageAssetsFolder(imageAssetsFolder);
    }

    @JvmStatic
    public static final void setColorFilters(ReadableArray colorFilters, LottieAnimationViewPropertyManager viewManager) {
        Intrinsics.checkNotNullParameter(viewManager, "viewManager");
        viewManager.setColorFilters(colorFilters);
    }

    @JvmStatic
    public static final void setTextFilters(ReadableArray textFilters, LottieAnimationViewPropertyManager viewManager) {
        Intrinsics.checkNotNullParameter(viewManager, "viewManager");
        viewManager.setTextFilters(textFilters);
    }
}
