package expo.modules.structuredheaders;

import java.util.Objects;
import kotlin.text.Typography;
import org.apache.commons.io.IOUtils;

/* loaded from: classes2.dex */
public class StringItem implements Item<String> {
    private final Parameters params;
    private final String value;

    @Override // androidx.core.util.Supplier
    public String get() {
        return this.value;
    }

    @Override // expo.modules.structuredheaders.Parametrizable
    public Parameters getParams() {
        return this.params;
    }

    private StringItem(String str, Parameters parameters) {
        this.value = checkParam((String) Objects.requireNonNull(str, "value must not be null"));
        this.params = (Parameters) Objects.requireNonNull(parameters, "params must not be null");
    }

    public static StringItem valueOf(String str) {
        return new StringItem(str, Parameters.EMPTY);
    }

    @Override // expo.modules.structuredheaders.Parametrizable
    public StringItem withParams(Parameters parameters) {
        return ((Parameters) Objects.requireNonNull(parameters, "params must not be null")).isEmpty() ? this : new StringItem(this.value, parameters);
    }

    @Override // expo.modules.structuredheaders.Type
    public StringBuilder serializeTo(StringBuilder sb) {
        sb.append(Typography.quote);
        for (int i = 0; i < this.value.length(); i++) {
            char charAt = this.value.charAt(i);
            if (charAt == '\\' || charAt == '\"') {
                sb.append(IOUtils.DIR_SEPARATOR_WINDOWS);
            }
            sb.append(charAt);
        }
        sb.append(Typography.quote);
        this.params.serializeTo(sb);
        return sb;
    }

    @Override // expo.modules.structuredheaders.Type
    public String serialize() {
        return serializeTo(new StringBuilder(this.value.length() + 2)).toString();
    }

    private static String checkParam(String str) {
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            if (charAt < ' ' || charAt >= 127) {
                throw new IllegalArgumentException(String.format("Invalid character in String at position %d: '%c' (0x%04x)", Integer.valueOf(i), Character.valueOf(charAt), Integer.valueOf(charAt)));
            }
        }
        return str;
    }
}
