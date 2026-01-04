package expo.modules.structuredheaders;

import java.math.BigDecimal;
import java.util.Objects;

/* loaded from: classes2.dex */
public class DecimalItem implements NumberItem<BigDecimal> {
    private static final long MAX = 999999999999999L;
    private static final long MIN = -999999999999999L;
    private static final BigDecimal THOUSAND = new BigDecimal(1000);
    private final Parameters params;
    private final long value;

    @Override // expo.modules.structuredheaders.LongSupplier
    public long getAsLong() {
        return this.value;
    }

    @Override // expo.modules.structuredheaders.NumberItem
    public int getDivisor() {
        return 1000;
    }

    @Override // expo.modules.structuredheaders.Parametrizable
    public Parameters getParams() {
        return this.params;
    }

    private DecimalItem(long j, Parameters parameters) {
        if (j < MIN || j > MAX) {
            throw new IllegalArgumentException("value must be in the range from -999999999999999 to 999999999999999");
        }
        this.value = j;
        this.params = (Parameters) Objects.requireNonNull(parameters, "params must not be null");
    }

    public static DecimalItem valueOf(long j) {
        return new DecimalItem(j, Parameters.EMPTY);
    }

    public static DecimalItem valueOf(BigDecimal bigDecimal) {
        return valueOf(((BigDecimal) Objects.requireNonNull(bigDecimal, "value must not be null")).multiply(THOUSAND).longValue());
    }

    @Override // expo.modules.structuredheaders.Parametrizable
    /* renamed from: withParams */
    public DecimalItem withParams2(Parameters parameters) {
        return ((Parameters) Objects.requireNonNull(parameters, "params must not be null")).isEmpty() ? this : new DecimalItem(this.value, parameters);
    }

    @Override // expo.modules.structuredheaders.Type
    public StringBuilder serializeTo(StringBuilder sb) {
        long j = this.value;
        String str = j < 0 ? "-" : "";
        long abs = Math.abs(j);
        long j2 = abs / 1000;
        long j3 = abs % 1000;
        if (j3 % 10 == 0) {
            j3 /= 10;
        }
        if (j3 % 10 == 0) {
            j3 /= 10;
        }
        sb.append(str);
        sb.append(Long.toString(j2));
        sb.append('.');
        sb.append(Long.toString(j3));
        this.params.serializeTo(sb);
        return sb;
    }

    @Override // expo.modules.structuredheaders.Type
    public String serialize() {
        return serializeTo(new StringBuilder(20)).toString();
    }

    @Override // androidx.core.util.Supplier
    public BigDecimal get() {
        return BigDecimal.valueOf(this.value, 3);
    }
}
