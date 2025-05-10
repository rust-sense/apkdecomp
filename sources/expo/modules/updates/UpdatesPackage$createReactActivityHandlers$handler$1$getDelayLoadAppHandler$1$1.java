package expo.modules.updates;

import android.content.Context;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import org.bouncycastle.asn1.eac.EACTags;

/* compiled from: UpdatesPackage.kt */
@Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 9, 0})
@DebugMetadata(c = "expo.modules.updates.UpdatesPackage$createReactActivityHandlers$handler$1$getDelayLoadAppHandler$1$1", f = "UpdatesPackage.kt", i = {}, l = {EACTags.APPLICATION_IMAGE, EACTags.DISPLAY_IMAGE}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes2.dex */
final class UpdatesPackage$createReactActivityHandlers$handler$1$getDelayLoadAppHandler$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Context $context;
    final /* synthetic */ Runnable $whenReadyRunnable;
    int label;
    final /* synthetic */ UpdatesPackage$createReactActivityHandlers$handler$1 this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    UpdatesPackage$createReactActivityHandlers$handler$1$getDelayLoadAppHandler$1$1(UpdatesPackage$createReactActivityHandlers$handler$1 updatesPackage$createReactActivityHandlers$handler$1, Context context, Runnable runnable, Continuation<? super UpdatesPackage$createReactActivityHandlers$handler$1$getDelayLoadAppHandler$1$1> continuation) {
        super(2, continuation);
        this.this$0 = updatesPackage$createReactActivityHandlers$handler$1;
        this.$context = context;
        this.$whenReadyRunnable = runnable;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new UpdatesPackage$createReactActivityHandlers$handler$1$getDelayLoadAppHandler$1$1(this.this$0, this.$context, this.$whenReadyRunnable, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((UpdatesPackage$createReactActivityHandlers$handler$1$getDelayLoadAppHandler$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object startUpdatesController;
        Object invokeReadyRunnable;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            UpdatesPackage$createReactActivityHandlers$handler$1 updatesPackage$createReactActivityHandlers$handler$1 = this.this$0;
            Context context = this.$context;
            Intrinsics.checkNotNullExpressionValue(context, "$context");
            this.label = 1;
            startUpdatesController = updatesPackage$createReactActivityHandlers$handler$1.startUpdatesController(context, this);
            if (startUpdatesController == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                if (i != 2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                return Unit.INSTANCE;
            }
            ResultKt.throwOnFailure(obj);
        }
        UpdatesPackage$createReactActivityHandlers$handler$1 updatesPackage$createReactActivityHandlers$handler$12 = this.this$0;
        Runnable whenReadyRunnable = this.$whenReadyRunnable;
        Intrinsics.checkNotNullExpressionValue(whenReadyRunnable, "$whenReadyRunnable");
        this.label = 2;
        invokeReadyRunnable = updatesPackage$createReactActivityHandlers$handler$12.invokeReadyRunnable(whenReadyRunnable, this);
        if (invokeReadyRunnable == coroutine_suspended) {
            return coroutine_suspended;
        }
        return Unit.INSTANCE;
    }
}
