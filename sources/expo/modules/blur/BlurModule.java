package expo.modules.blur;

import android.view.View;
import androidx.tracing.Trace;
import expo.modules.blur.enums.BlurMethod;
import expo.modules.blur.enums.TintStyle;
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
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import kotlin.reflect.KType;

/* compiled from: BlurModule.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016¨\u0006\u0005"}, d2 = {"Lexpo/modules/blur/BlurModule;", "Lexpo/modules/kotlin/modules/Module;", "()V", "definition", "Lexpo/modules/kotlin/modules/ModuleDefinitionData;", "expo-blur_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class BlurModule extends Module {
    @Override // expo.modules.kotlin.modules.Module
    public ModuleDefinitionData definition() {
        BlurModule blurModule = this;
        Trace.beginSection("[ExpoModulesCore] " + (blurModule.getClass() + ".ModuleDefinition"));
        try {
            ModuleDefinitionBuilder moduleDefinitionBuilder = new ModuleDefinitionBuilder(blurModule);
            moduleDefinitionBuilder.Name("ExpoBlurView");
            KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(ExpoBlurView.class);
            if (moduleDefinitionBuilder.getViewManagerDefinition() != null) {
                throw new IllegalArgumentException("The module definition may have exported only one view manager.".toString());
            }
            ViewDefinitionBuilder viewDefinitionBuilder = new ViewDefinitionBuilder(orCreateKotlinClass, new LazyKType(Reflection.getOrCreateKotlinClass(ExpoBlurView.class), false, new Function0<KType>() { // from class: expo.modules.blur.BlurModule$definition$lambda$2$$inlined$View$1
                @Override // kotlin.jvm.functions.Function0
                public final KType invoke() {
                    return Reflection.typeOf(ExpoBlurView.class);
                }
            }, 2, null));
            viewDefinitionBuilder.getProps().put("intensity", new ConcreteViewProp("intensity", new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(Float.class), false, new Function0<KType>() { // from class: expo.modules.blur.BlurModule$definition$lambda$2$lambda$1$$inlined$Prop$1
                @Override // kotlin.jvm.functions.Function0
                public final KType invoke() {
                    return Reflection.typeOf(Float.TYPE);
                }
            })), new Function2<ExpoBlurView, Float, Unit>() { // from class: expo.modules.blur.BlurModule$definition$1$1$1
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(ExpoBlurView expoBlurView, Float f) {
                    invoke(expoBlurView, f.floatValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(ExpoBlurView view, float f) {
                    Intrinsics.checkNotNullParameter(view, "view");
                    view.setBlurRadius(f);
                }
            }));
            viewDefinitionBuilder.getProps().put("tint", new ConcreteViewProp("tint", new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(TintStyle.class), false, new Function0<KType>() { // from class: expo.modules.blur.BlurModule$definition$lambda$2$lambda$1$$inlined$Prop$2
                @Override // kotlin.jvm.functions.Function0
                public final KType invoke() {
                    return Reflection.typeOf(TintStyle.class);
                }
            })), new Function2<ExpoBlurView, TintStyle, Unit>() { // from class: expo.modules.blur.BlurModule$definition$1$1$2
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(ExpoBlurView expoBlurView, TintStyle tintStyle) {
                    invoke2(expoBlurView, tintStyle);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(ExpoBlurView view, TintStyle tint) {
                    Intrinsics.checkNotNullParameter(view, "view");
                    Intrinsics.checkNotNullParameter(tint, "tint");
                    view.setTint$expo_blur_release(tint);
                }
            }));
            viewDefinitionBuilder.getProps().put("blurReductionFactor", new ConcreteViewProp("blurReductionFactor", new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(Float.class), false, new Function0<KType>() { // from class: expo.modules.blur.BlurModule$definition$lambda$2$lambda$1$$inlined$Prop$3
                @Override // kotlin.jvm.functions.Function0
                public final KType invoke() {
                    return Reflection.typeOf(Float.TYPE);
                }
            })), new Function2<ExpoBlurView, Float, Unit>() { // from class: expo.modules.blur.BlurModule$definition$1$1$3
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(ExpoBlurView expoBlurView, Float f) {
                    invoke(expoBlurView, f.floatValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(ExpoBlurView view, float f) {
                    Intrinsics.checkNotNullParameter(view, "view");
                    view.applyBlurReduction(f);
                }
            }));
            viewDefinitionBuilder.getProps().put("experimentalBlurMethod", new ConcreteViewProp("experimentalBlurMethod", new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(BlurMethod.class), false, new Function0<KType>() { // from class: expo.modules.blur.BlurModule$definition$lambda$2$lambda$1$$inlined$Prop$4
                @Override // kotlin.jvm.functions.Function0
                public final KType invoke() {
                    return Reflection.typeOf(BlurMethod.class);
                }
            })), new Function2<ExpoBlurView, BlurMethod, Unit>() { // from class: expo.modules.blur.BlurModule$definition$1$1$4
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(ExpoBlurView expoBlurView, BlurMethod blurMethod) {
                    invoke2(expoBlurView, blurMethod);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(ExpoBlurView view, BlurMethod experimentalBlurMethod) {
                    Intrinsics.checkNotNullParameter(view, "view");
                    Intrinsics.checkNotNullParameter(experimentalBlurMethod, "experimentalBlurMethod");
                    view.setBlurMethod(experimentalBlurMethod);
                }
            }));
            viewDefinitionBuilder.setOnViewDidUpdateProps(new Function1<View, Unit>() { // from class: expo.modules.blur.BlurModule$definition$lambda$2$lambda$1$$inlined$OnViewDidUpdateProps$1
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(View view) {
                    invoke2(view);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(View it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    ((ExpoBlurView) it).applyTint();
                }
            });
            moduleDefinitionBuilder.setViewManagerDefinition(viewDefinitionBuilder.build());
            return moduleDefinitionBuilder.buildModule();
        } finally {
            Trace.endSection();
        }
    }
}
