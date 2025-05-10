package expo.modules.kotlin.functions;

import com.facebook.react.bridge.BaseJavaModule;
import com.facebook.react.bridge.ReadableArray;
import expo.modules.kotlin.AppContext;
import expo.modules.kotlin.Promise;
import expo.modules.kotlin.exception.CodedException;
import expo.modules.kotlin.types.AnyType;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AsyncFunctionComponent.kt */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0016\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002BH\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012+\u0010\b\u001a'\u0012\u001d\u0012\u001b\u0012\b\b\u0001\u0012\u0004\u0018\u00010\n0\u0006¢\u0006\f\b\u000b\u0012\b\b\u0003\u0012\u0004\b\b(\f\u0012\u0004\u0012\u00028\u00000\t¢\u0006\u0002\u0010\rJ\u001d\u0010\u0010\u001a\u00020\u00112\u0006\u0010\f\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0010¢\u0006\u0002\b\u0015J/\u0010\u0010\u001a\u00020\u00112\u000e\u0010\f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\u00062\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u0017H\u0010¢\u0006\u0004\b\u0015\u0010\u0018R9\u0010\b\u001a'\u0012\u001d\u0012\u001b\u0012\b\b\u0001\u0012\u0004\u0018\u00010\n0\u0006¢\u0006\f\b\u000b\u0012\b\b\u0003\u0012\u0004\b\b(\f\u0012\u0004\u0012\u00028\u00000\tX\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0019"}, d2 = {"Lexpo/modules/kotlin/functions/AsyncFunctionComponent;", "ReturnType", "Lexpo/modules/kotlin/functions/AsyncFunction;", "name", "", "desiredArgsTypes", "", "Lexpo/modules/kotlin/types/AnyType;", "body", "Lkotlin/Function1;", "", "Lkotlin/ParameterName;", "args", "(Ljava/lang/String;[Lexpo/modules/kotlin/types/AnyType;Lkotlin/jvm/functions/Function1;)V", "getBody", "()Lkotlin/jvm/functions/Function1;", "callUserImplementation", "", "Lcom/facebook/react/bridge/ReadableArray;", BaseJavaModule.METHOD_TYPE_PROMISE, "Lexpo/modules/kotlin/Promise;", "callUserImplementation$expo_modules_core_release", "appContext", "Lexpo/modules/kotlin/AppContext;", "([Ljava/lang/Object;Lexpo/modules/kotlin/Promise;Lexpo/modules/kotlin/AppContext;)V", "expo-modules-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public class AsyncFunctionComponent<ReturnType> extends AsyncFunction {
    private final Function1<Object[], ReturnType> body;

    protected final Function1<Object[], ReturnType> getBody() {
        return this.body;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public AsyncFunctionComponent(String name, AnyType[] desiredArgsTypes, Function1<? super Object[], ? extends ReturnType> body) {
        super(name, desiredArgsTypes);
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(desiredArgsTypes, "desiredArgsTypes");
        Intrinsics.checkNotNullParameter(body, "body");
        this.body = body;
    }

    @Override // expo.modules.kotlin.functions.AsyncFunction
    public void callUserImplementation$expo_modules_core_release(ReadableArray args, Promise promise) throws CodedException {
        Intrinsics.checkNotNullParameter(args, "args");
        Intrinsics.checkNotNullParameter(promise, "promise");
        promise.resolve(this.body.invoke(convertArgs(args)));
    }

    @Override // expo.modules.kotlin.functions.AsyncFunction
    public void callUserImplementation$expo_modules_core_release(Object[] args, Promise promise, AppContext appContext) {
        Intrinsics.checkNotNullParameter(args, "args");
        Intrinsics.checkNotNullParameter(promise, "promise");
        Intrinsics.checkNotNullParameter(appContext, "appContext");
        promise.resolve(this.body.invoke(convertArgs(args, appContext)));
    }
}
