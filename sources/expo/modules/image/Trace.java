package expo.modules.image;

import io.sentry.protocol.ViewHierarchyNode;
import kotlin.Metadata;

/* compiled from: Trace.kt */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u000b\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\u00020\u0006X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\t\u001a\u00020\u0006X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\b¨\u0006\f"}, d2 = {"Lexpo/modules/image/Trace;", "", "()V", "lastCookieValue", "", "loadNewImageBlock", "", "getLoadNewImageBlock", "()Ljava/lang/String;", ViewHierarchyNode.JsonKeys.TAG, "getTag", "getNextCookieValue", "expo-image_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class Trace {
    private static int lastCookieValue;
    public static final Trace INSTANCE = new Trace();
    private static final String tag = "ExpoImage";
    private static final String loadNewImageBlock = "load new image";

    public final String getLoadNewImageBlock() {
        return loadNewImageBlock;
    }

    public final String getTag() {
        return tag;
    }

    private Trace() {
    }

    public final int getNextCookieValue() {
        int i;
        synchronized (this) {
            i = lastCookieValue;
            lastCookieValue = i + 1;
        }
        return i;
    }
}
