package expo.modules.kotlin.types;

import expo.modules.kotlin.AppContext;
import java.lang.ref.WeakReference;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Either.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B#\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\bJ\u0006\u0010\f\u001a\u00020\u0003R\u0016\u0010\t\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lexpo/modules/kotlin/types/UnconvertedValue;", "Lexpo/modules/kotlin/types/DeferredValue;", "unconvertedValue", "", "typeConverter", "Lexpo/modules/kotlin/types/TypeConverter;", "context", "Lexpo/modules/kotlin/AppContext;", "(Ljava/lang/Object;Lexpo/modules/kotlin/types/TypeConverter;Lexpo/modules/kotlin/AppContext;)V", "contextHolder", "Ljava/lang/ref/WeakReference;", "convertedValue", "getConvertedValue", "expo-modules-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class UnconvertedValue extends DeferredValue {
    private final WeakReference<AppContext> contextHolder;
    private Object convertedValue;
    private final TypeConverter<?> typeConverter;
    private final Object unconvertedValue;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public UnconvertedValue(Object unconvertedValue, TypeConverter<?> typeConverter, AppContext appContext) {
        super(null);
        Intrinsics.checkNotNullParameter(unconvertedValue, "unconvertedValue");
        Intrinsics.checkNotNullParameter(typeConverter, "typeConverter");
        this.unconvertedValue = unconvertedValue;
        this.typeConverter = typeConverter;
        this.contextHolder = new WeakReference<>(appContext);
    }

    public final Object getConvertedValue() {
        if (this.convertedValue == null) {
            this.convertedValue = this.typeConverter.convert(this.unconvertedValue, this.contextHolder.get());
        }
        Object obj = this.convertedValue;
        Intrinsics.checkNotNull(obj);
        return obj;
    }
}
