package expo.modules.structuredheaders;

import java.util.Collections;
import java.util.Map;

/* loaded from: classes2.dex */
public class Dictionary implements Type<Map<String, ListElement<? extends Object>>> {
    private final Map<String, ListElement<? extends Object>> value;

    @Override // androidx.core.util.Supplier
    public Map<String, ListElement<? extends Object>> get() {
        return this.value;
    }

    private Dictionary(Map<String, ListElement<? extends Object>> map) {
        this.value = Collections.unmodifiableMap(Utils.checkKeys(map));
    }

    public static Dictionary valueOf(Map<String, ListElement<? extends Object>> map) {
        return new Dictionary(map);
    }

    @Override // expo.modules.structuredheaders.Type
    public StringBuilder serializeTo(StringBuilder sb) {
        String str = "";
        for (Map.Entry<String, ListElement<? extends Object>> entry : this.value.entrySet()) {
            sb.append(str);
            String key = entry.getKey();
            ListElement<? extends Object> value = entry.getValue();
            sb.append(key);
            if (Boolean.TRUE.equals(value.get())) {
                value.getParams().serializeTo(sb);
            } else {
                sb.append("=");
                value.serializeTo(sb);
            }
            str = ", ";
        }
        return sb;
    }

    @Override // expo.modules.structuredheaders.Type
    public String serialize() {
        return serializeTo(new StringBuilder()).toString();
    }
}
