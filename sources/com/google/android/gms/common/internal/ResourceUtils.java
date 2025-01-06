package com.google.android.gms.common.internal;

import android.net.Uri;
import com.facebook.common.util.UriUtil;

/* loaded from: classes2.dex */
public final class ResourceUtils {
    private static final Uri zzet = new Uri.Builder().scheme(UriUtil.QUALIFIED_RESOURCE_SCHEME).authority("com.google.android.gms").appendPath("drawable").build();

    private ResourceUtils() {
    }
}
