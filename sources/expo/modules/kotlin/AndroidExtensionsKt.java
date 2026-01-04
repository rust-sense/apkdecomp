package expo.modules.kotlin;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import androidx.exifinterface.media.ExifInterface;
import java.io.Serializable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AndroidExtensions.kt */
@Metadata(d1 = {"\u0000&\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a(\u0010\u0000\u001a\u0004\u0018\u0001H\u0001\"\n\b\u0000\u0010\u0001\u0018\u0001*\u00020\u0002*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0086\b¢\u0006\u0002\u0010\u0006\u001a(\u0010\u0007\u001a\u0004\u0018\u0001H\u0001\"\n\b\u0000\u0010\u0001\u0018\u0001*\u00020\u0002*\u00020\b2\u0006\u0010\u0004\u001a\u00020\u0005H\u0086\b¢\u0006\u0002\u0010\t\u001a(\u0010\n\u001a\u0004\u0018\u0001H\u0001\"\n\b\u0000\u0010\u0001\u0018\u0001*\u00020\u000b*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0086\b¢\u0006\u0002\u0010\f¨\u0006\r"}, d2 = {"safeGetParcelable", ExifInterface.GPS_DIRECTION_TRUE, "Landroid/os/Parcelable;", "Landroid/os/Bundle;", "name", "", "(Landroid/os/Bundle;Ljava/lang/String;)Landroid/os/Parcelable;", "safeGetParcelableExtra", "Landroid/content/Intent;", "(Landroid/content/Intent;Ljava/lang/String;)Landroid/os/Parcelable;", "safeGetSerializable", "Ljava/io/Serializable;", "(Landroid/os/Bundle;Ljava/lang/String;)Ljava/io/Serializable;", "expo-modules-core_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class AndroidExtensionsKt {
    public static final /* synthetic */ <T extends Parcelable> T safeGetParcelableExtra(Intent intent, String name) {
        Object parcelableExtra;
        Intrinsics.checkNotNullParameter(intent, "<this>");
        Intrinsics.checkNotNullParameter(name, "name");
        if (Build.VERSION.SDK_INT >= 33) {
            Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
            parcelableExtra = intent.getParcelableExtra(name, Parcelable.class);
            return (T) parcelableExtra;
        }
        return (T) intent.getParcelableExtra(name);
    }

    public static final /* synthetic */ <T extends Parcelable> T safeGetParcelable(Bundle bundle, String name) {
        Object parcelable;
        Intrinsics.checkNotNullParameter(bundle, "<this>");
        Intrinsics.checkNotNullParameter(name, "name");
        if (Build.VERSION.SDK_INT >= 33) {
            Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
            parcelable = bundle.getParcelable(name, Parcelable.class);
            return (T) parcelable;
        }
        return (T) bundle.getParcelable(name);
    }

    public static final /* synthetic */ <T extends Serializable> T safeGetSerializable(Bundle bundle, String name) {
        Serializable serializable;
        Intrinsics.checkNotNullParameter(bundle, "<this>");
        Intrinsics.checkNotNullParameter(name, "name");
        if (Build.VERSION.SDK_INT >= 33) {
            Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
            serializable = bundle.getSerializable(name, Serializable.class);
            return (T) serializable;
        }
        T t = (T) bundle.getSerializable(name);
        Intrinsics.reifiedOperationMarker(1, "T?");
        return t;
    }
}
