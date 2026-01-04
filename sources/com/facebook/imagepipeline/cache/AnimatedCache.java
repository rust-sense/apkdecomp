package com.facebook.imagepipeline.cache;

import com.facebook.common.internal.Predicate;
import com.facebook.common.internal.Supplier;
import com.facebook.common.memory.MemoryTrimType;
import com.facebook.common.references.CloseableReference;
import com.facebook.imagepipeline.cache.MemoryCache;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AnimatedCache.kt */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u0000 \u00152\u00020\u0001:\u0001\u0015B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0016\u0010\r\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\u000e2\u0006\u0010\u000f\u001a\u00020\tJ\u000e\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\tJ\u000e\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u000f\u001a\u00020\tJ\u001e\u0010\u0013\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\u000e2\u0006\u0010\u000f\u001a\u00020\t2\u0006\u0010\u0014\u001a\u00020\nR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lcom/facebook/imagepipeline/cache/AnimatedCache;", "", "memoryMB", "", "(I)V", "evictionRatio", "", "lruCache", "Lcom/facebook/imagepipeline/cache/LruCountingMemoryCache;", "", "Lcom/facebook/imagepipeline/cache/AnimationFrames;", "maxCacheEntrySize", "sizeBytes", "findAnimation", "Lcom/facebook/common/references/CloseableReference;", "key", "getSize", "removeAnimation", "", "saveAnimation", "animationFrames", "Companion", "imagepipeline-base_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class AnimatedCache {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final int EVICTION_QUEUE = 50;
    private static AnimatedCache instance;
    private final float evictionRatio;
    private final LruCountingMemoryCache<String, AnimationFrames> lruCache;
    private final int maxCacheEntrySize;
    private final int sizeBytes;

    public /* synthetic */ AnimatedCache(int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(i);
    }

    @JvmStatic
    public static final AnimatedCache getInstance(int i) {
        return INSTANCE.getInstance(i);
    }

    private AnimatedCache(int i) {
        int i2 = 1048576 * i;
        this.sizeBytes = i2;
        this.evictionRatio = i < 90 ? 0.0f : 0.3f;
        this.maxCacheEntrySize = (int) (i2 * 0.1d);
        this.lruCache = new LruCountingMemoryCache<>(new ValueDescriptor() { // from class: com.facebook.imagepipeline.cache.AnimatedCache$$ExternalSyntheticLambda1
            @Override // com.facebook.imagepipeline.cache.ValueDescriptor
            public final int getSizeInBytes(Object obj) {
                int sizeBytes;
                sizeBytes = ((AnimationFrames) obj).getSizeBytes();
                return sizeBytes;
            }
        }, new MemoryCache.CacheTrimStrategy() { // from class: com.facebook.imagepipeline.cache.AnimatedCache$$ExternalSyntheticLambda2
            @Override // com.facebook.imagepipeline.cache.MemoryCache.CacheTrimStrategy
            public final double getTrimRatio(MemoryTrimType memoryTrimType) {
                double lruCache$lambda$1;
                lruCache$lambda$1 = AnimatedCache.lruCache$lambda$1(memoryTrimType);
                return lruCache$lambda$1;
            }
        }, new Supplier() { // from class: com.facebook.imagepipeline.cache.AnimatedCache$$ExternalSyntheticLambda3
            @Override // com.facebook.common.internal.Supplier
            public final Object get() {
                MemoryCacheParams lruCache$lambda$2;
                lruCache$lambda$2 = AnimatedCache.lruCache$lambda$2(AnimatedCache.this);
                return lruCache$lambda$2;
            }
        }, null, false, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final double lruCache$lambda$1(MemoryTrimType it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return it.getSuggestedTrimRatio();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final MemoryCacheParams lruCache$lambda$2(AnimatedCache this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        int i = this$0.sizeBytes;
        return new MemoryCacheParams(i, Integer.MAX_VALUE, (int) (i * this$0.evictionRatio), 50, this$0.maxCacheEntrySize, TimeUnit.SECONDS.toMillis(5L));
    }

    public final int getSize(String key) {
        Intrinsics.checkNotNullParameter(key, "key");
        return this.lruCache.getSizeInBytes();
    }

    public final CloseableReference<AnimationFrames> findAnimation(String key) {
        Intrinsics.checkNotNullParameter(key, "key");
        return this.lruCache.get(key);
    }

    public final CloseableReference<AnimationFrames> saveAnimation(String key, AnimationFrames animationFrames) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(animationFrames, "animationFrames");
        return this.lruCache.cache(key, CloseableReference.of(animationFrames));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean removeAnimation$lambda$3(String key, String cacheKey) {
        Intrinsics.checkNotNullParameter(key, "$key");
        Intrinsics.checkNotNullParameter(cacheKey, "cacheKey");
        return Intrinsics.areEqual(key, cacheKey);
    }

    public final void removeAnimation(final String key) {
        Intrinsics.checkNotNullParameter(key, "key");
        this.lruCache.removeAll(new Predicate() { // from class: com.facebook.imagepipeline.cache.AnimatedCache$$ExternalSyntheticLambda0
            @Override // com.facebook.common.internal.Predicate
            public final boolean apply(Object obj) {
                boolean removeAnimation$lambda$3;
                removeAnimation$lambda$3 = AnimatedCache.removeAnimation$lambda$3(key, (String) obj);
                return removeAnimation$lambda$3;
            }
        });
    }

    /* compiled from: AnimatedCache.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0004H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/facebook/imagepipeline/cache/AnimatedCache$Companion;", "", "()V", "EVICTION_QUEUE", "", "instance", "Lcom/facebook/imagepipeline/cache/AnimatedCache;", "getInstance", "memoryMB", "imagepipeline-base_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final AnimatedCache getInstance(int memoryMB) {
            AnimatedCache animatedCache = AnimatedCache.instance;
            if (animatedCache != null) {
                return animatedCache;
            }
            AnimatedCache animatedCache2 = new AnimatedCache(memoryMB, null);
            Companion companion = AnimatedCache.INSTANCE;
            AnimatedCache.instance = animatedCache2;
            return animatedCache2;
        }
    }
}
