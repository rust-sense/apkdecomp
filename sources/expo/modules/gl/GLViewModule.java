package expo.modules.gl;

import androidx.tracing.Trace;
import expo.modules.kotlin.modules.Module;
import expo.modules.kotlin.modules.ModuleDefinitionBuilder;
import expo.modules.kotlin.modules.ModuleDefinitionData;
import expo.modules.kotlin.types.AnyType;
import expo.modules.kotlin.types.LazyKType;
import expo.modules.kotlin.views.ConcreteViewProp;
import expo.modules.kotlin.views.ViewDefinitionBuilder;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import kotlin.reflect.KType;

/* compiled from: GLViewModule.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016¨\u0006\u0005"}, d2 = {"Lexpo/modules/gl/GLViewModule;", "Lexpo/modules/kotlin/modules/Module;", "()V", "definition", "Lexpo/modules/kotlin/modules/ModuleDefinitionData;", "expo-gl_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class GLViewModule extends Module {
    @Override // expo.modules.kotlin.modules.Module
    public ModuleDefinitionData definition() {
        GLViewModule gLViewModule = this;
        Trace.beginSection("[ExpoModulesCore] " + (gLViewModule.getClass() + ".ModuleDefinition"));
        try {
            ModuleDefinitionBuilder moduleDefinitionBuilder = new ModuleDefinitionBuilder(gLViewModule);
            moduleDefinitionBuilder.Name("ExponentGLView");
            KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(GLView.class);
            if (moduleDefinitionBuilder.getViewManagerDefinition() != null) {
                throw new IllegalArgumentException("The module definition may have exported only one view manager.".toString());
            }
            ViewDefinitionBuilder viewDefinitionBuilder = new ViewDefinitionBuilder(orCreateKotlinClass, new LazyKType(Reflection.getOrCreateKotlinClass(GLView.class), false, new Function0<KType>() { // from class: expo.modules.gl.GLViewModule$definition$lambda$1$$inlined$View$1
                @Override // kotlin.jvm.functions.Function0
                public final KType invoke() {
                    return Reflection.typeOf(GLView.class);
                }
            }, 2, null));
            viewDefinitionBuilder.Events("onSurfaceCreate");
            viewDefinitionBuilder.getProps().put("enableExperimentalWorkletSupport", new ConcreteViewProp("enableExperimentalWorkletSupport", new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(Boolean.class), true, new Function0<KType>() { // from class: expo.modules.gl.GLViewModule$definition$lambda$1$lambda$0$$inlined$Prop$1
                @Override // kotlin.jvm.functions.Function0
                public final KType invoke() {
                    return Reflection.nullableTypeOf(Boolean.class);
                }
            })), new Function2<GLView, Boolean, Unit>() { // from class: expo.modules.gl.GLViewModule$definition$1$1$1
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(GLView gLView, Boolean bool) {
                    invoke2(gLView, bool);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(GLView view, Boolean bool) {
                    Intrinsics.checkNotNullParameter(view, "view");
                    view.setEnableExperimentalWorkletSupport(bool != null ? bool.booleanValue() : false);
                }
            }));
            moduleDefinitionBuilder.setViewManagerDefinition(viewDefinitionBuilder.build());
            return moduleDefinitionBuilder.buildModule();
        } finally {
            Trace.endSection();
        }
    }
}
