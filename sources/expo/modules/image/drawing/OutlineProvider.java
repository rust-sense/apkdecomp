package expo.modules.image.drawing;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Outline;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Build;
import android.view.View;
import android.view.ViewOutlineProvider;
import com.facebook.react.modules.i18nmanager.I18nUtil;
import com.facebook.react.uimanager.FloatUtil;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.yoga.YogaConstants;
import expo.modules.image.YogaUtilsKt;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.Intrinsics;
import org.apache.commons.codec.language.bm.Rule;

/* compiled from: OutlineProvider.kt */
@Metadata(d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0014\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001:\u0002./B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0016\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018J\u0018\u0010\u0019\u001a\u00020\u00142\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u001a\u001a\u00020\u001bH\u0016J\u0006\u0010\u001c\u001a\u00020\u000eJ\u0016\u0010\u001d\u001a\u00020\u000e2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u0012J\u0010\u0010!\u001a\u00020\u00142\u0006\u0010\u0017\u001a\u00020\u0018H\u0002J\b\u0010\"\u001a\u00020\u0014H\u0002J\b\u0010#\u001a\u00020\u0014H\u0002J@\u0010$\u001a\u00020\u00142\u0006\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020(2\u0006\u0010*\u001a\u00020(2\u0006\u0010+\u001a\u00020(2\u0006\u0010,\u001a\u00020\u000e2\u0006\u0010-\u001a\u00020\u000eH\u0002R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000¨\u00060"}, d2 = {"Lexpo/modules/image/drawing/OutlineProvider;", "Landroid/view/ViewOutlineProvider;", "mContext", "Landroid/content/Context;", "(Landroid/content/Context;)V", "borderRadiiConfig", "", "getBorderRadiiConfig", "()[F", "mBounds", "Landroid/graphics/RectF;", "mConvexPath", "Landroid/graphics/Path;", "mConvexPathInvalidated", "", "mCornerRadii", "mCornerRadiiInvalidated", "mLayoutDirection", "", "clipCanvasIfNeeded", "", "canvas", "Landroid/graphics/Canvas;", "view", "Landroid/view/View;", "getOutline", "outline", "Landroid/graphics/Outline;", "hasEqualCorners", "setBorderRadius", "radius", "", ViewProps.POSITION, "updateBoundsAndLayoutDirection", "updateConvexPathIfNeeded", "updateCornerRadiiIfNeeded", "updateCornerRadius", "outputPosition", "Lexpo/modules/image/drawing/OutlineProvider$CornerRadius;", "inputPosition", "Lexpo/modules/image/drawing/OutlineProvider$BorderRadiusConfig;", "oppositePosition", "startPosition", "endPosition", "isRTL", "isRTLSwap", "BorderRadiusConfig", "CornerRadius", "expo-image_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class OutlineProvider extends ViewOutlineProvider {
    private final float[] borderRadiiConfig;
    private final RectF mBounds;
    private final Context mContext;
    private final Path mConvexPath;
    private boolean mConvexPathInvalidated;
    private final float[] mCornerRadii;
    private boolean mCornerRadiiInvalidated;
    private int mLayoutDirection;

    public final float[] getBorderRadiiConfig() {
        return this.borderRadiiConfig;
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* compiled from: OutlineProvider.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u000b\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000b¨\u0006\f"}, d2 = {"Lexpo/modules/image/drawing/OutlineProvider$BorderRadiusConfig;", "", "(Ljava/lang/String;I)V", Rule.ALL, "TOP_LEFT", "TOP_RIGHT", "BOTTOM_RIGHT", "BOTTOM_LEFT", "TOP_START", "TOP_END", "BOTTOM_START", "BOTTOM_END", "expo-image_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class BorderRadiusConfig {
        private static final /* synthetic */ EnumEntries $ENTRIES;
        private static final /* synthetic */ BorderRadiusConfig[] $VALUES;
        public static final BorderRadiusConfig ALL = new BorderRadiusConfig(Rule.ALL, 0);
        public static final BorderRadiusConfig TOP_LEFT = new BorderRadiusConfig("TOP_LEFT", 1);
        public static final BorderRadiusConfig TOP_RIGHT = new BorderRadiusConfig("TOP_RIGHT", 2);
        public static final BorderRadiusConfig BOTTOM_RIGHT = new BorderRadiusConfig("BOTTOM_RIGHT", 3);
        public static final BorderRadiusConfig BOTTOM_LEFT = new BorderRadiusConfig("BOTTOM_LEFT", 4);
        public static final BorderRadiusConfig TOP_START = new BorderRadiusConfig("TOP_START", 5);
        public static final BorderRadiusConfig TOP_END = new BorderRadiusConfig("TOP_END", 6);
        public static final BorderRadiusConfig BOTTOM_START = new BorderRadiusConfig("BOTTOM_START", 7);
        public static final BorderRadiusConfig BOTTOM_END = new BorderRadiusConfig("BOTTOM_END", 8);

        private static final /* synthetic */ BorderRadiusConfig[] $values() {
            return new BorderRadiusConfig[]{ALL, TOP_LEFT, TOP_RIGHT, BOTTOM_RIGHT, BOTTOM_LEFT, TOP_START, TOP_END, BOTTOM_START, BOTTOM_END};
        }

        public static EnumEntries<BorderRadiusConfig> getEntries() {
            return $ENTRIES;
        }

        public static BorderRadiusConfig valueOf(String str) {
            return (BorderRadiusConfig) Enum.valueOf(BorderRadiusConfig.class, str);
        }

        public static BorderRadiusConfig[] values() {
            return (BorderRadiusConfig[]) $VALUES.clone();
        }

        private BorderRadiusConfig(String str, int i) {
        }

        static {
            BorderRadiusConfig[] $values = $values();
            $VALUES = $values;
            $ENTRIES = EnumEntriesKt.enumEntries($values);
        }
    }

    public OutlineProvider(Context mContext) {
        Intrinsics.checkNotNullParameter(mContext, "mContext");
        this.mContext = mContext;
        this.mBounds = new RectF();
        float[] fArr = new float[9];
        for (int i = 0; i < 9; i++) {
            fArr[i] = Float.NaN;
        }
        this.borderRadiiConfig = fArr;
        this.mCornerRadii = new float[4];
        this.mCornerRadiiInvalidated = true;
        this.mConvexPath = new Path();
        this.mConvexPathInvalidated = true;
        updateCornerRadiiIfNeeded();
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* compiled from: OutlineProvider.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Lexpo/modules/image/drawing/OutlineProvider$CornerRadius;", "", "(Ljava/lang/String;I)V", "TOP_LEFT", "TOP_RIGHT", "BOTTOM_RIGHT", "BOTTOM_LEFT", "expo-image_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class CornerRadius {
        private static final /* synthetic */ EnumEntries $ENTRIES;
        private static final /* synthetic */ CornerRadius[] $VALUES;
        public static final CornerRadius TOP_LEFT = new CornerRadius("TOP_LEFT", 0);
        public static final CornerRadius TOP_RIGHT = new CornerRadius("TOP_RIGHT", 1);
        public static final CornerRadius BOTTOM_RIGHT = new CornerRadius("BOTTOM_RIGHT", 2);
        public static final CornerRadius BOTTOM_LEFT = new CornerRadius("BOTTOM_LEFT", 3);

        private static final /* synthetic */ CornerRadius[] $values() {
            return new CornerRadius[]{TOP_LEFT, TOP_RIGHT, BOTTOM_RIGHT, BOTTOM_LEFT};
        }

        public static EnumEntries<CornerRadius> getEntries() {
            return $ENTRIES;
        }

        public static CornerRadius valueOf(String str) {
            return (CornerRadius) Enum.valueOf(CornerRadius.class, str);
        }

        public static CornerRadius[] values() {
            return (CornerRadius[]) $VALUES.clone();
        }

        private CornerRadius(String str, int i) {
        }

        static {
            CornerRadius[] $values = $values();
            $VALUES = $values;
            $ENTRIES = EnumEntriesKt.enumEntries($values);
        }
    }

    private final void updateCornerRadiiIfNeeded() {
        if (this.mCornerRadiiInvalidated) {
            boolean z = this.mLayoutDirection == 1;
            boolean doLeftAndRightSwapInRTL = I18nUtil.getInstance().doLeftAndRightSwapInRTL(this.mContext);
            boolean z2 = z;
            updateCornerRadius(CornerRadius.TOP_LEFT, BorderRadiusConfig.TOP_LEFT, BorderRadiusConfig.TOP_RIGHT, BorderRadiusConfig.TOP_START, BorderRadiusConfig.TOP_END, z2, doLeftAndRightSwapInRTL);
            updateCornerRadius(CornerRadius.TOP_RIGHT, BorderRadiusConfig.TOP_RIGHT, BorderRadiusConfig.TOP_LEFT, BorderRadiusConfig.TOP_END, BorderRadiusConfig.TOP_START, z2, doLeftAndRightSwapInRTL);
            updateCornerRadius(CornerRadius.BOTTOM_LEFT, BorderRadiusConfig.BOTTOM_LEFT, BorderRadiusConfig.BOTTOM_RIGHT, BorderRadiusConfig.BOTTOM_START, BorderRadiusConfig.BOTTOM_END, z2, doLeftAndRightSwapInRTL);
            updateCornerRadius(CornerRadius.BOTTOM_RIGHT, BorderRadiusConfig.BOTTOM_RIGHT, BorderRadiusConfig.BOTTOM_LEFT, BorderRadiusConfig.BOTTOM_END, BorderRadiusConfig.BOTTOM_START, z2, doLeftAndRightSwapInRTL);
            this.mCornerRadiiInvalidated = false;
            this.mConvexPathInvalidated = true;
        }
    }

    private final void updateCornerRadius(CornerRadius outputPosition, BorderRadiusConfig inputPosition, BorderRadiusConfig oppositePosition, BorderRadiusConfig startPosition, BorderRadiusConfig endPosition, boolean isRTL, boolean isRTLSwap) {
        float f = this.borderRadiiConfig[inputPosition.ordinal()];
        if (isRTL) {
            if (isRTLSwap) {
                f = this.borderRadiiConfig[oppositePosition.ordinal()];
            }
            if (YogaConstants.isUndefined(f)) {
                f = this.borderRadiiConfig[endPosition.ordinal()];
            }
        } else if (YogaConstants.isUndefined(f)) {
            f = this.borderRadiiConfig[startPosition.ordinal()];
        }
        this.mCornerRadii[outputPosition.ordinal()] = PixelUtil.toPixelFromDIP(YogaUtilsKt.ifYogaUndefinedUse(YogaUtilsKt.ifYogaUndefinedUse(f, this.borderRadiiConfig[BorderRadiusConfig.ALL.ordinal()]), 0.0f));
    }

    private final void updateConvexPathIfNeeded() {
        if (this.mConvexPathInvalidated) {
            this.mConvexPath.reset();
            this.mConvexPath.addRoundRect(this.mBounds, new float[]{this.mCornerRadii[CornerRadius.TOP_LEFT.ordinal()], this.mCornerRadii[CornerRadius.TOP_LEFT.ordinal()], this.mCornerRadii[CornerRadius.TOP_RIGHT.ordinal()], this.mCornerRadii[CornerRadius.TOP_RIGHT.ordinal()], this.mCornerRadii[CornerRadius.BOTTOM_RIGHT.ordinal()], this.mCornerRadii[CornerRadius.BOTTOM_RIGHT.ordinal()], this.mCornerRadii[CornerRadius.BOTTOM_LEFT.ordinal()], this.mCornerRadii[CornerRadius.BOTTOM_LEFT.ordinal()]}, Path.Direction.CW);
            this.mConvexPathInvalidated = false;
        }
    }

    public final boolean hasEqualCorners() {
        updateCornerRadiiIfNeeded();
        float[] fArr = this.mCornerRadii;
        float f = fArr[0];
        for (float f2 : fArr) {
            if (f != f2) {
                return false;
            }
        }
        return true;
    }

    public final boolean setBorderRadius(float radius, int position) {
        if (FloatUtil.floatsEqual(this.borderRadiiConfig[position], radius)) {
            return false;
        }
        this.borderRadiiConfig[position] = radius;
        this.mCornerRadiiInvalidated = true;
        return true;
    }

    private final void updateBoundsAndLayoutDirection(View view) {
        int layoutDirection = view.getLayoutDirection();
        if (this.mLayoutDirection != layoutDirection) {
            this.mLayoutDirection = layoutDirection;
            this.mCornerRadiiInvalidated = true;
        }
        int width = view.getWidth();
        int height = view.getHeight();
        float f = 0;
        if (this.mBounds.left == f && this.mBounds.top == f && this.mBounds.right == width && this.mBounds.bottom == height) {
            return;
        }
        this.mBounds.set(f, f, width, height);
        this.mCornerRadiiInvalidated = true;
    }

    @Override // android.view.ViewOutlineProvider
    public void getOutline(View view, Outline outline) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(outline, "outline");
        updateBoundsAndLayoutDirection(view);
        updateCornerRadiiIfNeeded();
        if (hasEqualCorners()) {
            float f = this.mCornerRadii[0];
            if (f > 0.0f) {
                outline.setRoundRect(0, 0, (int) this.mBounds.width(), (int) this.mBounds.height(), f);
                return;
            } else {
                outline.setRect(0, 0, (int) this.mBounds.width(), (int) this.mBounds.height());
                return;
            }
        }
        updateConvexPathIfNeeded();
        if (Build.VERSION.SDK_INT >= 30) {
            outline.setPath(this.mConvexPath);
        } else {
            outline.setConvexPath(this.mConvexPath);
        }
    }

    public final void clipCanvasIfNeeded(Canvas canvas, View view) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        Intrinsics.checkNotNullParameter(view, "view");
        updateBoundsAndLayoutDirection(view);
        updateCornerRadiiIfNeeded();
        if (hasEqualCorners()) {
            return;
        }
        updateConvexPathIfNeeded();
        canvas.clipPath(this.mConvexPath);
    }
}
