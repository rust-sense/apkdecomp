package expo.modules.localization;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LocalizationPackage.kt */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0014\u0010\u0007\u001a\u00020\u00062\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005J\u0006\u0010\t\u001a\u00020\u0006J\u0014\u0010\n\u001a\u00020\u00062\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005R\u001a\u0010\u0003\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lexpo/modules/localization/Notifier;", "", "()V", "observers", "", "Lkotlin/Function0;", "", "deregisterObserver", "observer", "onConfigurationChanged", "registerObserver", "expo-localization_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class Notifier {
    public static final Notifier INSTANCE = new Notifier();
    private static final List<Function0<Unit>> observers = new ArrayList();

    private Notifier() {
    }

    public final void registerObserver(Function0<Unit> observer) {
        Intrinsics.checkNotNullParameter(observer, "observer");
        observers.add(observer);
    }

    public final void deregisterObserver(Function0<Unit> observer) {
        Intrinsics.checkNotNullParameter(observer, "observer");
        observers.remove(observer);
    }

    public final void onConfigurationChanged() {
        Iterator<T> it = observers.iterator();
        while (it.hasNext()) {
            ((Function0) it.next()).invoke();
        }
    }
}
