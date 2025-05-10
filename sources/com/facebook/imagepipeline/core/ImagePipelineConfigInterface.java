package com.facebook.imagepipeline.core;

import android.content.Context;
import android.graphics.Bitmap;
import com.facebook.cache.common.CacheKey;
import com.facebook.cache.disk.DiskCacheConfig;
import com.facebook.callercontext.CallerContextVerifier;
import com.facebook.common.executors.SerialExecutorService;
import com.facebook.common.internal.Supplier;
import com.facebook.common.memory.MemoryTrimmableRegistry;
import com.facebook.common.memory.PooledByteBuffer;
import com.facebook.imagepipeline.bitmaps.PlatformBitmapFactory;
import com.facebook.imagepipeline.cache.BitmapMemoryCacheFactory;
import com.facebook.imagepipeline.cache.CacheKeyFactory;
import com.facebook.imagepipeline.cache.CountingMemoryCache;
import com.facebook.imagepipeline.cache.ImageCacheStatsTracker;
import com.facebook.imagepipeline.cache.MemoryCache;
import com.facebook.imagepipeline.cache.MemoryCacheParams;
import com.facebook.imagepipeline.debug.CloseableReferenceLeakTracker;
import com.facebook.imagepipeline.decoder.ImageDecoder;
import com.facebook.imagepipeline.decoder.ImageDecoderConfig;
import com.facebook.imagepipeline.decoder.ProgressiveJpegConfig;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.listener.RequestListener;
import com.facebook.imagepipeline.listener.RequestListener2;
import com.facebook.imagepipeline.memory.PoolFactory;
import com.facebook.imagepipeline.producers.CustomProducerSequenceFactory;
import com.facebook.imagepipeline.producers.NetworkFetcher;
import com.facebook.imagepipeline.transcoder.ImageTranscoderFactory;
import java.util.Set;
import kotlin.Metadata;

/* compiled from: ImagePipelineConfigInterface.kt */
@Metadata(d1 = {"\u0000\u008a\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001R \u0010\u0002\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u0004\u0018\u00010\tX¦\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u001a\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\rX¦\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u0012\u0010\u0010\u001a\u00020\u0011X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013R\u0018\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00160\u0015X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0018R\u0012\u0010\u0019\u001a\u00020\u001aX¦\u0004¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u001cR\u0012\u0010\u001d\u001a\u00020\u001eX¦\u0004¢\u0006\u0006\u001a\u0004\b\u001f\u0010 R\u0014\u0010!\u001a\u0004\u0018\u00010\"X¦\u0004¢\u0006\u0006\u001a\u0004\b#\u0010$R\u0012\u0010%\u001a\u00020&X¦\u0004¢\u0006\u0006\u001a\u0004\b'\u0010(R\u0012\u0010)\u001a\u00020*X¦\u0004¢\u0006\u0006\u001a\u0004\b+\u0010,R\u0018\u0010-\u001a\b\u0012\u0004\u0012\u00020/0.X¦\u0004¢\u0006\u0006\u001a\u0004\b0\u00101R\u0018\u00102\u001a\b\u0012\u0004\u0012\u0002030\u0015X¦\u0004¢\u0006\u0006\u001a\u0004\b4\u0010\u0018R \u00105\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u000206\u0018\u00010\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b7\u0010\u0007R\u0018\u00108\u001a\b\u0012\u0004\u0012\u00020\u00160\u0015X¦\u0004¢\u0006\u0006\u001a\u0004\b9\u0010\u0018R\u0012\u0010:\u001a\u00020\u001aX¦\u0004¢\u0006\u0006\u001a\u0004\b;\u0010\u001cR\u0014\u0010<\u001a\u0004\u0018\u00010=X¦\u0004¢\u0006\u0006\u001a\u0004\b>\u0010?R\u0012\u0010@\u001a\u00020AX¦\u0004¢\u0006\u0006\u001a\u0004\bB\u0010CR\u0012\u0010D\u001a\u00020EX¦\u0004¢\u0006\u0006\u001a\u0004\bF\u0010GR\u0012\u0010H\u001a\u00020IX¦\u0004¢\u0006\u0006\u001a\u0004\bJ\u0010KR\u0012\u0010L\u001a\u00020MX¦\u0004¢\u0006\u0006\u001a\u0004\bN\u0010OR\u0014\u0010P\u001a\u0004\u0018\u00010QX¦\u0004¢\u0006\u0006\u001a\u0004\bR\u0010SR\u0014\u0010T\u001a\u0004\u0018\u00010UX¦\u0004¢\u0006\u0006\u001a\u0004\bV\u0010WR\u0014\u0010X\u001a\u0004\u0018\u00010YX¦\u0004¢\u0006\u0006\u001a\u0004\bZ\u0010[R\u0016\u0010\\\u001a\u0004\u0018\u00010]8fX¦\u0004¢\u0006\u0006\u001a\u0004\b^\u0010_R\u0012\u0010`\u001a\u000203X¦\u0004¢\u0006\u0006\u001a\u0004\b`\u0010aR\u0012\u0010b\u001a\u000203X¦\u0004¢\u0006\u0006\u001a\u0004\bb\u0010aR\u0018\u0010c\u001a\b\u0012\u0004\u0012\u0002030\u0015X¦\u0004¢\u0006\u0006\u001a\u0004\bc\u0010\u0018R\u0012\u0010d\u001a\u000203X¦\u0004¢\u0006\u0006\u001a\u0004\bd\u0010aR\u0012\u0010e\u001a\u00020fX¦\u0004¢\u0006\u0006\u001a\u0004\bg\u0010hR\u0014\u0010i\u001a\u00020]8fX¦\u0004¢\u0006\u0006\u001a\u0004\bj\u0010kR\u0012\u0010l\u001a\u00020mX¦\u0004¢\u0006\u0006\u001a\u0004\bn\u0010oR\u0016\u0010p\u001a\u0006\u0012\u0002\b\u00030qX¦\u0004¢\u0006\u0006\u001a\u0004\br\u0010sR\u0014\u0010t\u001a\u0004\u0018\u00010uX¦\u0004¢\u0006\u0006\u001a\u0004\bv\u0010wR\u0012\u0010x\u001a\u00020yX¦\u0004¢\u0006\u0006\u001a\u0004\bz\u0010{R\u0012\u0010|\u001a\u00020}X¦\u0004¢\u0006\u0006\u001a\u0004\b~\u0010\u007fR\u001d\u0010\u0080\u0001\u001a\u000b\u0012\u0007\u0012\u0005\u0018\u00010\u0081\u00010.X¦\u0004¢\u0006\u0007\u001a\u0005\b\u0082\u0001\u00101R\u001d\u0010\u0083\u0001\u001a\u000b\u0012\u0007\u0012\u0005\u0018\u00010\u0084\u00010.X¦\u0004¢\u0006\u0007\u001a\u0005\b\u0085\u0001\u00101R\u0014\u0010\u0086\u0001\u001a\u00020fX¦\u0004¢\u0006\u0007\u001a\u0005\b\u0087\u0001\u0010h¨\u0006\u0088\u0001"}, d2 = {"Lcom/facebook/imagepipeline/core/ImagePipelineConfigInterface;", "", "bitmapCacheOverride", "Lcom/facebook/imagepipeline/cache/MemoryCache;", "Lcom/facebook/cache/common/CacheKey;", "Lcom/facebook/imagepipeline/image/CloseableImage;", "getBitmapCacheOverride", "()Lcom/facebook/imagepipeline/cache/MemoryCache;", "bitmapConfig", "Landroid/graphics/Bitmap$Config;", "getBitmapConfig", "()Landroid/graphics/Bitmap$Config;", "bitmapMemoryCacheEntryStateObserver", "Lcom/facebook/imagepipeline/cache/CountingMemoryCache$EntryStateObserver;", "getBitmapMemoryCacheEntryStateObserver", "()Lcom/facebook/imagepipeline/cache/CountingMemoryCache$EntryStateObserver;", "bitmapMemoryCacheFactory", "Lcom/facebook/imagepipeline/cache/BitmapMemoryCacheFactory;", "getBitmapMemoryCacheFactory", "()Lcom/facebook/imagepipeline/cache/BitmapMemoryCacheFactory;", "bitmapMemoryCacheParamsSupplier", "Lcom/facebook/common/internal/Supplier;", "Lcom/facebook/imagepipeline/cache/MemoryCacheParams;", "getBitmapMemoryCacheParamsSupplier", "()Lcom/facebook/common/internal/Supplier;", "bitmapMemoryCacheTrimStrategy", "Lcom/facebook/imagepipeline/cache/MemoryCache$CacheTrimStrategy;", "getBitmapMemoryCacheTrimStrategy", "()Lcom/facebook/imagepipeline/cache/MemoryCache$CacheTrimStrategy;", "cacheKeyFactory", "Lcom/facebook/imagepipeline/cache/CacheKeyFactory;", "getCacheKeyFactory", "()Lcom/facebook/imagepipeline/cache/CacheKeyFactory;", "callerContextVerifier", "Lcom/facebook/callercontext/CallerContextVerifier;", "getCallerContextVerifier", "()Lcom/facebook/callercontext/CallerContextVerifier;", "closeableReferenceLeakTracker", "Lcom/facebook/imagepipeline/debug/CloseableReferenceLeakTracker;", "getCloseableReferenceLeakTracker", "()Lcom/facebook/imagepipeline/debug/CloseableReferenceLeakTracker;", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "customProducerSequenceFactories", "", "Lcom/facebook/imagepipeline/producers/CustomProducerSequenceFactory;", "getCustomProducerSequenceFactories", "()Ljava/util/Set;", "enableEncodedImageColorSpaceUsage", "", "getEnableEncodedImageColorSpaceUsage", "encodedMemoryCacheOverride", "Lcom/facebook/common/memory/PooledByteBuffer;", "getEncodedMemoryCacheOverride", "encodedMemoryCacheParamsSupplier", "getEncodedMemoryCacheParamsSupplier", "encodedMemoryCacheTrimStrategy", "getEncodedMemoryCacheTrimStrategy", "executorServiceForAnimatedImages", "Lcom/facebook/common/executors/SerialExecutorService;", "getExecutorServiceForAnimatedImages", "()Lcom/facebook/common/executors/SerialExecutorService;", "executorSupplier", "Lcom/facebook/imagepipeline/core/ExecutorSupplier;", "getExecutorSupplier", "()Lcom/facebook/imagepipeline/core/ExecutorSupplier;", "experiments", "Lcom/facebook/imagepipeline/core/ImagePipelineExperiments;", "getExperiments", "()Lcom/facebook/imagepipeline/core/ImagePipelineExperiments;", "fileCacheFactory", "Lcom/facebook/imagepipeline/core/FileCacheFactory;", "getFileCacheFactory", "()Lcom/facebook/imagepipeline/core/FileCacheFactory;", "imageCacheStatsTracker", "Lcom/facebook/imagepipeline/cache/ImageCacheStatsTracker;", "getImageCacheStatsTracker", "()Lcom/facebook/imagepipeline/cache/ImageCacheStatsTracker;", "imageDecoder", "Lcom/facebook/imagepipeline/decoder/ImageDecoder;", "getImageDecoder", "()Lcom/facebook/imagepipeline/decoder/ImageDecoder;", "imageDecoderConfig", "Lcom/facebook/imagepipeline/decoder/ImageDecoderConfig;", "getImageDecoderConfig", "()Lcom/facebook/imagepipeline/decoder/ImageDecoderConfig;", "imageTranscoderFactory", "Lcom/facebook/imagepipeline/transcoder/ImageTranscoderFactory;", "getImageTranscoderFactory", "()Lcom/facebook/imagepipeline/transcoder/ImageTranscoderFactory;", "imageTranscoderType", "", "getImageTranscoderType", "()Ljava/lang/Integer;", "isDiskCacheEnabled", "()Z", "isDownsampleEnabled", "isPrefetchEnabledSupplier", "isResizeAndRotateEnabledForNetwork", "mainDiskCacheConfig", "Lcom/facebook/cache/disk/DiskCacheConfig;", "getMainDiskCacheConfig", "()Lcom/facebook/cache/disk/DiskCacheConfig;", "memoryChunkType", "getMemoryChunkType", "()I", "memoryTrimmableRegistry", "Lcom/facebook/common/memory/MemoryTrimmableRegistry;", "getMemoryTrimmableRegistry", "()Lcom/facebook/common/memory/MemoryTrimmableRegistry;", "networkFetcher", "Lcom/facebook/imagepipeline/producers/NetworkFetcher;", "getNetworkFetcher", "()Lcom/facebook/imagepipeline/producers/NetworkFetcher;", "platformBitmapFactory", "Lcom/facebook/imagepipeline/bitmaps/PlatformBitmapFactory;", "getPlatformBitmapFactory", "()Lcom/facebook/imagepipeline/bitmaps/PlatformBitmapFactory;", "poolFactory", "Lcom/facebook/imagepipeline/memory/PoolFactory;", "getPoolFactory", "()Lcom/facebook/imagepipeline/memory/PoolFactory;", "progressiveJpegConfig", "Lcom/facebook/imagepipeline/decoder/ProgressiveJpegConfig;", "getProgressiveJpegConfig", "()Lcom/facebook/imagepipeline/decoder/ProgressiveJpegConfig;", "requestListener2s", "Lcom/facebook/imagepipeline/listener/RequestListener2;", "getRequestListener2s", "requestListeners", "Lcom/facebook/imagepipeline/listener/RequestListener;", "getRequestListeners", "smallImageDiskCacheConfig", "getSmallImageDiskCacheConfig", "imagepipeline_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public interface ImagePipelineConfigInterface {
    MemoryCache<CacheKey, CloseableImage> getBitmapCacheOverride();

    Bitmap.Config getBitmapConfig();

    CountingMemoryCache.EntryStateObserver<CacheKey> getBitmapMemoryCacheEntryStateObserver();

    BitmapMemoryCacheFactory getBitmapMemoryCacheFactory();

    Supplier<MemoryCacheParams> getBitmapMemoryCacheParamsSupplier();

    MemoryCache.CacheTrimStrategy getBitmapMemoryCacheTrimStrategy();

    CacheKeyFactory getCacheKeyFactory();

    CallerContextVerifier getCallerContextVerifier();

    CloseableReferenceLeakTracker getCloseableReferenceLeakTracker();

    Context getContext();

    Set<CustomProducerSequenceFactory> getCustomProducerSequenceFactories();

    Supplier<Boolean> getEnableEncodedImageColorSpaceUsage();

    MemoryCache<CacheKey, PooledByteBuffer> getEncodedMemoryCacheOverride();

    Supplier<MemoryCacheParams> getEncodedMemoryCacheParamsSupplier();

    MemoryCache.CacheTrimStrategy getEncodedMemoryCacheTrimStrategy();

    SerialExecutorService getExecutorServiceForAnimatedImages();

    ExecutorSupplier getExecutorSupplier();

    ImagePipelineExperiments getExperiments();

    FileCacheFactory getFileCacheFactory();

    ImageCacheStatsTracker getImageCacheStatsTracker();

    ImageDecoder getImageDecoder();

    ImageDecoderConfig getImageDecoderConfig();

    ImageTranscoderFactory getImageTranscoderFactory();

    Integer getImageTranscoderType();

    DiskCacheConfig getMainDiskCacheConfig();

    int getMemoryChunkType();

    MemoryTrimmableRegistry getMemoryTrimmableRegistry();

    NetworkFetcher<?> getNetworkFetcher();

    PlatformBitmapFactory getPlatformBitmapFactory();

    PoolFactory getPoolFactory();

    ProgressiveJpegConfig getProgressiveJpegConfig();

    Set<RequestListener2> getRequestListener2s();

    Set<RequestListener> getRequestListeners();

    DiskCacheConfig getSmallImageDiskCacheConfig();

    /* renamed from: isDiskCacheEnabled */
    boolean getIsDiskCacheEnabled();

    /* renamed from: isDownsampleEnabled */
    boolean getIsDownsampleEnabled();

    Supplier<Boolean> isPrefetchEnabledSupplier();

    /* renamed from: isResizeAndRotateEnabledForNetwork */
    boolean getIsResizeAndRotateEnabledForNetwork();
}
