package expo.modules.kotlin;

import androidx.exifinterface.media.ExifInterface;
import expo.modules.kotlin.exception.Exceptions;
import java.lang.ref.WeakReference;
import kotlin.Metadata;

/* compiled from: Utils.kt */
@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\u001a\u000f\u0010\u0000\u001a\u00020\u0001*\u0004\u0018\u00010\u0001H\u0086\b\u001a!\u0010\u0002\u001a\b\u0012\u0004\u0012\u0002H\u00040\u0003\"\b\b\u0000\u0010\u0004*\u00020\u0005*\u0004\u0018\u0001H\u0004¢\u0006\u0002\u0010\u0006¨\u0006\u0007"}, d2 = {"toStrongReference", "Lexpo/modules/kotlin/AppContext;", "weak", "Ljava/lang/ref/WeakReference;", ExifInterface.GPS_DIRECTION_TRUE, "", "(Ljava/lang/Object;)Ljava/lang/ref/WeakReference;", "expo-modules-core_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class UtilsKt {
    public static final AppContext toStrongReference(AppContext appContext) {
        if (appContext != null) {
            return appContext;
        }
        throw new Exceptions.AppContextLost();
    }

    public static final <T> WeakReference<T> weak(T t) {
        return new WeakReference<>(t);
    }
}
