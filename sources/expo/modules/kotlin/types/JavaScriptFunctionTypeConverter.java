package expo.modules.kotlin.types;

import androidx.exifinterface.media.ExifInterface;
import expo.modules.kotlin.AppContext;
import expo.modules.kotlin.jni.CppType;
import expo.modules.kotlin.jni.ExpectedType;
import expo.modules.kotlin.jni.JavaScriptFunction;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KType;
import kotlin.reflect.KTypeProjection;

/* compiled from: JavaScriptFunctionTypeConverter.kt */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u00040\u0003B\r\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J \u0010\n\u001a\b\u0012\u0004\u0012\u00028\u00000\u00042\u0006\u0010\u000b\u001a\u00020\u00022\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0016J\b\u0010\u000e\u001a\u00020\u000fH\u0016J\b\u0010\u0010\u001a\u00020\u0011H\u0016R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\u0012"}, d2 = {"Lexpo/modules/kotlin/types/JavaScriptFunctionTypeConverter;", ExifInterface.GPS_DIRECTION_TRUE, "", "Lexpo/modules/kotlin/types/NullAwareTypeConverter;", "Lexpo/modules/kotlin/jni/JavaScriptFunction;", "type", "Lkotlin/reflect/KType;", "(Lkotlin/reflect/KType;)V", "getType", "()Lkotlin/reflect/KType;", "convertNonOptional", "value", "context", "Lexpo/modules/kotlin/AppContext;", "getCppRequiredTypes", "Lexpo/modules/kotlin/jni/ExpectedType;", "isTrivial", "", "expo-modules-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class JavaScriptFunctionTypeConverter<T> extends NullAwareTypeConverter<JavaScriptFunction<T>> {
    private final KType type;

    public final KType getType() {
        return this.type;
    }

    @Override // expo.modules.kotlin.types.TypeConverter
    public boolean isTrivial() {
        return false;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public JavaScriptFunctionTypeConverter(KType type) {
        super(type.getIsMarkedNullable());
        Intrinsics.checkNotNullParameter(type, "type");
        this.type = type;
    }

    @Override // expo.modules.kotlin.types.NullAwareTypeConverter
    public JavaScriptFunction<T> convertNonOptional(Object value, AppContext context) {
        Intrinsics.checkNotNullParameter(value, "value");
        JavaScriptFunction<T> javaScriptFunction = (JavaScriptFunction) value;
        KType type = ((KTypeProjection) CollectionsKt.first((List) this.type.getArguments())).getType();
        if (type == null) {
            throw new IllegalArgumentException("Required value was null.".toString());
        }
        javaScriptFunction.setReturnType(type);
        return javaScriptFunction;
    }

    @Override // expo.modules.kotlin.types.TypeConverter
    /* renamed from: getCppRequiredTypes */
    public ExpectedType get$cppRequireType() {
        return new ExpectedType(CppType.JS_FUNCTION);
    }
}
