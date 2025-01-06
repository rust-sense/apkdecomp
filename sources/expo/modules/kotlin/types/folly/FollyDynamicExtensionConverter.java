package expo.modules.kotlin.types.folly;

import android.util.ArrayMap;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.apache.commons.codec.language.bm.Languages;

/* compiled from: FollyDynamicExtensionConverter.kt */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0007\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lexpo/modules/kotlin/types/folly/FollyDynamicExtensionConverter;", "", "()V", "Companion", "expo-modules-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class FollyDynamicExtensionConverter {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final ArrayMap<Integer, Object> instanceMap = new ArrayMap<>();
    private static int nextId;

    @JvmStatic
    public static final synchronized Object get(String str) {
        Object obj;
        synchronized (FollyDynamicExtensionConverter.class) {
            obj = INSTANCE.get(str);
        }
        return obj;
    }

    @JvmStatic
    public static final synchronized String put(Object obj) {
        String put;
        synchronized (FollyDynamicExtensionConverter.class) {
            put = INSTANCE.put(obj);
        }
        return put;
    }

    /* compiled from: FollyDynamicExtensionConverter.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0007\u001a\u0004\u0018\u00010\u00012\u0006\u0010\b\u001a\u00020\tH\u0007J\u0010\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\u0001H\u0007R\u001a\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lexpo/modules/kotlin/types/folly/FollyDynamicExtensionConverter$Companion;", "", "()V", "instanceMap", "Landroid/util/ArrayMap;", "", "nextId", "get", "payload", "", "put", Languages.ANY, "expo-modules-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final synchronized String put(Object any) {
            int i;
            Intrinsics.checkNotNullParameter(any, "any");
            i = FollyDynamicExtensionConverter.nextId;
            FollyDynamicExtensionConverter.nextId = i + 1;
            FollyDynamicExtensionConverter.instanceMap.put(Integer.valueOf(i), any);
            return FollyDynamicExtensionConverterKt.DYNAMIC_EXTENSION_PREFIX + i;
        }

        @JvmStatic
        public final synchronized Object get(String payload) {
            String substring;
            Intrinsics.checkNotNullParameter(payload, "payload");
            if (!StringsKt.startsWith$default(payload, FollyDynamicExtensionConverterKt.DYNAMIC_EXTENSION_PREFIX, false, 2, (Object) null)) {
                throw new InvalidDynamicExtensionFormatException();
            }
            substring = payload.substring(27);
            Intrinsics.checkNotNullExpressionValue(substring, "substring(...)");
            return FollyDynamicExtensionConverter.instanceMap.remove(Integer.valueOf(Integer.parseInt(substring)));
        }
    }
}
