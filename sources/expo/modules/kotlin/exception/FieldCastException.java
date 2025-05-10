package expo.modules.kotlin.exception;

import com.facebook.react.bridge.ReadableType;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KType;

/* compiled from: CodedException.kt */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\n¨\u0006\u000b"}, d2 = {"Lexpo/modules/kotlin/exception/FieldCastException;", "Lexpo/modules/kotlin/exception/DecoratedException;", "fieldName", "", "fieldType", "Lkotlin/reflect/KType;", "providedType", "Lcom/facebook/react/bridge/ReadableType;", "cause", "Lexpo/modules/kotlin/exception/CodedException;", "(Ljava/lang/String;Lkotlin/reflect/KType;Lcom/facebook/react/bridge/ReadableType;Lexpo/modules/kotlin/exception/CodedException;)V", "expo-modules-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class FieldCastException extends DecoratedException {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FieldCastException(String fieldName, KType fieldType, ReadableType providedType, CodedException cause) {
        super("Cannot cast '" + providedType.name() + "' for field '" + fieldName + "' ('" + fieldType + "').", cause);
        Intrinsics.checkNotNullParameter(fieldName, "fieldName");
        Intrinsics.checkNotNullParameter(fieldType, "fieldType");
        Intrinsics.checkNotNullParameter(providedType, "providedType");
        Intrinsics.checkNotNullParameter(cause, "cause");
    }
}
