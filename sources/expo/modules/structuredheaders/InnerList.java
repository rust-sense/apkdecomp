package expo.modules.structuredheaders;

import java.util.List;
import java.util.Objects;
import org.apache.commons.lang3.StringUtils;

/* loaded from: classes2.dex */
public class InnerList implements ListElement<List<Item<? extends Object>>>, Parametrizable<List<Item<? extends Object>>> {
    private final Parameters params;
    private final List<Item<? extends Object>> value;

    @Override // androidx.core.util.Supplier
    public List<Item<? extends Object>> get() {
        return this.value;
    }

    @Override // expo.modules.structuredheaders.Parametrizable
    public Parameters getParams() {
        return this.params;
    }

    private InnerList(List<Item<? extends Object>> list, Parameters parameters) {
        this.value = (List) Objects.requireNonNull(list, "value must not be null");
        this.params = (Parameters) Objects.requireNonNull(parameters, "params must not be null");
    }

    public static InnerList valueOf(List<Item<? extends Object>> list) {
        return new InnerList(list, Parameters.EMPTY);
    }

    @Override // expo.modules.structuredheaders.Parametrizable
    /* renamed from: withParams, reason: merged with bridge method [inline-methods] */
    public Parametrizable<List<Item<? extends Object>>> withParams2(Parameters parameters) {
        return ((Parameters) Objects.requireNonNull(parameters, "params must not be null")).isEmpty() ? this : new InnerList(this.value, parameters);
    }

    @Override // expo.modules.structuredheaders.Type
    public StringBuilder serializeTo(StringBuilder sb) {
        sb.append('(');
        String str = "";
        for (Item<? extends Object> item : this.value) {
            sb.append(str);
            item.serializeTo(sb);
            str = StringUtils.SPACE;
        }
        sb.append(')');
        this.params.serializeTo(sb);
        return sb;
    }

    @Override // expo.modules.structuredheaders.Type
    public String serialize() {
        return serializeTo(new StringBuilder()).toString();
    }
}
