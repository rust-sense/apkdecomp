package com.facebook.imagepipeline.cache;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;

/* compiled from: ValueDescriptor.kt */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\bæ\u0080\u0001\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002J\u0015\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00028\u0000H&¢\u0006\u0002\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/facebook/imagepipeline/cache/ValueDescriptor;", ExifInterface.GPS_MEASUREMENT_INTERRUPTED, "", "getSizeInBytes", "", "value", "(Ljava/lang/Object;)I", "imagepipeline-base_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public interface ValueDescriptor<V> {
    int getSizeInBytes(V value);
}