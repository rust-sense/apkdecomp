package expo.modules.kotlin.types;

import expo.modules.kotlin.jni.JavaScriptTypedArray;
import expo.modules.kotlin.typedarray.Uint8ClampedArray;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: TypedArrayTypeConverter.kt */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0010\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\bH\u0016¨\u0006\t"}, d2 = {"Lexpo/modules/kotlin/types/Uint8ClampedArrayTypeConverter;", "Lexpo/modules/kotlin/types/BaseTypeArrayConverter;", "Lexpo/modules/kotlin/typedarray/Uint8ClampedArray;", "isOptional", "", "(Z)V", "wrapJavaScriptTypedArray", "value", "Lexpo/modules/kotlin/jni/JavaScriptTypedArray;", "expo-modules-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class Uint8ClampedArrayTypeConverter extends BaseTypeArrayConverter<Uint8ClampedArray> {
    public Uint8ClampedArrayTypeConverter(boolean z) {
        super(z);
    }

    @Override // expo.modules.kotlin.types.BaseTypeArrayConverter
    public Uint8ClampedArray wrapJavaScriptTypedArray(JavaScriptTypedArray value) {
        Intrinsics.checkNotNullParameter(value, "value");
        return new Uint8ClampedArray(value);
    }
}