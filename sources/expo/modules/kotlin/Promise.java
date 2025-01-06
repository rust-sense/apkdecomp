package expo.modules.kotlin;

import expo.modules.kotlin.exception.CodedException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Promise.kt */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\u0010\u0006\n\u0002\u0010\u0007\n\u0002\u0010\b\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J$\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\u00072\b\u0010\t\u001a\u0004\u0018\u00010\nH&J\b\u0010\u000b\u001a\u00020\u0003H\u0016J\u0012\u0010\u000b\u001a\u00020\u00032\b\u0010\f\u001a\u0004\u0018\u00010\u0001H&J\u0010\u0010\u000b\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u0010\u0010\u000b\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u000fH\u0016J\u0010\u0010\u000b\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u0010H\u0016J\u0010\u0010\u000b\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u0011H\u0016J\u0010\u0010\u000b\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u0007H\u0016¨\u0006\u0012"}, d2 = {"Lexpo/modules/kotlin/Promise;", "", "reject", "", "exception", "Lexpo/modules/kotlin/exception/CodedException;", "code", "", "message", "cause", "", "resolve", "value", "result", "", "", "", "", "expo-modules-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public interface Promise {
    void reject(CodedException exception);

    void reject(String code, String message, Throwable cause);

    void resolve();

    void resolve(double result);

    void resolve(float result);

    void resolve(int result);

    void resolve(Object value);

    void resolve(String result);

    void resolve(boolean result);

    /* compiled from: Promise.kt */
    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    public static final class DefaultImpls {
        public static void resolve(Promise promise) {
            promise.resolve((Object) null);
        }

        public static void resolve(Promise promise, int i) {
            promise.resolve(Integer.valueOf(i));
        }

        public static void resolve(Promise promise, boolean z) {
            promise.resolve(Boolean.valueOf(z));
        }

        public static void resolve(Promise promise, double d) {
            promise.resolve(Double.valueOf(d));
        }

        public static void resolve(Promise promise, float f) {
            promise.resolve(Float.valueOf(f));
        }

        public static void resolve(Promise promise, String result) {
            Intrinsics.checkNotNullParameter(result, "result");
            promise.resolve((Object) result);
        }

        public static void reject(Promise promise, CodedException exception) {
            Intrinsics.checkNotNullParameter(exception, "exception");
            promise.reject(exception.getCode(), exception.getLocalizedMessage(), exception.getCause());
        }
    }
}
