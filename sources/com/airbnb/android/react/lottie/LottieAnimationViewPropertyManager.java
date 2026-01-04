package com.airbnb.android.react.lottie;

import android.graphics.Typeface;
import android.net.Uri;
import android.widget.ImageView;
import androidx.core.app.NotificationCompat;
import com.airbnb.lottie.FontAssetDelegate;
import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieProperty;
import com.airbnb.lottie.RenderMode;
import com.airbnb.lottie.SimpleColorFilter;
import com.airbnb.lottie.TextDelegate;
import com.airbnb.lottie.model.KeyPath;
import com.airbnb.lottie.value.LottieValueCallback;
import com.facebook.react.bridge.ColorPropConverter;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.util.RNLog;
import com.facebook.react.views.text.ReactFontManager;
import java.io.File;
import java.io.FileInputStream;
import java.lang.ref.WeakReference;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;
import java.util.regex.Pattern;
import java.util.zip.ZipInputStream;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;

/* compiled from: LottieAnimationViewPropertyManager.kt */
@Metadata(d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u0007\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010N\u001a\u00020OJ\u0018\u0010P\u001a\u00020O2\u0006\u0010Q\u001a\u00020R2\u0006\u0010\u0002\u001a\u00020\u0003H\u0002R\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR(\u0010\f\u001a\u0004\u0018\u00010\u00062\b\u0010\u000b\u001a\u0004\u0018\u00010\u0006@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\b\"\u0004\b\u000e\u0010\nR\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u0011\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\b\"\u0004\b\u0013\u0010\nR\u001e\u0010\u0014\u001a\u0004\u0018\u00010\u0010X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0019\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u001c\u0010\u001a\u001a\u0004\u0018\u00010\u001bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\u001e\u0010 \u001a\u0004\u0018\u00010\u0010X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0019\u001a\u0004\b!\u0010\u0016\"\u0004\b\"\u0010\u0018R\u001c\u0010#\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010\b\"\u0004\b%\u0010\nR\u001e\u0010&\u001a\u0004\u0018\u00010'X\u0086\u000e¢\u0006\u0010\n\u0002\u0010,\u001a\u0004\b(\u0010)\"\u0004\b*\u0010+R\u001e\u0010-\u001a\u0004\u0018\u00010\u0010X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0019\u001a\u0004\b.\u0010\u0016\"\u0004\b/\u0010\u0018R\u001e\u00100\u001a\u0004\u0018\u000101X\u0086\u000e¢\u0006\u0010\n\u0002\u00106\u001a\u0004\b2\u00103\"\u0004\b4\u00105R\u001c\u00107\u001a\u0004\u0018\u000108X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b9\u0010:\"\u0004\b;\u0010<R\u001c\u0010=\u001a\u0004\u0018\u00010>X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b?\u0010@\"\u0004\bA\u0010BR\u001c\u0010C\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bD\u0010\b\"\u0004\bE\u0010\nR\u001e\u0010F\u001a\u0004\u0018\u000101X\u0086\u000e¢\u0006\u0010\n\u0002\u00106\u001a\u0004\bG\u00103\"\u0004\bH\u00105R\u001c\u0010I\u001a\u0004\u0018\u00010\u001bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bJ\u0010\u001d\"\u0004\bK\u0010\u001fR\u0014\u0010L\u001a\b\u0012\u0004\u0012\u00020\u00030MX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006S"}, d2 = {"Lcom/airbnb/android/react/lottie/LottieAnimationViewPropertyManager;", "", "view", "Lcom/airbnb/lottie/LottieAnimationView;", "(Lcom/airbnb/lottie/LottieAnimationView;)V", "animationJson", "", "getAnimationJson", "()Ljava/lang/String;", "setAnimationJson", "(Ljava/lang/String;)V", "value", "animationName", "getAnimationName", "setAnimationName", "animationNameDirty", "", "animationURL", "getAnimationURL", "setAnimationURL", "autoPlay", "getAutoPlay", "()Ljava/lang/Boolean;", "setAutoPlay", "(Ljava/lang/Boolean;)V", "Ljava/lang/Boolean;", "colorFilters", "Lcom/facebook/react/bridge/ReadableArray;", "getColorFilters", "()Lcom/facebook/react/bridge/ReadableArray;", "setColorFilters", "(Lcom/facebook/react/bridge/ReadableArray;)V", "enableMergePaths", "getEnableMergePaths", "setEnableMergePaths", "imageAssetsFolder", "getImageAssetsFolder", "setImageAssetsFolder", "layerType", "", "getLayerType", "()Ljava/lang/Integer;", "setLayerType", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "loop", "getLoop", "setLoop", NotificationCompat.CATEGORY_PROGRESS, "", "getProgress", "()Ljava/lang/Float;", "setProgress", "(Ljava/lang/Float;)V", "Ljava/lang/Float;", "renderMode", "Lcom/airbnb/lottie/RenderMode;", "getRenderMode", "()Lcom/airbnb/lottie/RenderMode;", "setRenderMode", "(Lcom/airbnb/lottie/RenderMode;)V", "scaleType", "Landroid/widget/ImageView$ScaleType;", "getScaleType", "()Landroid/widget/ImageView$ScaleType;", "setScaleType", "(Landroid/widget/ImageView$ScaleType;)V", "sourceDotLottie", "getSourceDotLottie", "setSourceDotLottie", "speed", "getSpeed", "setSpeed", "textFilters", "getTextFilters", "setTextFilters", "viewWeakReference", "Ljava/lang/ref/WeakReference;", "commitChanges", "", "parseColorFilter", "colorFilter", "Lcom/facebook/react/bridge/ReadableMap;", "lottie-react-native_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes.dex */
public final class LottieAnimationViewPropertyManager {
    private String animationJson;
    private String animationName;
    private boolean animationNameDirty;
    private String animationURL;
    private Boolean autoPlay;
    private ReadableArray colorFilters;
    private Boolean enableMergePaths;
    private String imageAssetsFolder;
    private Integer layerType;
    private Boolean loop;
    private Float progress;
    private RenderMode renderMode;
    private ImageView.ScaleType scaleType;
    private String sourceDotLottie;
    private Float speed;
    private ReadableArray textFilters;
    private final WeakReference<LottieAnimationView> viewWeakReference;

    public final String getAnimationJson() {
        return this.animationJson;
    }

    public final String getAnimationName() {
        return this.animationName;
    }

    public final String getAnimationURL() {
        return this.animationURL;
    }

    public final Boolean getAutoPlay() {
        return this.autoPlay;
    }

    public final ReadableArray getColorFilters() {
        return this.colorFilters;
    }

    public final Boolean getEnableMergePaths() {
        return this.enableMergePaths;
    }

    public final String getImageAssetsFolder() {
        return this.imageAssetsFolder;
    }

    public final Integer getLayerType() {
        return this.layerType;
    }

    public final Boolean getLoop() {
        return this.loop;
    }

    public final Float getProgress() {
        return this.progress;
    }

    public final RenderMode getRenderMode() {
        return this.renderMode;
    }

    public final ImageView.ScaleType getScaleType() {
        return this.scaleType;
    }

    public final String getSourceDotLottie() {
        return this.sourceDotLottie;
    }

    public final Float getSpeed() {
        return this.speed;
    }

    public final ReadableArray getTextFilters() {
        return this.textFilters;
    }

    public final void setAnimationJson(String str) {
        this.animationJson = str;
    }

    public final void setAnimationName(String str) {
        this.animationName = str;
        this.animationNameDirty = true;
    }

    public final void setAnimationURL(String str) {
        this.animationURL = str;
    }

    public final void setAutoPlay(Boolean bool) {
        this.autoPlay = bool;
    }

    public final void setColorFilters(ReadableArray readableArray) {
        this.colorFilters = readableArray;
    }

    public final void setEnableMergePaths(Boolean bool) {
        this.enableMergePaths = bool;
    }

    public final void setImageAssetsFolder(String str) {
        this.imageAssetsFolder = str;
    }

    public final void setLayerType(Integer num) {
        this.layerType = num;
    }

    public final void setLoop(Boolean bool) {
        this.loop = bool;
    }

    public final void setProgress(Float f) {
        this.progress = f;
    }

    public final void setRenderMode(RenderMode renderMode) {
        this.renderMode = renderMode;
    }

    public final void setScaleType(ImageView.ScaleType scaleType) {
        this.scaleType = scaleType;
    }

    public final void setSourceDotLottie(String str) {
        this.sourceDotLottie = str;
    }

    public final void setSpeed(Float f) {
        this.speed = f;
    }

    public final void setTextFilters(ReadableArray readableArray) {
        this.textFilters = readableArray;
    }

    public LottieAnimationViewPropertyManager(final LottieAnimationView view) {
        Intrinsics.checkNotNullParameter(view, "view");
        this.viewWeakReference = new WeakReference<>(view);
        view.setFontAssetDelegate(new FontAssetDelegate() { // from class: com.airbnb.android.react.lottie.LottieAnimationViewPropertyManager.1
            @Override // com.airbnb.lottie.FontAssetDelegate
            public Typeface fetchFont(String fontFamily) {
                Intrinsics.checkNotNullParameter(fontFamily, "fontFamily");
                Typeface typeface = ReactFontManager.getInstance().getTypeface(fontFamily, -1, -1, LottieAnimationView.this.getContext().getAssets());
                Intrinsics.checkNotNullExpressionValue(typeface, "getTypeface(...)");
                return typeface;
            }

            /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
            /* JADX WARN: Code restructure failed: missing block: B:19:0x004d, code lost:
            
                if (r4.equals("Regular") == false) goto L31;
             */
            /* JADX WARN: Code restructure failed: missing block: B:20:0x0059, code lost:
            
                r3 = 400;
             */
            /* JADX WARN: Code restructure failed: missing block: B:22:0x0056, code lost:
            
                if (r4.equals("Normal") == false) goto L31;
             */
            @Override // com.airbnb.lottie.FontAssetDelegate
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public android.graphics.Typeface fetchFont(java.lang.String r3, java.lang.String r4, java.lang.String r5) {
                /*
                    r2 = this;
                    java.lang.String r0 = "fontFamily"
                    kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r0)
                    java.lang.String r3 = "fontStyle"
                    kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r3)
                    java.lang.String r3 = "fontName"
                    kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r3)
                    int r3 = r4.hashCode()
                    r0 = -1
                    switch(r3) {
                        case -1994163307: goto L5c;
                        case -1955878649: goto L50;
                        case -1543850116: goto L47;
                        case 2076325: goto L3b;
                        case 2605753: goto L30;
                        case 64266207: goto L24;
                        case 73417974: goto L18;
                        default: goto L17;
                    }
                L17:
                    goto L68
                L18:
                    java.lang.String r3 = "Light"
                    boolean r3 = r4.equals(r3)
                    if (r3 != 0) goto L21
                    goto L68
                L21:
                    r3 = 200(0xc8, float:2.8E-43)
                    goto L69
                L24:
                    java.lang.String r3 = "Black"
                    boolean r3 = r4.equals(r3)
                    if (r3 != 0) goto L2d
                    goto L68
                L2d:
                    r3 = 900(0x384, float:1.261E-42)
                    goto L69
                L30:
                    java.lang.String r3 = "Thin"
                    boolean r3 = r4.equals(r3)
                    if (r3 == 0) goto L68
                    r3 = 100
                    goto L69
                L3b:
                    java.lang.String r3 = "Bold"
                    boolean r3 = r4.equals(r3)
                    if (r3 != 0) goto L44
                    goto L68
                L44:
                    r3 = 700(0x2bc, float:9.81E-43)
                    goto L69
                L47:
                    java.lang.String r3 = "Regular"
                    boolean r3 = r4.equals(r3)
                    if (r3 != 0) goto L59
                    goto L68
                L50:
                    java.lang.String r3 = "Normal"
                    boolean r3 = r4.equals(r3)
                    if (r3 != 0) goto L59
                    goto L68
                L59:
                    r3 = 400(0x190, float:5.6E-43)
                    goto L69
                L5c:
                    java.lang.String r3 = "Medium"
                    boolean r3 = r4.equals(r3)
                    if (r3 != 0) goto L65
                    goto L68
                L65:
                    r3 = 500(0x1f4, float:7.0E-43)
                    goto L69
                L68:
                    r3 = r0
                L69:
                    com.facebook.react.views.text.ReactFontManager r4 = com.facebook.react.views.text.ReactFontManager.getInstance()
                    com.airbnb.lottie.LottieAnimationView r1 = com.airbnb.lottie.LottieAnimationView.this
                    android.content.Context r1 = r1.getContext()
                    android.content.res.AssetManager r1 = r1.getAssets()
                    android.graphics.Typeface r3 = r4.getTypeface(r5, r0, r3, r1)
                    java.lang.String r4 = "getTypeface(...)"
                    kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r3, r4)
                    return r3
                */
                throw new UnsupportedOperationException("Method not decompiled: com.airbnb.android.react.lottie.LottieAnimationViewPropertyManager.AnonymousClass1.fetchFont(java.lang.String, java.lang.String, java.lang.String):android.graphics.Typeface");
            }
        });
    }

    public final void commitChanges() {
        Object m868constructorimpl;
        LottieAnimationView lottieAnimationView = this.viewWeakReference.get();
        if (lottieAnimationView == null) {
            return;
        }
        ReadableArray readableArray = this.textFilters;
        if (readableArray != null && readableArray.size() > 0) {
            TextDelegate textDelegate = new TextDelegate(lottieAnimationView);
            ReadableArray readableArray2 = this.textFilters;
            Intrinsics.checkNotNull(readableArray2);
            int size = readableArray2.size();
            for (int i = 0; i < size; i++) {
                ReadableArray readableArray3 = this.textFilters;
                Intrinsics.checkNotNull(readableArray3);
                ReadableMap map = readableArray3.getMap(i);
                Intrinsics.checkNotNullExpressionValue(map, "getMap(...)");
                textDelegate.setText(map.getString("find"), map.getString("replace"));
            }
            lottieAnimationView.setTextDelegate(textDelegate);
        }
        String str = this.animationJson;
        if (str != null) {
            lottieAnimationView.setAnimationFromJson(str, String.valueOf(str.hashCode()));
            this.animationJson = null;
        }
        String str2 = this.animationURL;
        if (str2 != null) {
            File file = new File(str2);
            if (file.exists()) {
                lottieAnimationView.setAnimation(new FileInputStream(file), String.valueOf(str2.hashCode()));
            } else {
                lottieAnimationView.setAnimationFromUrl(str2, String.valueOf(str2.hashCode()));
            }
            this.animationURL = null;
        }
        String str3 = this.sourceDotLottie;
        if (str3 != null) {
            File file2 = new File(str3);
            if (file2.exists()) {
                lottieAnimationView.setAnimation(new ZipInputStream(new FileInputStream(file2)), String.valueOf(str3.hashCode()));
                this.sourceDotLottie = null;
                return;
            }
            try {
                Result.Companion companion = Result.INSTANCE;
                LottieAnimationViewPropertyManager lottieAnimationViewPropertyManager = this;
                m868constructorimpl = Result.m868constructorimpl(Uri.parse(str3).getScheme());
            } catch (Throwable th) {
                Result.Companion companion2 = Result.INSTANCE;
                m868constructorimpl = Result.m868constructorimpl(ResultKt.createFailure(th));
            }
            if (Result.m874isFailureimpl(m868constructorimpl)) {
                m868constructorimpl = null;
            }
            if (((String) m868constructorimpl) != null) {
                lottieAnimationView.setAnimationFromUrl(str3);
                this.sourceDotLottie = null;
                return;
            }
            int identifier = lottieAnimationView.getResources().getIdentifier(str3, "raw", lottieAnimationView.getContext().getPackageName());
            if (identifier == 0) {
                RNLog.e("Animation for " + str3 + " was not found in raw resources");
                return;
            }
            lottieAnimationView.setAnimation(identifier);
            this.animationNameDirty = false;
            this.sourceDotLottie = null;
        }
        if (this.animationNameDirty) {
            lottieAnimationView.setAnimation(this.animationName);
            this.animationNameDirty = false;
        }
        Float f = this.progress;
        if (f != null) {
            lottieAnimationView.setProgress(f.floatValue());
            this.progress = null;
        }
        Boolean bool = this.loop;
        if (bool != null) {
            lottieAnimationView.setRepeatCount(bool.booleanValue() ? -1 : 0);
            this.loop = null;
        }
        Boolean bool2 = this.autoPlay;
        if (bool2 != null && bool2.booleanValue() && !lottieAnimationView.isAnimating()) {
            lottieAnimationView.playAnimation();
        }
        Float f2 = this.speed;
        if (f2 != null) {
            lottieAnimationView.setSpeed(f2.floatValue());
            this.speed = null;
        }
        ImageView.ScaleType scaleType = this.scaleType;
        if (scaleType != null) {
            lottieAnimationView.setScaleType(scaleType);
            this.scaleType = null;
        }
        RenderMode renderMode = this.renderMode;
        if (renderMode != null) {
            lottieAnimationView.setRenderMode(renderMode);
            this.renderMode = null;
        }
        Integer num = this.layerType;
        if (num != null) {
            lottieAnimationView.setLayerType(num.intValue(), null);
        }
        String str4 = this.imageAssetsFolder;
        if (str4 != null) {
            lottieAnimationView.setImageAssetsFolder(str4);
            this.imageAssetsFolder = null;
        }
        Boolean bool3 = this.enableMergePaths;
        if (bool3 != null) {
            lottieAnimationView.enableMergePathsForKitKatAndAbove(bool3.booleanValue());
            this.enableMergePaths = null;
        }
        ReadableArray readableArray4 = this.colorFilters;
        if (readableArray4 == null || readableArray4.size() <= 0) {
            return;
        }
        int size2 = readableArray4.size();
        for (int i2 = 0; i2 < size2; i2++) {
            ReadableMap map2 = readableArray4.getMap(i2);
            Intrinsics.checkNotNullExpressionValue(map2, "getMap(...)");
            parseColorFilter(map2, lottieAnimationView);
        }
    }

    private final void parseColorFilter(ReadableMap colorFilter, LottieAnimationView view) {
        int i;
        List emptyList;
        if (colorFilter.getType(ViewProps.COLOR) == ReadableType.Map) {
            Integer color = ColorPropConverter.getColor(colorFilter.getMap(ViewProps.COLOR), view.getContext());
            Intrinsics.checkNotNull(color);
            i = color.intValue();
        } else {
            i = colorFilter.getInt(ViewProps.COLOR);
        }
        String str = colorFilter.getString("keypath") + ".**";
        String quote = Pattern.quote(".");
        Intrinsics.checkNotNullExpressionValue(quote, "quote(...)");
        List<String> split = new Regex(quote).split(str, 0);
        if (!split.isEmpty()) {
            ListIterator<String> listIterator = split.listIterator(split.size());
            while (listIterator.hasPrevious()) {
                if (listIterator.previous().length() != 0) {
                    emptyList = CollectionsKt.take(split, listIterator.nextIndex() + 1);
                    break;
                }
            }
        }
        emptyList = CollectionsKt.emptyList();
        String[] strArr = (String[]) emptyList.toArray(new String[0]);
        view.addValueCallback(new KeyPath((String[]) Arrays.copyOf(strArr, strArr.length)), (KeyPath) LottieProperty.COLOR_FILTER, (LottieValueCallback<KeyPath>) new LottieValueCallback(new SimpleColorFilter(i)));
    }
}
