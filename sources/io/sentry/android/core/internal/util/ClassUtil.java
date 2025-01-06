package io.sentry.android.core.internal.util;

/* loaded from: classes2.dex */
public class ClassUtil {
    public static String getClassName(Object obj) {
        if (obj == null) {
            return null;
        }
        String canonicalName = obj.getClass().getCanonicalName();
        return canonicalName != null ? canonicalName : obj.getClass().getSimpleName();
    }
}
