package expo.modules.kotlin.jni;

import com.facebook.infer.annotation.ThreadConfined;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import expo.modules.kotlin.typedarray.TypedArray;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: CppType.kt */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u001a\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u001d\b\u0002\u0012\n\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006R\u0015\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015j\u0002\b\u0016j\u0002\b\u0017j\u0002\b\u0018j\u0002\b\u0019j\u0002\b\u001aj\u0002\b\u001bj\u0002\b\u001cj\u0002\b\u001dj\u0002\b\u001e¨\u0006\u001f"}, d2 = {"Lexpo/modules/kotlin/jni/CppType;", "", "clazz", "Lkotlin/reflect/KClass;", "value", "", "(Ljava/lang/String;ILkotlin/reflect/KClass;I)V", "getClazz", "()Lkotlin/reflect/KClass;", "getValue", "()I", "NONE", "DOUBLE", "INT", "LONG", "FLOAT", "BOOLEAN", "STRING", "JS_OBJECT", "JS_VALUE", "READABLE_ARRAY", "READABLE_MAP", "UINT8_TYPED_ARRAY", "TYPED_ARRAY", "PRIMITIVE_ARRAY", "LIST", "MAP", "VIEW_TAG", "SHARED_OBJECT_ID", "JS_FUNCTION", ThreadConfined.ANY, "expo-modules-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class CppType {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ CppType[] $VALUES;
    public static final CppType ANY;
    public static final CppType BOOLEAN;
    public static final CppType FLOAT;
    public static final CppType INT;
    public static final CppType JS_FUNCTION;
    public static final CppType JS_OBJECT;
    public static final CppType JS_VALUE;
    public static final CppType LIST;
    public static final CppType LONG;
    public static final CppType MAP;
    public static final CppType PRIMITIVE_ARRAY;
    public static final CppType READABLE_ARRAY;
    public static final CppType READABLE_MAP;
    public static final CppType SHARED_OBJECT_ID;
    public static final CppType STRING;
    public static final CppType TYPED_ARRAY;
    public static final CppType UINT8_TYPED_ARRAY;
    public static final CppType VIEW_TAG;
    private final KClass<?> clazz;
    private final int value;
    public static final CppType NONE = new CppType("NONE", 0, Reflection.getOrCreateKotlinClass(Void.class), 0);
    public static final CppType DOUBLE = new CppType("DOUBLE", 1, Reflection.getOrCreateKotlinClass(Double.TYPE), 0, 2, null);

    private static final /* synthetic */ CppType[] $values() {
        return new CppType[]{NONE, DOUBLE, INT, LONG, FLOAT, BOOLEAN, STRING, JS_OBJECT, JS_VALUE, READABLE_ARRAY, READABLE_MAP, UINT8_TYPED_ARRAY, TYPED_ARRAY, PRIMITIVE_ARRAY, LIST, MAP, VIEW_TAG, SHARED_OBJECT_ID, JS_FUNCTION, ANY};
    }

    public static EnumEntries<CppType> getEntries() {
        return $ENTRIES;
    }

    public static CppType valueOf(String str) {
        return (CppType) Enum.valueOf(CppType.class, str);
    }

    public static CppType[] values() {
        return (CppType[]) $VALUES.clone();
    }

    public final KClass<?> getClazz() {
        return this.clazz;
    }

    public final int getValue() {
        return this.value;
    }

    private CppType(String str, int i, KClass kClass, int i2) {
        this.clazz = kClass;
        this.value = i2;
    }

    /* synthetic */ CppType(String str, int i, KClass kClass, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, i, kClass, (i3 & 2) != 0 ? CppTypeKt.nextValue() : i2);
    }

    static {
        int i = 2;
        INT = new CppType("INT", i, Reflection.getOrCreateKotlinClass(Integer.TYPE), 0, 2, null);
        int i2 = 0;
        int i3 = 2;
        DefaultConstructorMarker defaultConstructorMarker = null;
        LONG = new CppType("LONG", 3, Reflection.getOrCreateKotlinClass(Long.TYPE), i2, i3, defaultConstructorMarker);
        int i4 = 0;
        DefaultConstructorMarker defaultConstructorMarker2 = null;
        FLOAT = new CppType("FLOAT", 4, Reflection.getOrCreateKotlinClass(Float.TYPE), i4, i, defaultConstructorMarker2);
        BOOLEAN = new CppType("BOOLEAN", 5, Reflection.getOrCreateKotlinClass(Boolean.TYPE), i2, i3, defaultConstructorMarker);
        STRING = new CppType("STRING", 6, Reflection.getOrCreateKotlinClass(String.class), i4, i, defaultConstructorMarker2);
        JS_OBJECT = new CppType("JS_OBJECT", 7, Reflection.getOrCreateKotlinClass(JavaScriptObject.class), i2, i3, defaultConstructorMarker);
        JS_VALUE = new CppType("JS_VALUE", 8, Reflection.getOrCreateKotlinClass(JavaScriptValue.class), i4, i, defaultConstructorMarker2);
        READABLE_ARRAY = new CppType("READABLE_ARRAY", 9, Reflection.getOrCreateKotlinClass(ReadableArray.class), i2, i3, defaultConstructorMarker);
        READABLE_MAP = new CppType("READABLE_MAP", 10, Reflection.getOrCreateKotlinClass(ReadableMap.class), i4, i, defaultConstructorMarker2);
        UINT8_TYPED_ARRAY = new CppType("UINT8_TYPED_ARRAY", 11, Reflection.getOrCreateKotlinClass(byte[].class), i2, i3, defaultConstructorMarker);
        TYPED_ARRAY = new CppType("TYPED_ARRAY", 12, Reflection.getOrCreateKotlinClass(TypedArray.class), i4, i, defaultConstructorMarker2);
        PRIMITIVE_ARRAY = new CppType("PRIMITIVE_ARRAY", 13, Reflection.getOrCreateKotlinClass(Object[].class), i2, i3, defaultConstructorMarker);
        LIST = new CppType("LIST", 14, Reflection.getOrCreateKotlinClass(List.class), i4, i, defaultConstructorMarker2);
        MAP = new CppType("MAP", 15, Reflection.getOrCreateKotlinClass(Map.class), i2, i3, defaultConstructorMarker);
        VIEW_TAG = new CppType("VIEW_TAG", 16, Reflection.getOrCreateKotlinClass(Integer.TYPE), i4, i, defaultConstructorMarker2);
        SHARED_OBJECT_ID = new CppType("SHARED_OBJECT_ID", 17, Reflection.getOrCreateKotlinClass(Integer.TYPE), i2, i3, defaultConstructorMarker);
        JS_FUNCTION = new CppType("JS_FUNCTION", 18, Reflection.getOrCreateKotlinClass(JavaScriptFunction.class), i4, i, defaultConstructorMarker2);
        ANY = new CppType(ThreadConfined.ANY, 19, Reflection.getOrCreateKotlinClass(Object.class), i2, i3, defaultConstructorMarker);
        CppType[] $values = $values();
        $VALUES = $values;
        $ENTRIES = EnumEntriesKt.enumEntries($values);
    }
}
