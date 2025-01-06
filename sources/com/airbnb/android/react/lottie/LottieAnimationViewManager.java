package com.airbnb.android.react.lottie;

import android.animation.Animator;
import androidx.core.app.NotificationCompat;
import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.LottieListener;
import com.airbnb.lottie.LottieOnCompositionLoadedListener;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.uimanager.annotations.ReactProp;
import java.util.Map;
import java.util.WeakHashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LottieAnimationViewManager.kt */
@Metadata(d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010$\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u000f\n\u0002\u0010\u0007\n\u0002\b\u000e\n\u0002\u0010\u0006\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u0010\u0010\u0007\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\tH\u0016J\u0016\u0010\n\u001a\u0010\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r\u0018\u00010\u000bH\u0016J\u0014\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u000fH\u0016J\b\u0010\u0010\u001a\u00020\fH\u0016J\u0010\u0010\u0011\u001a\u00020\u00062\u0006\u0010\u0012\u001a\u00020\u0002H\u0002J\u0010\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0012\u001a\u00020\u0002H\u0014J\"\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0012\u001a\u00020\u00022\u0006\u0010\u0016\u001a\u00020\f2\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0016J\u0018\u0010\u0019\u001a\u00020\u00142\u0006\u0010\u0012\u001a\u00020\u00022\u0006\u0010\u001a\u001a\u00020\u001bH\u0007J\u001a\u0010\u001c\u001a\u00020\u00142\b\u0010\u0012\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u001d\u001a\u00020\u001bH\u0007J\u001a\u0010\u001e\u001a\u00020\u00142\u0006\u0010\u0012\u001a\u00020\u00022\b\u0010\u001f\u001a\u0004\u0018\u00010\u0018H\u0007J\u0018\u0010 \u001a\u00020\u00142\u0006\u0010\u0012\u001a\u00020\u00022\u0006\u0010!\u001a\u00020\u001bH\u0007J\u001f\u0010\"\u001a\u00020\u00142\u0006\u0010\u0012\u001a\u00020\u00022\b\u0010#\u001a\u0004\u0018\u00010\u001bH\u0007¢\u0006\u0002\u0010$J\u001a\u0010%\u001a\u00020\u00142\u0006\u0010\u0012\u001a\u00020\u00022\b\u0010&\u001a\u0004\u0018\u00010\fH\u0007J\u0018\u0010'\u001a\u00020\u00142\u0006\u0010\u0012\u001a\u00020\u00022\u0006\u0010(\u001a\u00020\u001bH\u0007J\u0018\u0010)\u001a\u00020\u00142\u0006\u0010\u0012\u001a\u00020\u00022\u0006\u0010*\u001a\u00020+H\u0007J\u001a\u0010,\u001a\u00020\u00142\u0006\u0010\u0012\u001a\u00020\u00022\b\u0010-\u001a\u0004\u0018\u00010\fH\u0007J\u001a\u0010.\u001a\u00020\u00142\u0006\u0010\u0012\u001a\u00020\u00022\b\u0010/\u001a\u0004\u0018\u00010\fH\u0007J\u001a\u00100\u001a\u00020\u00142\u0006\u0010\u0012\u001a\u00020\u00022\b\u00101\u001a\u0004\u0018\u00010\fH\u0007J\u001a\u00102\u001a\u00020\u00142\u0006\u0010\u0012\u001a\u00020\u00022\b\u00103\u001a\u0004\u0018\u00010\fH\u0007J\u001a\u00104\u001a\u00020\u00142\u0006\u0010\u0012\u001a\u00020\u00022\b\u00105\u001a\u0004\u0018\u00010\fH\u0007J\u001a\u00106\u001a\u00020\u00142\u0006\u0010\u0012\u001a\u00020\u00022\b\u00107\u001a\u0004\u0018\u00010\fH\u0007J\u0018\u00108\u001a\u00020\u00142\u0006\u0010\u0012\u001a\u00020\u00022\u0006\u00109\u001a\u00020:H\u0007J\u001a\u0010;\u001a\u00020\u00142\u0006\u0010\u0012\u001a\u00020\u00022\b\u0010<\u001a\u0004\u0018\u00010\u0018H\u0007R\u001a\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006="}, d2 = {"Lcom/airbnb/android/react/lottie/LottieAnimationViewManager;", "Lcom/facebook/react/uimanager/SimpleViewManager;", "Lcom/airbnb/lottie/LottieAnimationView;", "()V", "propManagersMap", "Ljava/util/WeakHashMap;", "Lcom/airbnb/android/react/lottie/LottieAnimationViewPropertyManager;", "createViewInstance", "context", "Lcom/facebook/react/uimanager/ThemedReactContext;", "getExportedCustomDirectEventTypeConstants", "", "", "", "getExportedViewConstants", "", "getName", "getOrCreatePropertyManager", "view", "onAfterUpdateTransaction", "", "receiveCommand", "commandName", "args", "Lcom/facebook/react/bridge/ReadableArray;", "setAutoPlay", "autoPlay", "", "setCacheComposition", "cacheComposition", "setColorFilters", "colorFilters", "setEnableMergePaths", "enableMergePaths", "setHardwareAccelerationAndroid", "hardwareAccelerationAndroid", "(Lcom/airbnb/lottie/LottieAnimationView;Ljava/lang/Boolean;)V", "setImageAssetsFolder", "imageAssetsFolder", "setLoop", "loop", "setProgress", NotificationCompat.CATEGORY_PROGRESS, "", "setRenderMode", "renderMode", "setResizeMode", ViewProps.RESIZE_MODE, "setSourceDotLottie", "uri", "setSourceJson", "json", "setSourceName", "name", "setSourceURL", "urlString", "setSpeed", "speed", "", "setTextFilters", "textFilters", "lottie-react-native_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes.dex */
public final class LottieAnimationViewManager extends SimpleViewManager<LottieAnimationView> {
    private final WeakHashMap<LottieAnimationView, LottieAnimationViewPropertyManager> propManagersMap = new WeakHashMap<>();

    @Override // com.facebook.react.uimanager.ViewManager, com.facebook.react.bridge.NativeModule
    public String getName() {
        return LottieAnimationViewManagerImpl.REACT_CLASS;
    }

    private final LottieAnimationViewPropertyManager getOrCreatePropertyManager(LottieAnimationView view) {
        LottieAnimationViewPropertyManager lottieAnimationViewPropertyManager = this.propManagersMap.get(view);
        if (lottieAnimationViewPropertyManager != null) {
            return lottieAnimationViewPropertyManager;
        }
        LottieAnimationViewPropertyManager lottieAnimationViewPropertyManager2 = new LottieAnimationViewPropertyManager(view);
        this.propManagersMap.put(view, lottieAnimationViewPropertyManager2);
        return lottieAnimationViewPropertyManager2;
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public Map<String, Object> getExportedViewConstants() {
        return LottieAnimationViewManagerImpl.getExportedViewConstants();
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public LottieAnimationView createViewInstance(ThemedReactContext context) {
        Intrinsics.checkNotNullParameter(context, "context");
        final LottieAnimationView createViewInstance = LottieAnimationViewManagerImpl.createViewInstance(context);
        createViewInstance.setFailureListener(new LottieListener() { // from class: com.airbnb.android.react.lottie.LottieAnimationViewManager$$ExternalSyntheticLambda0
            @Override // com.airbnb.lottie.LottieListener
            public final void onResult(Object obj) {
                LottieAnimationViewManager.createViewInstance$lambda$0(LottieAnimationView.this, (Throwable) obj);
            }
        });
        createViewInstance.addLottieOnCompositionLoadedListener(new LottieOnCompositionLoadedListener() { // from class: com.airbnb.android.react.lottie.LottieAnimationViewManager$$ExternalSyntheticLambda1
            @Override // com.airbnb.lottie.LottieOnCompositionLoadedListener
            public final void onCompositionLoaded(LottieComposition lottieComposition) {
                LottieAnimationViewManager.createViewInstance$lambda$1(LottieAnimationView.this, lottieComposition);
            }
        });
        createViewInstance.addAnimatorListener(new Animator.AnimatorListener() { // from class: com.airbnb.android.react.lottie.LottieAnimationViewManager$createViewInstance$3
            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationRepeat(Animator animation) {
                Intrinsics.checkNotNullParameter(animation, "animation");
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animation) {
                Intrinsics.checkNotNullParameter(animation, "animation");
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animation) {
                Intrinsics.checkNotNullParameter(animation, "animation");
                LottieAnimationViewManagerImpl.sendOnAnimationFinishEvent(LottieAnimationView.this, false);
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animation) {
                Intrinsics.checkNotNullParameter(animation, "animation");
                LottieAnimationViewManagerImpl.sendOnAnimationFinishEvent(LottieAnimationView.this, true);
            }
        });
        return createViewInstance;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void createViewInstance$lambda$0(LottieAnimationView view, Throwable th) {
        Intrinsics.checkNotNullParameter(view, "$view");
        Intrinsics.checkNotNull(th);
        LottieAnimationViewManagerImpl.sendAnimationFailureEvent(view, th);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void createViewInstance$lambda$1(LottieAnimationView view, LottieComposition lottieComposition) {
        Intrinsics.checkNotNullParameter(view, "$view");
        LottieAnimationViewManagerImpl.sendAnimationLoadedEvent(view);
    }

    @Override // com.facebook.react.uimanager.BaseViewManager, com.facebook.react.uimanager.ViewManager
    public Map<String, Object> getExportedCustomDirectEventTypeConstants() {
        return LottieAnimationViewManagerImpl.getExportedCustomDirectEventTypeConstants();
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue
    java.lang.NullPointerException
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.restoreSwitchOverString(SwitchOverStringVisitor.java:109)
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.visitRegion(SwitchOverStringVisitor.java:66)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:77)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:82)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterative(DepthRegionTraversal.java:31)
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.visit(SwitchOverStringVisitor.java:60)
     */
    @Override // com.facebook.react.uimanager.ViewManager
    public void receiveCommand(LottieAnimationView view, String commandName, ReadableArray args) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(commandName, "commandName");
        switch (commandName.hashCode()) {
            case -934426579:
                if (commandName.equals("resume")) {
                    LottieAnimationViewManagerImpl.resume(view);
                    break;
                }
                break;
            case 3443508:
                if (commandName.equals("play")) {
                    LottieAnimationViewManagerImpl.play(view, args != null ? args.getInt(0) : -1, args != null ? args.getInt(1) : -1);
                    break;
                }
                break;
            case 106440182:
                if (commandName.equals("pause")) {
                    LottieAnimationViewManagerImpl.pause(view);
                    break;
                }
                break;
            case 108404047:
                if (commandName.equals("reset")) {
                    LottieAnimationViewManagerImpl.reset(view);
                    break;
                }
                break;
        }
    }

    @ReactProp(name = "sourceName")
    public final void setSourceName(LottieAnimationView view, String name) {
        Intrinsics.checkNotNullParameter(view, "view");
        LottieAnimationViewManagerImpl.setSourceName(name, getOrCreatePropertyManager(view));
    }

    @ReactProp(name = "sourceJson")
    public final void setSourceJson(LottieAnimationView view, String json) {
        Intrinsics.checkNotNullParameter(view, "view");
        LottieAnimationViewManagerImpl.setSourceJson(json, getOrCreatePropertyManager(view));
    }

    @ReactProp(name = "sourceURL")
    public final void setSourceURL(LottieAnimationView view, String urlString) {
        Intrinsics.checkNotNullParameter(view, "view");
        LottieAnimationViewManagerImpl.setSourceURL(urlString, getOrCreatePropertyManager(view));
    }

    @ReactProp(name = "cacheComposition")
    public final void setCacheComposition(LottieAnimationView view, boolean cacheComposition) {
        Intrinsics.checkNotNull(view);
        LottieAnimationViewManagerImpl.setCacheComposition(view, cacheComposition);
    }

    @ReactProp(name = ViewProps.RESIZE_MODE)
    public final void setResizeMode(LottieAnimationView view, String resizeMode) {
        Intrinsics.checkNotNullParameter(view, "view");
        LottieAnimationViewManagerImpl.setResizeMode(resizeMode, getOrCreatePropertyManager(view));
    }

    @ReactProp(name = "renderMode")
    public final void setRenderMode(LottieAnimationView view, String renderMode) {
        Intrinsics.checkNotNullParameter(view, "view");
        LottieAnimationViewManagerImpl.setRenderMode(renderMode, getOrCreatePropertyManager(view));
    }

    @ReactProp(name = "hardwareAccelerationAndroid")
    public final void setHardwareAccelerationAndroid(LottieAnimationView view, Boolean hardwareAccelerationAndroid) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNull(hardwareAccelerationAndroid);
        LottieAnimationViewManagerImpl.setHardwareAcceleration(hardwareAccelerationAndroid.booleanValue(), getOrCreatePropertyManager(view));
    }

    @ReactProp(name = NotificationCompat.CATEGORY_PROGRESS)
    public final void setProgress(LottieAnimationView view, float progress) {
        Intrinsics.checkNotNullParameter(view, "view");
        LottieAnimationViewManagerImpl.setProgress(progress, getOrCreatePropertyManager(view));
    }

    @ReactProp(name = "speed")
    public final void setSpeed(LottieAnimationView view, double speed) {
        Intrinsics.checkNotNullParameter(view, "view");
        LottieAnimationViewManagerImpl.setSpeed(speed, getOrCreatePropertyManager(view));
    }

    @ReactProp(name = "loop")
    public final void setLoop(LottieAnimationView view, boolean loop) {
        Intrinsics.checkNotNullParameter(view, "view");
        LottieAnimationViewManagerImpl.setLoop(loop, getOrCreatePropertyManager(view));
    }

    @ReactProp(name = "autoPlay")
    public final void setAutoPlay(LottieAnimationView view, boolean autoPlay) {
        Intrinsics.checkNotNullParameter(view, "view");
        LottieAnimationViewManagerImpl.setAutoPlay(autoPlay, getOrCreatePropertyManager(view));
    }

    @ReactProp(name = "imageAssetsFolder")
    public final void setImageAssetsFolder(LottieAnimationView view, String imageAssetsFolder) {
        Intrinsics.checkNotNullParameter(view, "view");
        LottieAnimationViewManagerImpl.setImageAssetsFolder(imageAssetsFolder, getOrCreatePropertyManager(view));
    }

    @ReactProp(name = "enableMergePathsAndroidForKitKatAndAbove")
    public final void setEnableMergePaths(LottieAnimationView view, boolean enableMergePaths) {
        Intrinsics.checkNotNullParameter(view, "view");
        LottieAnimationViewManagerImpl.setEnableMergePaths(enableMergePaths, getOrCreatePropertyManager(view));
    }

    @ReactProp(name = "colorFilters")
    public final void setColorFilters(LottieAnimationView view, ReadableArray colorFilters) {
        Intrinsics.checkNotNullParameter(view, "view");
        LottieAnimationViewManagerImpl.setColorFilters(colorFilters, getOrCreatePropertyManager(view));
    }

    @ReactProp(name = "textFiltersAndroid")
    public final void setTextFilters(LottieAnimationView view, ReadableArray textFilters) {
        Intrinsics.checkNotNullParameter(view, "view");
        LottieAnimationViewManagerImpl.setTextFilters(textFilters, getOrCreatePropertyManager(view));
    }

    @ReactProp(name = "sourceDotLottieURI")
    public final void setSourceDotLottie(LottieAnimationView view, String uri) {
        Intrinsics.checkNotNullParameter(view, "view");
        LottieAnimationViewManagerImpl.setSourceDotLottieURI(uri, getOrCreatePropertyManager(view));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.react.uimanager.BaseViewManager, com.facebook.react.uimanager.ViewManager
    public void onAfterUpdateTransaction(LottieAnimationView view) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onAfterUpdateTransaction((LottieAnimationViewManager) view);
        getOrCreatePropertyManager(view).commitChanges();
    }
}
