package expo.modules.kotlin.functions;

import com.facebook.react.bridge.ReadableArray;
import expo.modules.kotlin.ModuleHolder;
import expo.modules.kotlin.Promise;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: SuspendFunctionComponent.kt */
@Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 9, 0})
@DebugMetadata(c = "expo.modules.kotlin.functions.SuspendFunctionComponent$call$1", f = "SuspendFunctionComponent.kt", i = {0}, l = {37}, m = "invokeSuspend", n = {"$this$launch"}, s = {"L$0"})
/* loaded from: classes2.dex */
final class SuspendFunctionComponent$call$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ ReadableArray $args;
    final /* synthetic */ ModuleHolder<?> $holder;
    final /* synthetic */ Promise $promise;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;
    final /* synthetic */ SuspendFunctionComponent this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    SuspendFunctionComponent$call$1(Promise promise, SuspendFunctionComponent suspendFunctionComponent, ModuleHolder<?> moduleHolder, ReadableArray readableArray, Continuation<? super SuspendFunctionComponent$call$1> continuation) {
        super(2, continuation);
        this.$promise = promise;
        this.this$0 = suspendFunctionComponent;
        this.$holder = moduleHolder;
        this.$args = readableArray;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        SuspendFunctionComponent$call$1 suspendFunctionComponent$call$1 = new SuspendFunctionComponent$call$1(this.$promise, this.this$0, this.$holder, this.$args, continuation);
        suspendFunctionComponent$call$1.L$0 = obj;
        return suspendFunctionComponent$call$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((SuspendFunctionComponent$call$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x006a A[Catch: all -> 0x00a3, CodedException -> 0x00b1, TryCatch #4 {CodedException -> 0x00b1, all -> 0x00a3, blocks: (B:16:0x0065, B:18:0x006a, B:20:0x006e, B:21:0x0093, B:22:0x00a2, B:28:0x0088, B:29:0x0090, B:34:0x0031), top: B:33:0x0031 }] */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0090 A[Catch: all -> 0x00a3, CodedException -> 0x00b1, TryCatch #4 {CodedException -> 0x00b1, all -> 0x00a3, blocks: (B:16:0x0065, B:18:0x006a, B:20:0x006e, B:21:0x0093, B:22:0x00a2, B:28:0x0088, B:29:0x0090, B:34:0x0031), top: B:33:0x0031 }] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r9) {
        /*
            r8 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r8.label
            r2 = 1
            if (r1 == 0) goto L29
            if (r1 != r2) goto L21
            java.lang.Object r0 = r8.L$3
            expo.modules.kotlin.Promise r0 = (expo.modules.kotlin.Promise) r0
            java.lang.Object r1 = r8.L$2
            expo.modules.kotlin.ModuleHolder r1 = (expo.modules.kotlin.ModuleHolder) r1
            java.lang.Object r2 = r8.L$1
            expo.modules.kotlin.functions.SuspendFunctionComponent r2 = (expo.modules.kotlin.functions.SuspendFunctionComponent) r2
            java.lang.Object r3 = r8.L$0
            kotlinx.coroutines.CoroutineScope r3 = (kotlinx.coroutines.CoroutineScope) r3
            kotlin.ResultKt.throwOnFailure(r9)     // Catch: java.lang.Throwable -> L1f
            goto L56
        L1f:
            r9 = move-exception
            goto L65
        L21:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r0)
            throw r9
        L29:
            kotlin.ResultKt.throwOnFailure(r9)
            java.lang.Object r9 = r8.L$0
            r3 = r9
            kotlinx.coroutines.CoroutineScope r3 = (kotlinx.coroutines.CoroutineScope) r3
            expo.modules.kotlin.functions.SuspendFunctionComponent r9 = r8.this$0     // Catch: java.lang.Throwable -> La3 expo.modules.kotlin.exception.CodedException -> Lb1
            expo.modules.kotlin.ModuleHolder<?> r1 = r8.$holder     // Catch: java.lang.Throwable -> La3 expo.modules.kotlin.exception.CodedException -> Lb1
            com.facebook.react.bridge.ReadableArray r4 = r8.$args     // Catch: java.lang.Throwable -> La3 expo.modules.kotlin.exception.CodedException -> Lb1
            expo.modules.kotlin.Promise r5 = r8.$promise     // Catch: java.lang.Throwable -> La3 expo.modules.kotlin.exception.CodedException -> Lb1
            kotlin.jvm.functions.Function3 r6 = expo.modules.kotlin.functions.SuspendFunctionComponent.access$getBody$p(r9)     // Catch: java.lang.Throwable -> L62
            java.lang.Object[] r4 = r9.convertArgs(r4)     // Catch: java.lang.Throwable -> L62
            r8.L$0 = r3     // Catch: java.lang.Throwable -> L62
            r8.L$1 = r9     // Catch: java.lang.Throwable -> L62
            r8.L$2 = r1     // Catch: java.lang.Throwable -> L62
            r8.L$3 = r5     // Catch: java.lang.Throwable -> L62
            r8.label = r2     // Catch: java.lang.Throwable -> L62
            java.lang.Object r2 = r6.invoke(r3, r4, r8)     // Catch: java.lang.Throwable -> L62
            if (r2 != r0) goto L52
            return r0
        L52:
            r0 = r5
            r7 = r2
            r2 = r9
            r9 = r7
        L56:
            boolean r3 = kotlinx.coroutines.CoroutineScopeKt.isActive(r3)     // Catch: java.lang.Throwable -> L1f
            if (r3 == 0) goto L5f
            r0.resolve(r9)     // Catch: java.lang.Throwable -> L1f
        L5f:
            kotlin.Unit r9 = kotlin.Unit.INSTANCE     // Catch: java.lang.Throwable -> L1f
            goto Lb7
        L62:
            r0 = move-exception
            r2 = r9
            r9 = r0
        L65:
            boolean r0 = r9 instanceof expo.modules.kotlin.exception.CodedException     // Catch: java.lang.Throwable -> La3 expo.modules.kotlin.exception.CodedException -> Lb1
            if (r0 != 0) goto L90
            boolean r0 = r9 instanceof expo.modules.core.errors.CodedException     // Catch: java.lang.Throwable -> La3 expo.modules.kotlin.exception.CodedException -> Lb1
            if (r0 == 0) goto L88
            expo.modules.kotlin.exception.CodedException r0 = new expo.modules.kotlin.exception.CodedException     // Catch: java.lang.Throwable -> La3 expo.modules.kotlin.exception.CodedException -> Lb1
            r3 = r9
            expo.modules.core.errors.CodedException r3 = (expo.modules.core.errors.CodedException) r3     // Catch: java.lang.Throwable -> La3 expo.modules.kotlin.exception.CodedException -> Lb1
            java.lang.String r3 = r3.getCode()     // Catch: java.lang.Throwable -> La3 expo.modules.kotlin.exception.CodedException -> Lb1
            java.lang.String r4 = "getCode(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r3, r4)     // Catch: java.lang.Throwable -> La3 expo.modules.kotlin.exception.CodedException -> Lb1
            java.lang.String r4 = r9.getMessage()     // Catch: java.lang.Throwable -> La3 expo.modules.kotlin.exception.CodedException -> Lb1
            java.lang.Throwable r9 = r9.getCause()     // Catch: java.lang.Throwable -> La3 expo.modules.kotlin.exception.CodedException -> Lb1
            r0.<init>(r3, r4, r9)     // Catch: java.lang.Throwable -> La3 expo.modules.kotlin.exception.CodedException -> Lb1
            goto L93
        L88:
            expo.modules.kotlin.exception.UnexpectedException r0 = new expo.modules.kotlin.exception.UnexpectedException     // Catch: java.lang.Throwable -> La3 expo.modules.kotlin.exception.CodedException -> Lb1
            r0.<init>(r9)     // Catch: java.lang.Throwable -> La3 expo.modules.kotlin.exception.CodedException -> Lb1
            expo.modules.kotlin.exception.CodedException r0 = (expo.modules.kotlin.exception.CodedException) r0     // Catch: java.lang.Throwable -> La3 expo.modules.kotlin.exception.CodedException -> Lb1
            goto L93
        L90:
            r0 = r9
            expo.modules.kotlin.exception.CodedException r0 = (expo.modules.kotlin.exception.CodedException) r0     // Catch: java.lang.Throwable -> La3 expo.modules.kotlin.exception.CodedException -> Lb1
        L93:
            expo.modules.kotlin.exception.FunctionCallException r9 = new expo.modules.kotlin.exception.FunctionCallException     // Catch: java.lang.Throwable -> La3 expo.modules.kotlin.exception.CodedException -> Lb1
            java.lang.String r2 = r2.getName()     // Catch: java.lang.Throwable -> La3 expo.modules.kotlin.exception.CodedException -> Lb1
            java.lang.String r1 = r1.getName()     // Catch: java.lang.Throwable -> La3 expo.modules.kotlin.exception.CodedException -> Lb1
            r9.<init>(r2, r1, r0)     // Catch: java.lang.Throwable -> La3 expo.modules.kotlin.exception.CodedException -> Lb1
            java.lang.Throwable r9 = (java.lang.Throwable) r9     // Catch: java.lang.Throwable -> La3 expo.modules.kotlin.exception.CodedException -> Lb1
            throw r9     // Catch: java.lang.Throwable -> La3 expo.modules.kotlin.exception.CodedException -> Lb1
        La3:
            r9 = move-exception
            expo.modules.kotlin.Promise r0 = r8.$promise
            expo.modules.kotlin.exception.UnexpectedException r1 = new expo.modules.kotlin.exception.UnexpectedException
            r1.<init>(r9)
            expo.modules.kotlin.exception.CodedException r1 = (expo.modules.kotlin.exception.CodedException) r1
            r0.reject(r1)
            goto Lb7
        Lb1:
            r9 = move-exception
            expo.modules.kotlin.Promise r0 = r8.$promise
            r0.reject(r9)
        Lb7:
            kotlin.Unit r9 = kotlin.Unit.INSTANCE
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.kotlin.functions.SuspendFunctionComponent$call$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
