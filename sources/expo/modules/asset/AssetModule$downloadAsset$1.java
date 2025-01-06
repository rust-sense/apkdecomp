package expo.modules.asset;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* compiled from: AssetModule.kt */
@Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "expo.modules.asset.AssetModule", f = "AssetModule.kt", i = {}, l = {56}, m = "downloadAsset", n = {}, s = {})
/* loaded from: classes2.dex */
final class AssetModule$downloadAsset$1 extends ContinuationImpl {
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ AssetModule this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    AssetModule$downloadAsset$1(AssetModule assetModule, Continuation<? super AssetModule$downloadAsset$1> continuation) {
        super(continuation);
        this.this$0 = assetModule;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object downloadAsset;
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        downloadAsset = this.this$0.downloadAsset(null, null, null, this);
        return downloadAsset;
    }
}
