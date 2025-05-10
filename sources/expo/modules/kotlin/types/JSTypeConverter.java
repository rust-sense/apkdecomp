package expo.modules.kotlin.types;

import android.net.Uri;
import android.os.Bundle;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import expo.modules.kotlin.records.Record;
import expo.modules.kotlin.typedarray.RawTypedArrayHolder;
import expo.modules.kotlin.types.folly.FollyDynamicExtensionConverter;
import java.io.File;
import java.net.URI;
import java.net.URL;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: JSTypeConverter.kt */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001:\u0002\u0007\bB\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u00012\b\u0010\u0004\u001a\u0004\u0018\u00010\u00012\b\b\u0002\u0010\u0005\u001a\u00020\u0006¨\u0006\t"}, d2 = {"Lexpo/modules/kotlin/types/JSTypeConverter;", "", "()V", "convertToJSValue", "value", "containerProvider", "Lexpo/modules/kotlin/types/JSTypeConverter$ContainerProvider;", "ContainerProvider", "DefaultContainerProvider", "expo-modules-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class JSTypeConverter {
    public static final JSTypeConverter INSTANCE = new JSTypeConverter();

    /* compiled from: JSTypeConverter.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, d2 = {"Lexpo/modules/kotlin/types/JSTypeConverter$ContainerProvider;", "", "createArray", "Lcom/facebook/react/bridge/WritableArray;", "createMap", "Lcom/facebook/react/bridge/WritableMap;", "expo-modules-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public interface ContainerProvider {
        WritableArray createArray();

        WritableMap createMap();
    }

    private JSTypeConverter() {
    }

    /* compiled from: JSTypeConverter.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016J\b\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\u0007"}, d2 = {"Lexpo/modules/kotlin/types/JSTypeConverter$DefaultContainerProvider;", "Lexpo/modules/kotlin/types/JSTypeConverter$ContainerProvider;", "()V", "createArray", "Lcom/facebook/react/bridge/WritableArray;", "createMap", "Lcom/facebook/react/bridge/WritableMap;", "expo-modules-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class DefaultContainerProvider implements ContainerProvider {
        public static final DefaultContainerProvider INSTANCE = new DefaultContainerProvider();

        private DefaultContainerProvider() {
        }

        @Override // expo.modules.kotlin.types.JSTypeConverter.ContainerProvider
        public WritableMap createMap() {
            WritableMap createMap = Arguments.createMap();
            Intrinsics.checkNotNullExpressionValue(createMap, "createMap(...)");
            return createMap;
        }

        @Override // expo.modules.kotlin.types.JSTypeConverter.ContainerProvider
        public WritableArray createArray() {
            WritableArray createArray = Arguments.createArray();
            Intrinsics.checkNotNullExpressionValue(createArray, "createArray(...)");
            return createArray;
        }
    }

    public static /* synthetic */ Object convertToJSValue$default(JSTypeConverter jSTypeConverter, Object obj, ContainerProvider containerProvider, int i, Object obj2) {
        if ((i & 2) != 0) {
            containerProvider = DefaultContainerProvider.INSTANCE;
        }
        return jSTypeConverter.convertToJSValue(obj, containerProvider);
    }

    public final Object convertToJSValue(Object value, ContainerProvider containerProvider) {
        Intrinsics.checkNotNullParameter(containerProvider, "containerProvider");
        if (value == null || (value instanceof Unit)) {
            return null;
        }
        return value instanceof Bundle ? JSTypeConverterHelperKt.toJSValue((Bundle) value, containerProvider) : value instanceof Object[] ? JSTypeConverterHelperKt.toJSValue((Object[]) value, containerProvider) : value instanceof int[] ? JSTypeConverterHelperKt.toJSValue((int[]) value, containerProvider) : value instanceof float[] ? JSTypeConverterHelperKt.toJSValue((float[]) value, containerProvider) : value instanceof double[] ? JSTypeConverterHelperKt.toJSValue((double[]) value, containerProvider) : value instanceof boolean[] ? JSTypeConverterHelperKt.toJSValue((boolean[]) value, containerProvider) : value instanceof byte[] ? FollyDynamicExtensionConverter.INSTANCE.put(value) : value instanceof Map ? JSTypeConverterHelperKt.toJSValue((Map) value, containerProvider) : value instanceof Enum ? JSTypeConverterHelperKt.toJSValue((Enum<?>) value) : value instanceof Record ? JSTypeConverterHelperKt.toJSValue((Record) value, containerProvider) : value instanceof URI ? JSTypeConverterHelperKt.toJSValue((URI) value) : value instanceof URL ? JSTypeConverterHelperKt.toJSValue((URL) value) : value instanceof Uri ? JSTypeConverterHelperKt.toJSValue((Uri) value) : value instanceof File ? JSTypeConverterHelperKt.toJSValue((File) value) : value instanceof Pair ? JSTypeConverterHelperKt.toJSValue((Pair<?, ?>) value, containerProvider) : value instanceof Long ? Double.valueOf(((Number) value).longValue()) : value instanceof RawTypedArrayHolder ? ((RawTypedArrayHolder) value).getRawArray() : value instanceof Iterable ? JSTypeConverterHelperKt.toJSValue((Iterable) value, containerProvider) : value;
    }
}
