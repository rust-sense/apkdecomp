package expo.modules.kotlin.viewevent;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;

/* compiled from: ViewEvent.kt */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\bæ\u0080\u0001\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002J\u0016\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00028\u0000H¦\u0002¢\u0006\u0002\u0010\u0006¨\u0006\u0007"}, d2 = {"Lexpo/modules/kotlin/viewevent/ViewEventCallback;", ExifInterface.GPS_DIRECTION_TRUE, "", "invoke", "", "arg", "(Ljava/lang/Object;)V", "expo-modules-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public interface ViewEventCallback<T> {
    void invoke(T arg);
}