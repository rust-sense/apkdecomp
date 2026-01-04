package expo.modules.notifications;

import expo.modules.core.Promise;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Exceptions.kt */
@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002¨\u0006\u0003"}, d2 = {"toLegacyPromise", "Lexpo/modules/core/Promise;", "Lexpo/modules/kotlin/Promise;", "expo-notifications_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class ExceptionsKt {
    public static final Promise toLegacyPromise(final expo.modules.kotlin.Promise promise) {
        Intrinsics.checkNotNullParameter(promise, "<this>");
        return new Promise() { // from class: expo.modules.notifications.ExceptionsKt$toLegacyPromise$1
            @Override // expo.modules.core.Promise
            public /* synthetic */ void reject(String str, String str2) {
                reject(str, str2, null);
            }

            @Override // expo.modules.core.Promise
            public /* synthetic */ void reject(String str, Throwable th) {
                reject(str, th.getMessage(), th);
            }

            @Override // expo.modules.core.Promise
            public /* synthetic */ void reject(Throwable th) {
                Promise.CC.$default$reject(this, th);
            }

            @Override // expo.modules.core.Promise
            public void resolve(Object value) {
                expo.modules.kotlin.Promise.this.resolve(value);
            }

            @Override // expo.modules.core.Promise
            public void reject(String c, String m, Throwable e) {
                expo.modules.kotlin.Promise promise2 = expo.modules.kotlin.Promise.this;
                if (c == null) {
                    c = "unknown";
                }
                promise2.reject(c, m, e);
            }
        };
    }
}
