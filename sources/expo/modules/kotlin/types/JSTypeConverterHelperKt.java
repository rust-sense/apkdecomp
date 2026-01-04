package expo.modules.kotlin.types;

import android.net.Uri;
import android.os.Bundle;
import androidx.exifinterface.media.ExifInterface;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import expo.modules.kotlin.records.Field;
import expo.modules.kotlin.records.Record;
import expo.modules.kotlin.types.JSTypeConverter;
import java.io.File;
import java.lang.annotation.Annotation;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KFunction;
import kotlin.reflect.KParameter;
import kotlin.reflect.KProperty1;
import kotlin.reflect.full.KClasses;
import kotlin.reflect.jvm.KCallablesJvm;

/* compiled from: JSTypeConverterHelper.kt */
@Metadata(d1 = {"\u0000h\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\u0018\n\u0002\u0010\u0013\n\u0002\u0010\u0010\n\u0002\u0010\u0014\n\u0002\u0010\u0015\n\u0002\u0018\u0002\n\u0002\u0010\u001c\n\u0002\b\u0002\n\u0002\u0010$\n\u0000\u001a\u0016\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004H\u0000\u001a\u001e\u0010\u0000\u001a\u00020\u0001*\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004H\u0000\u001a\n\u0010\b\u001a\u00020\u0007*\u00020\t\u001a\u0012\u0010\b\u001a\u00020\u0005*\u00020\n2\u0006\u0010\u000b\u001a\u00020\f\u001a\u0012\u0010\b\u001a\u00020\u0005*\u00020\r2\u0006\u0010\u000b\u001a\u00020\f\u001a\n\u0010\b\u001a\u00020\u0007*\u00020\u000e\u001a\n\u0010\b\u001a\u00020\u0007*\u00020\u000f\u001a\n\u0010\b\u001a\u00020\u0007*\u00020\u0010\u001a#\u0010\b\u001a\u00020\u0002\"\u0004\b\u0000\u0010\u0011*\b\u0012\u0004\u0012\u0002H\u00110\u00122\u0006\u0010\u000b\u001a\u00020\f¢\u0006\u0002\u0010\u0013\u001a\u0012\u0010\b\u001a\u00020\u0002*\u00020\u00142\u0006\u0010\u000b\u001a\u00020\f\u001a\u0012\u0010\b\u001a\u00020\u0002*\u00020\u00152\u0006\u0010\u000b\u001a\u00020\f\u001a\u0010\u0010\b\u001a\u0004\u0018\u00010\u0004*\u0006\u0012\u0002\b\u00030\u0016\u001a\u0012\u0010\b\u001a\u00020\u0002*\u00020\u00172\u0006\u0010\u000b\u001a\u00020\f\u001a\u0012\u0010\b\u001a\u00020\u0002*\u00020\u00182\u0006\u0010\u000b\u001a\u00020\f\u001a\u001a\u0010\b\u001a\u00020\u0002*\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u00192\u0006\u0010\u000b\u001a\u00020\f\u001a\u001e\u0010\b\u001a\u00020\u0002\"\u0004\b\u0000\u0010\u0011*\b\u0012\u0004\u0012\u0002H\u00110\u001a2\u0006\u0010\u000b\u001a\u00020\f\u001a*\u0010\b\u001a\u00020\u0005\"\u0004\b\u0000\u0010\u001b\"\u0004\b\u0001\u0010\u001c*\u000e\u0012\u0004\u0012\u0002H\u001b\u0012\u0004\u0012\u0002H\u001c0\u001d2\u0006\u0010\u000b\u001a\u00020\f¨\u0006\u001e"}, d2 = {"putGeneric", "", "Lcom/facebook/react/bridge/WritableArray;", "value", "", "Lcom/facebook/react/bridge/WritableMap;", "key", "", "toJSValue", "Landroid/net/Uri;", "Landroid/os/Bundle;", "containerProvider", "Lexpo/modules/kotlin/types/JSTypeConverter$ContainerProvider;", "Lexpo/modules/kotlin/records/Record;", "Ljava/io/File;", "Ljava/net/URI;", "Ljava/net/URL;", ExifInterface.GPS_DIRECTION_TRUE, "", "([Ljava/lang/Object;Lexpo/modules/kotlin/types/JSTypeConverter$ContainerProvider;)Lcom/facebook/react/bridge/WritableArray;", "", "", "", "", "", "Lkotlin/Pair;", "", "K", ExifInterface.GPS_MEASUREMENT_INTERRUPTED, "", "expo-modules-core_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class JSTypeConverterHelperKt {
    public static final WritableMap toJSValue(Record record, JSTypeConverter.ContainerProvider containerProvider) {
        Object obj;
        Intrinsics.checkNotNullParameter(record, "<this>");
        Intrinsics.checkNotNullParameter(containerProvider, "containerProvider");
        WritableMap createMap = containerProvider.createMap();
        Collection<KProperty1> memberProperties = KClasses.getMemberProperties(JvmClassMappingKt.getKotlinClass(record.getClass()));
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(memberProperties, 10));
        for (KProperty1 kProperty1 : memberProperties) {
            Iterator<T> it = kProperty1.getAnnotations().iterator();
            while (true) {
                if (!it.hasNext()) {
                    obj = null;
                    break;
                }
                obj = it.next();
                if (((Annotation) obj) instanceof Field) {
                    break;
                }
            }
            Field field = (Field) obj;
            if (field != null) {
                String key = field.key();
                String str = Intrinsics.areEqual(key, "") ? null : key;
                if (str == null) {
                    str = kProperty1.getName();
                }
                KCallablesJvm.setAccessible(kProperty1, true);
                putGeneric(createMap, str, JSTypeConverter.INSTANCE.convertToJSValue(kProperty1.get(record), containerProvider));
            }
            arrayList.add(Unit.INSTANCE);
        }
        return createMap;
    }

    public static final WritableMap toJSValue(Bundle bundle, JSTypeConverter.ContainerProvider containerProvider) {
        Intrinsics.checkNotNullParameter(bundle, "<this>");
        Intrinsics.checkNotNullParameter(containerProvider, "containerProvider");
        WritableMap createMap = containerProvider.createMap();
        for (String str : bundle.keySet()) {
            Object convertToJSValue = JSTypeConverter.INSTANCE.convertToJSValue(bundle.get(str), containerProvider);
            Intrinsics.checkNotNull(str);
            putGeneric(createMap, str, convertToJSValue);
        }
        return createMap;
    }

    public static final <K, V> WritableMap toJSValue(Map<K, ? extends V> map, JSTypeConverter.ContainerProvider containerProvider) {
        Intrinsics.checkNotNullParameter(map, "<this>");
        Intrinsics.checkNotNullParameter(containerProvider, "containerProvider");
        WritableMap createMap = containerProvider.createMap();
        for (Map.Entry<K, ? extends V> entry : map.entrySet()) {
            K key = entry.getKey();
            putGeneric(createMap, String.valueOf(key), JSTypeConverter.INSTANCE.convertToJSValue(entry.getValue(), containerProvider));
        }
        return createMap;
    }

    public static final <T> WritableArray toJSValue(Iterable<? extends T> iterable, JSTypeConverter.ContainerProvider containerProvider) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        Intrinsics.checkNotNullParameter(containerProvider, "containerProvider");
        WritableArray createArray = containerProvider.createArray();
        Iterator<? extends T> it = iterable.iterator();
        while (it.hasNext()) {
            putGeneric(createArray, JSTypeConverter.INSTANCE.convertToJSValue(it.next(), containerProvider));
        }
        return createArray;
    }

    public static final <T> WritableArray toJSValue(T[] tArr, JSTypeConverter.ContainerProvider containerProvider) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(containerProvider, "containerProvider");
        WritableArray createArray = containerProvider.createArray();
        for (T t : tArr) {
            putGeneric(createArray, JSTypeConverter.INSTANCE.convertToJSValue(t, containerProvider));
        }
        return createArray;
    }

    public static final WritableArray toJSValue(int[] iArr, JSTypeConverter.ContainerProvider containerProvider) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(containerProvider, "containerProvider");
        WritableArray createArray = containerProvider.createArray();
        for (int i : iArr) {
            createArray.pushInt(i);
        }
        return createArray;
    }

    public static final WritableArray toJSValue(float[] fArr, JSTypeConverter.ContainerProvider containerProvider) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(containerProvider, "containerProvider");
        WritableArray createArray = containerProvider.createArray();
        for (float f : fArr) {
            createArray.pushDouble(f);
        }
        return createArray;
    }

    public static final WritableArray toJSValue(double[] dArr, JSTypeConverter.ContainerProvider containerProvider) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(containerProvider, "containerProvider");
        WritableArray createArray = containerProvider.createArray();
        for (double d : dArr) {
            createArray.pushDouble(d);
        }
        return createArray;
    }

    public static final WritableArray toJSValue(boolean[] zArr, JSTypeConverter.ContainerProvider containerProvider) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(containerProvider, "containerProvider");
        WritableArray createArray = containerProvider.createArray();
        for (boolean z : zArr) {
            createArray.pushBoolean(z);
        }
        return createArray;
    }

    public static final Object toJSValue(Enum<?> r4) {
        Object obj;
        Intrinsics.checkNotNullParameter(r4, "<this>");
        KFunction primaryConstructor = KClasses.getPrimaryConstructor(Reflection.getOrCreateKotlinClass(r4.getClass()));
        if (primaryConstructor == null) {
            throw new IllegalArgumentException("Cannot convert enum without the primary constructor to js value".toString());
        }
        if (primaryConstructor.getParameters().isEmpty()) {
            return r4.name();
        }
        if (primaryConstructor.getParameters().size() == 1) {
            String name = ((KParameter) CollectionsKt.first((List) primaryConstructor.getParameters())).getName();
            Intrinsics.checkNotNull(name);
            Iterator it = KClasses.getDeclaredMemberProperties(Reflection.getOrCreateKotlinClass(r4.getClass())).iterator();
            while (true) {
                if (!it.hasNext()) {
                    obj = null;
                    break;
                }
                obj = it.next();
                if (Intrinsics.areEqual(((KProperty1) obj).getName(), name)) {
                    break;
                }
            }
            Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.reflect.KProperty1<kotlin.Enum<*>, *>");
            return ((KProperty1) obj).get(r4);
        }
        throw new IllegalStateException("Enum '" + r4.getClass() + "' cannot be used as return type (incompatible with JS)");
    }

    public static final String toJSValue(URL url) {
        Intrinsics.checkNotNullParameter(url, "<this>");
        String url2 = url.toString();
        Intrinsics.checkNotNullExpressionValue(url2, "toString(...)");
        return url2;
    }

    public static final String toJSValue(Uri uri) {
        Intrinsics.checkNotNullParameter(uri, "<this>");
        String uri2 = uri.toString();
        Intrinsics.checkNotNullExpressionValue(uri2, "toString(...)");
        return uri2;
    }

    public static final String toJSValue(URI uri) {
        Intrinsics.checkNotNullParameter(uri, "<this>");
        String uri2 = uri.toString();
        Intrinsics.checkNotNullExpressionValue(uri2, "toString(...)");
        return uri2;
    }

    public static final String toJSValue(File file) {
        Intrinsics.checkNotNullParameter(file, "<this>");
        String absolutePath = file.getAbsolutePath();
        Intrinsics.checkNotNullExpressionValue(absolutePath, "getAbsolutePath(...)");
        return absolutePath;
    }

    public static final WritableArray toJSValue(Pair<?, ?> pair, JSTypeConverter.ContainerProvider containerProvider) {
        Intrinsics.checkNotNullParameter(pair, "<this>");
        Intrinsics.checkNotNullParameter(containerProvider, "containerProvider");
        WritableArray createArray = containerProvider.createArray();
        Object convertToJSValue = JSTypeConverter.INSTANCE.convertToJSValue(pair.getFirst(), containerProvider);
        Object convertToJSValue2 = JSTypeConverter.INSTANCE.convertToJSValue(pair.getSecond(), containerProvider);
        putGeneric(createArray, convertToJSValue);
        putGeneric(createArray, convertToJSValue2);
        return createArray;
    }

    public static final void putGeneric(WritableMap writableMap, String key, Object obj) {
        Intrinsics.checkNotNullParameter(writableMap, "<this>");
        Intrinsics.checkNotNullParameter(key, "key");
        if (obj == null || (obj instanceof Unit)) {
            writableMap.putNull(key);
            return;
        }
        if (obj instanceof ReadableArray) {
            writableMap.putArray(key, (ReadableArray) obj);
            return;
        }
        if (obj instanceof ReadableMap) {
            writableMap.putMap(key, (ReadableMap) obj);
            return;
        }
        if (obj instanceof String) {
            writableMap.putString(key, (String) obj);
            return;
        }
        if (obj instanceof Integer) {
            writableMap.putInt(key, ((Number) obj).intValue());
            return;
        }
        if (obj instanceof Number) {
            writableMap.putDouble(key, ((Number) obj).doubleValue());
            return;
        }
        if (obj instanceof Boolean) {
            writableMap.putBoolean(key, ((Boolean) obj).booleanValue());
            return;
        }
        throw new IllegalArgumentException("Could not put '" + obj.getClass() + "' to WritableMap");
    }

    public static final void putGeneric(WritableArray writableArray, Object obj) {
        Intrinsics.checkNotNullParameter(writableArray, "<this>");
        if (obj == null || (obj instanceof Unit)) {
            writableArray.pushNull();
            return;
        }
        if (obj instanceof ReadableArray) {
            writableArray.pushArray((ReadableArray) obj);
            return;
        }
        if (obj instanceof ReadableMap) {
            writableArray.pushMap((ReadableMap) obj);
            return;
        }
        if (obj instanceof String) {
            writableArray.pushString((String) obj);
            return;
        }
        if (obj instanceof Integer) {
            writableArray.pushInt(((Number) obj).intValue());
            return;
        }
        if (obj instanceof Number) {
            writableArray.pushDouble(((Number) obj).doubleValue());
            return;
        }
        if (obj instanceof Boolean) {
            writableArray.pushBoolean(((Boolean) obj).booleanValue());
            return;
        }
        throw new IllegalArgumentException("Could not put '" + obj.getClass() + "' to WritableArray");
    }
}
