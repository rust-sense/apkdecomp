package expo.modules.manifests.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: Manifest.kt */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b0\u0018\u0000 \u00032\u00020\u0001:\u0003\u0003\u0004\u0005B\u0007\b\u0004¢\u0006\u0002\u0010\u0002\u0082\u0001\u0002\u0006\u0007¨\u0006\b"}, d2 = {"Lexpo/modules/manifests/core/PluginType;", "", "()V", "Companion", "WithProps", "WithoutProps", "Lexpo/modules/manifests/core/PluginType$WithProps;", "Lexpo/modules/manifests/core/PluginType$WithoutProps;", "expo-manifests_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public abstract class PluginType {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    public /* synthetic */ PluginType(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    /* compiled from: Manifest.kt */
    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010$\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B)\u0012\"\u0010\u0002\u001a\u001e\u0012\u0004\u0012\u00020\u0004\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00060\u00050\u0003j\u0002`\u0007¢\u0006\u0002\u0010\bJ%\u0010\u000b\u001a\u001e\u0012\u0004\u0012\u00020\u0004\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00060\u00050\u0003j\u0002`\u0007HÆ\u0003J/\u0010\f\u001a\u00020\u00002$\b\u0002\u0010\u0002\u001a\u001e\u0012\u0004\u0012\u00020\u0004\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00060\u00050\u0003j\u0002`\u0007HÆ\u0001J\u0013\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0006HÖ\u0003J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001J\t\u0010\u0012\u001a\u00020\u0004HÖ\u0001R-\u0010\u0002\u001a\u001e\u0012\u0004\u0012\u00020\u0004\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00060\u00050\u0003j\u0002`\u0007¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0013"}, d2 = {"Lexpo/modules/manifests/core/PluginType$WithProps;", "Lexpo/modules/manifests/core/PluginType;", "plugin", "Lkotlin/Pair;", "", "", "", "Lexpo/modules/manifests/core/PluginWithProps;", "(Lkotlin/Pair;)V", "getPlugin", "()Lkotlin/Pair;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "expo-manifests_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class WithProps extends PluginType {
        private final Pair<String, Map<String, Object>> plugin;

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ WithProps copy$default(WithProps withProps, Pair pair, int i, Object obj) {
            if ((i & 1) != 0) {
                pair = withProps.plugin;
            }
            return withProps.copy(pair);
        }

        public final Pair<String, Map<String, Object>> component1() {
            return this.plugin;
        }

        public final WithProps copy(Pair<String, ? extends Map<String, ? extends Object>> plugin) {
            Intrinsics.checkNotNullParameter(plugin, "plugin");
            return new WithProps(plugin);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof WithProps) && Intrinsics.areEqual(this.plugin, ((WithProps) other).plugin);
        }

        public final Pair<String, Map<String, Object>> getPlugin() {
            return this.plugin;
        }

        public int hashCode() {
            return this.plugin.hashCode();
        }

        public String toString() {
            return "WithProps(plugin=" + this.plugin + ")";
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public WithProps(Pair<String, ? extends Map<String, ? extends Object>> plugin) {
            super(null);
            Intrinsics.checkNotNullParameter(plugin, "plugin");
            this.plugin = plugin;
        }
    }

    private PluginType() {
    }

    /* compiled from: Manifest.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0011\u0012\n\u0010\u0002\u001a\u00060\u0003j\u0002`\u0004¢\u0006\u0002\u0010\u0005J\r\u0010\b\u001a\u00060\u0003j\u0002`\u0004HÆ\u0003J\u0017\u0010\t\u001a\u00020\u00002\f\b\u0002\u0010\u0002\u001a\u00060\u0003j\u0002`\u0004HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0003HÖ\u0001R\u0015\u0010\u0002\u001a\u00060\u0003j\u0002`\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lexpo/modules/manifests/core/PluginType$WithoutProps;", "Lexpo/modules/manifests/core/PluginType;", "plugin", "", "Lexpo/modules/manifests/core/PluginWithoutProps;", "(Ljava/lang/String;)V", "getPlugin", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "expo-manifests_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class WithoutProps extends PluginType {
        private final String plugin;

        public static /* synthetic */ WithoutProps copy$default(WithoutProps withoutProps, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                str = withoutProps.plugin;
            }
            return withoutProps.copy(str);
        }

        /* renamed from: component1, reason: from getter */
        public final String getPlugin() {
            return this.plugin;
        }

        public final WithoutProps copy(String plugin) {
            Intrinsics.checkNotNullParameter(plugin, "plugin");
            return new WithoutProps(plugin);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof WithoutProps) && Intrinsics.areEqual(this.plugin, ((WithoutProps) other).plugin);
        }

        public final String getPlugin() {
            return this.plugin;
        }

        public int hashCode() {
            return this.plugin.hashCode();
        }

        public String toString() {
            return "WithoutProps(plugin=" + this.plugin + ")";
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public WithoutProps(String plugin) {
            super(null);
            Intrinsics.checkNotNullParameter(plugin, "plugin");
            this.plugin = plugin;
        }
    }

    /* compiled from: Manifest.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u0006\u001a\u00020\u0007J\u0012\u0010\b\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u0001H\u0002¨\u0006\t"}, d2 = {"Lexpo/modules/manifests/core/PluginType$Companion;", "", "()V", "fromRawArrayValue", "", "Lexpo/modules/manifests/core/PluginType;", "value", "Lorg/json/JSONArray;", "fromRawValue", "expo-manifests_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        private final PluginType fromRawValue(Object value) throws IllegalArgumentException {
            if (value instanceof JSONArray) {
                JSONArray jSONArray = (JSONArray) value;
                if (jSONArray.length() == 0) {
                    throw new IllegalArgumentException("Value for (key = plugins) has incorrect type");
                }
                Object obj = jSONArray.get(0);
                String str = obj instanceof String ? (String) obj : null;
                if (str == null) {
                    return null;
                }
                if (jSONArray.length() == 2) {
                    Object obj2 = jSONArray.get(1);
                    JSONObject jSONObject = obj2 instanceof JSONObject ? (JSONObject) obj2 : null;
                    if (jSONObject == null) {
                        return null;
                    }
                    return new WithProps(TuplesKt.to(str, JSONObjectExtensionKt.toMap(jSONObject)));
                }
                return new WithoutProps(str);
            }
            if (value instanceof String) {
                return new WithoutProps((String) value);
            }
            throw new IllegalArgumentException("Value for (key = plugins) has incorrect type");
        }

        public final List<PluginType> fromRawArrayValue(JSONArray value) throws IllegalArgumentException {
            Intrinsics.checkNotNullParameter(value, "value");
            ArrayList arrayList = new ArrayList();
            int length = value.length();
            for (int i = 0; i < length; i++) {
                Companion companion = PluginType.INSTANCE;
                Object obj = value.get(i);
                Intrinsics.checkNotNullExpressionValue(obj, "get(...)");
                PluginType fromRawValue = companion.fromRawValue(obj);
                if (fromRawValue != null) {
                    arrayList.add(fromRawValue);
                }
            }
            return arrayList;
        }
    }
}
