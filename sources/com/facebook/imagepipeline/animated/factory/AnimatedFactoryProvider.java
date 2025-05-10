package com.facebook.imagepipeline.animated.factory;

import com.facebook.cache.common.CacheKey;
import com.facebook.common.executors.SerialExecutorService;
import com.facebook.imagepipeline.bitmaps.PlatformBitmapFactory;
import com.facebook.imagepipeline.cache.AnimatedCache;
import com.facebook.imagepipeline.cache.CountingMemoryCache;
import com.facebook.imagepipeline.core.ExecutorSupplier;
import com.facebook.imagepipeline.image.CloseableImage;
import java.util.concurrent.ExecutorService;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AnimatedFactoryProvider.kt */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002Jj\u0010\u0007\u001a\u0004\u0018\u00010\u00042\b\u0010\b\u001a\u0004\u0018\u00010\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0018\u0010\f\u001a\u0014\u0012\u0006\u0012\u0004\u0018\u00010\u000e\u0012\u0006\u0012\u0004\u0018\u00010\u000f\u0018\u00010\r2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00062\u0006\u0010\u0013\u001a\u00020\u00062\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00152\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0007R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lcom/facebook/imagepipeline/animated/factory/AnimatedFactoryProvider;", "", "()V", "impl", "Lcom/facebook/imagepipeline/animated/factory/AnimatedFactory;", "implLoaded", "", "getAnimatedFactory", "platformBitmapFactory", "Lcom/facebook/imagepipeline/bitmaps/PlatformBitmapFactory;", "executorSupplier", "Lcom/facebook/imagepipeline/core/ExecutorSupplier;", "backingCache", "Lcom/facebook/imagepipeline/cache/CountingMemoryCache;", "Lcom/facebook/cache/common/CacheKey;", "Lcom/facebook/imagepipeline/image/CloseableImage;", "animatedCache", "Lcom/facebook/imagepipeline/cache/AnimatedCache;", "downscaleFrameToDrawableDimensions", "useBalancedAnimationStrategy", "balancedStrategyPreparationMs", "", "animationFpsLimit", "serialExecutorService", "Ljava/util/concurrent/ExecutorService;", "imagepipeline-base_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class AnimatedFactoryProvider {
    public static final AnimatedFactoryProvider INSTANCE = new AnimatedFactoryProvider();
    private static AnimatedFactory impl;
    private static boolean implLoaded;

    private AnimatedFactoryProvider() {
    }

    @JvmStatic
    public static final AnimatedFactory getAnimatedFactory(PlatformBitmapFactory platformBitmapFactory, ExecutorSupplier executorSupplier, CountingMemoryCache<CacheKey, CloseableImage> backingCache, AnimatedCache animatedCache, boolean downscaleFrameToDrawableDimensions, boolean useBalancedAnimationStrategy, int balancedStrategyPreparationMs, int animationFpsLimit, ExecutorService serialExecutorService) {
        Intrinsics.checkNotNullParameter(animatedCache, "animatedCache");
        if (!implLoaded) {
            try {
                Object newInstance = Class.forName("com.facebook.fresco.animation.factory.AnimatedFactoryV2Impl").getConstructor(PlatformBitmapFactory.class, ExecutorSupplier.class, CountingMemoryCache.class, AnimatedCache.class, Boolean.TYPE, Boolean.TYPE, Integer.TYPE, Integer.TYPE, SerialExecutorService.class).newInstance(platformBitmapFactory, executorSupplier, backingCache, animatedCache, Boolean.valueOf(downscaleFrameToDrawableDimensions), Boolean.valueOf(useBalancedAnimationStrategy), Integer.valueOf(balancedStrategyPreparationMs), Integer.valueOf(animationFpsLimit), serialExecutorService);
                Intrinsics.checkNotNull(newInstance, "null cannot be cast to non-null type com.facebook.imagepipeline.animated.factory.AnimatedFactory");
                impl = (AnimatedFactory) newInstance;
            } catch (Throwable unused) {
            }
            if (impl != null) {
                implLoaded = true;
            }
        }
        return impl;
    }
}
