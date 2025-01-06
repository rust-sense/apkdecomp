package expo.modules.kotlin.views;

import android.content.Context;
import android.view.View;
import com.facebook.react.bridge.Dynamic;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableMapKeySetIterator;
import com.facebook.react.common.MapBuilder;
import expo.modules.kotlin.CoreLoggerKt;
import expo.modules.kotlin.ModuleHolder;
import expo.modules.kotlin.events.KModuleEventEmitterWrapperKt;
import expo.modules.kotlin.exception.CodedException;
import expo.modules.kotlin.exception.OnViewDidUpdatePropsException;
import expo.modules.kotlin.exception.UnexpectedException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ViewManagerWrapperDelegate.kt */
@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0011\u0012\n\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001cJ\u0014\u0010\u001d\u001a\u0010\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0011J\u000e\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u001aJ\u000e\u0010!\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u001aJ\u001c\u0010\"\u001a\b\u0012\u0004\u0012\u00020\r0#2\u0006\u0010 \u001a\u00020\u001a2\u0006\u0010$\u001a\u00020%R\u0014\u0010\u0005\u001a\u00020\u00068BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u001e\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\u0004R\u0011\u0010\f\u001a\u00020\r8F¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u001d\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u00120\u00118F¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014R\u0016\u0010\u0015\u001a\u0004\u0018\u00010\u00168@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0018¨\u0006&"}, d2 = {"Lexpo/modules/kotlin/views/ViewManagerWrapperDelegate;", "", "moduleHolder", "Lexpo/modules/kotlin/ModuleHolder;", "(Lexpo/modules/kotlin/ModuleHolder;)V", "definition", "Lexpo/modules/kotlin/views/ViewManagerDefinition;", "getDefinition", "()Lexpo/modules/kotlin/views/ViewManagerDefinition;", "getModuleHolder$expo_modules_core_release", "()Lexpo/modules/kotlin/ModuleHolder;", "setModuleHolder$expo_modules_core_release", "name", "", "getName", "()Ljava/lang/String;", "props", "", "Lexpo/modules/kotlin/views/AnyViewProp;", "getProps", "()Ljava/util/Map;", "viewGroupDefinition", "Lexpo/modules/kotlin/views/ViewGroupDefinition;", "getViewGroupDefinition$expo_modules_core_release", "()Lexpo/modules/kotlin/views/ViewGroupDefinition;", "createView", "Landroid/view/View;", "context", "Landroid/content/Context;", "getExportedCustomDirectEventTypeConstants", "onDestroy", "", "view", "onViewDidUpdateProps", "updateProperties", "", "propsMap", "Lcom/facebook/react/bridge/ReadableMap;", "expo-modules-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class ViewManagerWrapperDelegate {
    private ModuleHolder<?> moduleHolder;

    public final ModuleHolder<?> getModuleHolder$expo_modules_core_release() {
        return this.moduleHolder;
    }

    public final void setModuleHolder$expo_modules_core_release(ModuleHolder<?> moduleHolder) {
        Intrinsics.checkNotNullParameter(moduleHolder, "<set-?>");
        this.moduleHolder = moduleHolder;
    }

    public ViewManagerWrapperDelegate(ModuleHolder<?> moduleHolder) {
        Intrinsics.checkNotNullParameter(moduleHolder, "moduleHolder");
        this.moduleHolder = moduleHolder;
    }

    private final ViewManagerDefinition getDefinition() {
        ViewManagerDefinition viewManagerDefinition = this.moduleHolder.getDefinition().getViewManagerDefinition();
        if (viewManagerDefinition != null) {
            return viewManagerDefinition;
        }
        throw new IllegalArgumentException("Required value was null.".toString());
    }

    public final ViewGroupDefinition getViewGroupDefinition$expo_modules_core_release() {
        return getDefinition().getViewGroupDefinition();
    }

    public final String getName() {
        return this.moduleHolder.getName();
    }

    public final Map<String, AnyViewProp> getProps() {
        return getDefinition().getProps$expo_modules_core_release();
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [expo.modules.kotlin.modules.Module] */
    public final View createView(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return getDefinition().createView(context, this.moduleHolder.getModule().getAppContext());
    }

    public final void onViewDidUpdateProps(View view) {
        UnexpectedException unexpectedException;
        UnexpectedException unexpectedException2;
        Intrinsics.checkNotNullParameter(view, "view");
        Function1<View, Unit> onViewDidUpdateProps = getDefinition().getOnViewDidUpdateProps();
        if (onViewDidUpdateProps != null) {
            try {
                onViewDidUpdateProps.invoke(view);
                Unit unit = Unit.INSTANCE;
            } catch (Throwable th) {
                try {
                    if (th instanceof CodedException) {
                        unexpectedException2 = (CodedException) th;
                    } else if (th instanceof expo.modules.core.errors.CodedException) {
                        String code = ((expo.modules.core.errors.CodedException) th).getCode();
                        Intrinsics.checkNotNullExpressionValue(code, "getCode(...)");
                        unexpectedException2 = new CodedException(code, th.getMessage(), th.getCause());
                    } else {
                        unexpectedException2 = new UnexpectedException(th);
                    }
                    throw new OnViewDidUpdatePropsException(JvmClassMappingKt.getKotlinClass(view.getClass()), unexpectedException2);
                } catch (Throwable th2) {
                    if (ErrorViewKt.isErrorView(view)) {
                        return;
                    }
                    if (th2 instanceof CodedException) {
                        unexpectedException = (CodedException) th2;
                    } else if (th2 instanceof expo.modules.core.errors.CodedException) {
                        String code2 = ((expo.modules.core.errors.CodedException) th2).getCode();
                        Intrinsics.checkNotNullExpressionValue(code2, "getCode(...)");
                        unexpectedException = new CodedException(code2, th2.getMessage(), th2.getCause());
                    } else {
                        unexpectedException = new UnexpectedException(th2);
                    }
                    CoreLoggerKt.getLogger().error("❌ Error occurred when invoking 'onViewDidUpdateProps' on '" + view.getClass().getSimpleName() + "'", unexpectedException);
                    getDefinition().handleException(view, unexpectedException);
                }
            }
        }
    }

    /* JADX WARN: Type inference failed for: r6v9, types: [expo.modules.kotlin.modules.Module] */
    public final List<String> updateProperties(View view, ReadableMap propsMap) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(propsMap, "propsMap");
        Map<String, AnyViewProp> props = getProps();
        ArrayList arrayList = new ArrayList();
        ReadableMapKeySetIterator keySetIterator = propsMap.keySetIterator();
        Intrinsics.checkNotNullExpressionValue(keySetIterator, "keySetIterator(...)");
        while (keySetIterator.hasNextKey()) {
            String nextKey = keySetIterator.nextKey();
            AnyViewProp anyViewProp = props.get(nextKey);
            if (anyViewProp != null) {
                try {
                    Dynamic dynamic = propsMap.getDynamic(nextKey);
                    Intrinsics.checkNotNullExpressionValue(dynamic, "getDynamic(...)");
                    anyViewProp.set(dynamic, view, this.moduleHolder.getModule().get_appContext());
                } finally {
                    try {
                    } finally {
                    }
                }
            }
        }
        return arrayList;
    }

    public final void onDestroy(View view) {
        UnexpectedException unexpectedException;
        Intrinsics.checkNotNullParameter(view, "view");
        try {
            Function1<View, Unit> onViewDestroys = getDefinition().getOnViewDestroys();
            if (onViewDestroys != null) {
                onViewDestroys.invoke(view);
            }
        } catch (Throwable th) {
            if (ErrorViewKt.isErrorView(view)) {
                return;
            }
            if (th instanceof CodedException) {
                unexpectedException = (CodedException) th;
            } else if (th instanceof expo.modules.core.errors.CodedException) {
                String code = ((expo.modules.core.errors.CodedException) th).getCode();
                Intrinsics.checkNotNullExpressionValue(code, "getCode(...)");
                unexpectedException = new CodedException(code, th.getMessage(), th.getCause());
            } else {
                unexpectedException = new UnexpectedException(th);
            }
            CoreLoggerKt.getLogger().error("❌ '" + view + "' wasn't able to destroy itself", unexpectedException);
            getDefinition().handleException(view, unexpectedException);
        }
    }

    public final Map<String, Object> getExportedCustomDirectEventTypeConstants() {
        String[] names;
        MapBuilder.Builder builder = MapBuilder.builder();
        Intrinsics.checkNotNullExpressionValue(builder, "builder(...)");
        CallbacksDefinition callbacksDefinition = getDefinition().getCallbacksDefinition();
        if (callbacksDefinition != null && (names = callbacksDefinition.getNames()) != null) {
            for (String str : names) {
                builder.put(KModuleEventEmitterWrapperKt.normalizeEventName(str), MapBuilder.of("registrationName", str));
            }
        }
        return builder.build();
    }
}
