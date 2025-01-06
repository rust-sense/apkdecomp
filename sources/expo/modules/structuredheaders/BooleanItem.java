package expo.modules.structuredheaders;

import java.util.Objects;

/* loaded from: classes2.dex */
public class BooleanItem implements Item<Boolean> {
    private final Parameters params;
    private final boolean value;
    private static final BooleanItem TRUE = new BooleanItem(true, Parameters.EMPTY);
    private static final BooleanItem FALSE = new BooleanItem(false, Parameters.EMPTY);

    public static BooleanItem valueOf(boolean z) {
        return z ? TRUE : FALSE;
    }

    @Override // expo.modules.structuredheaders.Parametrizable
    public Parameters getParams() {
        return this.params;
    }

    private BooleanItem(boolean z, Parameters parameters) {
        this.value = z;
        this.params = (Parameters) Objects.requireNonNull(parameters, "params must not be null");
    }

    @Override // expo.modules.structuredheaders.Parametrizable
    public BooleanItem withParams(Parameters parameters) {
        return ((Parameters) Objects.requireNonNull(parameters, "params must not be null")).isEmpty() ? this : new BooleanItem(this.value, parameters);
    }

    @Override // expo.modules.structuredheaders.Type
    public StringBuilder serializeTo(StringBuilder sb) {
        sb.append(this.value ? "?1" : "?0");
        this.params.serializeTo(sb);
        return sb;
    }

    @Override // expo.modules.structuredheaders.Type
    public String serialize() {
        return serializeTo(new StringBuilder()).toString();
    }

    @Override // androidx.core.util.Supplier
    public Boolean get() {
        return Boolean.valueOf(this.value);
    }
}
