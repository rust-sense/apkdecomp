package com.facebook.react;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.queue.ReactQueueConfiguration;
import com.facebook.react.common.LifecycleState;
import com.facebook.react.devsupport.interfaces.DevSupportManager;
import com.facebook.react.interfaces.TaskInterface;
import com.facebook.react.interfaces.fabric.ReactSurface;
import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler;
import com.facebook.react.uimanager.ViewProps;
import io.sentry.clientreport.DiscardedEvent;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;

/* compiled from: ReactHost.kt */
@Metadata(d1 = {"\u0000\u0092\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\bf\u0018\u00002\u00020\u0001J\u0016\u0010\u0018\u001a\u00020\u00192\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00190\u001bH&J$\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!2\b\u0010\"\u001a\u0004\u0018\u00010#H&J&\u0010$\u001a\b\u0012\u0004\u0012\u00020&0%2\u0006\u0010'\u001a\u00020!2\u000e\u0010(\u001a\n\u0018\u00010)j\u0004\u0018\u0001`*H&J*\u0010+\u001a\u00020\u00192\u0006\u0010,\u001a\u00020-2\u0006\u0010.\u001a\u00020/2\u0006\u00100\u001a\u00020/2\b\u00101\u001a\u0004\u0018\u000102H&J\b\u00103\u001a\u000204H&J\u0010\u00105\u001a\u00020\u00192\u0006\u0010\u001e\u001a\u00020\u001fH&J\b\u00106\u001a\u00020\u0019H&J\u0012\u00106\u001a\u00020\u00192\b\u0010,\u001a\u0004\u0018\u00010-H&J\b\u00107\u001a\u00020\u0019H&J\u0012\u00107\u001a\u00020\u00192\b\u0010,\u001a\u0004\u0018\u00010-H&J\u0012\u00108\u001a\u00020\u00192\b\u0010,\u001a\u0004\u0018\u00010-H&J\u001c\u00108\u001a\u00020\u00192\b\u0010,\u001a\u0004\u0018\u00010-2\b\u00109\u001a\u0004\u0018\u00010:H&J\u0010\u0010;\u001a\u00020\u00192\u0006\u0010<\u001a\u000202H&J\u0010\u0010=\u001a\u00020\u00192\u0006\u0010>\u001a\u000204H&J\u0016\u0010?\u001a\b\u0012\u0004\u0012\u00020&0%2\u0006\u0010'\u001a\u00020!H&J\u0016\u0010@\u001a\u00020\u00192\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00190\u001bH&J\u000e\u0010A\u001a\b\u0012\u0004\u0012\u00020&0%H&R\u0014\u0010\u0002\u001a\u0004\u0018\u00010\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0014\u0010\u0006\u001a\u0004\u0018\u00010\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u001a\u0010\n\u001a\u0004\u0018\u00010\u000bX¦\u000e¢\u0006\f\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u0012\u0010\u0010\u001a\u00020\u0011X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013R\u0014\u0010\u0014\u001a\u0004\u0018\u00010\u0015X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006BÀ\u0006\u0001"}, d2 = {"Lcom/facebook/react/ReactHost;", "", "currentReactContext", "Lcom/facebook/react/bridge/ReactContext;", "getCurrentReactContext", "()Lcom/facebook/react/bridge/ReactContext;", "devSupportManager", "Lcom/facebook/react/devsupport/interfaces/DevSupportManager;", "getDevSupportManager", "()Lcom/facebook/react/devsupport/interfaces/DevSupportManager;", "jsEngineResolutionAlgorithm", "Lcom/facebook/react/JSEngineResolutionAlgorithm;", "getJsEngineResolutionAlgorithm", "()Lcom/facebook/react/JSEngineResolutionAlgorithm;", "setJsEngineResolutionAlgorithm", "(Lcom/facebook/react/JSEngineResolutionAlgorithm;)V", "lifecycleState", "Lcom/facebook/react/common/LifecycleState;", "getLifecycleState", "()Lcom/facebook/react/common/LifecycleState;", "reactQueueConfiguration", "Lcom/facebook/react/bridge/queue/ReactQueueConfiguration;", "getReactQueueConfiguration", "()Lcom/facebook/react/bridge/queue/ReactQueueConfiguration;", "addBeforeDestroyListener", "", "onBeforeDestroy", "Lkotlin/Function0;", "createSurface", "Lcom/facebook/react/interfaces/fabric/ReactSurface;", "context", "Landroid/content/Context;", "moduleName", "", "initialProps", "Landroid/os/Bundle;", "destroy", "Lcom/facebook/react/interfaces/TaskInterface;", "Ljava/lang/Void;", DiscardedEvent.JsonKeys.REASON, "ex", "Ljava/lang/Exception;", "Lkotlin/Exception;", "onActivityResult", "activity", "Landroid/app/Activity;", "requestCode", "", "resultCode", "data", "Landroid/content/Intent;", "onBackPressed", "", "onConfigurationChanged", "onHostDestroy", "onHostPause", "onHostResume", "defaultBackButtonImpl", "Lcom/facebook/react/modules/core/DefaultHardwareBackBtnHandler;", "onNewIntent", "intent", "onWindowFocusChange", "hasFocus", "reload", "removeBeforeDestroyListener", ViewProps.START, "ReactAndroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes.dex */
public interface ReactHost {
    void addBeforeDestroyListener(Function0<Unit> onBeforeDestroy);

    ReactSurface createSurface(Context context, String moduleName, Bundle initialProps);

    TaskInterface<Void> destroy(String reason, Exception ex);

    ReactContext getCurrentReactContext();

    DevSupportManager getDevSupportManager();

    JSEngineResolutionAlgorithm getJsEngineResolutionAlgorithm();

    LifecycleState getLifecycleState();

    ReactQueueConfiguration getReactQueueConfiguration();

    void onActivityResult(Activity activity, int requestCode, int resultCode, Intent data);

    boolean onBackPressed();

    void onConfigurationChanged(Context context);

    void onHostDestroy();

    void onHostDestroy(Activity activity);

    void onHostPause();

    void onHostPause(Activity activity);

    void onHostResume(Activity activity);

    void onHostResume(Activity activity, DefaultHardwareBackBtnHandler defaultBackButtonImpl);

    void onNewIntent(Intent intent);

    void onWindowFocusChange(boolean hasFocus);

    TaskInterface<Void> reload(String reason);

    void removeBeforeDestroyListener(Function0<Unit> onBeforeDestroy);

    void setJsEngineResolutionAlgorithm(JSEngineResolutionAlgorithm jSEngineResolutionAlgorithm);

    TaskInterface<Void> start();
}
