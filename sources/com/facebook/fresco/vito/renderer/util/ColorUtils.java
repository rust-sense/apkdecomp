package com.facebook.fresco.vito.renderer.util;

import androidx.core.view.ViewCompat;
import com.facebook.react.uimanager.ViewProps;
import io.sentry.protocol.ViewHierarchyNode;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: ColorUtils.kt */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lcom/facebook/fresco/vito/renderer/util/ColorUtils;", "", "()V", "Companion", "renderer_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class ColorUtils {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    /* compiled from: ColorUtils.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0004¨\u0006\u0007"}, d2 = {"Lcom/facebook/fresco/vito/renderer/util/ColorUtils$Companion;", "", "()V", "multiplyColorAlpha", "", ViewProps.COLOR, ViewHierarchyNode.JsonKeys.ALPHA, "renderer_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final int multiplyColorAlpha(int color, int alpha) {
            if (alpha == 0) {
                return color & ViewCompat.MEASURED_SIZE_MASK;
            }
            if (alpha == 255) {
                return color;
            }
            return (color & ViewCompat.MEASURED_SIZE_MASK) | ((((color >>> 24) * (alpha + (alpha >> 7))) >> 8) << 24);
        }

        private Companion() {
        }
    }
}
