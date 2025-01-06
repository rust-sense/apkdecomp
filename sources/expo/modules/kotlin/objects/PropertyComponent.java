package expo.modules.kotlin.objects;

import expo.modules.kotlin.AppContext;
import expo.modules.kotlin.functions.SyncFunctionComponent;
import expo.modules.kotlin.jni.ExpectedType;
import expo.modules.kotlin.jni.JNIFunctionBody;
import expo.modules.kotlin.jni.JavaScriptModuleObject;
import expo.modules.kotlin.types.JSTypeConverter;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PropertyComponent.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0007J\u0016\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\t¨\u0006\u0013"}, d2 = {"Lexpo/modules/kotlin/objects/PropertyComponent;", "", "name", "", "getter", "Lexpo/modules/kotlin/functions/SyncFunctionComponent;", "setter", "(Ljava/lang/String;Lexpo/modules/kotlin/functions/SyncFunctionComponent;Lexpo/modules/kotlin/functions/SyncFunctionComponent;)V", "getGetter", "()Lexpo/modules/kotlin/functions/SyncFunctionComponent;", "getName", "()Ljava/lang/String;", "getSetter", "attachToJSObject", "", "appContext", "Lexpo/modules/kotlin/AppContext;", "jsObject", "Lexpo/modules/kotlin/jni/JavaScriptModuleObject;", "expo-modules-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class PropertyComponent {
    private final SyncFunctionComponent getter;
    private final String name;
    private final SyncFunctionComponent setter;

    public final SyncFunctionComponent getGetter() {
        return this.getter;
    }

    public final String getName() {
        return this.name;
    }

    public final SyncFunctionComponent getSetter() {
        return this.setter;
    }

    public PropertyComponent(String name, SyncFunctionComponent syncFunctionComponent, SyncFunctionComponent syncFunctionComponent2) {
        Intrinsics.checkNotNullParameter(name, "name");
        this.name = name;
        this.getter = syncFunctionComponent;
        this.setter = syncFunctionComponent2;
    }

    public /* synthetic */ PropertyComponent(String str, SyncFunctionComponent syncFunctionComponent, SyncFunctionComponent syncFunctionComponent2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i & 2) != 0 ? null : syncFunctionComponent, (i & 4) != 0 ? null : syncFunctionComponent2);
    }

    public final void attachToJSObject(final AppContext appContext, JavaScriptModuleObject jsObject) {
        ExpectedType[] expectedTypeArr;
        ExpectedType[] expectedTypeArr2;
        List<ExpectedType> cppRequiredTypes;
        List<ExpectedType> cppRequiredTypes2;
        Intrinsics.checkNotNullParameter(appContext, "appContext");
        Intrinsics.checkNotNullParameter(jsObject, "jsObject");
        JNIFunctionBody jNIFunctionBody = this.getter != null ? new JNIFunctionBody() { // from class: expo.modules.kotlin.objects.PropertyComponent$$ExternalSyntheticLambda0
            @Override // expo.modules.kotlin.jni.JNIFunctionBody
            public final Object invoke(Object[] objArr) {
                Object attachToJSObject$lambda$0;
                attachToJSObject$lambda$0 = PropertyComponent.attachToJSObject$lambda$0(PropertyComponent.this, appContext, objArr);
                return attachToJSObject$lambda$0;
            }
        } : null;
        JNIFunctionBody jNIFunctionBody2 = this.setter != null ? new JNIFunctionBody() { // from class: expo.modules.kotlin.objects.PropertyComponent$$ExternalSyntheticLambda1
            @Override // expo.modules.kotlin.jni.JNIFunctionBody
            public final Object invoke(Object[] objArr) {
                Object attachToJSObject$lambda$1;
                attachToJSObject$lambda$1 = PropertyComponent.attachToJSObject$lambda$1(PropertyComponent.this, appContext, objArr);
                return attachToJSObject$lambda$1;
            }
        } : null;
        String str = this.name;
        SyncFunctionComponent syncFunctionComponent = this.getter;
        boolean takesOwner$expo_modules_core_release = syncFunctionComponent != null ? syncFunctionComponent.getTakesOwner$expo_modules_core_release() : false;
        SyncFunctionComponent syncFunctionComponent2 = this.getter;
        if (syncFunctionComponent2 == null || (cppRequiredTypes2 = syncFunctionComponent2.getCppRequiredTypes()) == null || (expectedTypeArr = (ExpectedType[]) cppRequiredTypes2.toArray(new ExpectedType[0])) == null) {
            expectedTypeArr = new ExpectedType[0];
        }
        ExpectedType[] expectedTypeArr3 = expectedTypeArr;
        SyncFunctionComponent syncFunctionComponent3 = this.setter;
        boolean takesOwner$expo_modules_core_release2 = syncFunctionComponent3 != null ? syncFunctionComponent3.getTakesOwner$expo_modules_core_release() : false;
        SyncFunctionComponent syncFunctionComponent4 = this.setter;
        if (syncFunctionComponent4 == null || (cppRequiredTypes = syncFunctionComponent4.getCppRequiredTypes()) == null || (expectedTypeArr2 = (ExpectedType[]) cppRequiredTypes.toArray(new ExpectedType[0])) == null) {
            expectedTypeArr2 = new ExpectedType[0];
        }
        jsObject.registerProperty(str, takesOwner$expo_modules_core_release, expectedTypeArr3, jNIFunctionBody, takesOwner$expo_modules_core_release2, expectedTypeArr2, jNIFunctionBody2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Object attachToJSObject$lambda$0(PropertyComponent this$0, AppContext appContext, Object[] args) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(appContext, "$appContext");
        Intrinsics.checkNotNullParameter(args, "args");
        return JSTypeConverter.convertToJSValue$default(JSTypeConverter.INSTANCE, this$0.getter.call(args, appContext), null, 2, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Object attachToJSObject$lambda$1(PropertyComponent this$0, AppContext appContext, Object[] args) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(appContext, "$appContext");
        Intrinsics.checkNotNullParameter(args, "args");
        this$0.setter.call(args, appContext);
        return null;
    }
}
