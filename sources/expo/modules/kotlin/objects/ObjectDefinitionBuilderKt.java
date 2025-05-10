package expo.modules.kotlin.objects;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableNativeMap;
import expo.modules.kotlin.ConcatIterator;
import expo.modules.kotlin.functions.AnyFunction;
import expo.modules.kotlin.jni.JavaScriptModuleObject;
import expo.modules.kotlin.modules.Module;
import expo.modules.kotlin.modules.ModuleDefinitionBuilder;
import java.util.Iterator;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ObjectDefinitionBuilder.kt */
@Metadata(d1 = {"\u0000\"\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a)\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0017\u0010\u0003\u001a\u0013\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007H\u0086\bø\u0001\u0000\u001a)\u0010\u0000\u001a\u00020\u0001*\u00020\b2\u0017\u0010\u0003\u001a\u0013\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007H\u0086\bø\u0001\u0000\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\t"}, d2 = {"Object", "Lexpo/modules/kotlin/jni/JavaScriptModuleObject;", "Lexpo/modules/kotlin/modules/Module;", "block", "Lkotlin/Function1;", "Lexpo/modules/kotlin/objects/ObjectDefinitionBuilder;", "", "Lkotlin/ExtensionFunctionType;", "Lexpo/modules/kotlin/modules/ModuleDefinitionBuilder;", "expo-modules-core_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class ObjectDefinitionBuilderKt {
    public static final JavaScriptModuleObject Object(ModuleDefinitionBuilder moduleDefinitionBuilder, Function1<? super ObjectDefinitionBuilder, Unit> block) {
        Intrinsics.checkNotNullParameter(moduleDefinitionBuilder, "<this>");
        Intrinsics.checkNotNullParameter(block, "block");
        Module module = moduleDefinitionBuilder.getModule();
        Intrinsics.checkNotNull(module);
        ObjectDefinitionBuilder objectDefinitionBuilder = new ObjectDefinitionBuilder();
        block.invoke(objectDefinitionBuilder);
        ObjectDefinitionData buildObject = objectDefinitionBuilder.buildObject();
        JavaScriptModuleObject javaScriptModuleObject = new JavaScriptModuleObject(module.getAppContext().getJniDeallocator(), "[Anonymous Object]");
        WritableNativeMap makeNativeMap = Arguments.makeNativeMap(buildObject.getConstantsProvider().invoke());
        Intrinsics.checkNotNull(makeNativeMap);
        javaScriptModuleObject.exportConstants(makeNativeMap);
        ConcatIterator<AnyFunction> functions = buildObject.getFunctions();
        while (functions.hasNext()) {
            functions.next().attachToJSObject(module.getAppContext(), javaScriptModuleObject);
        }
        Iterator<Map.Entry<String, PropertyComponent>> it = buildObject.getProperties().entrySet().iterator();
        while (it.hasNext()) {
            it.next().getValue().attachToJSObject(module.getAppContext(), javaScriptModuleObject);
        }
        return javaScriptModuleObject;
    }

    public static final JavaScriptModuleObject Object(Module module, Function1<? super ObjectDefinitionBuilder, Unit> block) {
        Intrinsics.checkNotNullParameter(module, "<this>");
        Intrinsics.checkNotNullParameter(block, "block");
        ObjectDefinitionBuilder objectDefinitionBuilder = new ObjectDefinitionBuilder();
        block.invoke(objectDefinitionBuilder);
        ObjectDefinitionData buildObject = objectDefinitionBuilder.buildObject();
        JavaScriptModuleObject javaScriptModuleObject = new JavaScriptModuleObject(module.getAppContext().getJniDeallocator(), "[Anonymous Object]");
        WritableNativeMap makeNativeMap = Arguments.makeNativeMap(buildObject.getConstantsProvider().invoke());
        Intrinsics.checkNotNull(makeNativeMap);
        javaScriptModuleObject.exportConstants(makeNativeMap);
        ConcatIterator<AnyFunction> functions = buildObject.getFunctions();
        while (functions.hasNext()) {
            functions.next().attachToJSObject(module.getAppContext(), javaScriptModuleObject);
        }
        Iterator<Map.Entry<String, PropertyComponent>> it = buildObject.getProperties().entrySet().iterator();
        while (it.hasNext()) {
            it.next().getValue().attachToJSObject(module.getAppContext(), javaScriptModuleObject);
        }
        return javaScriptModuleObject;
    }
}
