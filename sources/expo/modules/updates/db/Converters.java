package expo.modules.updates.db;

import android.net.Uri;
import android.util.Log;
import expo.modules.updates.db.enums.HashType;
import expo.modules.updates.db.enums.UpdateStatus;
import java.nio.ByteBuffer;
import java.util.Date;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: Converters.kt */
@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0007J\u0019\u0010\n\u001a\u0004\u0018\u00010\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0007¢\u0006\u0002\u0010\u000eJ\u0012\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0007J\u0010\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0014\u001a\u00020\u0010H\u0007J\u0010\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0014\u001a\u00020\u0010H\u0007J\u0014\u0010\u0017\u001a\u0004\u0018\u00010\u00042\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0007J\u0019\u0010\u001a\u001a\u0004\u0018\u00010\r2\b\u0010\u0014\u001a\u0004\u0018\u00010\u000bH\u0007¢\u0006\u0002\u0010\u001bJ\u0012\u0010\u001c\u001a\u00020\u00102\b\u0010\u001d\u001a\u0004\u0018\u00010\u0016H\u0007J\u0014\u0010\u001e\u001a\u0004\u0018\u00010\u00192\b\u0010\u001f\u001a\u0004\u0018\u00010\u0004H\u0007J\u0014\u0010 \u001a\u0004\u0018\u00010!2\b\u0010\u001f\u001a\u0004\u0018\u00010\u0004H\u0007J\u0014\u0010\"\u001a\u0004\u0018\u00010\u00042\b\u0010#\u001a\u0004\u0018\u00010!H\u0007J\u0010\u0010$\u001a\u00020\t2\u0006\u0010%\u001a\u00020\u0007H\u0007R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006&"}, d2 = {"Lexpo/modules/updates/db/Converters;", "", "()V", "TAG", "", "kotlin.jvm.PlatformType", "bytesToUuid", "Ljava/util/UUID;", "bytes", "", "dateToLong", "", "date", "Ljava/util/Date;", "(Ljava/util/Date;)Ljava/lang/Long;", "hashTypeToInt", "", "hashType", "Lexpo/modules/updates/db/enums/HashType;", "intToHashType", "value", "intToStatus", "Lexpo/modules/updates/db/enums/UpdateStatus;", "jsonObjectToString", "jsonObject", "Lorg/json/JSONObject;", "longToDate", "(Ljava/lang/Long;)Ljava/util/Date;", "statusToInt", "status", "stringToJsonObject", "string", "stringToUri", "Landroid/net/Uri;", "uriToString", "uri", "uuidToBytes", "uuid", "expo-updates_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class Converters {
    private final String TAG = "Converters";

    /* compiled from: Converters.kt */
    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[UpdateStatus.values().length];
            try {
                iArr[UpdateStatus.READY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[UpdateStatus.PENDING.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[UpdateStatus.EMBEDDED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[UpdateStatus.DEVELOPMENT.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public final int hashTypeToInt(HashType hashType) {
        return 0;
    }

    public final Date longToDate(Long value) {
        if (value == null) {
            return null;
        }
        return new Date(value.longValue());
    }

    public final Long dateToLong(Date date) {
        if (date != null) {
            return Long.valueOf(date.getTime());
        }
        return null;
    }

    public final Uri stringToUri(String string) {
        if (string == null) {
            return null;
        }
        return Uri.parse(string);
    }

    public final String uriToString(Uri uri) {
        if (uri != null) {
            return uri.toString();
        }
        return null;
    }

    public final JSONObject stringToJsonObject(String string) {
        if (string == null) {
            return null;
        }
        try {
            return new JSONObject(string);
        } catch (JSONException e) {
            Log.e(this.TAG, "Could not convert string to JSONObject", e);
            return new JSONObject();
        }
    }

    public final String jsonObjectToString(JSONObject jsonObject) {
        if (jsonObject != null) {
            return jsonObject.toString();
        }
        return null;
    }

    public final UUID bytesToUuid(byte[] bytes) {
        ByteBuffer wrap = ByteBuffer.wrap(bytes);
        return new UUID(wrap.getLong(), wrap.getLong());
    }

    public final byte[] uuidToBytes(UUID uuid) {
        Intrinsics.checkNotNullParameter(uuid, "uuid");
        ByteBuffer wrap = ByteBuffer.wrap(new byte[16]);
        wrap.putLong(uuid.getMostSignificantBits());
        wrap.putLong(uuid.getLeastSignificantBits());
        byte[] array = wrap.array();
        Intrinsics.checkNotNullExpressionValue(array, "array(...)");
        return array;
    }

    public final UpdateStatus intToStatus(int value) {
        if (value == 1) {
            return UpdateStatus.READY;
        }
        if (value == 3) {
            return UpdateStatus.PENDING;
        }
        if (value == 5) {
            return UpdateStatus.EMBEDDED;
        }
        if (value == 6) {
            return UpdateStatus.DEVELOPMENT;
        }
        throw new AssertionError("Invalid UpdateStatus value in database: " + value);
    }

    public final int statusToInt(UpdateStatus status) {
        int i = status == null ? -1 : WhenMappings.$EnumSwitchMapping$0[status.ordinal()];
        if (i == 1) {
            return 1;
        }
        if (i == 2) {
            return 3;
        }
        if (i == 3) {
            return 5;
        }
        if (i == 4) {
            return 6;
        }
        throw new AssertionError("Invalid UpdateStatus value: " + status);
    }

    public final HashType intToHashType(int value) {
        return HashType.SHA256;
    }
}
