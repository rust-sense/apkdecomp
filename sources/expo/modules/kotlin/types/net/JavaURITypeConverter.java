package expo.modules.kotlin.types.net;

import com.facebook.react.bridge.Dynamic;
import expo.modules.kotlin.jni.CppType;
import expo.modules.kotlin.jni.ExpectedType;
import expo.modules.kotlin.types.DynamicAwareTypeConverters;
import java.net.URI;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: UriTypeConverter.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0010\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u0010\u0010\t\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\nH\u0016J\b\u0010\u000b\u001a\u00020\fH\u0016J\b\u0010\r\u001a\u00020\u0004H\u0016¨\u0006\u000e"}, d2 = {"Lexpo/modules/kotlin/types/net/JavaURITypeConverter;", "Lexpo/modules/kotlin/types/DynamicAwareTypeConverters;", "Ljava/net/URI;", "isOptional", "", "(Z)V", "convertFromAny", "value", "", "convertFromDynamic", "Lcom/facebook/react/bridge/Dynamic;", "getCppRequiredTypes", "Lexpo/modules/kotlin/jni/ExpectedType;", "isTrivial", "expo-modules-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class JavaURITypeConverter extends DynamicAwareTypeConverters<URI> {
    @Override // expo.modules.kotlin.types.TypeConverter
    public boolean isTrivial() {
        return false;
    }

    public JavaURITypeConverter(boolean z) {
        super(z);
    }

    @Override // expo.modules.kotlin.types.DynamicAwareTypeConverters
    public URI convertFromDynamic(Dynamic value) {
        Intrinsics.checkNotNullParameter(value, "value");
        URI create = URI.create(value.asString());
        Intrinsics.checkNotNullExpressionValue(create, "create(...)");
        return create;
    }

    @Override // expo.modules.kotlin.types.DynamicAwareTypeConverters
    public URI convertFromAny(Object value) {
        Intrinsics.checkNotNullParameter(value, "value");
        URI create = URI.create((String) value);
        Intrinsics.checkNotNullExpressionValue(create, "create(...)");
        return create;
    }

    @Override // expo.modules.kotlin.types.TypeConverter
    /* renamed from: getCppRequiredTypes */
    public ExpectedType get$cppRequireType() {
        return new ExpectedType(CppType.STRING);
    }
}
