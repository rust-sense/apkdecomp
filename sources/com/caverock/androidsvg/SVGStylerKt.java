package com.caverock.androidsvg;

import android.support.v4.media.session.PlaybackStateCompat;
import com.caverock.androidsvg.CSSParser;
import com.caverock.androidsvg.SVG;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SVGStyler.kt */
@Metadata(d1 = {"\u00006\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u0016\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005\u001a \u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\tH\u0000\u001a \u0010\n\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u000b2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\tH\u0000\u001a\u0010\u0010\f\u001a\u00020\t2\u0006\u0010\u0006\u001a\u00020\u000bH\u0000\u001a\u001a\u0010\r\u001a\u00020\u00012\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0004\u001a\u00020\u0005H\u0000\u001a\u001a\u0010\u0010\u001a\u00020\u00012\b\u0010\u0011\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u0004\u001a\u00020\u0005H\u0000¨\u0006\u0013"}, d2 = {"applyTintColor", "", "svg", "Lcom/caverock/androidsvg/SVG;", "newColor", "", "element", "Lcom/caverock/androidsvg/SVG$SvgObject;", "parentDefinesStyle", "", "defineStyles", "Lcom/caverock/androidsvg/SVG$SvgElementBase;", "hasStyle", "replaceColor", "paint", "Lcom/caverock/androidsvg/SVG$SvgPaint;", "replaceStyles", "style", "Lcom/caverock/androidsvg/SVG$Style;", "expo-image_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes.dex */
public final class SVGStylerKt {
    public static final void replaceColor(SVG.SvgPaint svgPaint, int i) {
        if (!(svgPaint instanceof SVG.Colour) || svgPaint == SVG.Colour.TRANSPARENT) {
            return;
        }
        ((SVG.Colour) svgPaint).colour = i;
    }

    public static final void replaceStyles(SVG.Style style, int i) {
        if (style == null) {
            return;
        }
        replaceColor(style.color, i);
        replaceColor(style.fill, i);
        replaceColor(style.stroke, i);
        replaceColor(style.stopColor, i);
        replaceColor(style.solidColor, i);
        replaceColor(style.viewportFill, i);
    }

    public static final boolean hasStyle(SVG.SvgElementBase element) {
        Intrinsics.checkNotNullParameter(element, "element");
        if (element.style == null && element.baseStyle == null) {
            return false;
        }
        SVG.Style style = element.style;
        if (style != null && (style.color != null || style.fill != null || style.stroke != null || style.stroke != null || style.stopColor != null || style.solidColor != null)) {
            return true;
        }
        SVG.Style style2 = element.baseStyle;
        if (style2 == null) {
            return false;
        }
        return (style2.color == null && style2.fill == null && style2.stroke == null && style2.viewportFill == null && style2.stopColor == null && style2.solidColor == null) ? false : true;
    }

    public static final void defineStyles(SVG.SvgElementBase element, int i, boolean z) {
        SVG.Style style;
        Intrinsics.checkNotNullParameter(element, "element");
        if (z) {
            return;
        }
        if (element.style != null) {
            style = element.style;
        } else {
            style = new SVG.Style();
            element.style = style;
        }
        SVG.Colour colour = new SVG.Colour(i);
        if ((element instanceof SVG.Path) || (element instanceof SVG.Circle) || (element instanceof SVG.Ellipse) || (element instanceof SVG.Rect) || (element instanceof SVG.SolidColor) || (element instanceof SVG.Line) || (element instanceof SVG.Polygon) || (element instanceof SVG.PolyLine)) {
            style.fill = colour;
            style.specifiedFlags = 1L;
        } else if (element instanceof SVG.TextPath) {
            style.color = colour;
            style.specifiedFlags = PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final void applyTintColor(SVG.SvgObject element, int i, boolean z) {
        Intrinsics.checkNotNullParameter(element, "element");
        if (element instanceof SVG.Mask) {
            return;
        }
        if (element instanceof SVG.SvgElementBase) {
            z = z || hasStyle((SVG.SvgElementBase) element);
            SVG.SvgElementBase svgElementBase = (SVG.SvgElementBase) element;
            replaceStyles(svgElementBase.baseStyle, i);
            replaceStyles(svgElementBase.style, i);
            defineStyles(svgElementBase, i, z);
        }
        if (element instanceof SVG.SvgContainer) {
            for (SVG.SvgObject svgObject : ((SVG.SvgContainer) element).getChildren()) {
                Intrinsics.checkNotNull(svgObject);
                applyTintColor(svgObject, i, z);
            }
        }
    }

    public static final void applyTintColor(SVG svg, int i) {
        Intrinsics.checkNotNullParameter(svg, "svg");
        SVG.Svg rootElement = svg.getRootElement();
        List<CSSParser.Rule> cSSRules = svg.getCSSRules();
        if (cSSRules != null) {
            Iterator<T> it = cSSRules.iterator();
            while (it.hasNext()) {
                replaceStyles(((CSSParser.Rule) it.next()).style, i);
            }
        }
        replaceStyles(rootElement.baseStyle, i);
        replaceStyles(rootElement.style, i);
        Intrinsics.checkNotNull(rootElement);
        boolean hasStyle = hasStyle(rootElement);
        for (SVG.SvgObject svgObject : rootElement.children) {
            Intrinsics.checkNotNull(svgObject);
            applyTintColor(svgObject, i, hasStyle);
        }
    }
}
