package expo.modules.kotlin.jni;

import com.facebook.jni.HybridData;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: JavaScriptObject.kt */
@Metadata(d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0001\n\u0002\b\u0005\n\u0002\u0010\u0011\n\u0002\b\r\b\u0017\u0018\u00002\u00020\u0001:\u00011B\u000f\b\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0005\u001a\u00020\u0006H\u0086 J\b\u0010\u0007\u001a\u00020\bH\u0016J!\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0082 J\u001b\u0010\u0010\u001a\u00020\b2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\b0\u0012H\u0000¢\u0006\u0002\b\u0013J!\u0010\u0014\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00152\u0006\u0010\u000e\u001a\u00020\u000fH\u0082 J#\u0010\u0016\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u00002\u0006\u0010\u000e\u001a\u00020\u000fH\u0082 J#\u0010\u0017\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u00182\u0006\u0010\u000e\u001a\u00020\u000fH\u0082 J\u0011\u0010\u0019\u001a\u00020\b2\u0006\u0010\u0011\u001a\u00020\u001aH\u0082 J(\u0010\u001b\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u00002\u000e\b\u0002\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001cJ(\u0010\u001b\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u00182\u000e\b\u0002\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001cJ&\u0010\u001b\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u000e\b\u0002\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001cJ&\u0010\u001b\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00152\u000e\b\u0002\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001cJ&\u0010\u001b\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000f2\u000e\b\u0002\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001cJ(\u0010\u001b\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000b2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\u000e\b\u0002\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001cJ(\u0010\u001b\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u000b2\u000e\b\u0002\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001cJ#\u0010 \u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u000e\u001a\u00020\u000fH\u0082 J\b\u0010!\u001a\u00020\bH\u0004J\u0013\u0010\"\u001a\u0004\u0018\u00010\u00182\u0006\u0010\n\u001a\u00020\u000bH\u0086\u0002J\u0011\u0010#\u001a\u00020\u00182\u0006\u0010\n\u001a\u00020\u000bH\u0086 J\u0014\u0010$\u001a\b\u0012\u0004\u0012\u00020\u000b0%H\u0086 ¢\u0006\u0002\u0010&J\u0011\u0010'\u001a\u00020\r2\u0006\u0010\n\u001a\u00020\u000bH\u0086 J\u0006\u0010(\u001a\u00020\rJ\u001b\u0010)\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0000H\u0086\u0002J\u001b\u0010)\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0018H\u0086\u0002J\u0019\u0010)\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0086\u0002J\u0019\u0010)\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0015H\u0086\u0002J\u0019\u0010)\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000fH\u0086\u0002J\u001b\u0010)\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000b2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u0086\u0002J\u001b\u0010)\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u000bH\u0086\u0002J\u0019\u0010*\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0082 J\u0019\u0010+\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0015H\u0082 J\u001b\u0010,\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0000H\u0082 J\u001b\u0010-\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0018H\u0082 J\u0018\u0010.\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0000J\u0018\u0010.\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0018J\u0016\u0010.\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rJ\u0016\u0010.\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0015J\u0016\u0010.\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000fJ\u0018\u0010.\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000b2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fJ\u0018\u0010.\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u000bJ\u001b\u0010/\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u000bH\u0082 J\u0011\u00100\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000bH\u0082 R\u0010\u0010\u0002\u001a\u00020\u00038\u0002X\u0083\u0004¢\u0006\u0002\n\u0000¨\u00062"}, d2 = {"Lexpo/modules/kotlin/jni/JavaScriptObject;", "Lexpo/modules/kotlin/jni/Destructible;", "mHybridData", "Lcom/facebook/jni/HybridData;", "(Lcom/facebook/jni/HybridData;)V", "createWeak", "Lexpo/modules/kotlin/jni/JavaScriptWeakObject;", "deallocate", "", "defineBoolProperty", "name", "", "value", "", "options", "", "defineDeallocator", "deallocator", "Lkotlin/Function0;", "defineDeallocator$expo_modules_core_release", "defineDoubleProperty", "", "defineJSObjectProperty", "defineJSValueProperty", "Lexpo/modules/kotlin/jni/JavaScriptValue;", "defineNativeDeallocator", "Lexpo/modules/kotlin/jni/JNIFunctionBody;", "defineProperty", "", "Lexpo/modules/kotlin/jni/JavaScriptObject$PropertyDescriptor;", "null", "", "defineStringProperty", "finalize", "get", "getProperty", "getPropertyNames", "", "()[Ljava/lang/String;", "hasProperty", "isValid", "set", "setBoolProperty", "setDoubleProperty", "setJSObjectProperty", "setJSValueProperty", "setProperty", "setStringProperty", "unsetProperty", "PropertyDescriptor", "expo-modules-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public class JavaScriptObject implements Destructible {
    private final HybridData mHybridData;

    private final native void defineBoolProperty(String name, boolean value, int options);

    private final native void defineDoubleProperty(String name, double value, int options);

    private final native void defineJSObjectProperty(String name, JavaScriptObject value, int options);

    private final native void defineJSValueProperty(String name, JavaScriptValue value, int options);

    private final native void defineNativeDeallocator(JNIFunctionBody deallocator);

    private final native void defineStringProperty(String name, String value, int options);

    private final native void setBoolProperty(String name, boolean value);

    private final native void setDoubleProperty(String name, double value);

    private final native void setJSObjectProperty(String name, JavaScriptObject value);

    private final native void setJSValueProperty(String name, JavaScriptValue value);

    private final native void setStringProperty(String name, String value);

    private final native void unsetProperty(String name);

    public final native JavaScriptWeakObject createWeak();

    public final native JavaScriptValue getProperty(String name);

    public final native String[] getPropertyNames();

    public final native boolean hasProperty(String name);

    public JavaScriptObject(HybridData mHybridData) {
        Intrinsics.checkNotNullParameter(mHybridData, "mHybridData");
        this.mHybridData = mHybridData;
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* compiled from: JavaScriptObject.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\t¨\u0006\n"}, d2 = {"Lexpo/modules/kotlin/jni/JavaScriptObject$PropertyDescriptor;", "", "value", "", "(Ljava/lang/String;II)V", "getValue", "()I", "Configurable", "Enumerable", "Writable", "expo-modules-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class PropertyDescriptor {
        private static final /* synthetic */ EnumEntries $ENTRIES;
        private static final /* synthetic */ PropertyDescriptor[] $VALUES;
        public static final PropertyDescriptor Configurable = new PropertyDescriptor("Configurable", 0, 1);
        public static final PropertyDescriptor Enumerable = new PropertyDescriptor("Enumerable", 1, 2);
        public static final PropertyDescriptor Writable = new PropertyDescriptor("Writable", 2, 4);
        private final int value;

        private static final /* synthetic */ PropertyDescriptor[] $values() {
            return new PropertyDescriptor[]{Configurable, Enumerable, Writable};
        }

        public static EnumEntries<PropertyDescriptor> getEntries() {
            return $ENTRIES;
        }

        public static PropertyDescriptor valueOf(String str) {
            return (PropertyDescriptor) Enum.valueOf(PropertyDescriptor.class, str);
        }

        public static PropertyDescriptor[] values() {
            return (PropertyDescriptor[]) $VALUES.clone();
        }

        public final int getValue() {
            return this.value;
        }

        private PropertyDescriptor(String str, int i, int i2) {
            this.value = i2;
        }

        static {
            PropertyDescriptor[] $values = $values();
            $VALUES = $values;
            $ENTRIES = EnumEntriesKt.enumEntries($values);
        }
    }

    public final boolean isValid() {
        return this.mHybridData.isValid();
    }

    public final JavaScriptValue get(String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        if (hasProperty(name)) {
            return getProperty(name);
        }
        return null;
    }

    public final void defineDeallocator$expo_modules_core_release(final Function0<Unit> deallocator) {
        Intrinsics.checkNotNullParameter(deallocator, "deallocator");
        defineNativeDeallocator(new JNIFunctionBody() { // from class: expo.modules.kotlin.jni.JavaScriptObject$$ExternalSyntheticLambda0
            @Override // expo.modules.kotlin.jni.JNIFunctionBody
            public final Object invoke(Object[] objArr) {
                Unit defineDeallocator$lambda$0;
                defineDeallocator$lambda$0 = JavaScriptObject.defineDeallocator$lambda$0(Function0.this, objArr);
                return defineDeallocator$lambda$0;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit defineDeallocator$lambda$0(Function0 deallocator, Object[] it) {
        Intrinsics.checkNotNullParameter(deallocator, "$deallocator");
        Intrinsics.checkNotNullParameter(it, "it");
        deallocator.invoke();
        return Unit.INSTANCE;
    }

    public final void setProperty(String name, boolean value) {
        Intrinsics.checkNotNullParameter(name, "name");
        setBoolProperty(name, value);
    }

    public final void set(String name, boolean value) {
        Intrinsics.checkNotNullParameter(name, "name");
        setBoolProperty(name, value);
    }

    public final void setProperty(String name, int value) {
        Intrinsics.checkNotNullParameter(name, "name");
        setDoubleProperty(name, value);
    }

    public final void set(String name, int value) {
        Intrinsics.checkNotNullParameter(name, "name");
        setDoubleProperty(name, value);
    }

    public final void setProperty(String name, double value) {
        Intrinsics.checkNotNullParameter(name, "name");
        setDoubleProperty(name, value);
    }

    public final void set(String name, double value) {
        Intrinsics.checkNotNullParameter(name, "name");
        setDoubleProperty(name, value);
    }

    public final void setProperty(String name, String value) {
        Intrinsics.checkNotNullParameter(name, "name");
        setStringProperty(name, value);
    }

    public final void set(String name, String value) {
        Intrinsics.checkNotNullParameter(name, "name");
        setStringProperty(name, value);
    }

    public final void setProperty(String name, JavaScriptValue value) {
        Intrinsics.checkNotNullParameter(name, "name");
        setJSValueProperty(name, value);
    }

    public final void set(String name, JavaScriptValue value) {
        Intrinsics.checkNotNullParameter(name, "name");
        setJSValueProperty(name, value);
    }

    public final void setProperty(String name, JavaScriptObject value) {
        Intrinsics.checkNotNullParameter(name, "name");
        setJSObjectProperty(name, value);
    }

    public final void set(String name, JavaScriptObject value) {
        Intrinsics.checkNotNullParameter(name, "name");
        setJSObjectProperty(name, value);
    }

    public final void setProperty(String name, Void r2) {
        Intrinsics.checkNotNullParameter(name, "name");
        unsetProperty(name);
    }

    public final void set(String name, Void r2) {
        Intrinsics.checkNotNullParameter(name, "name");
        unsetProperty(name);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void defineProperty$default(JavaScriptObject javaScriptObject, String str, boolean z, List list, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: defineProperty");
        }
        if ((i & 4) != 0) {
            list = CollectionsKt.emptyList();
        }
        javaScriptObject.defineProperty(str, z, (List<? extends PropertyDescriptor>) list);
    }

    public final void defineProperty(String name, boolean value, List<? extends PropertyDescriptor> options) {
        int cppOptions;
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(options, "options");
        cppOptions = JavaScriptObjectKt.toCppOptions(options);
        defineBoolProperty(name, value, cppOptions);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void defineProperty$default(JavaScriptObject javaScriptObject, String str, int i, List list, int i2, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: defineProperty");
        }
        if ((i2 & 4) != 0) {
            list = CollectionsKt.emptyList();
        }
        javaScriptObject.defineProperty(str, i, (List<? extends PropertyDescriptor>) list);
    }

    public final void defineProperty(String name, int value, List<? extends PropertyDescriptor> options) {
        int cppOptions;
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(options, "options");
        double d = value;
        cppOptions = JavaScriptObjectKt.toCppOptions(options);
        defineDoubleProperty(name, d, cppOptions);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void defineProperty$default(JavaScriptObject javaScriptObject, String str, double d, List list, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: defineProperty");
        }
        if ((i & 4) != 0) {
            list = CollectionsKt.emptyList();
        }
        javaScriptObject.defineProperty(str, d, (List<? extends PropertyDescriptor>) list);
    }

    public final void defineProperty(String name, double value, List<? extends PropertyDescriptor> options) {
        int cppOptions;
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(options, "options");
        cppOptions = JavaScriptObjectKt.toCppOptions(options);
        defineDoubleProperty(name, value, cppOptions);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void defineProperty$default(JavaScriptObject javaScriptObject, String str, String str2, List list, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: defineProperty");
        }
        if ((i & 4) != 0) {
            list = CollectionsKt.emptyList();
        }
        javaScriptObject.defineProperty(str, str2, (List<? extends PropertyDescriptor>) list);
    }

    public final void defineProperty(String name, String value, List<? extends PropertyDescriptor> options) {
        int cppOptions;
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(options, "options");
        cppOptions = JavaScriptObjectKt.toCppOptions(options);
        defineStringProperty(name, value, cppOptions);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void defineProperty$default(JavaScriptObject javaScriptObject, String str, JavaScriptValue javaScriptValue, List list, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: defineProperty");
        }
        if ((i & 4) != 0) {
            list = CollectionsKt.emptyList();
        }
        javaScriptObject.defineProperty(str, javaScriptValue, (List<? extends PropertyDescriptor>) list);
    }

    public final void defineProperty(String name, JavaScriptValue value, List<? extends PropertyDescriptor> options) {
        int cppOptions;
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(options, "options");
        cppOptions = JavaScriptObjectKt.toCppOptions(options);
        defineJSValueProperty(name, value, cppOptions);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void defineProperty$default(JavaScriptObject javaScriptObject, String str, JavaScriptObject javaScriptObject2, List list, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: defineProperty");
        }
        if ((i & 4) != 0) {
            list = CollectionsKt.emptyList();
        }
        javaScriptObject.defineProperty(str, javaScriptObject2, (List<? extends PropertyDescriptor>) list);
    }

    public final void defineProperty(String name, JavaScriptObject value, List<? extends PropertyDescriptor> options) {
        int cppOptions;
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(options, "options");
        cppOptions = JavaScriptObjectKt.toCppOptions(options);
        defineJSObjectProperty(name, value, cppOptions);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void defineProperty$default(JavaScriptObject javaScriptObject, String str, Void r2, List list, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: defineProperty");
        }
        if ((i & 4) != 0) {
            list = CollectionsKt.emptyList();
        }
        javaScriptObject.defineProperty(str, r2, (List<? extends PropertyDescriptor>) list);
    }

    public final void defineProperty(String name, Void r2, List<? extends PropertyDescriptor> options) {
        int cppOptions;
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(options, "options");
        cppOptions = JavaScriptObjectKt.toCppOptions(options);
        defineJSObjectProperty(name, null, cppOptions);
    }

    protected final void finalize() throws Throwable {
        deallocate();
    }

    @Override // expo.modules.kotlin.jni.Destructible
    public void deallocate() {
        this.mHybridData.resetNative();
    }
}
