package expo.modules.image.enums;

import android.graphics.Matrix;
import android.graphics.RectF;
import expo.modules.kotlin.types.Enumerable;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: ContentFit.kt */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\b\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u00012\u00020\u0002B\u000f\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J-\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000eH\u0000¢\u0006\u0002\b\u0010R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015¨\u0006\u0016"}, d2 = {"Lexpo/modules/image/enums/ContentFit;", "", "Lexpo/modules/kotlin/types/Enumerable;", "value", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getValue", "()Ljava/lang/String;", "toMatrix", "Landroid/graphics/Matrix;", "imageRect", "Landroid/graphics/RectF;", "viewRect", "sourceWidth", "", "sourceHeight", "toMatrix$expo_image_release", "Contain", "Cover", "Fill", "None", "ScaleDown", "expo-image_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class ContentFit implements Enumerable {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ ContentFit[] $VALUES;
    public static final ContentFit Contain = new ContentFit("Contain", 0, "contain");
    public static final ContentFit Cover = new ContentFit("Cover", 1, "cover");
    public static final ContentFit Fill = new ContentFit("Fill", 2, "fill");
    public static final ContentFit None = new ContentFit("None", 3, "none");
    public static final ContentFit ScaleDown = new ContentFit("ScaleDown", 4, "scale-down");
    private final String value;

    /* compiled from: ContentFit.kt */
    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ContentFit.values().length];
            try {
                iArr[ContentFit.Contain.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[ContentFit.Cover.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[ContentFit.Fill.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[ContentFit.None.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[ContentFit.ScaleDown.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    private static final /* synthetic */ ContentFit[] $values() {
        return new ContentFit[]{Contain, Cover, Fill, None, ScaleDown};
    }

    public static EnumEntries<ContentFit> getEntries() {
        return $ENTRIES;
    }

    public static ContentFit valueOf(String str) {
        return (ContentFit) Enum.valueOf(ContentFit.class, str);
    }

    public static ContentFit[] values() {
        return (ContentFit[]) $VALUES.clone();
    }

    public final String getValue() {
        return this.value;
    }

    private ContentFit(String str, int i, String str2) {
        this.value = str2;
    }

    static {
        ContentFit[] $values = $values();
        $VALUES = $values;
        $ENTRIES = EnumEntriesKt.enumEntries($values);
    }

    public final Matrix toMatrix$expo_image_release(RectF imageRect, RectF viewRect, int sourceWidth, int sourceHeight) {
        Intrinsics.checkNotNullParameter(imageRect, "imageRect");
        Intrinsics.checkNotNullParameter(viewRect, "viewRect");
        Matrix matrix = new Matrix();
        int i = WhenMappings.$EnumSwitchMapping$0[ordinal()];
        if (i == 1) {
            matrix.setRectToRect(imageRect, viewRect, Matrix.ScaleToFit.START);
        } else if (i == 2) {
            float max = Math.max(viewRect.width() / imageRect.width(), viewRect.height() / imageRect.height());
            matrix.setScale(max, max);
        } else if (i == 3) {
            matrix.setRectToRect(imageRect, viewRect, Matrix.ScaleToFit.FILL);
        } else if (i == 5) {
            if (sourceWidth != -1 && sourceHeight != -1) {
                RectF rectF = new RectF(0.0f, 0.0f, sourceWidth, sourceHeight);
                if (rectF.width() >= viewRect.width() || rectF.height() >= viewRect.height()) {
                    matrix.setRectToRect(imageRect, viewRect, Matrix.ScaleToFit.START);
                } else {
                    matrix.setRectToRect(imageRect, rectF, Matrix.ScaleToFit.START);
                }
            } else if (imageRect.width() >= viewRect.width() || imageRect.height() >= viewRect.height()) {
                matrix.setRectToRect(imageRect, viewRect, Matrix.ScaleToFit.START);
            }
        }
        return matrix;
    }
}
