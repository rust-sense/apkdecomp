package expo.modules.kotlin.viewevent;

import android.content.Context;
import android.view.View;
import androidx.core.app.NotificationCompat;
import androidx.exifinterface.media.ExifInterface;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.WritableMap;
import expo.modules.adapters.react.NativeModulesProxy;
import expo.modules.core.logging.Logger;
import expo.modules.kotlin.AppContext;
import expo.modules.kotlin.CoreLoggerKt;
import expo.modules.kotlin.ModuleHolder;
import expo.modules.kotlin.ReactExtensionsKt;
import expo.modules.kotlin.events.EventEmitter;
import expo.modules.kotlin.types.JSTypeConverter;
import expo.modules.kotlin.types.JSTypeConverterHelperKt;
import expo.modules.kotlin.views.CallbacksDefinition;
import expo.modules.kotlin.views.ViewManagerDefinition;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ViewEvent.kt */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\n\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0016\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002BF\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012/\u0010\u0007\u001a+\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\t\u0012\b\b\u0003\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\bj\n\u0012\u0004\u0012\u00028\u0000\u0018\u0001`\f¢\u0006\u0002\u0010\rJ\u0017\u0010\u0010\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0012\u001a\u00028\u0000H\u0002¢\u0006\u0002\u0010\u0013J\u0016\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0012\u001a\u00028\u0000H\u0096\u0002¢\u0006\u0002\u0010\u0016R7\u0010\u0007\u001a+\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\t\u0012\b\b\u0003\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\bj\n\u0012\u0004\u0012\u00028\u0000\u0018\u0001`\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lexpo/modules/kotlin/viewevent/ViewEvent;", ExifInterface.GPS_DIRECTION_TRUE, "Lexpo/modules/kotlin/viewevent/ViewEventCallback;", "name", "", "view", "Landroid/view/View;", "coalescingKey", "Lkotlin/Function1;", "Lkotlin/ParameterName;", NotificationCompat.CATEGORY_EVENT, "", "Lexpo/modules/kotlin/viewevent/CoalescingKey;", "(Ljava/lang/String;Landroid/view/View;Lkotlin/jvm/functions/Function1;)V", "isValidated", "", "convertEventBody", "Lcom/facebook/react/bridge/WritableMap;", "arg", "(Ljava/lang/Object;)Lcom/facebook/react/bridge/WritableMap;", "invoke", "", "(Ljava/lang/Object;)V", "expo-modules-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public class ViewEvent<T> implements ViewEventCallback<T> {
    private final Function1<T, Short> coalescingKey;
    private boolean isValidated;
    private final String name;
    private final View view;

    /* JADX WARN: Multi-variable type inference failed */
    public ViewEvent(String name, View view, Function1<? super T, Short> function1) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(view, "view");
        this.name = name;
        this.view = view;
        this.coalescingKey = function1;
    }

    @Override // expo.modules.kotlin.viewevent.ViewEventCallback
    public void invoke(T arg) {
        Context context = this.view.getContext();
        Intrinsics.checkNotNull(context, "null cannot be cast to non-null type com.facebook.react.bridge.ReactContext");
        NativeModulesProxy unimoduleProxy = ReactExtensionsKt.getUnimoduleProxy((ReactContext) context);
        if (unimoduleProxy == null) {
            return;
        }
        AppContext appContext = unimoduleProxy.getKotlinInteropModuleRegistry().getAppContext();
        if (!this.isValidated) {
            ModuleHolder<?> moduleHolder = appContext.getRegistry().getModuleHolder(this.view.getClass());
            if (moduleHolder == null) {
                Logger.warn$default(CoreLoggerKt.getLogger(), "⚠️ Cannot get module holder for " + this.view.getClass(), null, 2, null);
                return;
            }
            ViewManagerDefinition viewManagerDefinition = moduleHolder.getDefinition().getViewManagerDefinition();
            CallbacksDefinition callbacksDefinition = viewManagerDefinition != null ? viewManagerDefinition.getCallbacksDefinition() : null;
            if (callbacksDefinition == null) {
                Logger.warn$default(CoreLoggerKt.getLogger(), "⚠️ Cannot get callbacks for " + moduleHolder.getModule().getClass(), null, 2, null);
                return;
            }
            for (String str : callbacksDefinition.getNames()) {
                if (Intrinsics.areEqual(str, this.name)) {
                    this.isValidated = true;
                }
            }
            Logger.warn$default(CoreLoggerKt.getLogger(), "⚠️ Event " + this.name + " wasn't exported from " + moduleHolder.getModule().getClass(), null, 2, null);
            return;
        }
        EventEmitter callbackInvoker$expo_modules_core_release = appContext.getCallbackInvoker$expo_modules_core_release();
        if (callbackInvoker$expo_modules_core_release != null) {
            int id = this.view.getId();
            String str2 = this.name;
            WritableMap convertEventBody = convertEventBody(arg);
            Function1<T, Short> function1 = this.coalescingKey;
            callbackInvoker$expo_modules_core_release.emit(id, str2, convertEventBody, function1 != null ? function1.invoke(arg) : null);
        }
    }

    private final WritableMap convertEventBody(T arg) {
        Object convertToJSValue$default = JSTypeConverter.convertToJSValue$default(JSTypeConverter.INSTANCE, arg, null, 2, null);
        if ((convertToJSValue$default instanceof Unit) || convertToJSValue$default == null) {
            return null;
        }
        if (convertToJSValue$default instanceof WritableMap) {
            return (WritableMap) convertToJSValue$default;
        }
        WritableMap createMap = JSTypeConverter.DefaultContainerProvider.INSTANCE.createMap();
        JSTypeConverterHelperKt.putGeneric(createMap, "payload", convertToJSValue$default);
        return createMap;
    }
}
