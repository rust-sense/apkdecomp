package com.facebook.react.views.text;

import android.content.Context;
import android.graphics.Color;
import android.text.SpannableStringBuilder;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.ReactAccessibilityDelegate;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.views.text.fragments.TextFragment;
import com.facebook.react.views.text.fragments.TextFragmentList;
import com.facebook.react.views.text.internal.span.CustomLetterSpacingSpan;
import com.facebook.react.views.text.internal.span.CustomLineHeightSpan;
import com.facebook.react.views.text.internal.span.CustomStyleSpan;
import com.facebook.react.views.text.internal.span.ReactAbsoluteSizeSpan;
import com.facebook.react.views.text.internal.span.ReactBackgroundColorSpan;
import com.facebook.react.views.text.internal.span.ReactClickableSpan;
import com.facebook.react.views.text.internal.span.ReactForegroundColorSpan;
import com.facebook.react.views.text.internal.span.ReactStrikethroughSpan;
import com.facebook.react.views.text.internal.span.ReactTagSpan;
import com.facebook.react.views.text.internal.span.ReactUnderlineSpan;
import com.facebook.react.views.text.internal.span.SetSpanOperation;
import com.facebook.react.views.text.internal.span.ShadowStyleSpan;
import com.facebook.react.views.text.internal.span.TextInlineViewPlaceholderSpan;
import io.sentry.protocol.Request;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: TextLayoutUtils.kt */
@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u0007\n\u0002\b\f\n\u0002\u0018\u0002\n\u0000\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J.\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eH\u0002J>\u0010\u0010\u001a\u00020\u00062\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u0014H\u0007J.\u0010\u0017\u001a\u00020\u00062\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u0014H\u0003J.\u0010\u0018\u001a\u00020\u00062\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u0014H\u0003J6\u0010\u0019\u001a\u00020\u00062\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u0014H\u0003J.\u0010\u001a\u001a\u00020\u00062\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u0014H\u0003J6\u0010\u001b\u001a\u00020\u00062\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001dH\u0007J.\u0010\u001f\u001a\u00020\u00062\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u0014H\u0003J.\u0010 \u001a\u00020\u00062\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u0014H\u0003J6\u0010!\u001a\u00020\u00062\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u0014H\u0003J.\u0010\"\u001a\u00020\u00062\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u00142\u0006\u0010\u0013\u001a\u00020\u0014H\u0003J.\u0010#\u001a\u00020\u00062\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u0014H\u0003J.\u0010$\u001a\u00020\u00062\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u0014H\u0003J\"\u0010%\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\f2\b\u0010&\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0011\u001a\u00020\u0012H\u0007J.\u0010'\u001a\u00020\u00062\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u0014H\u0003J.\u0010(\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010)\u001a\u00020*2\u0006\u0010\u000b\u001a\u00020\f2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eH\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006+"}, d2 = {"Lcom/facebook/react/views/text/TextLayoutUtils;", "", "()V", "INLINE_VIEW_PLACEHOLDER", "", "addApplicableFragmentSpans", "", "context", "Landroid/content/Context;", Request.JsonKeys.FRAGMENT, "Lcom/facebook/react/views/text/fragments/TextFragment;", "sb", "Landroid/text/SpannableStringBuilder;", "ops", "", "Lcom/facebook/react/views/text/internal/span/SetSpanOperation;", "addApplicableTextAttributeSpans", "textAttributeProvider", "Lcom/facebook/react/views/text/EffectiveTextAttributeProvider;", "reactTag", "", ViewProps.START, ViewProps.END, "addBackgroundColorSpanIfApplicable", "addColorSpanIfApplicable", "addCustomStyleSpanIfApplicable", "addFontSizeSpanIfApplicable", "addInlineViewPlaceholderSpan", "width", "", "height", "addLetterSpacingSpanIfApplicable", "addLineHeightSpanIfApplicable", "addLinkSpanIfApplicable", "addReactTagSpan", "addShadowStyleSpanIfApplicable", "addStrikethroughSpanIfApplicable", "addText", "text", "addUnderlineSpanIfApplicable", "buildSpannableFromTextFragmentList", "textFragmentList", "Lcom/facebook/react/views/text/fragments/TextFragmentList;", "ReactAndroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes.dex */
public final class TextLayoutUtils {
    private static final String INLINE_VIEW_PLACEHOLDER = "0";
    public static final TextLayoutUtils INSTANCE = new TextLayoutUtils();

    private TextLayoutUtils() {
    }

    @JvmStatic
    public static final void buildSpannableFromTextFragmentList(Context context, TextFragmentList textFragmentList, SpannableStringBuilder sb, List<SetSpanOperation> ops) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(textFragmentList, "textFragmentList");
        Intrinsics.checkNotNullParameter(sb, "sb");
        Intrinsics.checkNotNullParameter(ops, "ops");
        int count = textFragmentList.getCount();
        for (int i = 0; i < count; i++) {
            INSTANCE.addApplicableFragmentSpans(context, textFragmentList.getFragment(i), sb, ops);
        }
    }

    private final void addApplicableFragmentSpans(Context context, TextFragment fragment, SpannableStringBuilder sb, List<SetSpanOperation> ops) {
        int length = sb.length();
        TextAttributeProps textAttributeProps = fragment.getTextAttributeProps();
        addText(sb, fragment.getString(), textAttributeProps);
        int length2 = sb.length();
        int reactTag = fragment.hasReactTag() ? fragment.getReactTag() : -1;
        if (fragment.hasIsAttachment() && fragment.isAttachment()) {
            addInlineViewPlaceholderSpan(ops, sb, reactTag, PixelUtil.toPixelFromSP(fragment.getWidth()), PixelUtil.toPixelFromSP(fragment.getHeight()));
        } else if (length2 >= length) {
            addApplicableTextAttributeSpans(ops, textAttributeProps, reactTag, context, length, length2);
        }
    }

    @JvmStatic
    public static final void addText(SpannableStringBuilder sb, String text, EffectiveTextAttributeProvider textAttributeProvider) {
        Intrinsics.checkNotNullParameter(sb, "sb");
        Intrinsics.checkNotNullParameter(textAttributeProvider, "textAttributeProvider");
        sb.append((CharSequence) TextTransform.apply(text, textAttributeProvider.getTextTransform()));
    }

    @JvmStatic
    public static final void addInlineViewPlaceholderSpan(List<SetSpanOperation> ops, SpannableStringBuilder sb, int reactTag, float width, float height) {
        Intrinsics.checkNotNullParameter(ops, "ops");
        Intrinsics.checkNotNullParameter(sb, "sb");
        ops.add(new SetSpanOperation(sb.length() - 1, sb.length(), new TextInlineViewPlaceholderSpan(reactTag, (int) width, (int) height)));
    }

    @JvmStatic
    public static final void addApplicableTextAttributeSpans(List<SetSpanOperation> ops, EffectiveTextAttributeProvider textAttributeProvider, int reactTag, Context context, int start, int end) {
        Intrinsics.checkNotNullParameter(ops, "ops");
        Intrinsics.checkNotNullParameter(textAttributeProvider, "textAttributeProvider");
        Intrinsics.checkNotNullParameter(context, "context");
        addColorSpanIfApplicable(ops, textAttributeProvider, start, end);
        addBackgroundColorSpanIfApplicable(ops, textAttributeProvider, start, end);
        addLinkSpanIfApplicable(ops, textAttributeProvider, reactTag, start, end);
        addLetterSpacingSpanIfApplicable(ops, textAttributeProvider, start, end);
        addFontSizeSpanIfApplicable(ops, textAttributeProvider, start, end);
        addCustomStyleSpanIfApplicable(ops, textAttributeProvider, context, start, end);
        addUnderlineSpanIfApplicable(ops, textAttributeProvider, start, end);
        addStrikethroughSpanIfApplicable(ops, textAttributeProvider, start, end);
        addShadowStyleSpanIfApplicable(ops, textAttributeProvider, start, end);
        addLineHeightSpanIfApplicable(ops, textAttributeProvider, start, end);
        addReactTagSpan(ops, start, end, reactTag);
    }

    @JvmStatic
    private static final void addLinkSpanIfApplicable(List<SetSpanOperation> ops, EffectiveTextAttributeProvider textAttributeProvider, int reactTag, int start, int end) {
        ReactAccessibilityDelegate.Role role = textAttributeProvider.getRole();
        if (role != null) {
            if (role != ReactAccessibilityDelegate.Role.LINK) {
                return;
            }
        } else if (textAttributeProvider.getAccessibilityRole() != ReactAccessibilityDelegate.AccessibilityRole.LINK) {
            return;
        }
        ops.add(new SetSpanOperation(start, end, new ReactClickableSpan(reactTag)));
    }

    @JvmStatic
    private static final void addColorSpanIfApplicable(List<SetSpanOperation> ops, EffectiveTextAttributeProvider textAttributeProvider, int start, int end) {
        if (textAttributeProvider.isColorSet()) {
            ops.add(new SetSpanOperation(start, end, new ReactForegroundColorSpan(textAttributeProvider.getColor())));
        }
    }

    @JvmStatic
    private static final void addBackgroundColorSpanIfApplicable(List<SetSpanOperation> ops, EffectiveTextAttributeProvider textAttributeProvider, int start, int end) {
        if (textAttributeProvider.isBackgroundColorSet()) {
            ops.add(new SetSpanOperation(start, end, new ReactBackgroundColorSpan(textAttributeProvider.getBackgroundColor())));
        }
    }

    @JvmStatic
    private static final void addLetterSpacingSpanIfApplicable(List<SetSpanOperation> ops, EffectiveTextAttributeProvider textAttributeProvider, int start, int end) {
        float effectiveLetterSpacing = textAttributeProvider.getEffectiveLetterSpacing();
        if (Float.isNaN(effectiveLetterSpacing)) {
            return;
        }
        ops.add(new SetSpanOperation(start, end, new CustomLetterSpacingSpan(effectiveLetterSpacing)));
    }

    @JvmStatic
    private static final void addFontSizeSpanIfApplicable(List<SetSpanOperation> ops, EffectiveTextAttributeProvider textAttributeProvider, int start, int end) {
        int effectiveFontSize = textAttributeProvider.getEffectiveFontSize();
        if (effectiveFontSize != -1) {
            ops.add(new SetSpanOperation(start, end, new ReactAbsoluteSizeSpan(effectiveFontSize)));
        }
    }

    @JvmStatic
    private static final void addCustomStyleSpanIfApplicable(List<SetSpanOperation> ops, EffectiveTextAttributeProvider textAttributeProvider, Context context, int start, int end) {
        int fontStyle = textAttributeProvider.getFontStyle();
        int fontWeight = textAttributeProvider.getFontWeight();
        String fontFamily = textAttributeProvider.getFontFamily();
        if (fontStyle == -1 && fontWeight == -1 && fontFamily == null) {
            return;
        }
        ops.add(new SetSpanOperation(start, end, new CustomStyleSpan(fontStyle, fontWeight, textAttributeProvider.getFontFeatureSettings(), fontFamily, context.getAssets())));
    }

    @JvmStatic
    private static final void addUnderlineSpanIfApplicable(List<SetSpanOperation> ops, EffectiveTextAttributeProvider textAttributeProvider, int start, int end) {
        if (textAttributeProvider.isUnderlineTextDecorationSet()) {
            ops.add(new SetSpanOperation(start, end, new ReactUnderlineSpan()));
        }
    }

    @JvmStatic
    private static final void addStrikethroughSpanIfApplicable(List<SetSpanOperation> ops, EffectiveTextAttributeProvider textAttributeProvider, int start, int end) {
        if (textAttributeProvider.isLineThroughTextDecorationSet()) {
            ops.add(new SetSpanOperation(start, end, new ReactStrikethroughSpan()));
        }
    }

    @JvmStatic
    private static final void addShadowStyleSpanIfApplicable(List<SetSpanOperation> ops, EffectiveTextAttributeProvider textAttributeProvider, int start, int end) {
        boolean z = (textAttributeProvider.getTextShadowOffsetDx() == 0.0f && textAttributeProvider.getTextShadowOffsetDy() == 0.0f) ? false : true;
        boolean z2 = !(textAttributeProvider.getTextShadowRadius() == 0.0f);
        boolean z3 = Color.alpha(textAttributeProvider.getTextShadowColor()) != 0;
        if ((z || z2) && z3) {
            ops.add(new SetSpanOperation(start, end, new ShadowStyleSpan(textAttributeProvider.getTextShadowOffsetDx(), textAttributeProvider.getTextShadowOffsetDy(), textAttributeProvider.getTextShadowRadius(), textAttributeProvider.getTextShadowColor())));
        }
    }

    @JvmStatic
    private static final void addLineHeightSpanIfApplicable(List<SetSpanOperation> ops, EffectiveTextAttributeProvider textAttributeProvider, int start, int end) {
        float effectiveLineHeight = textAttributeProvider.getEffectiveLineHeight();
        if (Float.isNaN(effectiveLineHeight)) {
            return;
        }
        ops.add(new SetSpanOperation(start, end, new CustomLineHeightSpan(effectiveLineHeight)));
    }

    @JvmStatic
    private static final void addReactTagSpan(List<SetSpanOperation> ops, int start, int end, int reactTag) {
        ops.add(new SetSpanOperation(start, end, new ReactTagSpan(reactTag)));
    }
}
