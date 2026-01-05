package expo.modules.kotlin.modules;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import androidx.exifinterface.media.ExifInterface;
import expo.modules.kotlin.activityresult.AppContextActivityResultCaller;
import expo.modules.kotlin.classcomponent.ClassComponentBuilder;
import expo.modules.kotlin.classcomponent.ClassDefinitionData;
import expo.modules.kotlin.events.BasicEventListener;
import expo.modules.kotlin.events.EventListener;
import expo.modules.kotlin.events.EventListenerWithPayload;
import expo.modules.kotlin.events.EventListenerWithSenderAndPayload;
import expo.modules.kotlin.events.EventName;
import expo.modules.kotlin.events.OnActivityResultPayload;
import expo.modules.kotlin.objects.ObjectDefinitionBuilder;
import expo.modules.kotlin.sharedobjects.SharedObject;
import expo.modules.kotlin.types.LazyKType;
import expo.modules.kotlin.views.ViewDefinitionBuilder;
import expo.modules.kotlin.views.ViewManagerDefinition;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import kotlin.reflect.KType;

/* compiled from: ModuleDefinitionBuilder.kt */
@Metadata(d1 = {"\u0000 \u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0011\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J5\u00102\u001a\u00020\"2\u0006\u0010\u0018\u001a\u00020\u00192\u001f\b\u0002\u00103\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u00020\"05\u0012\u0004\u0012\u00020\"04¢\u0006\u0002\b$H\u0086\bø\u0001\u0000JG\u00102\u001a\u00020\"\"\n\b\u0000\u00106\u0018\u0001*\u0002072\f\u00108\u001a\b\u0012\u0004\u0012\u0002H6092\u001f\b\u0002\u00103\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u0002H605\u0012\u0004\u0012\u00020\"04¢\u0006\u0002\b$H\u0086\bø\u0001\u0000J\u000e\u0010:\u001a\u00020\"2\u0006\u0010\u0018\u001a\u00020\u0019J\u001c\u0010;\u001a\u00020\"2\u000e\b\u0004\u00103\u001a\b\u0012\u0004\u0012\u00020\"0<H\u0086\bø\u0001\u0000J\u001c\u0010=\u001a\u00020\"2\u000e\b\u0004\u00103\u001a\b\u0012\u0004\u0012\u00020\"0<H\u0086\bø\u0001\u0000J\u001c\u0010>\u001a\u00020\"2\u000e\b\u0004\u00103\u001a\b\u0012\u0004\u0012\u00020\"0<H\u0086\bø\u0001\u0000J(\u0010?\u001a\u00020\"2\u001a\b\u0004\u00103\u001a\u0014\u0012\u0004\u0012\u00020@\u0012\u0004\u0012\u00020A\u0012\u0004\u0012\u00020\"0\u001fH\u0086\bø\u0001\u0000J\u001c\u0010B\u001a\u00020\"2\u000e\b\u0004\u00103\u001a\b\u0012\u0004\u0012\u00020\"0<H\u0086\bø\u0001\u0000J\u001c\u0010C\u001a\u00020\"2\u000e\b\u0004\u00103\u001a\b\u0012\u0004\u0012\u00020\"0<H\u0086\bø\u0001\u0000J\"\u0010D\u001a\u00020\"2\u0014\b\u0004\u00103\u001a\u000e\u0012\u0004\u0012\u00020E\u0012\u0004\u0012\u00020\"04H\u0086\bø\u0001\u0000J4\u0010F\u001a\u00020\"2'\u00103\u001a#\b\u0001\u0012\u0004\u0012\u00020 \u0012\n\u0012\b\u0012\u0004\u0012\u00020\"0!\u0012\u0006\u0012\u0004\u0018\u00010#0\u001f¢\u0006\u0002\b$¢\u0006\u0002\u0010)JE\u0010G\u001a\u00020\"\"\n\b\u0000\u0010H\u0018\u0001*\u00020I2\f\u0010J\u001a\b\u0012\u0004\u0012\u0002HH092\u001d\u00103\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u0002HH0K\u0012\u0004\u0012\u00020\"04¢\u0006\u0002\b$H\u0086\bø\u0001\u0000J\u0006\u0010L\u001a\u00020MR*\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00068\u0000@\u0000X\u0081\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\b\u0010\t\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR(\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00110\u000f8\u0000X\u0081\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0012\u0010\t\u001a\u0004\b\u0013\u0010\u0014R\u001e\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0000X\u0081\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0015\u0010\t\u001a\u0004\b\u0016\u0010\u0017R\u001c\u0010\u0018\u001a\u0004\u0018\u00010\u0019X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dRI\u0010\u001e\u001a%\b\u0001\u0012\u0004\u0012\u00020 \u0012\n\u0012\b\u0012\u0004\u0012\u00020\"0!\u0012\u0006\u0012\u0004\u0018\u00010#\u0018\u00010\u001f¢\u0006\u0002\b$8\u0000@\u0000X\u0081\u000e¢\u0006\u0016\n\u0002\u0010*\u0012\u0004\b%\u0010\t\u001a\u0004\b&\u0010'\"\u0004\b(\u0010)R&\u0010+\u001a\u0004\u0018\u00010,8\u0000@\u0000X\u0081\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b-\u0010\t\u001a\u0004\b.\u0010/\"\u0004\b0\u00101\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006N"}, d2 = {"Lexpo/modules/kotlin/modules/ModuleDefinitionBuilder;", "Lexpo/modules/kotlin/objects/ObjectDefinitionBuilder;", "module", "Lexpo/modules/kotlin/modules/Module;", "(Lexpo/modules/kotlin/modules/Module;)V", "classData", "", "Lexpo/modules/kotlin/classcomponent/ClassDefinitionData;", "getClassData$annotations", "()V", "getClassData", "()Ljava/util/List;", "setClassData", "(Ljava/util/List;)V", "eventListeners", "", "Lexpo/modules/kotlin/events/EventName;", "Lexpo/modules/kotlin/events/EventListener;", "getEventListeners$annotations", "getEventListeners", "()Ljava/util/Map;", "getModule$annotations", "getModule", "()Lexpo/modules/kotlin/modules/Module;", "name", "", "getName$expo_modules_core_release", "()Ljava/lang/String;", "setName$expo_modules_core_release", "(Ljava/lang/String;)V", "registerContracts", "Lkotlin/Function2;", "Lexpo/modules/kotlin/activityresult/AppContextActivityResultCaller;", "Lkotlin/coroutines/Continuation;", "", "", "Lkotlin/ExtensionFunctionType;", "getRegisterContracts$annotations", "getRegisterContracts", "()Lkotlin/jvm/functions/Function2;", "setRegisterContracts", "(Lkotlin/jvm/functions/Function2;)V", "Lkotlin/jvm/functions/Function2;", "viewManagerDefinition", "Lexpo/modules/kotlin/views/ViewManagerDefinition;", "getViewManagerDefinition$annotations", "getViewManagerDefinition", "()Lexpo/modules/kotlin/views/ViewManagerDefinition;", "setViewManagerDefinition", "(Lexpo/modules/kotlin/views/ViewManagerDefinition;)V", "Class", "body", "Lkotlin/Function1;", "Lexpo/modules/kotlin/classcomponent/ClassComponentBuilder;", "SharedObjectType", "Lexpo/modules/kotlin/sharedobjects/SharedObject;", "sharedObjectClass", "Lkotlin/reflect/KClass;", "Name", "OnActivityDestroys", "Lkotlin/Function0;", "OnActivityEntersBackground", "OnActivityEntersForeground", "OnActivityResult", "Landroid/app/Activity;", "Lexpo/modules/kotlin/events/OnActivityResultPayload;", "OnCreate", "OnDestroy", "OnNewIntent", "Landroid/content/Intent;", "RegisterActivityContracts", "View", ExifInterface.GPS_DIRECTION_TRUE, "Landroid/view/View;", "viewClass", "Lexpo/modules/kotlin/views/ViewDefinitionBuilder;", "buildModule", "Lexpo/modules/kotlin/modules/ModuleDefinitionData;", "expo-modules-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
@DefinitionMarker
/* loaded from: classes2.dex */
public final class ModuleDefinitionBuilder extends ObjectDefinitionBuilder {
    private List<ClassDefinitionData> classData;
    private final Map<EventName, EventListener> eventListeners;
    private final Module module;
    private String name;
    private Function2<? super AppContextActivityResultCaller, ? super Continuation<? super Unit>, ? extends Object> registerContracts;
    private ViewManagerDefinition viewManagerDefinition;

    /* JADX WARN: Multi-variable type inference failed */
    public ModuleDefinitionBuilder() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    public static /* synthetic */ void getClassData$annotations() {
    }

    public static /* synthetic */ void getEventListeners$annotations() {
    }

    public static /* synthetic */ void getModule$annotations() {
    }

    public static /* synthetic */ void getRegisterContracts$annotations() {
    }

    public static /* synthetic */ void getViewManagerDefinition$annotations() {
    }

    public final void Name(String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        this.name = name;
    }

    public final void RegisterActivityContracts(Function2<? super AppContextActivityResultCaller, ? super Continuation<? super Unit>, ? extends Object> body) {
        Intrinsics.checkNotNullParameter(body, "body");
        this.registerContracts = body;
    }

    public final List<ClassDefinitionData> getClassData() {
        return this.classData;
    }

    public final Map<EventName, EventListener> getEventListeners() {
        return this.eventListeners;
    }

    public final Module getModule() {
        return this.module;
    }

    /* renamed from: getName$expo_modules_core_release, reason: from getter */
    public final String getName() {
        return this.name;
    }

    public final Function2<AppContextActivityResultCaller, Continuation<? super Unit>, Object> getRegisterContracts() {
        return this.registerContracts;
    }

    public final ViewManagerDefinition getViewManagerDefinition() {
        return this.viewManagerDefinition;
    }

    public final void setClassData(List<ClassDefinitionData> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.classData = list;
    }

    public final void setName$expo_modules_core_release(String str) {
        this.name = str;
    }

    public final void setRegisterContracts(Function2<? super AppContextActivityResultCaller, ? super Continuation<? super Unit>, ? extends Object> function2) {
        this.registerContracts = function2;
    }

    public final void setViewManagerDefinition(ViewManagerDefinition viewManagerDefinition) {
        this.viewManagerDefinition = viewManagerDefinition;
    }

    public ModuleDefinitionBuilder(Module module) {
        this.module = module;
        this.eventListeners = new LinkedHashMap();
        this.classData = new ArrayList();
    }

    public /* synthetic */ ModuleDefinitionBuilder(Module module, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : module);
    }

    public final ModuleDefinitionData buildModule() {
        Class<?> cls;
        String str = this.name;
        if (str == null) {
            Module module = this.module;
            str = (module == null || (cls = module.getClass()) == null) ? null : cls.getSimpleName();
        }
        String str2 = str;
        if (str2 != null) {
            return new ModuleDefinitionData(str2, buildObject(), this.viewManagerDefinition, this.eventListeners, this.registerContracts, this.classData);
        }
        throw new IllegalArgumentException("Required value was null.".toString());
    }

    public final /* synthetic */ <T extends View> void View(KClass<T> viewClass, Function1<? super ViewDefinitionBuilder<T>, Unit> body) {
        Intrinsics.checkNotNullParameter(viewClass, "viewClass");
        Intrinsics.checkNotNullParameter(body, "body");
        if (getViewManagerDefinition() != null) {
            throw new IllegalArgumentException("The module definition may have exported only one view manager.".toString());
        }
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(View.class);
        Intrinsics.needClassReification();
        ViewDefinitionBuilder viewDefinitionBuilder = new ViewDefinitionBuilder(viewClass, new LazyKType(orCreateKotlinClass, false, new Function0<KType>() { // from class: expo.modules.kotlin.modules.ModuleDefinitionBuilder$View$viewDefinitionBuilder$1
            @Override // kotlin.jvm.functions.Function0
            public final KType invoke() {
                Intrinsics.reifiedOperationMarker(6, ExifInterface.GPS_DIRECTION_TRUE);
                return null;
            }
        }, 2, null));
        body.invoke(viewDefinitionBuilder);
        setViewManagerDefinition(viewDefinitionBuilder.build());
    }

    public final void OnCreate(final Function0<Unit> body) {
        Intrinsics.checkNotNullParameter(body, "body");
        getEventListeners().put(EventName.MODULE_CREATE, new BasicEventListener(EventName.MODULE_CREATE, new Function0<Unit>() { // from class: expo.modules.kotlin.modules.ModuleDefinitionBuilder$OnCreate$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                body.invoke();
            }
        }));
    }

    public final void OnDestroy(final Function0<Unit> body) {
        Intrinsics.checkNotNullParameter(body, "body");
        getEventListeners().put(EventName.MODULE_DESTROY, new BasicEventListener(EventName.MODULE_DESTROY, new Function0<Unit>() { // from class: expo.modules.kotlin.modules.ModuleDefinitionBuilder$OnDestroy$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                body.invoke();
            }
        }));
    }

    public final void OnActivityEntersForeground(final Function0<Unit> body) {
        Intrinsics.checkNotNullParameter(body, "body");
        getEventListeners().put(EventName.ACTIVITY_ENTERS_FOREGROUND, new BasicEventListener(EventName.ACTIVITY_ENTERS_FOREGROUND, new Function0<Unit>() { // from class: expo.modules.kotlin.modules.ModuleDefinitionBuilder$OnActivityEntersForeground$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                body.invoke();
            }
        }));
    }

    public final void OnActivityEntersBackground(final Function0<Unit> body) {
        Intrinsics.checkNotNullParameter(body, "body");
        getEventListeners().put(EventName.ACTIVITY_ENTERS_BACKGROUND, new BasicEventListener(EventName.ACTIVITY_ENTERS_BACKGROUND, new Function0<Unit>() { // from class: expo.modules.kotlin.modules.ModuleDefinitionBuilder$OnActivityEntersBackground$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                body.invoke();
            }
        }));
    }

    public final void OnActivityDestroys(final Function0<Unit> body) {
        Intrinsics.checkNotNullParameter(body, "body");
        getEventListeners().put(EventName.ACTIVITY_DESTROYS, new BasicEventListener(EventName.ACTIVITY_DESTROYS, new Function0<Unit>() { // from class: expo.modules.kotlin.modules.ModuleDefinitionBuilder$OnActivityDestroys$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                body.invoke();
            }
        }));
    }

    public final void OnNewIntent(final Function1<? super Intent, Unit> body) {
        Intrinsics.checkNotNullParameter(body, "body");
        getEventListeners().put(EventName.ON_NEW_INTENT, new EventListenerWithPayload(EventName.ON_NEW_INTENT, new Function1<Intent, Unit>() { // from class: expo.modules.kotlin.modules.ModuleDefinitionBuilder$OnNewIntent$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Intent intent) {
                invoke2(intent);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Intent it) {
                Intrinsics.checkNotNullParameter(it, "it");
                body.invoke(it);
            }
        }));
    }

    public final void OnActivityResult(final Function2<? super Activity, ? super OnActivityResultPayload, Unit> body) {
        Intrinsics.checkNotNullParameter(body, "body");
        getEventListeners().put(EventName.ON_ACTIVITY_RESULT, new EventListenerWithSenderAndPayload(EventName.ON_ACTIVITY_RESULT, new Function2<Activity, OnActivityResultPayload, Unit>() { // from class: expo.modules.kotlin.modules.ModuleDefinitionBuilder$OnActivityResult$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Activity activity, OnActivityResultPayload onActivityResultPayload) {
                invoke2(activity, onActivityResultPayload);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Activity sender, OnActivityResultPayload payload) {
                Intrinsics.checkNotNullParameter(sender, "sender");
                Intrinsics.checkNotNullParameter(payload, "payload");
                body.invoke(sender, payload);
            }
        }));
    }

    public static /* synthetic */ void Class$default(ModuleDefinitionBuilder moduleDefinitionBuilder, String name, Function1 body, int i, Object obj) {
        if ((i & 2) != 0) {
            body = new Function1<ClassComponentBuilder<Unit>, Unit>() { // from class: expo.modules.kotlin.modules.ModuleDefinitionBuilder$Class$1
                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(ClassComponentBuilder<Unit> classComponentBuilder) {
                    Intrinsics.checkNotNullParameter(classComponentBuilder, "$this$null");
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(ClassComponentBuilder<Unit> classComponentBuilder) {
                    invoke2(classComponentBuilder);
                    return Unit.INSTANCE;
                }
            };
        }
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(body, "body");
        ClassComponentBuilder classComponentBuilder = new ClassComponentBuilder(name, Reflection.getOrCreateKotlinClass(Unit.class), Reflection.typeOf(Unit.class));
        body.invoke(classComponentBuilder);
        moduleDefinitionBuilder.getClassData().add(classComponentBuilder.buildClass());
    }

    public final void Class(String name, Function1<? super ClassComponentBuilder<Unit>, Unit> body) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(body, "body");
        ClassComponentBuilder classComponentBuilder = new ClassComponentBuilder(name, Reflection.getOrCreateKotlinClass(Unit.class), Reflection.typeOf(Unit.class));
        body.invoke(classComponentBuilder);
        getClassData().add(classComponentBuilder.buildClass());
    }

    public static /* synthetic */ void Class$default(ModuleDefinitionBuilder moduleDefinitionBuilder, KClass sharedObjectClass, Function1 body, int i, Object obj) {
        if ((i & 2) != 0) {
            Intrinsics.needClassReification();
            body = ModuleDefinitionBuilder$Class$2.INSTANCE;
        }
        Intrinsics.checkNotNullParameter(sharedObjectClass, "sharedObjectClass");
        Intrinsics.checkNotNullParameter(body, "body");
        String simpleName = JvmClassMappingKt.getJavaClass(sharedObjectClass).getSimpleName();
        Intrinsics.checkNotNullExpressionValue(simpleName, "getSimpleName(...)");
        Intrinsics.reifiedOperationMarker(6, "SharedObjectType");
        ClassComponentBuilder classComponentBuilder = new ClassComponentBuilder(simpleName, sharedObjectClass, null);
        body.invoke(classComponentBuilder);
        moduleDefinitionBuilder.getClassData().add(classComponentBuilder.buildClass());
    }

    public final /* synthetic */ <SharedObjectType extends SharedObject> void Class(KClass<SharedObjectType> sharedObjectClass, Function1<? super ClassComponentBuilder<SharedObjectType>, Unit> body) {
        Intrinsics.checkNotNullParameter(sharedObjectClass, "sharedObjectClass");
        Intrinsics.checkNotNullParameter(body, "body");
        String simpleName = JvmClassMappingKt.getJavaClass((KClass) sharedObjectClass).getSimpleName();
        Intrinsics.checkNotNullExpressionValue(simpleName, "getSimpleName(...)");
        Intrinsics.reifiedOperationMarker(6, "SharedObjectType");
        ClassComponentBuilder classComponentBuilder = new ClassComponentBuilder(simpleName, sharedObjectClass, null);
        body.invoke(classComponentBuilder);
        getClassData().add(classComponentBuilder.buildClass());
    }
}
