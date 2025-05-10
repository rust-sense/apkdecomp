package com.facebook.react.views.text;

import android.icu.text.DecimalFormat;
import android.icu.text.MeasureFormat;
import android.icu.util.Measure;
import android.icu.util.MeasureUnit;
import android.media.ExifInterface;
import java.io.FileDescriptor;

/* compiled from: D8$$SyntheticClass */
/* loaded from: classes.dex */
public final /* synthetic */ class ReactTextView$$ExternalSyntheticApiModelOutline0 {
    public static /* bridge */ /* synthetic */ DecimalFormat m(Object obj) {
        return (DecimalFormat) obj;
    }

    public static /* synthetic */ Measure m(Number number, MeasureUnit measureUnit) {
        return new Measure(number, measureUnit);
    }

    /* renamed from: m, reason: collision with other method in class */
    public static /* bridge */ /* synthetic */ MeasureUnit m537m(Object obj) {
        return (MeasureUnit) obj;
    }

    public static /* synthetic */ ExifInterface m(FileDescriptor fileDescriptor) {
        return new ExifInterface(fileDescriptor);
    }

    /* renamed from: m, reason: collision with other method in class */
    public static /* synthetic */ void m544m() {
    }

    /* renamed from: m, reason: collision with other method in class */
    public static /* bridge */ /* synthetic */ boolean m546m(Object obj) {
        return obj instanceof DecimalFormat;
    }

    public static /* bridge */ /* synthetic */ boolean m$1(Object obj) {
        return obj instanceof MeasureFormat;
    }
}
