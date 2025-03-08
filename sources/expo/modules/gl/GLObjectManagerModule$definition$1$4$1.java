package expo.modules.gl;

import android.os.Bundle;
import expo.modules.kotlin.Promise;
import kotlin.Metadata;

/* compiled from: GLObjectManagerModule.kt */
@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "run"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
final class GLObjectManagerModule$definition$1$4$1 implements Runnable {
    final /* synthetic */ GLContext $glContext;
    final /* synthetic */ Promise $promise;

    GLObjectManagerModule$definition$1$4$1(GLContext gLContext, Promise promise) {
        this.$glContext = gLContext;
        this.$promise = promise;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Bundle bundle = new Bundle();
        bundle.putInt("exglCtxId", this.$glContext.getContextId());
        this.$promise.resolve(bundle);
    }
}
