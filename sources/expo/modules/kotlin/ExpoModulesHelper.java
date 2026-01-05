package expo.modules.kotlin;

import android.util.Log;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ExpoModulesHelper.kt */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lexpo/modules/kotlin/ExpoModulesHelper;", "", "()V", "Companion", "expo-modules-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class ExpoModulesHelper {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Lazy<ModulesProvider> modulesProvider$delegate = LazyKt.lazy(new Function0<ModulesProvider>() { // from class: expo.modules.kotlin.ExpoModulesHelper$Companion$modulesProvider$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final ModulesProvider invoke() {
            try {
                Object newInstance = Class.forName("expo.modules.ExpoModulesPackageList").getConstructor(new Class[0]).newInstance(new Object[0]);
                Intrinsics.checkNotNull(newInstance, "null cannot be cast to non-null type expo.modules.kotlin.ModulesProvider");
                return (ModulesProvider) newInstance;
            } catch (Exception e) {
                Log.e("ExpoModulesHelper", "Couldn't get expo modules list.", e);
                return null;
            }
        }
    });

    /* compiled from: ExpoModulesHelper.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001d\u0010\u0003\u001a\u0004\u0018\u00010\u00048FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006¨\u0006\t"}, d2 = {"Lexpo/modules/kotlin/ExpoModulesHelper$Companion;", "", "()V", "modulesProvider", "Lexpo/modules/kotlin/ModulesProvider;", "getModulesProvider", "()Lexpo/modules/kotlin/ModulesProvider;", "modulesProvider$delegate", "Lkotlin/Lazy;", "expo-modules-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final ModulesProvider getModulesProvider() {
            return (ModulesProvider) ExpoModulesHelper.modulesProvider$delegate.getValue();
        }
    }
}
