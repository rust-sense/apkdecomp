package com.facebook.fresco.animation.bitmap.preparation.loadframe;

import android.graphics.Bitmap;
import com.facebook.common.references.CloseableReference;
import io.sentry.protocol.SentryStackTrace;
import java.util.Map;
import kotlin.Metadata;

/* compiled from: LoadFrameTaskFactory.kt */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\"\u0010\u0004\u001a\u00020\u00032\u0018\u0010\u0005\u001a\u0014\u0012\u0004\u0012\u00020\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b0\u0006H&¨\u0006\n"}, d2 = {"Lcom/facebook/fresco/animation/bitmap/preparation/loadframe/LoadFrameOutput;", "", "onFail", "", "onSuccess", SentryStackTrace.JsonKeys.FRAMES, "", "", "Lcom/facebook/common/references/CloseableReference;", "Landroid/graphics/Bitmap;", "animated-drawable_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public interface LoadFrameOutput {
    void onFail();

    void onSuccess(Map<Integer, ? extends CloseableReference<Bitmap>> frames);
}
