package expo.modules.kotlin.uuidv5;

import com.google.common.base.Ascii;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import okio.Utf8;

/* compiled from: Uuidv5.kt */
@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\u001a\u0010\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0000\u001a\u0018\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0007H\u0000¨\u0006\b"}, d2 = {"toBytes", "", "uuid", "Ljava/util/UUID;", "uuidv5", "namespace", "name", "", "expo-modules-core_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class Uuidv5Kt {
    public static final UUID uuidv5(UUID namespace, String name) {
        Intrinsics.checkNotNullParameter(namespace, "namespace");
        Intrinsics.checkNotNullParameter(name, "name");
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
        messageDigest.update(toBytes(namespace));
        byte[] bytes = name.getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
        messageDigest.update(bytes);
        byte[] digest = messageDigest.digest();
        digest[6] = (byte) ((digest[6] & Ascii.SI) | 80);
        digest[8] = (byte) ((digest[8] & Utf8.REPLACEMENT_BYTE) | 128);
        ByteBuffer wrap = ByteBuffer.wrap(digest);
        return new UUID(wrap.getLong(), wrap.getLong());
    }

    public static final byte[] toBytes(UUID uuid) {
        Intrinsics.checkNotNullParameter(uuid, "uuid");
        ByteBuffer wrap = ByteBuffer.wrap(new byte[16]);
        wrap.putLong(uuid.getMostSignificantBits());
        wrap.putLong(uuid.getLeastSignificantBits());
        byte[] array = wrap.array();
        Intrinsics.checkNotNullExpressionValue(array, "array(...)");
        return array;
    }
}
