package expo.modules.kotlin.functions;

import androidx.exifinterface.media.ExifInterface;
import expo.modules.kotlin.types.AnyType;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AsyncFunctionComponent.kt */
@Metadata(d1 = {"\u0000*\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a^\u0010\u0000\u001a\u00020\u0001\"\u0006\b\u0000\u0010\u0002\u0018\u00012\u0006\u0010\u0003\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062-\b\b\u0010\b\u001a'\u0012\u001d\u0012\u001b\u0012\b\b\u0001\u0012\u0004\u0018\u00010\n0\u0006¢\u0006\f\b\u000b\u0012\b\b\u0003\u0012\u0004\b\b(\f\u0012\u0004\u0012\u0002H\u00020\tH\u0086\bø\u0001\u0000¢\u0006\u0002\u0010\r\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u000e"}, d2 = {"createAsyncFunctionComponent", "Lexpo/modules/kotlin/functions/AsyncFunction;", ExifInterface.GPS_DIRECTION_TRUE, "name", "", "desiredArgsTypes", "", "Lexpo/modules/kotlin/types/AnyType;", "body", "Lkotlin/Function1;", "", "Lkotlin/ParameterName;", "args", "(Ljava/lang/String;[Lexpo/modules/kotlin/types/AnyType;Lkotlin/jvm/functions/Function1;)Lexpo/modules/kotlin/functions/AsyncFunction;", "expo-modules-core_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class AsyncFunctionComponentKt {
    public static final /* synthetic */ <T> AsyncFunction createAsyncFunctionComponent(String name, AnyType[] desiredArgsTypes, Function1<? super Object[], ? extends T> body) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(desiredArgsTypes, "desiredArgsTypes");
        Intrinsics.checkNotNullParameter(body, "body");
        Intrinsics.reifiedOperationMarker(3, ExifInterface.GPS_DIRECTION_TRUE);
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        if (Intrinsics.areEqual(Object.class, Integer.TYPE)) {
            return new IntAsyncFunctionComponent(name, desiredArgsTypes, body);
        }
        if (Intrinsics.areEqual(Object.class, Boolean.TYPE)) {
            return new BoolAsyncFunctionComponent(name, desiredArgsTypes, body);
        }
        if (Intrinsics.areEqual(Object.class, Double.TYPE)) {
            return new DoubleAsyncFunctionComponent(name, desiredArgsTypes, body);
        }
        if (Intrinsics.areEqual(Object.class, Float.TYPE)) {
            return new FloatAsyncFunctionComponent(name, desiredArgsTypes, body);
        }
        if (Intrinsics.areEqual(Object.class, String.class)) {
            return new StringAsyncFunctionComponent(name, desiredArgsTypes, body);
        }
        return new AsyncFunctionComponent(name, desiredArgsTypes, body);
    }
}
