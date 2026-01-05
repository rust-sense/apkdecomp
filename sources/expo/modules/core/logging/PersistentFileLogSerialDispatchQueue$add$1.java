package expo.modules.core.logging;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.channels.Channel;

/* compiled from: PersistentFileLogSerialDispatchQueue.kt */
@Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 9, 0})
@DebugMetadata(c = "expo.modules.core.logging.PersistentFileLogSerialDispatchQueue$add$1", f = "PersistentFileLogSerialDispatchQueue.kt", i = {}, l = {15}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes2.dex */
final class PersistentFileLogSerialDispatchQueue$add$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function0<Unit> $block;
    int label;
    final /* synthetic */ PersistentFileLogSerialDispatchQueue this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    PersistentFileLogSerialDispatchQueue$add$1(PersistentFileLogSerialDispatchQueue persistentFileLogSerialDispatchQueue, Function0<Unit> function0, Continuation<? super PersistentFileLogSerialDispatchQueue$add$1> continuation) {
        super(2, continuation);
        this.this$0 = persistentFileLogSerialDispatchQueue;
        this.$block = function0;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new PersistentFileLogSerialDispatchQueue$add$1(this.this$0, this.$block, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((PersistentFileLogSerialDispatchQueue$add$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Channel channel;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            channel = this.this$0.channel;
            this.label = 1;
            if (channel.send(this.$block, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return Unit.INSTANCE;
    }
}
