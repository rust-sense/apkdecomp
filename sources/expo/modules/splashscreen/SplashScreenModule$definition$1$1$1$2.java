package expo.modules.splashscreen;

import expo.modules.kotlin.Promise;
import expo.modules.splashscreen.exceptions.PreventAutoHideException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* compiled from: SplashScreenModule.kt */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "m", "", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
final class SplashScreenModule$definition$1$1$1$2 extends Lambda implements Function1<String, Unit> {
    final /* synthetic */ Promise $promise;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    SplashScreenModule$definition$1$1$1$2(Promise promise) {
        super(1);
        this.$promise = promise;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(String str) {
        invoke2(str);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final void invoke2(String m) {
        Intrinsics.checkNotNullParameter(m, "m");
        this.$promise.reject(new PreventAutoHideException(m));
    }
}
