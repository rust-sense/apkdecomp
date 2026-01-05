package com.facebook.fresco.animation.bitmap.preparation.ondemandanimation;

import com.facebook.fresco.animation.backend.AnimationInformation;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;

/* compiled from: FrameLoader.kt */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0006\u001a\u00020\u0007H&J\u0010\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\nH\u0016J \u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\n2\u0006\u0010\u000f\u001a\u00020\nH'J\b\u0010\u0010\u001a\u00020\u0007H\u0016J&\u0010\u0011\u001a\u00020\u00072\u0006\u0010\u000e\u001a\u00020\n2\u0006\u0010\u000f\u001a\u00020\n2\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00070\u0013H'R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0014"}, d2 = {"Lcom/facebook/fresco/animation/bitmap/preparation/ondemandanimation/FrameLoader;", "", "animationInformation", "Lcom/facebook/fresco/animation/backend/AnimationInformation;", "getAnimationInformation", "()Lcom/facebook/fresco/animation/backend/AnimationInformation;", "clear", "", "compressToFps", "fps", "", "getFrame", "Lcom/facebook/fresco/animation/bitmap/preparation/ondemandanimation/FrameResult;", "frameNumber", "width", "height", "onStop", "prepareFrames", "onAnimationLoaded", "Lkotlin/Function0;", "animated-drawable_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public interface FrameLoader {

    /* compiled from: FrameLoader.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class DefaultImpls {
        public static void compressToFps(FrameLoader frameLoader, int i) {
        }

        public static void onStop(FrameLoader frameLoader) {
        }
    }

    void clear();

    void compressToFps(int fps);

    AnimationInformation getAnimationInformation();

    FrameResult getFrame(int frameNumber, int width, int height);

    void onStop();

    void prepareFrames(int width, int height, Function0<Unit> onAnimationLoaded);
}
