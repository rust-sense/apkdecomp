package expo.modules.kotlin.types;

import androidx.exifinterface.media.ExifInterface;
import com.facebook.react.bridge.Dynamic;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KType;

/* compiled from: TypeConverterProvider.kt */
@Metadata(d1 = {"\u0000\u001e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u001e\u0010\u0000\u001a\u0002H\u0001\"\u0006\b\u0000\u0010\u0001\u0018\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0086\b¢\u0006\u0002\u0010\u0004\u001a\u0018\u0010\u0000\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0007\u001a \u0010\u0000\u001a\u0002H\u0001\"\u0006\b\u0000\u0010\u0001\u0018\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0005H\u0086\b¢\u0006\u0002\u0010\b\u001a\u001b\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u00010\n\"\n\b\u0000\u0010\u0001\u0018\u0001*\u00020\u0005H\u0086\b¨\u0006\u000b"}, d2 = {"convert", ExifInterface.GPS_DIRECTION_TRUE, "value", "Lcom/facebook/react/bridge/Dynamic;", "(Lcom/facebook/react/bridge/Dynamic;)Ljava/lang/Object;", "", "type", "Lkotlin/reflect/KType;", "(Ljava/lang/Object;)Ljava/lang/Object;", "obtainTypeConverter", "Lexpo/modules/kotlin/types/TypeConverter;", "expo-modules-core_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class TypeConverterProviderKt {
    public static final /* synthetic */ <T> TypeConverter<T> obtainTypeConverter() {
        TypeConverterProviderImpl typeConverterProviderImpl = TypeConverterProviderImpl.INSTANCE;
        Intrinsics.reifiedOperationMarker(6, ExifInterface.GPS_DIRECTION_TRUE);
        TypeConverter obtainTypeConverter = typeConverterProviderImpl.obtainTypeConverter(null);
        Intrinsics.checkNotNull(obtainTypeConverter, "null cannot be cast to non-null type expo.modules.kotlin.types.TypeConverter<T of expo.modules.kotlin.types.TypeConverterProviderKt.obtainTypeConverter>");
        return obtainTypeConverter;
    }

    public static final /* synthetic */ <T> T convert(Dynamic value) {
        Intrinsics.checkNotNullParameter(value, "value");
        TypeConverterProviderImpl typeConverterProviderImpl = TypeConverterProviderImpl.INSTANCE;
        Intrinsics.reifiedOperationMarker(6, ExifInterface.GPS_DIRECTION_TRUE);
        T t = (T) TypeConverter.convert$default(typeConverterProviderImpl.obtainTypeConverter(null), value, null, 2, null);
        Intrinsics.reifiedOperationMarker(1, ExifInterface.GPS_DIRECTION_TRUE);
        return t;
    }

    public static final /* synthetic */ <T> T convert(Object obj) {
        TypeConverterProviderImpl typeConverterProviderImpl = TypeConverterProviderImpl.INSTANCE;
        Intrinsics.reifiedOperationMarker(6, ExifInterface.GPS_DIRECTION_TRUE);
        T t = (T) TypeConverter.convert$default(typeConverterProviderImpl.obtainTypeConverter(null), obj, null, 2, null);
        Intrinsics.reifiedOperationMarker(1, ExifInterface.GPS_DIRECTION_TRUE);
        return t;
    }

    public static final Object convert(Dynamic value, KType type) {
        Intrinsics.checkNotNullParameter(value, "value");
        Intrinsics.checkNotNullParameter(type, "type");
        return TypeConverter.convert$default(TypeConverterProviderImpl.INSTANCE.obtainTypeConverter(type), value, null, 2, null);
    }
}
