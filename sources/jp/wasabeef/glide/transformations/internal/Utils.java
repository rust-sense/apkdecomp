package jp.wasabeef.glide.transformations.internal;

import android.content.res.Resources;

/* loaded from: classes2.dex */
public final class Utils {
    private Utils() {
    }

    public static int toDp(int px) {
        return px * ((int) Resources.getSystem().getDisplayMetrics().density);
    }
}
