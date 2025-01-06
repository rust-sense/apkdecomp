package expo.modules.notifications.service.delegates;

import android.util.Base64;
import androidx.exifinterface.media.ExifInterface;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import kotlin.Metadata;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Base64Serialization.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u001a\u0010\u0000\u001a\u0002H\u0001\"\u0006\b\u0000\u0010\u0001\u0018\u0001*\u00020\u0002H\u0086\b¢\u0006\u0002\u0010\u0003\u001a\n\u0010\u0004\u001a\u00020\u0002*\u00020\u0005¨\u0006\u0006"}, d2 = {"asBase64EncodedObject", ExifInterface.GPS_DIRECTION_TRUE, "", "(Ljava/lang/String;)Ljava/lang/Object;", "encodedInBase64", "Ljava/io/Serializable;", "expo-notifications_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class Base64SerializationKt {
    public static final String encodedInBase64(Serializable serializable) throws IOException {
        Intrinsics.checkNotNullParameter(serializable, "<this>");
        ObjectOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            ByteArrayOutputStream byteArrayOutputStream2 = byteArrayOutputStream;
            byteArrayOutputStream = new ObjectOutputStream(byteArrayOutputStream2);
            try {
                byteArrayOutputStream.writeObject(serializable);
                String encodeToString = Base64.encodeToString(byteArrayOutputStream2.toByteArray(), 2);
                CloseableKt.closeFinally(byteArrayOutputStream, null);
                CloseableKt.closeFinally(byteArrayOutputStream, null);
                Intrinsics.checkNotNullExpressionValue(encodeToString, "use(...)");
                return encodeToString;
            } finally {
            }
        } finally {
        }
    }

    public static final /* synthetic */ <T> T asBase64EncodedObject(String str) throws IOException, ClassNotFoundException, InvalidClassException {
        Intrinsics.checkNotNullParameter(str, "<this>");
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(Base64.decode(str, 2));
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
            try {
                T t = (T) objectInputStream.readObject();
                Intrinsics.reifiedOperationMarker(3, ExifInterface.GPS_DIRECTION_TRUE);
                if (t instanceof Object) {
                    InlineMarker.finallyStart(2);
                    CloseableKt.closeFinally(objectInputStream, null);
                    InlineMarker.finallyEnd(2);
                    InlineMarker.finallyStart(2);
                    CloseableKt.closeFinally(byteArrayInputStream, null);
                    InlineMarker.finallyEnd(2);
                    return t;
                }
                Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
                throw new InvalidClassException("Expected serialized object to be an instance of " + Object.class + ". Found: " + t);
            } finally {
            }
        } finally {
        }
    }
}
