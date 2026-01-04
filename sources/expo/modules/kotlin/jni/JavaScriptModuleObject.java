package expo.modules.kotlin.jni;

import androidx.tracing.Trace;
import com.facebook.jni.HybridData;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.NativeMap;
import com.facebook.react.bridge.ReadableNativeMap;
import com.facebook.react.bridge.WritableNativeMap;
import expo.modules.kotlin.AppContext;
import expo.modules.kotlin.ConcatIterator;
import expo.modules.kotlin.functions.AnyFunction;
import expo.modules.kotlin.objects.ObjectDefinitionData;
import expo.modules.kotlin.objects.PropertyComponent;
import java.util.Iterator;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: JavaScriptModuleObject.kt */
@Metadata(d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000f\b\u0007\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u000b\u001a\u00020\fH\u0016J#\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00052\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0086 J\u0011\u0010\u0013\u001a\u00020\f2\u0006\u0010\u0014\u001a\u00020\u0015H\u0086 J\b\u0010\u0016\u001a\u00020\fH\u0004J\t\u0010\u0017\u001a\u00020\bH\u0082 J\u0016\u0010\u0018\u001a\u00020\u00002\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001cJ4\u0010\u001d\u001a\u00020\f2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u001e\u001a\u00020\u001f2\f\u0010 \u001a\b\u0012\u0004\u0012\u00020\"0!2\u0006\u0010#\u001a\u00020$H\u0086 ¢\u0006\u0002\u0010%JJ\u0010&\u001a\u00020\f2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010'\u001a\u00020\u00002\u0006\u0010\u001e\u001a\u00020\u001f2\f\u0010(\u001a\b\u0012\u0002\b\u0003\u0018\u00010)2\f\u0010 \u001a\b\u0012\u0004\u0012\u00020\"0!2\u0006\u0010#\u001a\u00020*H\u0086 ¢\u0006\u0002\u0010+JV\u0010,\u001a\u00020\f2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010-\u001a\u00020\u001f2\f\u0010.\u001a\b\u0012\u0004\u0012\u00020\"0!2\b\u0010/\u001a\u0004\u0018\u00010*2\u0006\u00100\u001a\u00020\u001f2\f\u00101\u001a\b\u0012\u0004\u0012\u00020\"0!2\b\u00102\u001a\u0004\u0018\u00010*H\u0086 ¢\u0006\u0002\u00103J4\u00104\u001a\u00020\f2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u001e\u001a\u00020\u001f2\f\u0010 \u001a\b\u0012\u0004\u0012\u00020\"0!2\u0006\u0010#\u001a\u00020*H\u0086 ¢\u0006\u0002\u00105J\u0011\u00106\u001a\u00020\f2\u0006\u00107\u001a\u00020\u0000H\u0086 J\b\u00108\u001a\u00020\u0005H\u0016R\u0010\u0010\u0007\u001a\u00020\b8\u0002X\u0083\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u00069"}, d2 = {"Lexpo/modules/kotlin/jni/JavaScriptModuleObject;", "Lexpo/modules/kotlin/jni/Destructible;", "jniDeallocator", "Lexpo/modules/kotlin/jni/JNIDeallocator;", "name", "", "(Lexpo/modules/kotlin/jni/JNIDeallocator;Ljava/lang/String;)V", "mHybridData", "Lcom/facebook/jni/HybridData;", "getName", "()Ljava/lang/String;", "deallocate", "", "emitEvent", "jsiContext", "Lexpo/modules/kotlin/jni/JSIContext;", "eventName", "eventBody", "Lcom/facebook/react/bridge/ReadableNativeMap;", "exportConstants", "constants", "Lcom/facebook/react/bridge/NativeMap;", "finalize", "initHybrid", "initUsingObjectDefinition", "appContext", "Lexpo/modules/kotlin/AppContext;", "definition", "Lexpo/modules/kotlin/objects/ObjectDefinitionData;", "registerAsyncFunction", "takesOwner", "", "desiredTypes", "", "Lexpo/modules/kotlin/jni/ExpectedType;", "body", "Lexpo/modules/kotlin/jni/JNIAsyncFunctionBody;", "(Ljava/lang/String;Z[Lexpo/modules/kotlin/jni/ExpectedType;Lexpo/modules/kotlin/jni/JNIAsyncFunctionBody;)V", "registerClass", "classModule", "ownerClass", "Ljava/lang/Class;", "Lexpo/modules/kotlin/jni/JNIFunctionBody;", "(Ljava/lang/String;Lexpo/modules/kotlin/jni/JavaScriptModuleObject;ZLjava/lang/Class;[Lexpo/modules/kotlin/jni/ExpectedType;Lexpo/modules/kotlin/jni/JNIFunctionBody;)V", "registerProperty", "getterTakesOwner", "getterExpectedType", "getter", "setterTakesOwner", "setterExpectedType", "setter", "(Ljava/lang/String;Z[Lexpo/modules/kotlin/jni/ExpectedType;Lexpo/modules/kotlin/jni/JNIFunctionBody;Z[Lexpo/modules/kotlin/jni/ExpectedType;Lexpo/modules/kotlin/jni/JNIFunctionBody;)V", "registerSyncFunction", "(Ljava/lang/String;Z[Lexpo/modules/kotlin/jni/ExpectedType;Lexpo/modules/kotlin/jni/JNIFunctionBody;)V", "registerViewPrototype", "viewPrototype", "toString", "expo-modules-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class JavaScriptModuleObject implements Destructible {
    private final HybridData mHybridData;
    private final String name;

    private final native HybridData initHybrid();

    public final native void emitEvent(JSIContext jsiContext, String eventName, ReadableNativeMap eventBody);

    public final native void exportConstants(NativeMap constants);

    public final String getName() {
        return this.name;
    }

    public final native void registerAsyncFunction(String name, boolean takesOwner, ExpectedType[] desiredTypes, JNIAsyncFunctionBody body);

    public final native void registerClass(String name, JavaScriptModuleObject classModule, boolean takesOwner, Class<?> ownerClass, ExpectedType[] desiredTypes, JNIFunctionBody body);

    public final native void registerProperty(String name, boolean getterTakesOwner, ExpectedType[] getterExpectedType, JNIFunctionBody getter, boolean setterTakesOwner, ExpectedType[] setterExpectedType, JNIFunctionBody setter);

    public final native void registerSyncFunction(String name, boolean takesOwner, ExpectedType[] desiredTypes, JNIFunctionBody body);

    public final native void registerViewPrototype(JavaScriptModuleObject viewPrototype);

    public JavaScriptModuleObject(JNIDeallocator jniDeallocator, String name) {
        Intrinsics.checkNotNullParameter(jniDeallocator, "jniDeallocator");
        Intrinsics.checkNotNullParameter(name, "name");
        this.name = name;
        this.mHybridData = initHybrid();
        jniDeallocator.addReference(this);
    }

    public final JavaScriptModuleObject initUsingObjectDefinition(AppContext appContext, ObjectDefinitionData definition) {
        Intrinsics.checkNotNullParameter(appContext, "appContext");
        Intrinsics.checkNotNullParameter(definition, "definition");
        WritableNativeMap makeNativeMap = Arguments.makeNativeMap(definition.getConstantsProvider().invoke());
        Trace.beginSection("[ExpoModulesCore] Exporting constants");
        try {
            Intrinsics.checkNotNull(makeNativeMap);
            exportConstants(makeNativeMap);
            Unit unit = Unit.INSTANCE;
            Trace.endSection();
            Trace.beginSection("[ExpoModulesCore] Attaching functions");
            try {
                ConcatIterator<AnyFunction> functions = definition.getFunctions();
                while (functions.hasNext()) {
                    functions.next().attachToJSObject(appContext, this);
                }
                Unit unit2 = Unit.INSTANCE;
                Trace.endSection();
                Trace.beginSection("[ExpoModulesCore] Attaching properties");
                try {
                    Iterator<Map.Entry<String, PropertyComponent>> it = definition.getProperties().entrySet().iterator();
                    while (it.hasNext()) {
                        it.next().getValue().attachToJSObject(appContext, this);
                    }
                    Unit unit3 = Unit.INSTANCE;
                    return this;
                } finally {
                }
            } finally {
            }
        } finally {
        }
    }

    protected final void finalize() throws Throwable {
        deallocate();
    }

    @Override // expo.modules.kotlin.jni.Destructible
    public void deallocate() {
        this.mHybridData.resetNative();
    }

    public String toString() {
        return "JavaScriptModuleObject_" + this.name;
    }
}
