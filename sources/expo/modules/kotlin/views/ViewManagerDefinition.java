package expo.modules.kotlin.views;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import com.facebook.react.bridge.ReactContext;
import expo.modules.adapters.react.NativeModulesProxy;
import expo.modules.kotlin.AppContext;
import expo.modules.kotlin.ReactExtensionsKt;
import expo.modules.kotlin.defaultmodules.ErrorManagerModule;
import expo.modules.kotlin.exception.CodedException;
import expo.modules.kotlin.functions.BaseAsyncFunctionComponent;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ViewManagerDefinition.kt */
@Metadata(d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u009b\u0001\u0012\u0018\u0010\u0002\u001a\u0014\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0003\u0012\u000e\u0010\u0007\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00060\b\u0012\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f0\n\u0012\u0016\b\u0002\u0010\r\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u000e\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0011\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0013\u0012\u0016\b\u0002\u0010\u0014\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u000e\u0012\u000e\b\u0002\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00170\u0016¢\u0006\u0002\u0010\u0018J\u0016\u0010(\u001a\u00020\u00062\u0006\u0010)\u001a\u00020\u00042\u0006\u0010*\u001a\u00020\u0005J\u0006\u0010+\u001a\u00020,J\u0016\u0010-\u001a\u00020\u000f2\u0006\u0010.\u001a\u00020\u00062\u0006\u0010/\u001a\u000200R\u0017\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00170\u0016¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0013\u0010\u0010\u001a\u0004\u0018\u00010\u0011¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u001f\u0010\r\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u001f\u0010\u0014\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u001eR \u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f0\nX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b \u0010!R\u0017\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0016¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\u001aR \u0010\u0002\u001a\u0014\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0013\u0010\u0012\u001a\u0004\u0018\u00010\u0013¢\u0006\b\n\u0000\u001a\u0004\b$\u0010%R\u001c\u0010\u0007\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00060\bX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b&\u0010'¨\u00061"}, d2 = {"Lexpo/modules/kotlin/views/ViewManagerDefinition;", "", "viewFactory", "Lkotlin/Function2;", "Landroid/content/Context;", "Lexpo/modules/kotlin/AppContext;", "Landroid/view/View;", "viewType", "Ljava/lang/Class;", "props", "", "", "Lexpo/modules/kotlin/views/AnyViewProp;", "onViewDestroys", "Lkotlin/Function1;", "", "callbacksDefinition", "Lexpo/modules/kotlin/views/CallbacksDefinition;", "viewGroupDefinition", "Lexpo/modules/kotlin/views/ViewGroupDefinition;", "onViewDidUpdateProps", "asyncFunctions", "", "Lexpo/modules/kotlin/functions/BaseAsyncFunctionComponent;", "(Lkotlin/jvm/functions/Function2;Ljava/lang/Class;Ljava/util/Map;Lkotlin/jvm/functions/Function1;Lexpo/modules/kotlin/views/CallbacksDefinition;Lexpo/modules/kotlin/views/ViewGroupDefinition;Lkotlin/jvm/functions/Function1;Ljava/util/List;)V", "getAsyncFunctions", "()Ljava/util/List;", "getCallbacksDefinition", "()Lexpo/modules/kotlin/views/CallbacksDefinition;", "getOnViewDestroys", "()Lkotlin/jvm/functions/Function1;", "getOnViewDidUpdateProps", "getProps$expo_modules_core_release", "()Ljava/util/Map;", "propsNames", "getPropsNames", "getViewGroupDefinition", "()Lexpo/modules/kotlin/views/ViewGroupDefinition;", "getViewType$expo_modules_core_release", "()Ljava/lang/Class;", "createView", "context", "appContext", "getViewManagerType", "Lexpo/modules/kotlin/views/ViewManagerType;", "handleException", "view", "exception", "Lexpo/modules/kotlin/exception/CodedException;", "expo-modules-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class ViewManagerDefinition {
    private final List<BaseAsyncFunctionComponent> asyncFunctions;
    private final CallbacksDefinition callbacksDefinition;
    private final Function1<View, Unit> onViewDestroys;
    private final Function1<View, Unit> onViewDidUpdateProps;
    private final Map<String, AnyViewProp> props;
    private final List<String> propsNames;
    private final Function2<Context, AppContext, View> viewFactory;
    private final ViewGroupDefinition viewGroupDefinition;
    private final Class<? extends View> viewType;

    public final List<BaseAsyncFunctionComponent> getAsyncFunctions() {
        return this.asyncFunctions;
    }

    public final CallbacksDefinition getCallbacksDefinition() {
        return this.callbacksDefinition;
    }

    public final Function1<View, Unit> getOnViewDestroys() {
        return this.onViewDestroys;
    }

    public final Function1<View, Unit> getOnViewDidUpdateProps() {
        return this.onViewDidUpdateProps;
    }

    public final Map<String, AnyViewProp> getProps$expo_modules_core_release() {
        return this.props;
    }

    public final List<String> getPropsNames() {
        return this.propsNames;
    }

    public final ViewGroupDefinition getViewGroupDefinition() {
        return this.viewGroupDefinition;
    }

    public final Class<? extends View> getViewType$expo_modules_core_release() {
        return this.viewType;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public ViewManagerDefinition(Function2<? super Context, ? super AppContext, ? extends View> viewFactory, Class<? extends View> viewType, Map<String, ? extends AnyViewProp> props, Function1<? super View, Unit> function1, CallbacksDefinition callbacksDefinition, ViewGroupDefinition viewGroupDefinition, Function1<? super View, Unit> function12, List<? extends BaseAsyncFunctionComponent> asyncFunctions) {
        Intrinsics.checkNotNullParameter(viewFactory, "viewFactory");
        Intrinsics.checkNotNullParameter(viewType, "viewType");
        Intrinsics.checkNotNullParameter(props, "props");
        Intrinsics.checkNotNullParameter(asyncFunctions, "asyncFunctions");
        this.viewFactory = viewFactory;
        this.viewType = viewType;
        this.props = props;
        this.onViewDestroys = function1;
        this.callbacksDefinition = callbacksDefinition;
        this.viewGroupDefinition = viewGroupDefinition;
        this.onViewDidUpdateProps = function12;
        this.asyncFunctions = asyncFunctions;
        this.propsNames = CollectionsKt.toList(props.keySet());
    }

    public /* synthetic */ ViewManagerDefinition(Function2 function2, Class cls, Map map, Function1 function1, CallbacksDefinition callbacksDefinition, ViewGroupDefinition viewGroupDefinition, Function1 function12, List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(function2, cls, map, (i & 8) != 0 ? null : function1, (i & 16) != 0 ? null : callbacksDefinition, (i & 32) != 0 ? null : viewGroupDefinition, (i & 64) != 0 ? null : function12, (i & 128) != 0 ? CollectionsKt.emptyList() : list);
    }

    public final View createView(Context context, AppContext appContext) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(appContext, "appContext");
        return this.viewFactory.invoke(context, appContext);
    }

    public final ViewManagerType getViewManagerType() {
        if (ViewGroup.class.isAssignableFrom(this.viewType)) {
            return ViewManagerType.GROUP;
        }
        return ViewManagerType.SIMPLE;
    }

    public final void handleException(View view, CodedException exception) {
        NativeModulesProxy unimoduleProxy;
        ErrorManagerModule errorManager;
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(exception, "exception");
        Context context = view.getContext();
        ReactContext reactContext = context instanceof ReactContext ? (ReactContext) context : null;
        if (reactContext == null || (unimoduleProxy = ReactExtensionsKt.getUnimoduleProxy(reactContext)) == null || (errorManager = unimoduleProxy.getKotlinInteropModuleRegistry().getAppContext().getErrorManager()) == null) {
            return;
        }
        errorManager.reportExceptionToLogBox(exception);
    }
}
