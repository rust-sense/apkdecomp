package expo.modules.kotlin.functions;

import com.facebook.react.bridge.ReadableArray;
import expo.modules.kotlin.ModuleHolder;
import expo.modules.kotlin.Promise;
import expo.modules.kotlin.exception.CodedException;
import expo.modules.kotlin.exception.FunctionCallException;
import expo.modules.kotlin.exception.UnexpectedException;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: AsyncFunction.kt */
@Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 9, 0})
@DebugMetadata(c = "expo.modules.kotlin.functions.AsyncFunction$call$1", f = "AsyncFunction.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes2.dex */
final class AsyncFunction$call$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ ReadableArray $args;
    final /* synthetic */ ModuleHolder<?> $holder;
    final /* synthetic */ Promise $promise;
    int label;
    final /* synthetic */ AsyncFunction this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    AsyncFunction$call$1(Promise promise, AsyncFunction asyncFunction, ModuleHolder<?> moduleHolder, ReadableArray readableArray, Continuation<? super AsyncFunction$call$1> continuation) {
        super(2, continuation);
        this.$promise = promise;
        this.this$0 = asyncFunction;
        this.$holder = moduleHolder;
        this.$args = readableArray;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new AsyncFunction$call$1(this.$promise, this.this$0, this.$holder, this.$args, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((AsyncFunction$call$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        FunctionCallException functionCallException;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            try {
                AsyncFunction asyncFunction = this.this$0;
                ModuleHolder<?> moduleHolder = this.$holder;
                try {
                    asyncFunction.callUserImplementation$expo_modules_core_release(this.$args, this.$promise);
                    Unit unit = Unit.INSTANCE;
                } finally {
                }
            } catch (CodedException e) {
                this.$promise.reject(e);
            } catch (Throwable th) {
                this.$promise.reject(new UnexpectedException(th));
            }
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
