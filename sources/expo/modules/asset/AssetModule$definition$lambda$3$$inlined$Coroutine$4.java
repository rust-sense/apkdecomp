package expo.modules.asset;

import android.net.Uri;
import expo.modules.kotlin.AppContext;
import java.io.File;
import java.net.URI;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: AsyncFunctionBuilder.kt */
@Metadata(d1 = {"\u0000\u0014\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0002\b\u0003\u0010\t\u001a\u0004\u0018\u00010\u0006\"\u0006\b\u0000\u0010\u0000\u0018\u0001\"\u0006\b\u0001\u0010\u0001\u0018\u0001\"\u0006\b\u0002\u0010\u0002\u0018\u0001\"\u0006\b\u0003\u0010\u0003\u0018\u0001*\u00020\u00042\u0010\u0010\u0007\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00060\u0005H\u008a@¨\u0006\b"}, d2 = {"R", "P0", "P1", "P2", "Lkotlinx/coroutines/CoroutineScope;", "", "", "<name for destructuring parameter 0>", "expo/modules/kotlin/functions/AsyncFunctionBuilder$SuspendBody$7", "<anonymous>"}, k = 3, mv = {1, 9, 0})
@DebugMetadata(c = "expo.modules.asset.AssetModule$definition$lambda$3$$inlined$Coroutine$4", f = "AssetModule.kt", i = {}, l = {280, 287}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes2.dex */
public final class AssetModule$definition$lambda$3$$inlined$Coroutine$4 extends SuspendLambda implements Function3<CoroutineScope, Object[], Continuation<? super Object>, Object> {
    /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ AssetModule this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AssetModule$definition$lambda$3$$inlined$Coroutine$4(Continuation continuation, AssetModule assetModule) {
        super(3, continuation);
        this.this$0 = assetModule;
    }

    @Override // kotlin.jvm.functions.Function3
    public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Object[] objArr, Continuation<? super Object> continuation) {
        return invoke2(coroutineScope, objArr, (Continuation<Object>) continuation);
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Object invoke2(CoroutineScope coroutineScope, Object[] objArr, Continuation<Object> continuation) {
        AssetModule$definition$lambda$3$$inlined$Coroutine$4 assetModule$definition$lambda$3$$inlined$Coroutine$4 = new AssetModule$definition$lambda$3$$inlined$Coroutine$4(continuation, this.this$0);
        assetModule$definition$lambda$3$$inlined$Coroutine$4.L$0 = objArr;
        return assetModule$definition$lambda$3$$inlined$Coroutine$4.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        String mD5HashOfFileContent;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            Object[] objArr = (Object[]) this.L$0;
            Object obj2 = objArr[0];
            Object obj3 = objArr[1];
            String str = (String) objArr[2];
            String str2 = (String) obj3;
            URI uri = (URI) obj2;
            if (uri.getScheme() == "file") {
                return uri;
            }
            String mD5HashOfFilePath = str2 == null ? this.this$0.getMD5HashOfFilePath(uri) : str2;
            File file = new File(this.this$0.getAppContext().getCacheDirectory() + "/ExponentAsset-" + mD5HashOfFilePath + "." + str);
            if (file.exists()) {
                if (str2 != null) {
                    mD5HashOfFileContent = this.this$0.getMD5HashOfFileContent(file);
                    if (!Intrinsics.areEqual(str2, mD5HashOfFileContent)) {
                        AssetModule assetModule = this.this$0;
                        AppContext appContext = assetModule.getAppContext();
                        this.label = 2;
                        obj = assetModule.downloadAsset(appContext, uri, file, this);
                        if (obj == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    }
                }
                return Uri.fromFile(file);
            }
            AssetModule assetModule2 = this.this$0;
            AppContext appContext2 = assetModule2.getAppContext();
            this.label = 1;
            obj = assetModule2.downloadAsset(appContext2, uri, file, this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1 && i != 2) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return obj;
    }
}
