package expo.modules.kotlin.functions;

import com.facebook.react.bridge.Dynamic;
import com.facebook.react.bridge.ReadableArray;
import expo.modules.kotlin.AppContext;
import expo.modules.kotlin.ReadableArrayIterator;
import expo.modules.kotlin.ReadableArrayIteratorKt;
import expo.modules.kotlin.exception.ArgumentCastException;
import expo.modules.kotlin.exception.CodedException;
import expo.modules.kotlin.exception.InvalidArgsNumberException;
import expo.modules.kotlin.exception.UnexpectedException;
import expo.modules.kotlin.jni.ExpectedType;
import expo.modules.kotlin.jni.JavaScriptModuleObject;
import expo.modules.kotlin.jni.JavaScriptObject;
import expo.modules.kotlin.types.AnyType;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.ArrayIteratorKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import kotlin.reflect.KClassifier;
import kotlin.reflect.KType;

/* compiled from: AnyFunction.kt */
@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\b&\u0018\u00002\u00020\u0001B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\u0010\u0007J\u0018\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020(H&J\u001f\u0010)\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00010\u00052\u0006\u0010*\u001a\u00020+H\u0004¢\u0006\u0002\u0010,J3\u0010)\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00010\u00052\u000e\u0010*\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u00052\n\b\u0002\u0010%\u001a\u0004\u0018\u00010&H\u0004¢\u0006\u0002\u0010-J\f\u0010.\u001a\b\u0012\u0004\u0012\u0002000/R\u0014\u0010\b\u001a\u00020\t8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR$\u0010\f\u001a\u00020\r8\u0000@\u0000X\u0081\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u001c\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0084\u0004¢\u0006\n\n\u0002\u0010\u0016\u001a\u0004\b\u0014\u0010\u0015R\u0014\u0010\u0002\u001a\u00020\u0003X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R&\u0010\u0019\u001a\u0004\u0018\u00010\u001a8\u0000@\u0000X\u0081\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\u001b\u0010\u000f\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\u000e\u0010 \u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010!\u001a\u00020\r8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\"\u0010\u0011¨\u00061"}, d2 = {"Lexpo/modules/kotlin/functions/AnyFunction;", "", "name", "", "desiredArgsTypes", "", "Lexpo/modules/kotlin/types/AnyType;", "(Ljava/lang/String;[Lexpo/modules/kotlin/types/AnyType;)V", "argsCount", "", "getArgsCount$expo_modules_core_release", "()I", "canTakeOwner", "", "getCanTakeOwner$annotations", "()V", "getCanTakeOwner", "()Z", "setCanTakeOwner", "(Z)V", "getDesiredArgsTypes", "()[Lexpo/modules/kotlin/types/AnyType;", "[Lexpo/modules/kotlin/types/AnyType;", "getName", "()Ljava/lang/String;", "ownerType", "Lkotlin/reflect/KType;", "getOwnerType$annotations", "getOwnerType", "()Lkotlin/reflect/KType;", "setOwnerType", "(Lkotlin/reflect/KType;)V", "requiredArgumentsCount", "takesOwner", "getTakesOwner$expo_modules_core_release", "attachToJSObject", "", "appContext", "Lexpo/modules/kotlin/AppContext;", "jsObject", "Lexpo/modules/kotlin/jni/JavaScriptModuleObject;", "convertArgs", "args", "Lcom/facebook/react/bridge/ReadableArray;", "(Lcom/facebook/react/bridge/ReadableArray;)[Ljava/lang/Object;", "([Ljava/lang/Object;Lexpo/modules/kotlin/AppContext;)[Ljava/lang/Object;", "getCppRequiredTypes", "", "Lexpo/modules/kotlin/jni/ExpectedType;", "expo-modules-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public abstract class AnyFunction {
    private boolean canTakeOwner;
    private final AnyType[] desiredArgsTypes;
    private final String name;
    private KType ownerType;
    private final int requiredArgumentsCount;

    public static /* synthetic */ void getCanTakeOwner$annotations() {
    }

    public static /* synthetic */ void getOwnerType$annotations() {
    }

    public abstract void attachToJSObject(AppContext appContext, JavaScriptModuleObject jsObject);

    public final boolean getCanTakeOwner() {
        return this.canTakeOwner;
    }

    protected final AnyType[] getDesiredArgsTypes() {
        return this.desiredArgsTypes;
    }

    protected final String getName() {
        return this.name;
    }

    public final KType getOwnerType() {
        return this.ownerType;
    }

    public final void setCanTakeOwner(boolean z) {
        this.canTakeOwner = z;
    }

    public final void setOwnerType(KType kType) {
        this.ownerType = kType;
    }

    public AnyFunction(String name, AnyType[] desiredArgsTypes) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(desiredArgsTypes, "desiredArgsTypes");
        this.name = name;
        this.desiredArgsTypes = desiredArgsTypes;
        Iterator it = ArraysKt.reversed(this.desiredArgsTypes).iterator();
        int i = 0;
        while (true) {
            if (!it.hasNext()) {
                i = -1;
                break;
            } else if (!((AnyType) it.next()).getKType().getIsMarkedNullable()) {
                break;
            } else {
                i++;
            }
        }
        this.requiredArgumentsCount = i >= 0 ? this.desiredArgsTypes.length - i : 0;
    }

    public final int getArgsCount$expo_modules_core_release() {
        return this.desiredArgsTypes.length;
    }

    public final boolean getTakesOwner$expo_modules_core_release() {
        KType kType;
        if (!this.canTakeOwner) {
            return false;
        }
        AnyType anyType = (AnyType) ArraysKt.firstOrNull(this.desiredArgsTypes);
        KClassifier classifier = (anyType == null || (kType = anyType.getKType()) == null) ? null : kType.getClassifier();
        KClass kClass = classifier instanceof KClass ? (KClass) classifier : null;
        if (kClass == null) {
            return false;
        }
        if (Intrinsics.areEqual(kClass, Reflection.getOrCreateKotlinClass(JavaScriptObject.class))) {
            return true;
        }
        KType kType2 = this.ownerType;
        Object classifier2 = kType2 != null ? kType2.getClassifier() : null;
        KClass kClass2 = classifier2 instanceof KClass ? (KClass) classifier2 : null;
        if (kClass2 == null) {
            return false;
        }
        return Intrinsics.areEqual(kClass, kClass2);
    }

    protected final Object[] convertArgs(ReadableArray args) throws CodedException {
        ArgumentCastException argumentCastException;
        Intrinsics.checkNotNullParameter(args, "args");
        if (this.requiredArgumentsCount <= args.size()) {
            int size = args.size();
            AnyType[] anyTypeArr = this.desiredArgsTypes;
            if (size <= anyTypeArr.length) {
                int length = anyTypeArr.length;
                Object[] objArr = new Object[length];
                int i = 0;
                for (int i2 = 0; i2 < length; i2++) {
                    objArr[i2] = null;
                }
                ReadableArrayIterator it = ReadableArrayIteratorKt.iterator(args);
                int size2 = args.size();
                while (i < size2) {
                    AnyType anyType = this.desiredArgsTypes[i];
                    Dynamic next = it.next();
                    try {
                        try {
                            objArr[i] = AnyType.convert$default(anyType, next, null, 2, null);
                            Unit unit = Unit.INSTANCE;
                            Unit unit2 = Unit.INSTANCE;
                            next.recycle();
                            i++;
                        } finally {
                        }
                    } catch (Throwable th) {
                        next.recycle();
                        throw th;
                    }
                }
                return objArr;
            }
        }
        throw new InvalidArgsNumberException(args.size(), this.desiredArgsTypes.length, this.requiredArgumentsCount);
    }

    public static /* synthetic */ Object[] convertArgs$default(AnyFunction anyFunction, Object[] objArr, AppContext appContext, int i, Object obj) throws CodedException {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: convertArgs");
        }
        if ((i & 2) != 0) {
            appContext = null;
        }
        return anyFunction.convertArgs(objArr, appContext);
    }

    protected final Object[] convertArgs(Object[] args, AppContext appContext) throws CodedException {
        UnexpectedException unexpectedException;
        Intrinsics.checkNotNullParameter(args, "args");
        if (this.requiredArgumentsCount <= args.length) {
            int length = args.length;
            AnyType[] anyTypeArr = this.desiredArgsTypes;
            if (length <= anyTypeArr.length) {
                int length2 = anyTypeArr.length;
                Object[] objArr = new Object[length2];
                int i = 0;
                while (true) {
                    if (i >= length2) {
                        break;
                    }
                    objArr[i] = null;
                    i++;
                }
                Iterator it = ArrayIteratorKt.iterator(args);
                int length3 = args.length;
                for (int i2 = 0; i2 < length3; i2++) {
                    Object next = it.next();
                    AnyType anyType = this.desiredArgsTypes[i2];
                    try {
                        objArr[i2] = anyType.convert(next, appContext);
                        Unit unit = Unit.INSTANCE;
                    } catch (Throwable th) {
                        if (th instanceof CodedException) {
                            unexpectedException = (CodedException) th;
                        } else if (th instanceof expo.modules.core.errors.CodedException) {
                            String code = ((expo.modules.core.errors.CodedException) th).getCode();
                            Intrinsics.checkNotNullExpressionValue(code, "getCode(...)");
                            unexpectedException = new CodedException(code, th.getMessage(), th.getCause());
                        } else {
                            unexpectedException = new UnexpectedException(th);
                        }
                        throw new ArgumentCastException(anyType.getKType(), i2, String.valueOf(next != null ? next.getClass() : null), unexpectedException);
                    }
                }
                return objArr;
            }
        }
        throw new InvalidArgsNumberException(args.length, this.desiredArgsTypes.length, this.requiredArgumentsCount);
    }

    public final List<ExpectedType> getCppRequiredTypes() {
        AnyType[] anyTypeArr = this.desiredArgsTypes;
        ArrayList arrayList = new ArrayList(anyTypeArr.length);
        for (AnyType anyType : anyTypeArr) {
            arrayList.add(anyType.getCppRequiredTypes());
        }
        return arrayList;
    }
}
