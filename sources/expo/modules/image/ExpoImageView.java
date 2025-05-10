package expo.modules.image;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PorterDuff;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.ViewParent;
import android.widget.ImageView;
import androidx.appcompat.widget.AppCompatImageView;
import com.bumptech.glide.RequestManager;
import com.facebook.react.modules.i18nmanager.I18nUtil;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.views.view.ReactViewBackgroundDrawable;
import com.facebook.yoga.YogaConstants;
import expo.modules.image.drawing.OutlineProvider;
import expo.modules.image.enums.ContentFit;
import expo.modules.image.records.ContentPosition;
import io.sentry.protocol.ViewHierarchyNode;
import java.util.ArrayList;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IndexedValue;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ExpoImageView.kt */
@Metadata(d1 = {"\u0000t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\u0007\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u0007\b\u0007\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010)\u001a\u00020*J?\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020,2\u0006\u0010\r\u001a\u00020\f2\b\b\u0002\u0010\u0013\u001a\u00020\u00122\n\b\u0002\u0010-\u001a\u0004\u0018\u00010.2\n\b\u0002\u0010/\u001a\u0004\u0018\u00010.H\u0002¢\u0006\u0002\u00100J\u0010\u00101\u001a\u00020*2\u0006\u00102\u001a\u000203H\u0016J\u0010\u00104\u001a\u00020*2\u0006\u0010+\u001a\u00020,H\u0016J\u0010\u00105\u001a\u00020*2\u0006\u00102\u001a\u000203H\u0016J0\u00106\u001a\u00020*2\u0006\u00107\u001a\u00020\u001f2\u0006\u00108\u001a\u00020.2\u0006\u00109\u001a\u00020.2\u0006\u0010:\u001a\u00020.2\u0006\u0010;\u001a\u00020.H\u0014J\b\u0010<\u001a\u0004\u0018\u00010\u0019J\u0019\u0010=\u001a\u00020*2\b\u0010>\u001a\u0004\u0018\u00010.H\u0000¢\u0006\u0004\b?\u0010@J%\u0010A\u001a\u00020*2\u0006\u0010B\u001a\u00020.2\u0006\u0010C\u001a\u00020D2\u0006\u0010E\u001a\u00020DH\u0000¢\u0006\u0002\bFJ\u001d\u0010G\u001a\u00020*2\u0006\u0010B\u001a\u00020.2\u0006\u0010H\u001a\u00020DH\u0000¢\u0006\u0002\bIJ\u0017\u0010J\u001a\u00020*2\b\u0010K\u001a\u0004\u0018\u00010LH\u0000¢\u0006\u0002\bMJ\u001d\u0010N\u001a\u00020*2\u0006\u0010B\u001a\u00020.2\u0006\u0010O\u001a\u00020DH\u0000¢\u0006\u0002\bPJ\u0019\u0010Q\u001a\u00020*2\b\u0010>\u001a\u0004\u0018\u00010.H\u0000¢\u0006\u0004\bR\u0010@R\u0014\u0010\u0005\u001a\u00020\u00068BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00060\nX\u0082\u0004¢\u0006\u0002\n\u0000R$\u0010\r\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\f@@X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R$\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u000b\u001a\u00020\u0012@@X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u001c\u0010\u0018\u001a\u0004\u0018\u00010\u0019X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u001a\u0010\u001e\u001a\u00020\u001fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010 \"\u0004\b!\u0010\"R\u000e\u0010#\u001a\u00020$X\u0082\u0004¢\u0006\u0002\n\u0000R$\u0010%\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\f@@X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010\u000f\"\u0004\b'\u0010\u0011R\u000e\u0010(\u001a\u00020\u001fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006S"}, d2 = {"Lexpo/modules/image/ExpoImageView;", "Landroidx/appcompat/widget/AppCompatImageView;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "borderDrawable", "Lcom/facebook/react/views/view/ReactViewBackgroundDrawable;", "getBorderDrawable", "()Lcom/facebook/react/views/view/ReactViewBackgroundDrawable;", "borderDrawableLazyHolder", "Lkotlin/Lazy;", "value", "Lexpo/modules/image/enums/ContentFit;", "contentFit", "getContentFit$expo_image_release", "()Lexpo/modules/image/enums/ContentFit;", "setContentFit$expo_image_release", "(Lexpo/modules/image/enums/ContentFit;)V", "Lexpo/modules/image/records/ContentPosition;", "contentPosition", "getContentPosition$expo_image_release", "()Lexpo/modules/image/records/ContentPosition;", "setContentPosition$expo_image_release", "(Lexpo/modules/image/records/ContentPosition;)V", "currentTarget", "Lexpo/modules/image/ImageViewWrapperTarget;", "getCurrentTarget", "()Lexpo/modules/image/ImageViewWrapperTarget;", "setCurrentTarget", "(Lexpo/modules/image/ImageViewWrapperTarget;)V", "isPlaceholder", "", "()Z", "setPlaceholder", "(Z)V", "outlineProvider", "Lexpo/modules/image/drawing/OutlineProvider;", "placeholderContentFit", "getPlaceholderContentFit$expo_image_release", "setPlaceholderContentFit$expo_image_release", "transformationMatrixChanged", "applyTransformationMatrix", "", "drawable", "Landroid/graphics/drawable/Drawable;", "sourceWidth", "", "sourceHeight", "(Landroid/graphics/drawable/Drawable;Lexpo/modules/image/enums/ContentFit;Lexpo/modules/image/records/ContentPosition;Ljava/lang/Integer;Ljava/lang/Integer;)V", "draw", "canvas", "Landroid/graphics/Canvas;", "invalidateDrawable", "onDraw", ViewProps.ON_LAYOUT, "changed", ViewProps.LEFT, ViewProps.TOP, ViewProps.RIGHT, ViewProps.BOTTOM, "recycleView", "setBackgroundColor", ViewProps.COLOR, "setBackgroundColor$expo_image_release", "(Ljava/lang/Integer;)V", "setBorderColor", ViewProps.POSITION, "rgb", "", ViewHierarchyNode.JsonKeys.ALPHA, "setBorderColor$expo_image_release", "setBorderRadius", "borderRadius", "setBorderRadius$expo_image_release", "setBorderStyle", "style", "", "setBorderStyle$expo_image_release", "setBorderWidth", "width", "setBorderWidth$expo_image_release", "setTintColor", "setTintColor$expo_image_release", "expo-image_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class ExpoImageView extends AppCompatImageView {
    private final Lazy<ReactViewBackgroundDrawable> borderDrawableLazyHolder;
    private ContentFit contentFit;
    private ContentPosition contentPosition;
    private ImageViewWrapperTarget currentTarget;
    private boolean isPlaceholder;
    private final OutlineProvider outlineProvider;
    private ContentFit placeholderContentFit;
    private boolean transformationMatrixChanged;

    /* renamed from: getContentFit$expo_image_release, reason: from getter */
    public final ContentFit getContentFit() {
        return this.contentFit;
    }

    /* renamed from: getContentPosition$expo_image_release, reason: from getter */
    public final ContentPosition getContentPosition() {
        return this.contentPosition;
    }

    public final ImageViewWrapperTarget getCurrentTarget() {
        return this.currentTarget;
    }

    /* renamed from: getPlaceholderContentFit$expo_image_release, reason: from getter */
    public final ContentFit getPlaceholderContentFit() {
        return this.placeholderContentFit;
    }

    /* renamed from: isPlaceholder, reason: from getter */
    public final boolean getIsPlaceholder() {
        return this.isPlaceholder;
    }

    public final void setContentFit$expo_image_release(ContentFit value) {
        Intrinsics.checkNotNullParameter(value, "value");
        this.contentFit = value;
        this.transformationMatrixChanged = true;
    }

    public final void setContentPosition$expo_image_release(ContentPosition value) {
        Intrinsics.checkNotNullParameter(value, "value");
        this.contentPosition = value;
        this.transformationMatrixChanged = true;
    }

    public final void setCurrentTarget(ImageViewWrapperTarget imageViewWrapperTarget) {
        this.currentTarget = imageViewWrapperTarget;
    }

    public final void setPlaceholder(boolean z) {
        this.isPlaceholder = z;
    }

    public final void setPlaceholderContentFit$expo_image_release(ContentFit value) {
        Intrinsics.checkNotNullParameter(value, "value");
        this.placeholderContentFit = value;
        this.transformationMatrixChanged = true;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ExpoImageView(final Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        OutlineProvider outlineProvider = new OutlineProvider(context);
        this.outlineProvider = outlineProvider;
        this.borderDrawableLazyHolder = LazyKt.lazy(new Function0<ReactViewBackgroundDrawable>() { // from class: expo.modules.image.ExpoImageView$borderDrawableLazyHolder$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ReactViewBackgroundDrawable invoke() {
                OutlineProvider outlineProvider2;
                ReactViewBackgroundDrawable reactViewBackgroundDrawable = new ReactViewBackgroundDrawable(context);
                ExpoImageView expoImageView = this;
                reactViewBackgroundDrawable.setCallback(expoImageView);
                outlineProvider2 = expoImageView.outlineProvider;
                float[] borderRadiiConfig = outlineProvider2.getBorderRadiiConfig();
                ArrayList arrayList = new ArrayList(borderRadiiConfig.length);
                for (float f : borderRadiiConfig) {
                    if (!YogaConstants.isUndefined(f)) {
                        f = PixelUtil.toPixelFromDIP(f);
                    }
                    arrayList.add(Float.valueOf(f));
                }
                for (IndexedValue indexedValue : CollectionsKt.withIndex(arrayList)) {
                    int index = indexedValue.getIndex();
                    float floatValue = ((Number) indexedValue.component2()).floatValue();
                    if (index == 0) {
                        reactViewBackgroundDrawable.setRadius(floatValue);
                    } else {
                        reactViewBackgroundDrawable.setRadius(floatValue, index - 1);
                    }
                }
                return reactViewBackgroundDrawable;
            }
        });
        setClipToOutline(true);
        setScaleType(ImageView.ScaleType.MATRIX);
        super.setOutlineProvider(outlineProvider);
        this.contentFit = ContentFit.Cover;
        this.placeholderContentFit = ContentFit.ScaleDown;
        this.contentPosition = ContentPosition.INSTANCE.getCenter();
    }

    public final ImageViewWrapperTarget recycleView() {
        setImageDrawable(null);
        ImageViewWrapperTarget imageViewWrapperTarget = this.currentTarget;
        if (imageViewWrapperTarget != null) {
            imageViewWrapperTarget.setUsed(false);
        } else {
            imageViewWrapperTarget = null;
        }
        this.currentTarget = null;
        setVisibility(8);
        this.isPlaceholder = false;
        return imageViewWrapperTarget;
    }

    @Override // android.view.View
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        applyTransformationMatrix();
    }

    public final void applyTransformationMatrix() {
        if (getDrawable() == null) {
            return;
        }
        if (this.isPlaceholder) {
            Drawable drawable = getDrawable();
            ContentFit contentFit = this.placeholderContentFit;
            ImageViewWrapperTarget imageViewWrapperTarget = this.currentTarget;
            Integer valueOf = imageViewWrapperTarget != null ? Integer.valueOf(imageViewWrapperTarget.getPlaceholderHeight()) : null;
            ImageViewWrapperTarget imageViewWrapperTarget2 = this.currentTarget;
            Integer valueOf2 = imageViewWrapperTarget2 != null ? Integer.valueOf(imageViewWrapperTarget2.getPlaceholderWidth()) : null;
            Intrinsics.checkNotNull(drawable);
            applyTransformationMatrix$default(this, drawable, contentFit, null, valueOf2, valueOf, 4, null);
            return;
        }
        Drawable drawable2 = getDrawable();
        Intrinsics.checkNotNullExpressionValue(drawable2, "getDrawable(...)");
        applyTransformationMatrix$default(this, drawable2, this.contentFit, this.contentPosition, null, null, 24, null);
    }

    static /* synthetic */ void applyTransformationMatrix$default(ExpoImageView expoImageView, Drawable drawable, ContentFit contentFit, ContentPosition contentPosition, Integer num, Integer num2, int i, Object obj) {
        if ((i & 4) != 0) {
            contentPosition = ContentPosition.INSTANCE.getCenter();
        }
        ContentPosition contentPosition2 = contentPosition;
        if ((i & 8) != 0) {
            ImageViewWrapperTarget imageViewWrapperTarget = expoImageView.currentTarget;
            num = imageViewWrapperTarget != null ? Integer.valueOf(imageViewWrapperTarget.getSourceWidth()) : null;
        }
        Integer num3 = num;
        if ((i & 16) != 0) {
            ImageViewWrapperTarget imageViewWrapperTarget2 = expoImageView.currentTarget;
            num2 = imageViewWrapperTarget2 != null ? Integer.valueOf(imageViewWrapperTarget2.getSourceHeight()) : null;
        }
        expoImageView.applyTransformationMatrix(drawable, contentFit, contentPosition2, num3, num2);
    }

    private final void applyTransformationMatrix(Drawable drawable, ContentFit contentFit, ContentPosition contentPosition, Integer sourceWidth, Integer sourceHeight) {
        RectF rectF = new RectF(0.0f, 0.0f, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        RectF rectF2 = new RectF(0.0f, 0.0f, getWidth(), getHeight());
        Matrix matrix$expo_image_release = contentFit.toMatrix$expo_image_release(rectF, rectF2, sourceWidth != null ? sourceWidth.intValue() : -1, sourceHeight != null ? sourceHeight.intValue() : -1);
        matrix$expo_image_release.mapRect(rectF);
        contentPosition.apply$expo_image_release(matrix$expo_image_release, rectF, rectF2);
        setImageMatrix(matrix$expo_image_release);
    }

    private final ReactViewBackgroundDrawable getBorderDrawable() {
        return this.borderDrawableLazyHolder.getValue();
    }

    public final void setBorderRadius$expo_image_release(int position, float borderRadius) {
        if (this.outlineProvider.setBorderRadius(borderRadius, position)) {
            invalidateOutline();
            if (!this.outlineProvider.hasEqualCorners()) {
                invalidate();
            }
        }
        if (this.borderDrawableLazyHolder.isInitialized()) {
            if (!YogaConstants.isUndefined(borderRadius)) {
                borderRadius = PixelUtil.toPixelFromDIP(borderRadius);
            }
            ReactViewBackgroundDrawable value = this.borderDrawableLazyHolder.getValue();
            if (position == 0) {
                value.setRadius(borderRadius);
            } else {
                value.setRadius(borderRadius, position - 1);
            }
        }
    }

    public final void setBorderWidth$expo_image_release(int position, float width) {
        getBorderDrawable().setBorderWidth(position, width);
    }

    public final void setBorderColor$expo_image_release(int position, float rgb, float alpha) {
        getBorderDrawable().setBorderColor(position, rgb, alpha);
    }

    public final void setBorderStyle$expo_image_release(String style) {
        getBorderDrawable().setBorderStyle(style);
    }

    public final void setBackgroundColor$expo_image_release(Integer color) {
        if (color == null) {
            setBackgroundColor(0);
        } else {
            setBackgroundColor(color.intValue());
        }
    }

    public final void setTintColor$expo_image_release(Integer color) {
        Unit unit;
        if (color != null) {
            setColorFilter(color.intValue(), PorterDuff.Mode.SRC_IN);
            unit = Unit.INSTANCE;
        } else {
            unit = null;
        }
        if (unit == null) {
            clearColorFilter();
        }
    }

    @Override // android.widget.ImageView, android.view.View, android.graphics.drawable.Drawable.Callback
    public void invalidateDrawable(Drawable drawable) {
        Intrinsics.checkNotNullParameter(drawable, "drawable");
        super.invalidateDrawable(drawable);
        if (this.borderDrawableLazyHolder.isInitialized() && drawable == getBorderDrawable()) {
            invalidate();
        }
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        Bitmap bitmap;
        RequestManager requestManager;
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        this.outlineProvider.clipCanvasIfNeeded(canvas, this);
        Drawable drawable = getDrawable();
        BitmapDrawable bitmapDrawable = drawable instanceof BitmapDrawable ? (BitmapDrawable) drawable : null;
        if (bitmapDrawable != null && (bitmap = bitmapDrawable.getBitmap()) != null && bitmap.isRecycled()) {
            Log.e("ExpoImage", "Trying to use a recycled bitmap");
            ImageViewWrapperTarget recycleView = recycleView();
            if (recycleView != null) {
                ViewParent parent = getParent();
                ExpoImageViewWrapper expoImageViewWrapper = parent instanceof ExpoImageViewWrapper ? (ExpoImageViewWrapper) parent : null;
                if (expoImageViewWrapper != null && (requestManager = expoImageViewWrapper.getRequestManager()) != null) {
                    recycleView.clear(requestManager);
                }
            }
        }
        super.draw(canvas);
    }

    @Override // android.widget.ImageView, android.view.View
    public void onDraw(Canvas canvas) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        super.onDraw(canvas);
        if (this.borderDrawableLazyHolder.isInitialized()) {
            boolean isRTL = I18nUtil.getInstance().isRTL(getContext());
            ReactViewBackgroundDrawable borderDrawable = getBorderDrawable();
            borderDrawable.setResolvedLayoutDirection(isRTL ? 1 : 0);
            borderDrawable.setBounds(0, 0, getWidth(), getHeight());
            borderDrawable.draw(canvas);
        }
    }
}
