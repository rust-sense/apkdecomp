package expo.modules.kotlin.views;

import android.view.View;
import com.facebook.react.bridge.Dynamic;
import expo.modules.kotlin.AppContext;
import expo.modules.kotlin.types.AnyType;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AnyViewProp.kt */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b&\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J$\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0015H&R\u0012\u0010\u0007\u001a\u00020\bX¦\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\u0004\u001a\u00020\u0005X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u0016"}, d2 = {"Lexpo/modules/kotlin/views/AnyViewProp;", "", "name", "", "type", "Lexpo/modules/kotlin/types/AnyType;", "(Ljava/lang/String;Lexpo/modules/kotlin/types/AnyType;)V", "isNullable", "", "()Z", "getName", "()Ljava/lang/String;", "getType$expo_modules_core_release", "()Lexpo/modules/kotlin/types/AnyType;", "set", "", "prop", "Lcom/facebook/react/bridge/Dynamic;", "onView", "Landroid/view/View;", "appContext", "Lexpo/modules/kotlin/AppContext;", "expo-modules-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public abstract class AnyViewProp {
    private final String name;
    private final AnyType type;

    public final String getName() {
        return this.name;
    }

    /* renamed from: getType$expo_modules_core_release, reason: from getter */
    public final AnyType getType() {
        return this.type;
    }

    public abstract boolean isNullable();

    public abstract void set(Dynamic prop, View onView, AppContext appContext);

    public AnyViewProp(String name, AnyType type) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(type, "type");
        this.name = name;
        this.type = type;
    }

    public static /* synthetic */ void set$default(AnyViewProp anyViewProp, Dynamic dynamic, View view, AppContext appContext, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: set");
        }
        if ((i & 4) != 0) {
            appContext = null;
        }
        anyViewProp.set(dynamic, view, appContext);
    }
}
