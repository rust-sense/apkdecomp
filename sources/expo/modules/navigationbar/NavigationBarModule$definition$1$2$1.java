package expo.modules.navigationbar;

import android.view.View;
import kotlin.Metadata;

/* compiled from: NavigationBarModule.kt */
@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "run"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
final class NavigationBarModule$definition$1$2$1 implements Runnable {
    final /* synthetic */ View $decorView;

    NavigationBarModule$definition$1$2$1(View view) {
        this.$decorView = view;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.$decorView.setOnSystemUiVisibilityChangeListener(null);
    }
}
