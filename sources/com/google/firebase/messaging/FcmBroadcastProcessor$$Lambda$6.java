package com.google.firebase.messaging;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import javax.servlet.http.HttpServletResponse;

/* compiled from: com.google.firebase:firebase-messaging@@22.0.0 */
/* loaded from: classes2.dex */
final /* synthetic */ class FcmBroadcastProcessor$$Lambda$6 implements Continuation {
    static final Continuation $instance = new FcmBroadcastProcessor$$Lambda$6();

    private FcmBroadcastProcessor$$Lambda$6() {
    }

    @Override // com.google.android.gms.tasks.Continuation
    public Object then(Task task) {
        Integer valueOf;
        valueOf = Integer.valueOf(HttpServletResponse.SC_FORBIDDEN);
        return valueOf;
    }
}
