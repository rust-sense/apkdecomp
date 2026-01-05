package expo.modules.navigationbar;

import android.os.Bundle;
import android.view.View;
import com.facebook.react.uimanager.ViewProps;
import io.sentry.protocol.ViewHierarchyNode;
import kotlin.Metadata;
import kotlin.Unit;

/* compiled from: NavigationBarModule.kt */
@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "run"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
final class NavigationBarModule$definition$1$1$1 implements Runnable {
    final /* synthetic */ View $decorView;
    final /* synthetic */ NavigationBarModule this$0;

    NavigationBarModule$definition$1$1$1(View view, NavigationBarModule navigationBarModule) {
        this.$decorView = view;
        this.this$0 = navigationBarModule;
    }

    @Override // java.lang.Runnable
    public final void run() {
        View view = this.$decorView;
        final NavigationBarModule navigationBarModule = this.this$0;
        view.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() { // from class: expo.modules.navigationbar.NavigationBarModule$definition$1$1$1.1
            @Override // android.view.View.OnSystemUiVisibilityChangeListener
            public final void onSystemUiVisibilityChange(int i) {
                String str = (i & 2) == 0 ? ViewProps.VISIBLE : ViewProps.HIDDEN;
                NavigationBarModule navigationBarModule2 = NavigationBarModule.this;
                Bundle bundle = new Bundle();
                bundle.putString(ViewHierarchyNode.JsonKeys.VISIBILITY, str);
                bundle.putInt("rawVisibility", i);
                Unit unit = Unit.INSTANCE;
                navigationBarModule2.sendEvent("ExpoNavigationBar.didChange", bundle);
            }
        });
    }
}
