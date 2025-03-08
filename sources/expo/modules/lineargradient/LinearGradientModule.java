package expo.modules.lineargradient;

import androidx.tracing.Trace;
import expo.modules.kotlin.modules.Module;
import expo.modules.kotlin.modules.ModuleDefinitionBuilder;
import expo.modules.kotlin.modules.ModuleDefinitionData;
import expo.modules.kotlin.types.AnyType;
import expo.modules.kotlin.types.LazyKType;
import expo.modules.kotlin.views.ConcreteViewProp;
import expo.modules.kotlin.views.ViewDefinitionBuilder;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import kotlin.reflect.KType;
import kotlin.reflect.KTypeProjection;

/* compiled from: LinearGradientModule.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016¨\u0006\u0005"}, d2 = {"Lexpo/modules/lineargradient/LinearGradientModule;", "Lexpo/modules/kotlin/modules/Module;", "()V", "definition", "Lexpo/modules/kotlin/modules/ModuleDefinitionData;", "expo-linear-gradient_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class LinearGradientModule extends Module {
    @Override // expo.modules.kotlin.modules.Module
    public ModuleDefinitionData definition() {
        LinearGradientModule linearGradientModule = this;
        Trace.beginSection("[ExpoModulesCore] " + (linearGradientModule.getClass() + ".ModuleDefinition"));
        try {
            ModuleDefinitionBuilder moduleDefinitionBuilder = new ModuleDefinitionBuilder(linearGradientModule);
            moduleDefinitionBuilder.Name("ExpoLinearGradient");
            KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(LinearGradientView.class);
            if (moduleDefinitionBuilder.getViewManagerDefinition() != null) {
                throw new IllegalArgumentException("The module definition may have exported only one view manager.".toString());
            }
            ViewDefinitionBuilder viewDefinitionBuilder = new ViewDefinitionBuilder(orCreateKotlinClass, new LazyKType(Reflection.getOrCreateKotlinClass(LinearGradientView.class), false, new Function0<KType>() { // from class: expo.modules.lineargradient.LinearGradientModule$definition$lambda$1$$inlined$View$1
                @Override // kotlin.jvm.functions.Function0
                public final KType invoke() {
                    return Reflection.typeOf(LinearGradientView.class);
                }
            }, 2, null));
            viewDefinitionBuilder.getProps().put("colors", new ConcreteViewProp("colors", new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(int[].class), false, new Function0<KType>() { // from class: expo.modules.lineargradient.LinearGradientModule$definition$lambda$1$lambda$0$$inlined$Prop$1
                @Override // kotlin.jvm.functions.Function0
                public final KType invoke() {
                    return Reflection.typeOf(int[].class);
                }
            })), new Function2<LinearGradientView, int[], Unit>() { // from class: expo.modules.lineargradient.LinearGradientModule$definition$1$1$1
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(LinearGradientView linearGradientView, int[] iArr) {
                    invoke2(linearGradientView, iArr);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(LinearGradientView view, int[] colors) {
                    Intrinsics.checkNotNullParameter(view, "view");
                    Intrinsics.checkNotNullParameter(colors, "colors");
                    view.setColors(colors);
                }
            }));
            viewDefinitionBuilder.getProps().put("locations", new ConcreteViewProp("locations", new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(float[].class), true, new Function0<KType>() { // from class: expo.modules.lineargradient.LinearGradientModule$definition$lambda$1$lambda$0$$inlined$Prop$2
                @Override // kotlin.jvm.functions.Function0
                public final KType invoke() {
                    return Reflection.nullableTypeOf(float[].class);
                }
            })), new Function2<LinearGradientView, float[], Unit>() { // from class: expo.modules.lineargradient.LinearGradientModule$definition$1$1$2
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(LinearGradientView linearGradientView, float[] fArr) {
                    invoke2(linearGradientView, fArr);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(LinearGradientView view, float[] fArr) {
                    Intrinsics.checkNotNullParameter(view, "view");
                    if (fArr != null) {
                        view.setLocations(fArr);
                    }
                }
            }));
            viewDefinitionBuilder.getProps().put("startPoint", new ConcreteViewProp("startPoint", new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(Pair.class), true, new Function0<KType>() { // from class: expo.modules.lineargradient.LinearGradientModule$definition$lambda$1$lambda$0$$inlined$Prop$3
                @Override // kotlin.jvm.functions.Function0
                public final KType invoke() {
                    return Reflection.nullableTypeOf(Pair.class, KTypeProjection.INSTANCE.invariant(Reflection.typeOf(Float.TYPE)), KTypeProjection.INSTANCE.invariant(Reflection.typeOf(Float.TYPE)));
                }
            })), new Function2<LinearGradientView, Pair<? extends Float, ? extends Float>, Unit>() { // from class: expo.modules.lineargradient.LinearGradientModule$definition$1$1$3
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(LinearGradientView linearGradientView, Pair<? extends Float, ? extends Float> pair) {
                    invoke2(linearGradientView, (Pair<Float, Float>) pair);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(LinearGradientView view, Pair<Float, Float> pair) {
                    Intrinsics.checkNotNullParameter(view, "view");
                    view.setStartPosition(pair != null ? pair.getFirst().floatValue() : 0.5f, pair != null ? pair.getSecond().floatValue() : 0.0f);
                }
            }));
            viewDefinitionBuilder.getProps().put("endPoint", new ConcreteViewProp("endPoint", new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(Pair.class), true, new Function0<KType>() { // from class: expo.modules.lineargradient.LinearGradientModule$definition$lambda$1$lambda$0$$inlined$Prop$4
                @Override // kotlin.jvm.functions.Function0
                public final KType invoke() {
                    return Reflection.nullableTypeOf(Pair.class, KTypeProjection.INSTANCE.invariant(Reflection.typeOf(Float.TYPE)), KTypeProjection.INSTANCE.invariant(Reflection.typeOf(Float.TYPE)));
                }
            })), new Function2<LinearGradientView, Pair<? extends Float, ? extends Float>, Unit>() { // from class: expo.modules.lineargradient.LinearGradientModule$definition$1$1$4
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(LinearGradientView linearGradientView, Pair<? extends Float, ? extends Float> pair) {
                    invoke2(linearGradientView, (Pair<Float, Float>) pair);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(LinearGradientView view, Pair<Float, Float> pair) {
                    Intrinsics.checkNotNullParameter(view, "view");
                    view.setEndPosition(pair != null ? pair.getFirst().floatValue() : 0.5f, pair != null ? pair.getSecond().floatValue() : 1.0f);
                }
            }));
            viewDefinitionBuilder.getProps().put("borderRadii", new ConcreteViewProp("borderRadii", new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(float[].class), true, new Function0<KType>() { // from class: expo.modules.lineargradient.LinearGradientModule$definition$lambda$1$lambda$0$$inlined$Prop$5
                @Override // kotlin.jvm.functions.Function0
                public final KType invoke() {
                    return Reflection.nullableTypeOf(float[].class);
                }
            })), new Function2<LinearGradientView, float[], Unit>() { // from class: expo.modules.lineargradient.LinearGradientModule$definition$1$1$5
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(LinearGradientView linearGradientView, float[] fArr) {
                    invoke2(linearGradientView, fArr);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(LinearGradientView view, float[] fArr) {
                    Intrinsics.checkNotNullParameter(view, "view");
                    if (fArr == null) {
                        float[] fArr2 = new float[8];
                        for (int i = 0; i < 8; i++) {
                            fArr2[i] = 0.0f;
                        }
                        fArr = fArr2;
                    }
                    view.setBorderRadii(fArr);
                }
            }));
            viewDefinitionBuilder.getProps().put("dither", new ConcreteViewProp("dither", new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(Boolean.class), true, new Function0<KType>() { // from class: expo.modules.lineargradient.LinearGradientModule$definition$lambda$1$lambda$0$$inlined$Prop$6
                @Override // kotlin.jvm.functions.Function0
                public final KType invoke() {
                    return Reflection.nullableTypeOf(Boolean.class);
                }
            })), new Function2<LinearGradientView, Boolean, Unit>() { // from class: expo.modules.lineargradient.LinearGradientModule$definition$1$1$6
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(LinearGradientView linearGradientView, Boolean bool) {
                    invoke2(linearGradientView, bool);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(LinearGradientView view, Boolean bool) {
                    Intrinsics.checkNotNullParameter(view, "view");
                    view.setDither(bool != null ? bool.booleanValue() : true);
                }
            }));
            moduleDefinitionBuilder.setViewManagerDefinition(viewDefinitionBuilder.build());
            return moduleDefinitionBuilder.buildModule();
        } finally {
            Trace.endSection();
        }
    }
}
