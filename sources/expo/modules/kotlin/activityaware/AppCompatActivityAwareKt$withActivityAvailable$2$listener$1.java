package expo.modules.kotlin.activityaware;

import androidx.appcompat.app.AppCompatActivity;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CancellableContinuation;

/* compiled from: AppCompatActivityAware.kt */
@Metadata(d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0006"}, d2 = {"expo/modules/kotlin/activityaware/AppCompatActivityAwareKt$withActivityAvailable$2$listener$1", "Lexpo/modules/kotlin/activityaware/OnActivityAvailableListener;", "onActivityAvailable", "", "activity", "Landroidx/appcompat/app/AppCompatActivity;", "expo-modules-core_release"}, k = 1, mv = {1, 9, 0}, xi = 176)
/* loaded from: classes2.dex */
public final class AppCompatActivityAwareKt$withActivityAvailable$2$listener$1 implements OnActivityAvailableListener {
    final /* synthetic */ CancellableContinuation<R> $continuation;
    final /* synthetic */ Function1<AppCompatActivity, R> $onActivityAvailable;
    final /* synthetic */ AppCompatActivityAware $this_withActivityAvailable;

    /* JADX WARN: Multi-variable type inference failed */
    public AppCompatActivityAwareKt$withActivityAvailable$2$listener$1(AppCompatActivityAware appCompatActivityAware, CancellableContinuation<? super R> cancellableContinuation, Function1<? super AppCompatActivity, ? extends R> function1) {
        this.$this_withActivityAvailable = appCompatActivityAware;
        this.$continuation = cancellableContinuation;
        this.$onActivityAvailable = function1;
    }

    @Override // expo.modules.kotlin.activityaware.OnActivityAvailableListener
    public void onActivityAvailable(AppCompatActivity activity) {
        Object m868constructorimpl;
        Intrinsics.checkNotNullParameter(activity, "activity");
        this.$this_withActivityAvailable.removeOnActivityAvailableListener(this);
        Continuation continuation = this.$continuation;
        Function1<AppCompatActivity, R> function1 = this.$onActivityAvailable;
        try {
            Result.Companion companion = Result.INSTANCE;
            AppCompatActivityAwareKt$withActivityAvailable$2$listener$1 appCompatActivityAwareKt$withActivityAvailable$2$listener$1 = this;
            m868constructorimpl = Result.m868constructorimpl(function1.invoke(activity));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            m868constructorimpl = Result.m868constructorimpl(ResultKt.createFailure(th));
        }
        continuation.resumeWith(m868constructorimpl);
    }
}
