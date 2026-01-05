package expo.modules.kotlin.types;

import androidx.exifinterface.media.ExifInterface;
import com.facebook.react.bridge.Dynamic;
import expo.modules.kotlin.jni.ExpectedType;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: Add missing generic type declarations: [T] */
/* compiled from: TypeConverter.kt */
@Metadata(d1 = {"\u0000#\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u0015\u0010\u0002\u001a\u00028\u00002\u0006\u0010\u0003\u001a\u00020\u0004H\u0016¢\u0006\u0002\u0010\u0005J\u0015\u0010\u0006\u001a\u00028\u00002\u0006\u0010\u0003\u001a\u00020\u0007H\u0016¢\u0006\u0002\u0010\bJ\b\u0010\t\u001a\u00020\nH\u0016¨\u0006\u000b"}, d2 = {"expo/modules/kotlin/types/TypeConverterKt$createTrivialTypeConverter$2", "Lexpo/modules/kotlin/types/DynamicAwareTypeConverters;", "convertFromAny", "value", "", "(Ljava/lang/Object;)Ljava/lang/Object;", "convertFromDynamic", "Lcom/facebook/react/bridge/Dynamic;", "(Lcom/facebook/react/bridge/Dynamic;)Ljava/lang/Object;", "getCppRequiredTypes", "Lexpo/modules/kotlin/jni/ExpectedType;", "expo-modules-core_release"}, k = 1, mv = {1, 9, 0}, xi = 176)
/* loaded from: classes2.dex */
public final class TypeConverterKt$createTrivialTypeConverter$2<T> extends DynamicAwareTypeConverters<T> {
    final /* synthetic */ ExpectedType $cppRequireType;
    final /* synthetic */ Function1<Dynamic, T> $dynamicFallback;

    @Override // expo.modules.kotlin.types.TypeConverter
    /* renamed from: getCppRequiredTypes, reason: from getter */
    public ExpectedType get$cppRequireType() {
        return this.$cppRequireType;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public TypeConverterKt$createTrivialTypeConverter$2(boolean z, Function1<? super Dynamic, ? extends T> function1, ExpectedType expectedType) {
        super(z);
        this.$dynamicFallback = function1;
        this.$cppRequireType = expectedType;
    }

    @Override // expo.modules.kotlin.types.DynamicAwareTypeConverters
    public T convertFromDynamic(Dynamic value) {
        Intrinsics.checkNotNullParameter(value, "value");
        return this.$dynamicFallback.invoke(value);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // expo.modules.kotlin.types.DynamicAwareTypeConverters
    public T convertFromAny(Object value) {
        Intrinsics.checkNotNullParameter(value, "value");
        Intrinsics.reifiedOperationMarker(1, ExifInterface.GPS_DIRECTION_TRUE);
        return value;
    }
}
