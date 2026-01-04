package expo.modules.kotlin.functions;

import android.view.View;
import androidx.core.app.NotificationCompat;
import com.facebook.react.bridge.BaseJavaModule;
import com.facebook.react.bridge.ReadableArray;
import expo.modules.kotlin.AppContext;
import expo.modules.kotlin.ModuleHolder;
import expo.modules.kotlin.Promise;
import expo.modules.kotlin.exception.CodedException;
import expo.modules.kotlin.exception.FunctionCallException;
import expo.modules.kotlin.exception.UnexpectedException;
import expo.modules.kotlin.jni.ExpectedType;
import expo.modules.kotlin.jni.JNIAsyncFunctionBody;
import expo.modules.kotlin.jni.JavaScriptModuleObject;
import expo.modules.kotlin.jni.PromiseImpl;
import expo.modules.kotlin.types.AnyType;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;
import kotlin.reflect.KClassifier;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: AsyncFunction.kt */
@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b&\u0018\u00002\u00020\u0001B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\u0010\u0007J\u0018\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0016J$\u0010\u000e\u001a\u00020\t2\n\u0010\u000f\u001a\u0006\u0012\u0002\b\u00030\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J\u001d\u0010\u0015\u001a\u00020\t2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H ¢\u0006\u0002\b\u0016J/\u0010\u0015\u001a\u00020\t2\u000e\u0010\u0011\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00170\u00052\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\n\u001a\u00020\u000bH ¢\u0006\u0004\b\u0016\u0010\u0018J\u001e\u0010\u0019\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\t0\u001bH\u0002¨\u0006\u001c"}, d2 = {"Lexpo/modules/kotlin/functions/AsyncFunction;", "Lexpo/modules/kotlin/functions/BaseAsyncFunctionComponent;", "name", "", "desiredArgsTypes", "", "Lexpo/modules/kotlin/types/AnyType;", "(Ljava/lang/String;[Lexpo/modules/kotlin/types/AnyType;)V", "attachToJSObject", "", "appContext", "Lexpo/modules/kotlin/AppContext;", "jsObject", "Lexpo/modules/kotlin/jni/JavaScriptModuleObject;", NotificationCompat.CATEGORY_CALL, "holder", "Lexpo/modules/kotlin/ModuleHolder;", "args", "Lcom/facebook/react/bridge/ReadableArray;", BaseJavaModule.METHOD_TYPE_PROMISE, "Lexpo/modules/kotlin/Promise;", "callUserImplementation", "callUserImplementation$expo_modules_core_release", "", "([Ljava/lang/Object;Lexpo/modules/kotlin/Promise;Lexpo/modules/kotlin/AppContext;)V", "dispatchOnQueue", "block", "Lkotlin/Function0;", "expo-modules-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public abstract class AsyncFunction extends BaseAsyncFunctionComponent {

    /* compiled from: AsyncFunction.kt */
    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[Queues.values().length];
            try {
                iArr[Queues.MAIN.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[Queues.DEFAULT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public abstract void callUserImplementation$expo_modules_core_release(ReadableArray args, Promise promise) throws CodedException;

    public abstract void callUserImplementation$expo_modules_core_release(Object[] args, Promise promise, AppContext appContext);

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AsyncFunction(String name, AnyType[] desiredArgsTypes) {
        super(name, desiredArgsTypes);
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(desiredArgsTypes, "desiredArgsTypes");
    }

    /* JADX WARN: Type inference failed for: r0v6, types: [expo.modules.kotlin.modules.Module] */
    @Override // expo.modules.kotlin.functions.BaseAsyncFunctionComponent
    public void call(ModuleHolder<?> holder, ReadableArray args, Promise promise) {
        CoroutineScope mainQueue;
        Intrinsics.checkNotNullParameter(holder, "holder");
        Intrinsics.checkNotNullParameter(args, "args");
        Intrinsics.checkNotNullParameter(promise, "promise");
        int i = WhenMappings.$EnumSwitchMapping$0[getQueue().ordinal()];
        if (i == 1) {
            mainQueue = holder.getModule().getAppContext().getMainQueue();
        } else {
            if (i != 2) {
                throw new NoWhenBranchMatchedException();
            }
            mainQueue = null;
        }
        CoroutineScope coroutineScope = mainQueue;
        if (coroutineScope != null) {
            BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new AsyncFunction$call$1(promise, this, holder, args, null), 3, null);
        } else {
            callUserImplementation$expo_modules_core_release(args, promise);
        }
    }

    @Override // expo.modules.kotlin.functions.AnyFunction
    public void attachToJSObject(final AppContext appContext, JavaScriptModuleObject jsObject) {
        Intrinsics.checkNotNullParameter(appContext, "appContext");
        Intrinsics.checkNotNullParameter(jsObject, "jsObject");
        final WeakReference<AppContext> appContextHolder$expo_modules_core_release = appContext.getJsiInterop$expo_modules_core_release().getAppContextHolder$expo_modules_core_release();
        final String name = jsObject.getName();
        String name2 = getName();
        boolean takesOwner$expo_modules_core_release = getTakesOwner$expo_modules_core_release();
        AnyType[] desiredArgsTypes = getDesiredArgsTypes();
        ArrayList arrayList = new ArrayList(desiredArgsTypes.length);
        for (AnyType anyType : desiredArgsTypes) {
            arrayList.add(anyType.getCppRequiredTypes());
        }
        jsObject.registerAsyncFunction(name2, takesOwner$expo_modules_core_release, (ExpectedType[]) arrayList.toArray(new ExpectedType[0]), new JNIAsyncFunctionBody() { // from class: expo.modules.kotlin.functions.AsyncFunction$$ExternalSyntheticLambda0
            @Override // expo.modules.kotlin.jni.JNIAsyncFunctionBody
            public final void invoke(Object[] objArr, PromiseImpl promiseImpl) {
                AsyncFunction.attachToJSObject$lambda$1(appContextHolder$expo_modules_core_release, name, this, appContext, objArr, promiseImpl);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void attachToJSObject$lambda$1(WeakReference appContextHolder, final String moduleName, final AsyncFunction this$0, final AppContext appContext, final Object[] args, final PromiseImpl promiseImpl) {
        Intrinsics.checkNotNullParameter(appContextHolder, "$appContextHolder");
        Intrinsics.checkNotNullParameter(moduleName, "$moduleName");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(appContext, "$appContext");
        Intrinsics.checkNotNullParameter(args, "args");
        Intrinsics.checkNotNullParameter(promiseImpl, "promiseImpl");
        this$0.dispatchOnQueue(appContext, new Function0<Unit>() { // from class: expo.modules.kotlin.functions.AsyncFunction$attachToJSObject$2$functionBody$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* JADX WARN: Multi-variable type inference failed */
            /* JADX WARN: Type inference failed for: r0v0, types: [java.lang.String] */
            /* JADX WARN: Type inference failed for: r0v5, types: [kotlin.Unit] */
            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                UnexpectedException unexpectedException;
                UnexpectedException unexpectedException2;
                String str = "getCode(...)";
                try {
                    AsyncFunction asyncFunction = this$0;
                    String str2 = moduleName;
                    try {
                        asyncFunction.callUserImplementation$expo_modules_core_release(args, PromiseImpl.this, appContext);
                        str = Unit.INSTANCE;
                    } catch (Throwable th) {
                        if (th instanceof CodedException) {
                            unexpectedException2 = (CodedException) th;
                        } else if (th instanceof expo.modules.core.errors.CodedException) {
                            String code = ((expo.modules.core.errors.CodedException) th).getCode();
                            Intrinsics.checkNotNullExpressionValue(code, "getCode(...)");
                            unexpectedException2 = new CodedException(code, th.getMessage(), th.getCause());
                        } else {
                            unexpectedException2 = new UnexpectedException(th);
                        }
                        throw new FunctionCallException(asyncFunction.getName(), str2, unexpectedException2);
                    }
                } catch (Throwable th2) {
                    if (PromiseImpl.this.getWasSettled()) {
                        throw th2;
                    }
                    PromiseImpl promiseImpl2 = PromiseImpl.this;
                    if (th2 instanceof CodedException) {
                        unexpectedException = (CodedException) th2;
                    } else if (th2 instanceof expo.modules.core.errors.CodedException) {
                        String code2 = ((expo.modules.core.errors.CodedException) th2).getCode();
                        Intrinsics.checkNotNullExpressionValue(code2, str);
                        unexpectedException = new CodedException(code2, th2.getMessage(), th2.getCause());
                    } else {
                        unexpectedException = new UnexpectedException(th2);
                    }
                    promiseImpl2.reject(unexpectedException);
                }
            }
        });
    }

    private final void dispatchOnQueue(AppContext appContext, Function0<Unit> block) {
        int i = WhenMappings.$EnumSwitchMapping$0[getQueue().ordinal()];
        if (i != 1) {
            if (i != 2) {
                return;
            }
            BuildersKt__Builders_commonKt.launch$default(appContext.getModulesQueue(), null, null, new AsyncFunction$dispatchOnQueue$1(block, null), 3, null);
            return;
        }
        for (AnyType anyType : getDesiredArgsTypes()) {
            KClassifier classifier = anyType.getKType().getClassifier();
            KClass kClass = classifier instanceof KClass ? (KClass) classifier : null;
            if (kClass != null && View.class.isAssignableFrom(JvmClassMappingKt.getJavaClass(kClass))) {
                appContext.dispatchOnMainUsingUIManager$expo_modules_core_release(block);
                return;
            }
        }
        BuildersKt__Builders_commonKt.launch$default(appContext.getMainQueue(), null, null, new AsyncFunction$dispatchOnQueue$3(block, null), 3, null);
    }
}
