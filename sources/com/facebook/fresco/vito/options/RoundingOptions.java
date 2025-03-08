package com.facebook.fresco.vito.options;

import java.util.Arrays;
import kotlin.Metadata;
import kotlin.UByte$$ExternalSyntheticBackport0;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: RoundingOptions.kt */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u0014\n\u0002\b\u0012\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\b\u0018\u0000 \u001d2\u00020\u0001:\u0001\u001dB/\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0003¢\u0006\u0002\u0010\nJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0005HÆ\u0003J\u000b\u0010\u0012\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J=\u0010\u0015\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0016\u001a\u00020\u00032\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\u0006\u0010\u0018\u001a\u00020\u0003J\b\u0010\u0019\u001a\u00020\u001aH\u0016J\t\u0010\u001b\u001a\u00020\u001cHÖ\u0001R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u000fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u000fR\u0011\u0010\t\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u000f¨\u0006\u001e"}, d2 = {"Lcom/facebook/fresco/vito/options/RoundingOptions;", "", "isCircular", "", "cornerRadius", "", "cornerRadii", "", "isAntiAliased", "isForceRoundAtDecode", "(ZF[FZZ)V", "getCornerRadii", "()[F", "getCornerRadius", "()F", "()Z", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "other", "hasRoundedCorners", "hashCode", "", "toString", "", "Companion", "options_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final /* data */ class RoundingOptions {
    public static final float CORNER_RADIUS_UNSET = 0.0f;
    private final float[] cornerRadii;
    private final float cornerRadius;
    private final boolean isAntiAliased;
    private final boolean isCircular;
    private final boolean isForceRoundAtDecode;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final RoundingOptions AS_CIRCLE = new RoundingOptions(true, 0.0f, null, false, false);
    private static final RoundingOptions AS_CIRCLE_ANTI_ALIASING = new RoundingOptions(true, 0.0f, null, true, false);

    @JvmStatic
    public static final RoundingOptions asCircle() {
        return INSTANCE.asCircle();
    }

    @JvmStatic
    public static final RoundingOptions asCircle(boolean z) {
        return INSTANCE.asCircle(z);
    }

    @JvmStatic
    public static final RoundingOptions asCircle(boolean z, boolean z2) {
        return INSTANCE.asCircle(z, z2);
    }

    public static /* synthetic */ RoundingOptions copy$default(RoundingOptions roundingOptions, boolean z, float f, float[] fArr, boolean z2, boolean z3, int i, Object obj) {
        if ((i & 1) != 0) {
            z = roundingOptions.isCircular;
        }
        if ((i & 2) != 0) {
            f = roundingOptions.cornerRadius;
        }
        float f2 = f;
        if ((i & 4) != 0) {
            fArr = roundingOptions.cornerRadii;
        }
        float[] fArr2 = fArr;
        if ((i & 8) != 0) {
            z2 = roundingOptions.isAntiAliased;
        }
        boolean z4 = z2;
        if ((i & 16) != 0) {
            z3 = roundingOptions.isForceRoundAtDecode;
        }
        return roundingOptions.copy(z, f2, fArr2, z4, z3);
    }

    @JvmStatic
    public static final RoundingOptions forCornerRadii(float f, float f2, float f3, float f4) {
        return INSTANCE.forCornerRadii(f, f2, f3, f4);
    }

    @JvmStatic
    public static final RoundingOptions forCornerRadii(float[] fArr) {
        return INSTANCE.forCornerRadii(fArr);
    }

    @JvmStatic
    public static final RoundingOptions forCornerRadii(float[] fArr, boolean z) {
        return INSTANCE.forCornerRadii(fArr, z);
    }

    @JvmStatic
    public static final RoundingOptions forCornerRadiusPx(float f) {
        return INSTANCE.forCornerRadiusPx(f);
    }

    /* renamed from: component1, reason: from getter */
    public final boolean getIsCircular() {
        return this.isCircular;
    }

    /* renamed from: component2, reason: from getter */
    public final float getCornerRadius() {
        return this.cornerRadius;
    }

    /* renamed from: component3, reason: from getter */
    public final float[] getCornerRadii() {
        return this.cornerRadii;
    }

    /* renamed from: component4, reason: from getter */
    public final boolean getIsAntiAliased() {
        return this.isAntiAliased;
    }

    /* renamed from: component5, reason: from getter */
    public final boolean getIsForceRoundAtDecode() {
        return this.isForceRoundAtDecode;
    }

    public final RoundingOptions copy(boolean isCircular, float cornerRadius, float[] cornerRadii, boolean isAntiAliased, boolean isForceRoundAtDecode) {
        return new RoundingOptions(isCircular, cornerRadius, cornerRadii, isAntiAliased, isForceRoundAtDecode);
    }

    public final float[] getCornerRadii() {
        return this.cornerRadii;
    }

    public final float getCornerRadius() {
        return this.cornerRadius;
    }

    public final boolean hasRoundedCorners() {
        return (this.cornerRadius == 0.0f && this.cornerRadii == null) ? false : true;
    }

    public final boolean isAntiAliased() {
        return this.isAntiAliased;
    }

    public final boolean isCircular() {
        return this.isCircular;
    }

    public final boolean isForceRoundAtDecode() {
        return this.isForceRoundAtDecode;
    }

    public String toString() {
        return "RoundingOptions(isCircular=" + this.isCircular + ", cornerRadius=" + this.cornerRadius + ", cornerRadii=" + Arrays.toString(this.cornerRadii) + ", isAntiAliased=" + this.isAntiAliased + ", isForceRoundAtDecode=" + this.isForceRoundAtDecode + ')';
    }

    public RoundingOptions(boolean z, float f, float[] fArr, boolean z2, boolean z3) {
        this.isCircular = z;
        this.cornerRadius = f;
        this.cornerRadii = fArr;
        this.isAntiAliased = z2;
        this.isForceRoundAtDecode = z3;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!Intrinsics.areEqual(getClass(), other != null ? other.getClass() : null)) {
            return false;
        }
        Intrinsics.checkNotNull(other, "null cannot be cast to non-null type com.facebook.fresco.vito.options.RoundingOptions");
        RoundingOptions roundingOptions = (RoundingOptions) other;
        return this.isCircular == roundingOptions.isCircular && this.cornerRadius == roundingOptions.cornerRadius && Arrays.equals(this.cornerRadii, roundingOptions.cornerRadii) && this.isAntiAliased == roundingOptions.isAntiAliased && this.isForceRoundAtDecode == roundingOptions.isForceRoundAtDecode;
    }

    public int hashCode() {
        return (((((((UByte$$ExternalSyntheticBackport0.m(this.isCircular) * 31) + Float.floatToIntBits(this.cornerRadius)) * 31) + Arrays.hashCode(this.cornerRadii)) * 31) + UByte$$ExternalSyntheticBackport0.m(this.isAntiAliased)) * 31) + UByte$$ExternalSyntheticBackport0.m(this.isForceRoundAtDecode);
    }

    /* compiled from: RoundingOptions.kt */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\u0014\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u000b\u001a\u00020\u0004H\u0007J\u0010\u0010\u000b\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\rH\u0007J\u0018\u0010\u000b\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\rH\u0007J(\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\n2\u0006\u0010\u0011\u001a\u00020\n2\u0006\u0010\u0012\u001a\u00020\n2\u0006\u0010\u0013\u001a\u00020\nH\u0007J\u001a\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u00152\b\b\u0002\u0010\f\u001a\u00020\rH\u0007J\u0010\u0010\u0016\u001a\u00020\u00042\u0006\u0010\u0017\u001a\u00020\nH\u0007R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0007\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006R\u000e\u0010\t\u001a\u00020\nX\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lcom/facebook/fresco/vito/options/RoundingOptions$Companion;", "", "()V", "AS_CIRCLE", "Lcom/facebook/fresco/vito/options/RoundingOptions;", "getAS_CIRCLE", "()Lcom/facebook/fresco/vito/options/RoundingOptions;", "AS_CIRCLE_ANTI_ALIASING", "getAS_CIRCLE_ANTI_ALIASING", "CORNER_RADIUS_UNSET", "", "asCircle", "antiAliasing", "", "forceRoundAtDecode", "forCornerRadii", "topLeft", "topRight", "bottomRight", "bottomLeft", "cornerRadii", "", "forCornerRadiusPx", "cornerRadiusPx", "options_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        public final RoundingOptions forCornerRadii(float[] cornerRadii) {
            Intrinsics.checkNotNullParameter(cornerRadii, "cornerRadii");
            return forCornerRadii$default(this, cornerRadii, false, 2, null);
        }

        private Companion() {
        }

        public final RoundingOptions getAS_CIRCLE() {
            return RoundingOptions.AS_CIRCLE;
        }

        public final RoundingOptions getAS_CIRCLE_ANTI_ALIASING() {
            return RoundingOptions.AS_CIRCLE_ANTI_ALIASING;
        }

        @JvmStatic
        public final RoundingOptions asCircle() {
            return getAS_CIRCLE();
        }

        @JvmStatic
        public final RoundingOptions asCircle(boolean antiAliasing) {
            return antiAliasing ? getAS_CIRCLE_ANTI_ALIASING() : getAS_CIRCLE();
        }

        @JvmStatic
        public final RoundingOptions asCircle(boolean antiAliasing, boolean forceRoundAtDecode) {
            return new RoundingOptions(true, 0.0f, null, antiAliasing, forceRoundAtDecode);
        }

        @JvmStatic
        public final RoundingOptions forCornerRadiusPx(float cornerRadiusPx) {
            return new RoundingOptions(false, cornerRadiusPx, null, false, false);
        }

        @JvmStatic
        public final RoundingOptions forCornerRadii(float topLeft, float topRight, float bottomRight, float bottomLeft) {
            return new RoundingOptions(false, 0.0f, new float[]{topLeft, topLeft, topRight, topRight, bottomRight, bottomRight, bottomLeft, bottomLeft}, false, false);
        }

        public static /* synthetic */ RoundingOptions forCornerRadii$default(Companion companion, float[] fArr, boolean z, int i, Object obj) {
            if ((i & 2) != 0) {
                z = false;
            }
            return companion.forCornerRadii(fArr, z);
        }

        @JvmStatic
        public final RoundingOptions forCornerRadii(float[] cornerRadii, boolean antiAliasing) {
            Intrinsics.checkNotNullParameter(cornerRadii, "cornerRadii");
            return new RoundingOptions(false, 0.0f, cornerRadii, antiAliasing, false);
        }
    }
}
