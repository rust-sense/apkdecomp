package expo.modules.image.records;

import android.graphics.RectF;
import expo.modules.image.ImageUtilsKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function5;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ContentPosition.kt */
@Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
/* synthetic */ class ContentPosition$offsetX$2 extends FunctionReferenceImpl implements Function5<Float, RectF, RectF, Boolean, Boolean, Float> {
    public static final ContentPosition$offsetX$2 INSTANCE = new ContentPosition$offsetX$2();

    ContentPosition$offsetX$2() {
        super(5, ImageUtilsKt.class, "calcXTranslation", "calcXTranslation(FLandroid/graphics/RectF;Landroid/graphics/RectF;ZZ)F", 1);
    }

    public final Float invoke(float f, RectF p1, RectF p2, boolean z, boolean z2) {
        Intrinsics.checkNotNullParameter(p1, "p1");
        Intrinsics.checkNotNullParameter(p2, "p2");
        return Float.valueOf(ImageUtilsKt.calcXTranslation(f, p1, p2, z, z2));
    }

    @Override // kotlin.jvm.functions.Function5
    public /* bridge */ /* synthetic */ Float invoke(Float f, RectF rectF, RectF rectF2, Boolean bool, Boolean bool2) {
        return invoke(f.floatValue(), rectF, rectF2, bool.booleanValue(), bool2.booleanValue());
    }
}
