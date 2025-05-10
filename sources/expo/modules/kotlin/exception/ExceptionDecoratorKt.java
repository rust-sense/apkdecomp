package expo.modules.kotlin.exception;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ExceptionDecorator.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001aH\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u00012!\u0010\u0002\u001a\u001d\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\b0\u00032\f\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u00010\nH\u0080\bø\u0001\u0000¢\u0006\u0002\u0010\u000b\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\f"}, d2 = {"exceptionDecorator", ExifInterface.GPS_DIRECTION_TRUE, "decoratorBlock", "Lkotlin/Function1;", "Lexpo/modules/kotlin/exception/CodedException;", "Lkotlin/ParameterName;", "name", "e", "", "block", "Lkotlin/Function0;", "(Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "expo-modules-core_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class ExceptionDecoratorKt {
    public static final <T> T exceptionDecorator(Function1<? super CodedException, ? extends Throwable> decoratorBlock, Function0<? extends T> block) throws CodedException {
        UnexpectedException unexpectedException;
        Intrinsics.checkNotNullParameter(decoratorBlock, "decoratorBlock");
        Intrinsics.checkNotNullParameter(block, "block");
        try {
            return block.invoke();
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
            throw decoratorBlock.invoke(unexpectedException);
        }
    }
}
