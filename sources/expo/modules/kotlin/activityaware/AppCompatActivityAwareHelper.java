package expo.modules.kotlin.activityaware;

import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AppCompatActivityAwareHelper.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\bH\u0016J\u000e\u0010\u000e\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u0005J\u0010\u0010\u0010\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\bH\u0016R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0011"}, d2 = {"Lexpo/modules/kotlin/activityaware/AppCompatActivityAwareHelper;", "Lexpo/modules/kotlin/activityaware/AppCompatActivityAware;", "()V", "activityReference", "Ljava/lang/ref/WeakReference;", "Landroidx/appcompat/app/AppCompatActivity;", "listeners", "Ljava/util/concurrent/CopyOnWriteArrayList;", "Lexpo/modules/kotlin/activityaware/OnActivityAvailableListener;", "getListeners", "()Ljava/util/concurrent/CopyOnWriteArrayList;", "addOnActivityAvailableListener", "", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "dispatchOnActivityAvailable", "activity", "removeOnActivityAvailableListener", "expo-modules-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class AppCompatActivityAwareHelper implements AppCompatActivityAware {
    private final CopyOnWriteArrayList<OnActivityAvailableListener> listeners = new CopyOnWriteArrayList<>();
    private WeakReference<AppCompatActivity> activityReference = new WeakReference<>(null);

    public final CopyOnWriteArrayList<OnActivityAvailableListener> getListeners() {
        return this.listeners;
    }

    public final void dispatchOnActivityAvailable(final AppCompatActivity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        this.activityReference = new WeakReference<>(activity);
        activity.runOnUiThread(new Runnable() { // from class: expo.modules.kotlin.activityaware.AppCompatActivityAwareHelper$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                AppCompatActivityAwareHelper.dispatchOnActivityAvailable$lambda$0(AppCompatActivityAwareHelper.this, activity);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void dispatchOnActivityAvailable$lambda$0(AppCompatActivityAwareHelper this$0, AppCompatActivity activity) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(activity, "$activity");
        Iterator<OnActivityAvailableListener> it = this$0.listeners.iterator();
        while (it.hasNext()) {
            it.next().onActivityAvailable(activity);
        }
    }

    @Override // expo.modules.kotlin.activityaware.AppCompatActivityAware
    public void addOnActivityAvailableListener(final OnActivityAvailableListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.listeners.add(listener);
        final AppCompatActivity appCompatActivity = this.activityReference.get();
        if (appCompatActivity != null) {
            appCompatActivity.runOnUiThread(new Runnable() { // from class: expo.modules.kotlin.activityaware.AppCompatActivityAwareHelper$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    AppCompatActivityAwareHelper.addOnActivityAvailableListener$lambda$2$lambda$1(OnActivityAvailableListener.this, appCompatActivity);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void addOnActivityAvailableListener$lambda$2$lambda$1(OnActivityAvailableListener listener, AppCompatActivity activity) {
        Intrinsics.checkNotNullParameter(listener, "$listener");
        Intrinsics.checkNotNullParameter(activity, "$activity");
        listener.onActivityAvailable(activity);
    }

    @Override // expo.modules.kotlin.activityaware.AppCompatActivityAware
    public void removeOnActivityAvailableListener(OnActivityAvailableListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.listeners.remove(listener);
    }
}
