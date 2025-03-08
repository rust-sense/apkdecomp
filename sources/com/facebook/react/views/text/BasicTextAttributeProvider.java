package com.facebook.react.views.text;

import com.facebook.react.uimanager.ReactAccessibilityDelegate;
import com.facebook.react.uimanager.ViewProps;
import kotlin.Metadata;

/* compiled from: BasicTextAttributeProvider.kt */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\u0007\b`\u0018\u00002\u00020\u0001R\u0014\u0010\u0002\u001a\u0004\u0018\u00010\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0012\u0010\u0006\u001a\u00020\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0012\u0010\n\u001a\u00020\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\tR\u0014\u0010\f\u001a\u0004\u0018\u00010\rX¦\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0010\u001a\u0004\u0018\u00010\rX¦\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u000fR\u0012\u0010\u0012\u001a\u00020\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\tR\u0012\u0010\u0014\u001a\u00020\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\tR\u0012\u0010\u0016\u001a\u00020\u0017X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0018R\u0012\u0010\u0019\u001a\u00020\u0017X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u0018R\u0012\u0010\u001a\u001a\u00020\u0017X¦\u0004¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u0018R\u0012\u0010\u001b\u001a\u00020\u0017X¦\u0004¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u0018R\u0014\u0010\u001c\u001a\u0004\u0018\u00010\u001dX¦\u0004¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u001fR\u0012\u0010 \u001a\u00020\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b!\u0010\tR\u0012\u0010\"\u001a\u00020#X¦\u0004¢\u0006\u0006\u001a\u0004\b$\u0010%R\u0012\u0010&\u001a\u00020#X¦\u0004¢\u0006\u0006\u001a\u0004\b'\u0010%R\u0012\u0010(\u001a\u00020#X¦\u0004¢\u0006\u0006\u001a\u0004\b)\u0010%ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006*À\u0006\u0001"}, d2 = {"Lcom/facebook/react/views/text/BasicTextAttributeProvider;", "", ViewProps.ACCESSIBILITY_ROLE, "Lcom/facebook/react/uimanager/ReactAccessibilityDelegate$AccessibilityRole;", "getAccessibilityRole", "()Lcom/facebook/react/uimanager/ReactAccessibilityDelegate$AccessibilityRole;", ViewProps.BACKGROUND_COLOR, "", "getBackgroundColor", "()I", ViewProps.COLOR, "getColor", ViewProps.FONT_FAMILY, "", "getFontFamily", "()Ljava/lang/String;", "fontFeatureSettings", "getFontFeatureSettings", ViewProps.FONT_STYLE, "getFontStyle", ViewProps.FONT_WEIGHT, "getFontWeight", "isBackgroundColorSet", "", "()Z", "isColorSet", "isLineThroughTextDecorationSet", "isUnderlineTextDecorationSet", ViewProps.ROLE, "Lcom/facebook/react/uimanager/ReactAccessibilityDelegate$Role;", "getRole", "()Lcom/facebook/react/uimanager/ReactAccessibilityDelegate$Role;", ReactBaseTextShadowNode.PROP_SHADOW_COLOR, "getTextShadowColor", "textShadowOffsetDx", "", "getTextShadowOffsetDx", "()F", "textShadowOffsetDy", "getTextShadowOffsetDy", ReactBaseTextShadowNode.PROP_SHADOW_RADIUS, "getTextShadowRadius", "ReactAndroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes.dex */
public interface BasicTextAttributeProvider {
    ReactAccessibilityDelegate.AccessibilityRole getAccessibilityRole();

    int getBackgroundColor();

    int getColor();

    String getFontFamily();

    String getFontFeatureSettings();

    int getFontStyle();

    int getFontWeight();

    ReactAccessibilityDelegate.Role getRole();

    int getTextShadowColor();

    float getTextShadowOffsetDx();

    float getTextShadowOffsetDy();

    float getTextShadowRadius();

    boolean isBackgroundColorSet();

    boolean isColorSet();

    boolean isLineThroughTextDecorationSet();

    boolean isUnderlineTextDecorationSet();
}
