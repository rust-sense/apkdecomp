package expo.modules.haptics.arguments;

import com.google.firebase.messaging.Constants;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: HapticsNotificationType.kt */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0005R\u001a\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lexpo/modules/haptics/arguments/HapticsNotificationType;", "", "()V", "types", "", "", "Lexpo/modules/haptics/arguments/HapticsVibrationType;", "fromString", "type", "expo-haptics_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class HapticsNotificationType {
    public static final HapticsNotificationType INSTANCE = new HapticsNotificationType();
    private static final Map<String, HapticsVibrationType> types = MapsKt.mapOf(TuplesKt.to("success", new HapticsVibrationType(new long[]{0, 40, 100, 40}, new int[]{0, 50, 0, 60}, new long[]{0, 40, 100, 40})), TuplesKt.to("warning", new HapticsVibrationType(new long[]{0, 40, 120, 60}, new int[]{0, 40, 0, 60}, new long[]{0, 40, 120, 60})), TuplesKt.to(Constants.IPC_BUNDLE_KEY_SEND_ERROR, new HapticsVibrationType(new long[]{0, 60, 100, 40, 80, 50}, new int[]{0, 50, 0, 40, 0, 50}, new long[]{0, 60, 100, 40, 80, 50})));

    private HapticsNotificationType() {
    }

    public final HapticsVibrationType fromString(String type) throws HapticsInvalidArgumentException {
        Intrinsics.checkNotNullParameter(type, "type");
        HapticsVibrationType hapticsVibrationType = types.get(type);
        if (hapticsVibrationType != null) {
            return hapticsVibrationType;
        }
        throw new HapticsInvalidArgumentException("'type' must be one of ['success', 'warning', 'error']. Obtained '" + type + "'.");
    }
}
