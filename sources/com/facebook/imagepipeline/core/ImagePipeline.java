package com.facebook.imagepipeline.core;

import android.net.Uri;
import bolts.Continuation;
import bolts.Task;
import com.facebook.cache.common.CacheKey;
import com.facebook.callercontext.CallerContextVerifier;
import com.facebook.common.internal.Objects;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.Predicate;
import com.facebook.common.internal.Supplier;
import com.facebook.common.memory.PooledByteBuffer;
import com.facebook.common.references.CloseableReference;
import com.facebook.datasource.DataSource;
import com.facebook.datasource.DataSources;
import com.facebook.datasource.SimpleDataSource;
import com.facebook.imagepipeline.cache.BufferedDiskCache;
import com.facebook.imagepipeline.cache.CacheKeyFactory;
import com.facebook.imagepipeline.cache.MemoryCache;
import com.facebook.imagepipeline.common.Priority;
import com.facebook.imagepipeline.datasource.CloseableProducerToDataSourceAdapter;
import com.facebook.imagepipeline.datasource.ProducerToDataSourceAdapter;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.listener.ForwardingRequestListener;
import com.facebook.imagepipeline.listener.ForwardingRequestListener2;
import com.facebook.imagepipeline.listener.RequestListener;
import com.facebook.imagepipeline.listener.RequestListener2;
import com.facebook.imagepipeline.producers.InternalRequestListener;
import com.facebook.imagepipeline.producers.Producer;
import com.facebook.imagepipeline.producers.SettableProducerContext;
import com.facebook.imagepipeline.producers.ThreadHandoffProducerQueue;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.facebook.imagepipeline.systrace.FrescoSystrace;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicLong;
import javax.annotation.Nullable;

/* loaded from: classes.dex */
public class ImagePipeline {
    private static final CancellationException PREFETCH_EXCEPTION = new CancellationException("Prefetching is not enabled");
    private final MemoryCache<CacheKey, CloseableImage> mBitmapMemoryCache;
    private final CacheKeyFactory mCacheKeyFactory;

    @Nullable
    private final CallerContextVerifier mCallerContextVerifier;
    private final ImagePipelineConfigInterface mConfig;
    private final MemoryCache<CacheKey, PooledByteBuffer> mEncodedMemoryCache;
    private AtomicLong mIdCounter = new AtomicLong();
    private final Supplier<Boolean> mIsPrefetchEnabledSupplier;
    private final Supplier<Boolean> mLazyDataSource;
    private final BufferedDiskCache mMainBufferedDiskCache;
    private final ProducerSequenceFactory mProducerSequenceFactory;
    private final RequestListener mRequestListener;
    private final RequestListener2 mRequestListener2;
    private final BufferedDiskCache mSmallImageBufferedDiskCache;
    private final Supplier<Boolean> mSuppressBitmapPrefetchingSupplier;
    private final ThreadHandoffProducerQueue mThreadHandoffProducerQueue;

    public MemoryCache<CacheKey, CloseableImage> getBitmapMemoryCache() {
        return this.mBitmapMemoryCache;
    }

    public CacheKeyFactory getCacheKeyFactory() {
        return this.mCacheKeyFactory;
    }

    public ImagePipelineConfigInterface getConfig() {
        return this.mConfig;
    }

    public ProducerSequenceFactory getProducerSequenceFactory() {
        return this.mProducerSequenceFactory;
    }

    public void init() {
    }

    public Supplier<Boolean> isLazyDataSource() {
        return this.mLazyDataSource;
    }

    public ImagePipeline(ProducerSequenceFactory producerSequenceFactory, Set<RequestListener> set, Set<RequestListener2> set2, Supplier<Boolean> supplier, MemoryCache<CacheKey, CloseableImage> memoryCache, MemoryCache<CacheKey, PooledByteBuffer> memoryCache2, BufferedDiskCache bufferedDiskCache, BufferedDiskCache bufferedDiskCache2, CacheKeyFactory cacheKeyFactory, ThreadHandoffProducerQueue threadHandoffProducerQueue, Supplier<Boolean> supplier2, Supplier<Boolean> supplier3, @Nullable CallerContextVerifier callerContextVerifier, ImagePipelineConfigInterface imagePipelineConfigInterface) {
        this.mProducerSequenceFactory = producerSequenceFactory;
        this.mRequestListener = new ForwardingRequestListener(set);
        this.mRequestListener2 = new ForwardingRequestListener2(set2);
        this.mIsPrefetchEnabledSupplier = supplier;
        this.mBitmapMemoryCache = memoryCache;
        this.mEncodedMemoryCache = memoryCache2;
        this.mMainBufferedDiskCache = bufferedDiskCache;
        this.mSmallImageBufferedDiskCache = bufferedDiskCache2;
        this.mCacheKeyFactory = cacheKeyFactory;
        this.mThreadHandoffProducerQueue = threadHandoffProducerQueue;
        this.mSuppressBitmapPrefetchingSupplier = supplier2;
        this.mLazyDataSource = supplier3;
        this.mCallerContextVerifier = callerContextVerifier;
        this.mConfig = imagePipelineConfigInterface;
    }

    public String generateUniqueFutureId() {
        return String.valueOf(this.mIdCounter.getAndIncrement());
    }

    public Supplier<DataSource<CloseableReference<CloseableImage>>> getDataSourceSupplier(final ImageRequest imageRequest, @Nullable final Object obj, final ImageRequest.RequestLevel requestLevel) {
        return new Supplier<DataSource<CloseableReference<CloseableImage>>>() { // from class: com.facebook.imagepipeline.core.ImagePipeline.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.facebook.common.internal.Supplier
            public DataSource<CloseableReference<CloseableImage>> get() {
                return ImagePipeline.this.fetchDecodedImage(imageRequest, obj, requestLevel);
            }

            public String toString() {
                return Objects.toStringHelper(this).add("uri", imageRequest.getSourceUri()).toString();
            }
        };
    }

    public Supplier<DataSource<CloseableReference<CloseableImage>>> getDataSourceSupplier(final ImageRequest imageRequest, @Nullable final Object obj, final ImageRequest.RequestLevel requestLevel, @Nullable final RequestListener requestListener) {
        return new Supplier<DataSource<CloseableReference<CloseableImage>>>() { // from class: com.facebook.imagepipeline.core.ImagePipeline.2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.facebook.common.internal.Supplier
            public DataSource<CloseableReference<CloseableImage>> get() {
                return ImagePipeline.this.fetchDecodedImage(imageRequest, obj, requestLevel, requestListener);
            }

            public String toString() {
                return Objects.toStringHelper(this).add("uri", imageRequest.getSourceUri()).toString();
            }
        };
    }

    public Supplier<DataSource<CloseableReference<CloseableImage>>> getDataSourceSupplier(final ImageRequest imageRequest, @Nullable final Object obj, final ImageRequest.RequestLevel requestLevel, @Nullable final RequestListener requestListener, @Nullable final String str) {
        return new Supplier<DataSource<CloseableReference<CloseableImage>>>() { // from class: com.facebook.imagepipeline.core.ImagePipeline.3
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.facebook.common.internal.Supplier
            public DataSource<CloseableReference<CloseableImage>> get() {
                return ImagePipeline.this.fetchDecodedImage(imageRequest, obj, requestLevel, requestListener, str);
            }

            public String toString() {
                return Objects.toStringHelper(this).add("uri", imageRequest.getSourceUri()).toString();
            }
        };
    }

    public Supplier<DataSource<CloseableReference<PooledByteBuffer>>> getEncodedImageDataSourceSupplier(final ImageRequest imageRequest, @Nullable final Object obj) {
        return new Supplier<DataSource<CloseableReference<PooledByteBuffer>>>() { // from class: com.facebook.imagepipeline.core.ImagePipeline.4
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.facebook.common.internal.Supplier
            public DataSource<CloseableReference<PooledByteBuffer>> get() {
                return ImagePipeline.this.fetchEncodedImage(imageRequest, obj);
            }

            public String toString() {
                return Objects.toStringHelper(this).add("uri", imageRequest.getSourceUri()).toString();
            }
        };
    }

    public DataSource<CloseableReference<CloseableImage>> fetchImageFromBitmapCache(ImageRequest imageRequest, @Nullable Object obj) {
        return fetchDecodedImage(imageRequest, obj, ImageRequest.RequestLevel.BITMAP_MEMORY_CACHE);
    }

    public DataSource<CloseableReference<CloseableImage>> fetchDecodedImage(@Nullable ImageRequest imageRequest, @Nullable Object obj) {
        return fetchDecodedImage(imageRequest, obj, ImageRequest.RequestLevel.FULL_FETCH);
    }

    public DataSource<CloseableReference<CloseableImage>> fetchDecodedImage(ImageRequest imageRequest, @Nullable Object obj, @Nullable RequestListener requestListener) {
        return fetchDecodedImage(imageRequest, obj, ImageRequest.RequestLevel.FULL_FETCH, requestListener);
    }

    public DataSource<CloseableReference<CloseableImage>> fetchDecodedImage(@Nullable ImageRequest imageRequest, @Nullable Object obj, ImageRequest.RequestLevel requestLevel) {
        return fetchDecodedImage(imageRequest, obj, requestLevel, null);
    }

    public DataSource<CloseableReference<CloseableImage>> fetchDecodedImage(@Nullable ImageRequest imageRequest, @Nullable Object obj, ImageRequest.RequestLevel requestLevel, @Nullable RequestListener requestListener) {
        return fetchDecodedImage(imageRequest, obj, requestLevel, requestListener, null);
    }

    public DataSource<CloseableReference<CloseableImage>> fetchDecodedImage(@Nullable ImageRequest imageRequest, @Nullable Object obj, ImageRequest.RequestLevel requestLevel, @Nullable RequestListener requestListener, @Nullable String str) {
        try {
            Preconditions.checkNotNull(imageRequest);
            return submitFetchRequest(this.mProducerSequenceFactory.getDecodedImageProducerSequence(imageRequest), imageRequest, requestLevel, obj, requestListener, str);
        } catch (Exception e) {
            return DataSources.immediateFailedDataSource(e);
        }
    }

    public DataSource<CloseableReference<CloseableImage>> fetchDecodedImage(ImageRequest imageRequest, @Nullable Object obj, ImageRequest.RequestLevel requestLevel, @Nullable RequestListener requestListener, @Nullable String str, @Nullable Map<String, ?> map) {
        try {
            return submitFetchRequest(this.mProducerSequenceFactory.getDecodedImageProducerSequence(imageRequest), imageRequest, requestLevel, obj, requestListener, str, map);
        } catch (Exception e) {
            return DataSources.immediateFailedDataSource(e);
        }
    }

    public DataSource<CloseableReference<PooledByteBuffer>> fetchEncodedImage(ImageRequest imageRequest, @Nullable Object obj) {
        return fetchEncodedImage(imageRequest, obj, null);
    }

    public DataSource<CloseableReference<PooledByteBuffer>> fetchEncodedImage(ImageRequest imageRequest, @Nullable Object obj, @Nullable RequestListener requestListener) {
        Preconditions.checkNotNull(imageRequest.getSourceUri());
        try {
            Producer<CloseableReference<PooledByteBuffer>> encodedImageProducerSequence = this.mProducerSequenceFactory.getEncodedImageProducerSequence(imageRequest);
            if (imageRequest.getResizeOptions() != null) {
                imageRequest = ImageRequestBuilder.fromRequest(imageRequest).setResizeOptions(null).build();
            }
            return submitFetchRequest(encodedImageProducerSequence, imageRequest, ImageRequest.RequestLevel.FULL_FETCH, obj, requestListener, null, null);
        } catch (Exception e) {
            return DataSources.immediateFailedDataSource(e);
        }
    }

    public DataSource<Void> prefetchToBitmapCache(ImageRequest imageRequest, @Nullable Object obj) {
        return prefetchToBitmapCache(imageRequest, obj, null);
    }

    /* JADX WARN: Code restructure failed: missing block: B:38:0x006a, code lost:
    
        if (r8.mSuppressBitmapPrefetchingSupplier.get().booleanValue() != false) goto L30;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public com.facebook.datasource.DataSource<java.lang.Void> prefetchToBitmapCache(com.facebook.imagepipeline.request.ImageRequest r9, @javax.annotation.Nullable java.lang.Object r10, @javax.annotation.Nullable com.facebook.imagepipeline.listener.RequestListener r11) {
        /*
            r8 = this;
            boolean r0 = com.facebook.imagepipeline.systrace.FrescoSystrace.isTracing()     // Catch: java.lang.Throwable -> L9f
            if (r0 == 0) goto Lb
            java.lang.String r0 = "ImagePipeline#prefetchToBitmapCache"
            com.facebook.imagepipeline.systrace.FrescoSystrace.beginSection(r0)     // Catch: java.lang.Throwable -> L9f
        Lb:
            com.facebook.common.internal.Supplier<java.lang.Boolean> r0 = r8.mIsPrefetchEnabledSupplier     // Catch: java.lang.Throwable -> L9f
            java.lang.Object r0 = r0.get()     // Catch: java.lang.Throwable -> L9f
            java.lang.Boolean r0 = (java.lang.Boolean) r0     // Catch: java.lang.Throwable -> L9f
            boolean r0 = r0.booleanValue()     // Catch: java.lang.Throwable -> L9f
            if (r0 != 0) goto L29
            java.util.concurrent.CancellationException r9 = com.facebook.imagepipeline.core.ImagePipeline.PREFETCH_EXCEPTION     // Catch: java.lang.Throwable -> L9f
            com.facebook.datasource.DataSource r9 = com.facebook.datasource.DataSources.immediateFailedDataSource(r9)     // Catch: java.lang.Throwable -> L9f
            boolean r10 = com.facebook.imagepipeline.systrace.FrescoSystrace.isTracing()
            if (r10 == 0) goto L28
            com.facebook.imagepipeline.systrace.FrescoSystrace.endSection()
        L28:
            return r9
        L29:
            com.facebook.imagepipeline.core.ImagePipelineConfigInterface r0 = r8.mConfig     // Catch: java.lang.Exception -> L90 java.lang.Throwable -> L9f
            com.facebook.imagepipeline.core.ImagePipelineExperiments r0 = r0.getExperiments()     // Catch: java.lang.Exception -> L90 java.lang.Throwable -> L9f
            if (r0 == 0) goto L51
            com.facebook.imagepipeline.core.ImagePipelineConfigInterface r0 = r8.mConfig     // Catch: java.lang.Exception -> L90 java.lang.Throwable -> L9f
            com.facebook.imagepipeline.core.ImagePipelineExperiments r0 = r0.getExperiments()     // Catch: java.lang.Exception -> L90 java.lang.Throwable -> L9f
            boolean r0 = r0.getPrefetchShortcutEnabled()     // Catch: java.lang.Exception -> L90 java.lang.Throwable -> L9f
            if (r0 == 0) goto L51
            boolean r0 = r8.isInBitmapMemoryCache(r9)     // Catch: java.lang.Exception -> L90 java.lang.Throwable -> L9f
            if (r0 == 0) goto L51
            com.facebook.datasource.DataSource r9 = com.facebook.datasource.DataSources.immediateSuccessfulDataSource()     // Catch: java.lang.Exception -> L90 java.lang.Throwable -> L9f
            boolean r10 = com.facebook.imagepipeline.systrace.FrescoSystrace.isTracing()
            if (r10 == 0) goto L50
            com.facebook.imagepipeline.systrace.FrescoSystrace.endSection()
        L50:
            return r9
        L51:
            java.lang.Boolean r0 = r9.shouldDecodePrefetches()     // Catch: java.lang.Exception -> L90 java.lang.Throwable -> L9f
            if (r0 == 0) goto L5e
            boolean r0 = r0.booleanValue()     // Catch: java.lang.Exception -> L90 java.lang.Throwable -> L9f
            if (r0 != 0) goto L73
            goto L6c
        L5e:
            com.facebook.common.internal.Supplier<java.lang.Boolean> r0 = r8.mSuppressBitmapPrefetchingSupplier     // Catch: java.lang.Exception -> L90 java.lang.Throwable -> L9f
            java.lang.Object r0 = r0.get()     // Catch: java.lang.Exception -> L90 java.lang.Throwable -> L9f
            java.lang.Boolean r0 = (java.lang.Boolean) r0     // Catch: java.lang.Exception -> L90 java.lang.Throwable -> L9f
            boolean r0 = r0.booleanValue()     // Catch: java.lang.Exception -> L90 java.lang.Throwable -> L9f
            if (r0 == 0) goto L73
        L6c:
            com.facebook.imagepipeline.core.ProducerSequenceFactory r0 = r8.mProducerSequenceFactory     // Catch: java.lang.Exception -> L90 java.lang.Throwable -> L9f
            com.facebook.imagepipeline.producers.Producer r0 = r0.getEncodedImagePrefetchProducerSequence(r9)     // Catch: java.lang.Exception -> L90 java.lang.Throwable -> L9f
            goto L79
        L73:
            com.facebook.imagepipeline.core.ProducerSequenceFactory r0 = r8.mProducerSequenceFactory     // Catch: java.lang.Exception -> L90 java.lang.Throwable -> L9f
            com.facebook.imagepipeline.producers.Producer r0 = r0.getDecodedImagePrefetchProducerSequence(r9)     // Catch: java.lang.Exception -> L90 java.lang.Throwable -> L9f
        L79:
            r2 = r0
            com.facebook.imagepipeline.request.ImageRequest$RequestLevel r4 = com.facebook.imagepipeline.request.ImageRequest.RequestLevel.FULL_FETCH     // Catch: java.lang.Exception -> L90 java.lang.Throwable -> L9f
            com.facebook.imagepipeline.common.Priority r6 = com.facebook.imagepipeline.common.Priority.MEDIUM     // Catch: java.lang.Exception -> L90 java.lang.Throwable -> L9f
            r1 = r8
            r3 = r9
            r5 = r10
            r7 = r11
            com.facebook.datasource.DataSource r9 = r1.submitPrefetchRequest(r2, r3, r4, r5, r6, r7)     // Catch: java.lang.Exception -> L90 java.lang.Throwable -> L9f
            boolean r10 = com.facebook.imagepipeline.systrace.FrescoSystrace.isTracing()
            if (r10 == 0) goto L8f
            com.facebook.imagepipeline.systrace.FrescoSystrace.endSection()
        L8f:
            return r9
        L90:
            r9 = move-exception
            com.facebook.datasource.DataSource r9 = com.facebook.datasource.DataSources.immediateFailedDataSource(r9)     // Catch: java.lang.Throwable -> L9f
            boolean r10 = com.facebook.imagepipeline.systrace.FrescoSystrace.isTracing()
            if (r10 == 0) goto L9e
            com.facebook.imagepipeline.systrace.FrescoSystrace.endSection()
        L9e:
            return r9
        L9f:
            r9 = move-exception
            boolean r10 = com.facebook.imagepipeline.systrace.FrescoSystrace.isTracing()
            if (r10 == 0) goto La9
            com.facebook.imagepipeline.systrace.FrescoSystrace.endSection()
        La9:
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.imagepipeline.core.ImagePipeline.prefetchToBitmapCache(com.facebook.imagepipeline.request.ImageRequest, java.lang.Object, com.facebook.imagepipeline.listener.RequestListener):com.facebook.datasource.DataSource");
    }

    public DataSource<Void> prefetchToDiskCache(@Nullable ImageRequest imageRequest, @Nullable Object obj) {
        return prefetchToDiskCache(imageRequest, obj, Priority.MEDIUM);
    }

    public DataSource<Void> prefetchToDiskCache(@Nullable ImageRequest imageRequest, @Nullable Object obj, @Nullable RequestListener requestListener) {
        return prefetchToDiskCache(imageRequest, obj, Priority.MEDIUM, requestListener);
    }

    public DataSource<Void> prefetchToDiskCache(@Nullable ImageRequest imageRequest, @Nullable Object obj, Priority priority) {
        return prefetchToDiskCache(imageRequest, obj, priority, null);
    }

    public DataSource<Void> prefetchToDiskCache(@Nullable ImageRequest imageRequest, @Nullable Object obj, Priority priority, @Nullable RequestListener requestListener) {
        if (!this.mIsPrefetchEnabledSupplier.get().booleanValue()) {
            return DataSources.immediateFailedDataSource(PREFETCH_EXCEPTION);
        }
        if (imageRequest == null) {
            return DataSources.immediateFailedDataSource(new NullPointerException("imageRequest is null"));
        }
        try {
            return submitPrefetchRequest(this.mProducerSequenceFactory.getEncodedImagePrefetchProducerSequence(imageRequest), imageRequest, ImageRequest.RequestLevel.FULL_FETCH, obj, priority, requestListener);
        } catch (Exception e) {
            return DataSources.immediateFailedDataSource(e);
        }
    }

    public DataSource<Void> prefetchToEncodedCache(ImageRequest imageRequest, @Nullable Object obj) {
        return prefetchToEncodedCache(imageRequest, obj, Priority.MEDIUM);
    }

    public DataSource<Void> prefetchToEncodedCache(ImageRequest imageRequest, @Nullable Object obj, @Nullable RequestListener requestListener) {
        return prefetchToEncodedCache(imageRequest, obj, Priority.MEDIUM, requestListener);
    }

    public DataSource<Void> prefetchToEncodedCache(ImageRequest imageRequest, @Nullable Object obj, Priority priority) {
        return prefetchToEncodedCache(imageRequest, obj, priority, null);
    }

    public DataSource<Void> prefetchToEncodedCache(ImageRequest imageRequest, @Nullable Object obj, Priority priority, @Nullable RequestListener requestListener) {
        try {
            if (FrescoSystrace.isTracing()) {
                FrescoSystrace.beginSection("ImagePipeline#prefetchToEncodedCache");
            }
            if (!this.mIsPrefetchEnabledSupplier.get().booleanValue()) {
                DataSource<Void> immediateFailedDataSource = DataSources.immediateFailedDataSource(PREFETCH_EXCEPTION);
                if (FrescoSystrace.isTracing()) {
                    FrescoSystrace.endSection();
                }
                return immediateFailedDataSource;
            }
            try {
                if (this.mConfig.getExperiments() != null && this.mConfig.getExperiments().getPrefetchShortcutEnabled() && isInEncodedMemoryCache(imageRequest)) {
                    DataSource<Void> immediateSuccessfulDataSource = DataSources.immediateSuccessfulDataSource();
                    if (FrescoSystrace.isTracing()) {
                        FrescoSystrace.endSection();
                    }
                    return immediateSuccessfulDataSource;
                }
                DataSource<Void> submitPrefetchRequest = submitPrefetchRequest(this.mProducerSequenceFactory.getEncodedImagePrefetchProducerSequence(imageRequest), imageRequest, ImageRequest.RequestLevel.FULL_FETCH, obj, priority, requestListener);
                if (FrescoSystrace.isTracing()) {
                    FrescoSystrace.endSection();
                }
                return submitPrefetchRequest;
            } catch (Exception e) {
                DataSource<Void> immediateFailedDataSource2 = DataSources.immediateFailedDataSource(e);
                if (FrescoSystrace.isTracing()) {
                    FrescoSystrace.endSection();
                }
                return immediateFailedDataSource2;
            }
        } catch (Throwable th) {
            if (FrescoSystrace.isTracing()) {
                FrescoSystrace.endSection();
            }
            throw th;
        }
    }

    public void evictFromMemoryCache(Uri uri) {
        Predicate<CacheKey> predicateForUri = predicateForUri(uri);
        this.mBitmapMemoryCache.removeAll(predicateForUri);
        this.mEncodedMemoryCache.removeAll(predicateForUri);
    }

    public void evictFromDiskCache(Uri uri) {
        evictFromDiskCache((ImageRequest) Preconditions.checkNotNull(ImageRequest.fromUri(uri)));
    }

    public void evictFromDiskCache(@Nullable ImageRequest imageRequest) {
        if (imageRequest == null) {
            return;
        }
        CacheKey encodedCacheKey = this.mCacheKeyFactory.getEncodedCacheKey(imageRequest, null);
        this.mMainBufferedDiskCache.remove(encodedCacheKey);
        this.mSmallImageBufferedDiskCache.remove(encodedCacheKey);
    }

    public void evictFromCache(Uri uri) {
        evictFromMemoryCache(uri);
        evictFromDiskCache(uri);
    }

    public void clearMemoryCaches() {
        Predicate<CacheKey> predicate = new Predicate<CacheKey>() { // from class: com.facebook.imagepipeline.core.ImagePipeline.5
            @Override // com.facebook.common.internal.Predicate
            public boolean apply(CacheKey cacheKey) {
                return true;
            }
        };
        this.mBitmapMemoryCache.removeAll(predicate);
        this.mEncodedMemoryCache.removeAll(predicate);
    }

    public void clearDiskCaches() {
        this.mMainBufferedDiskCache.clearAll();
        this.mSmallImageBufferedDiskCache.clearAll();
    }

    public long getUsedDiskCacheSize() {
        return this.mMainBufferedDiskCache.getSize() + this.mSmallImageBufferedDiskCache.getSize();
    }

    public void clearCaches() {
        clearMemoryCaches();
        clearDiskCaches();
    }

    public boolean isInBitmapMemoryCache(Uri uri) {
        if (uri == null) {
            return false;
        }
        return this.mBitmapMemoryCache.contains(predicateForUri(uri));
    }

    public boolean isInBitmapMemoryCache(ImageRequest imageRequest) {
        if (imageRequest == null) {
            return false;
        }
        CloseableReference<CloseableImage> closeableReference = this.mBitmapMemoryCache.get(this.mCacheKeyFactory.getBitmapCacheKey(imageRequest, null));
        try {
            return CloseableReference.isValid(closeableReference);
        } finally {
            CloseableReference.closeSafely(closeableReference);
        }
    }

    public boolean isInEncodedMemoryCache(Uri uri) {
        if (uri == null) {
            return false;
        }
        return this.mEncodedMemoryCache.contains(predicateForUri(uri));
    }

    public boolean isInEncodedMemoryCache(ImageRequest imageRequest) {
        if (imageRequest == null) {
            return false;
        }
        CloseableReference<PooledByteBuffer> closeableReference = this.mEncodedMemoryCache.get(this.mCacheKeyFactory.getEncodedCacheKey(imageRequest, null));
        try {
            return CloseableReference.isValid(closeableReference);
        } finally {
            CloseableReference.closeSafely(closeableReference);
        }
    }

    public boolean isInDiskCacheSync(Uri uri) {
        return isInDiskCacheSync(uri, ImageRequest.CacheChoice.SMALL) || isInDiskCacheSync(uri, ImageRequest.CacheChoice.DEFAULT);
    }

    public boolean isInDiskCacheSync(Uri uri, ImageRequest.CacheChoice cacheChoice) {
        return isInDiskCacheSync(ImageRequestBuilder.newBuilderWithSource(uri).setCacheChoice(cacheChoice).build());
    }

    public boolean isInDiskCacheSync(ImageRequest imageRequest) {
        CacheKey encodedCacheKey = this.mCacheKeyFactory.getEncodedCacheKey(imageRequest, null);
        int i = AnonymousClass9.$SwitchMap$com$facebook$imagepipeline$request$ImageRequest$CacheChoice[imageRequest.getCacheChoice().ordinal()];
        if (i == 1) {
            return this.mMainBufferedDiskCache.diskCheckSync(encodedCacheKey);
        }
        if (i != 2) {
            return false;
        }
        return this.mSmallImageBufferedDiskCache.diskCheckSync(encodedCacheKey);
    }

    /* renamed from: com.facebook.imagepipeline.core.ImagePipeline$9, reason: invalid class name */
    static /* synthetic */ class AnonymousClass9 {
        static final /* synthetic */ int[] $SwitchMap$com$facebook$imagepipeline$request$ImageRequest$CacheChoice;

        static {
            int[] iArr = new int[ImageRequest.CacheChoice.values().length];
            $SwitchMap$com$facebook$imagepipeline$request$ImageRequest$CacheChoice = iArr;
            try {
                iArr[ImageRequest.CacheChoice.DEFAULT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$facebook$imagepipeline$request$ImageRequest$CacheChoice[ImageRequest.CacheChoice.SMALL.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public DataSource<Boolean> isInDiskCache(Uri uri) {
        return isInDiskCache((ImageRequest) Preconditions.checkNotNull(ImageRequest.fromUri(uri)));
    }

    public DataSource<Boolean> isInDiskCache(ImageRequest imageRequest) {
        final CacheKey encodedCacheKey = this.mCacheKeyFactory.getEncodedCacheKey(imageRequest, null);
        final SimpleDataSource create = SimpleDataSource.create();
        this.mMainBufferedDiskCache.contains(encodedCacheKey).continueWithTask(new Continuation<Boolean, Task<Boolean>>() { // from class: com.facebook.imagepipeline.core.ImagePipeline.7
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // bolts.Continuation
            public Task<Boolean> then(Task<Boolean> task) throws Exception {
                if (task.isCancelled() || task.isFaulted() || !task.getResult().booleanValue()) {
                    return ImagePipeline.this.mSmallImageBufferedDiskCache.contains(encodedCacheKey);
                }
                return Task.forResult(true);
            }
        }).continueWith(new Continuation<Boolean, Void>() { // from class: com.facebook.imagepipeline.core.ImagePipeline.6
            @Override // bolts.Continuation
            public Void then(Task<Boolean> task) throws Exception {
                create.setResult(Boolean.valueOf((task.isCancelled() || task.isFaulted() || !task.getResult().booleanValue()) ? false : true));
                return null;
            }
        });
        return create;
    }

    @Nullable
    public CacheKey getCacheKey(@Nullable ImageRequest imageRequest, @Nullable Object obj) {
        CacheKey cacheKey;
        if (FrescoSystrace.isTracing()) {
            FrescoSystrace.beginSection("ImagePipeline#getCacheKey");
        }
        CacheKeyFactory cacheKeyFactory = this.mCacheKeyFactory;
        if (cacheKeyFactory == null || imageRequest == null) {
            cacheKey = null;
        } else if (imageRequest.getPostprocessor() != null) {
            cacheKey = cacheKeyFactory.getPostprocessedBitmapCacheKey(imageRequest, obj);
        } else {
            cacheKey = cacheKeyFactory.getBitmapCacheKey(imageRequest, obj);
        }
        if (FrescoSystrace.isTracing()) {
            FrescoSystrace.endSection();
        }
        return cacheKey;
    }

    @Nullable
    public CloseableReference<CloseableImage> getCachedImage(@Nullable CacheKey cacheKey) {
        MemoryCache<CacheKey, CloseableImage> memoryCache = this.mBitmapMemoryCache;
        if (memoryCache == null || cacheKey == null) {
            return null;
        }
        CloseableReference<CloseableImage> closeableReference = memoryCache.get(cacheKey);
        if (closeableReference == null || closeableReference.get().getQualityInfo().isOfFullQuality()) {
            return closeableReference;
        }
        closeableReference.close();
        return null;
    }

    public boolean hasCachedImage(@Nullable CacheKey cacheKey) {
        MemoryCache<CacheKey, CloseableImage> memoryCache = this.mBitmapMemoryCache;
        if (memoryCache == null || cacheKey == null) {
            return false;
        }
        return memoryCache.contains((MemoryCache<CacheKey, CloseableImage>) cacheKey);
    }

    private <T> DataSource<CloseableReference<T>> submitFetchRequest(Producer<CloseableReference<T>> producer, ImageRequest imageRequest, ImageRequest.RequestLevel requestLevel, @Nullable Object obj, @Nullable RequestListener requestListener, @Nullable String str) {
        return submitFetchRequest(producer, imageRequest, requestLevel, obj, requestListener, str, null);
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x006d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private <T> com.facebook.datasource.DataSource<com.facebook.common.references.CloseableReference<T>> submitFetchRequest(com.facebook.imagepipeline.producers.Producer<com.facebook.common.references.CloseableReference<T>> r15, com.facebook.imagepipeline.request.ImageRequest r16, com.facebook.imagepipeline.request.ImageRequest.RequestLevel r17, @javax.annotation.Nullable java.lang.Object r18, @javax.annotation.Nullable com.facebook.imagepipeline.listener.RequestListener r19, @javax.annotation.Nullable java.lang.String r20, @javax.annotation.Nullable java.util.Map<java.lang.String, ?> r21) {
        /*
            r14 = this;
            r1 = r14
            boolean r0 = com.facebook.imagepipeline.systrace.FrescoSystrace.isTracing()
            if (r0 == 0) goto Lc
            java.lang.String r0 = "ImagePipeline#submitFetchRequest"
            com.facebook.imagepipeline.systrace.FrescoSystrace.beginSection(r0)
        Lc:
            com.facebook.imagepipeline.producers.InternalRequestListener r0 = new com.facebook.imagepipeline.producers.InternalRequestListener
            r3 = r16
            r2 = r19
            com.facebook.imagepipeline.listener.RequestListener r2 = r14.getRequestListenerForRequest(r3, r2)
            com.facebook.imagepipeline.listener.RequestListener2 r4 = r1.mRequestListener2
            r0.<init>(r2, r4)
            com.facebook.callercontext.CallerContextVerifier r2 = r1.mCallerContextVerifier
            r4 = 0
            r7 = r18
            if (r2 == 0) goto L25
            r2.verifyCallerContext(r7, r4)
        L25:
            com.facebook.imagepipeline.request.ImageRequest$RequestLevel r2 = r16.getLowestPermittedRequestLevel()     // Catch: java.lang.Throwable -> L71 java.lang.Exception -> L73
            r5 = r17
            com.facebook.imagepipeline.request.ImageRequest$RequestLevel r8 = com.facebook.imagepipeline.request.ImageRequest.RequestLevel.getMax(r2, r5)     // Catch: java.lang.Throwable -> L71 java.lang.Exception -> L73
            com.facebook.imagepipeline.producers.SettableProducerContext r13 = new com.facebook.imagepipeline.producers.SettableProducerContext     // Catch: java.lang.Throwable -> L71 java.lang.Exception -> L73
            java.lang.String r5 = r14.generateUniqueFutureId()     // Catch: java.lang.Throwable -> L71 java.lang.Exception -> L73
            r9 = 0
            boolean r2 = r16.getProgressiveRenderingEnabled()     // Catch: java.lang.Throwable -> L71 java.lang.Exception -> L73
            if (r2 != 0) goto L49
            android.net.Uri r2 = r16.getSourceUri()     // Catch: java.lang.Throwable -> L71 java.lang.Exception -> L73
            boolean r2 = com.facebook.common.util.UriUtil.isNetworkUri(r2)     // Catch: java.lang.Throwable -> L71 java.lang.Exception -> L73
            if (r2 != 0) goto L47
            goto L49
        L47:
            r10 = r4
            goto L4b
        L49:
            r2 = 1
            r10 = r2
        L4b:
            com.facebook.imagepipeline.common.Priority r11 = r16.getPriority()     // Catch: java.lang.Throwable -> L71 java.lang.Exception -> L73
            com.facebook.imagepipeline.core.ImagePipelineConfigInterface r12 = r1.mConfig     // Catch: java.lang.Throwable -> L71 java.lang.Exception -> L73
            r2 = r13
            r3 = r16
            r4 = r5
            r5 = r20
            r6 = r0
            r7 = r18
            r2.<init>(r3, r4, r5, r6, r7, r8, r9, r10, r11, r12)     // Catch: java.lang.Throwable -> L71 java.lang.Exception -> L73
            r2 = r21
            r13.putExtras(r2)     // Catch: java.lang.Throwable -> L71 java.lang.Exception -> L73
            r2 = r15
            com.facebook.datasource.DataSource r0 = com.facebook.imagepipeline.datasource.CloseableProducerToDataSourceAdapter.create(r15, r13, r0)     // Catch: java.lang.Throwable -> L71 java.lang.Exception -> L73
            boolean r2 = com.facebook.imagepipeline.systrace.FrescoSystrace.isTracing()
            if (r2 == 0) goto L70
            com.facebook.imagepipeline.systrace.FrescoSystrace.endSection()
        L70:
            return r0
        L71:
            r0 = move-exception
            goto L82
        L73:
            r0 = move-exception
            com.facebook.datasource.DataSource r0 = com.facebook.datasource.DataSources.immediateFailedDataSource(r0)     // Catch: java.lang.Throwable -> L71
            boolean r2 = com.facebook.imagepipeline.systrace.FrescoSystrace.isTracing()
            if (r2 == 0) goto L81
            com.facebook.imagepipeline.systrace.FrescoSystrace.endSection()
        L81:
            return r0
        L82:
            boolean r2 = com.facebook.imagepipeline.systrace.FrescoSystrace.isTracing()
            if (r2 == 0) goto L8b
            com.facebook.imagepipeline.systrace.FrescoSystrace.endSection()
        L8b:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.imagepipeline.core.ImagePipeline.submitFetchRequest(com.facebook.imagepipeline.producers.Producer, com.facebook.imagepipeline.request.ImageRequest, com.facebook.imagepipeline.request.ImageRequest$RequestLevel, java.lang.Object, com.facebook.imagepipeline.listener.RequestListener, java.lang.String, java.util.Map):com.facebook.datasource.DataSource");
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0068  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private <T> com.facebook.datasource.DataSource<com.facebook.common.references.CloseableReference<T>> submitFetchRequest(com.facebook.imagepipeline.producers.Producer<com.facebook.common.references.CloseableReference<T>> r15, com.facebook.imagepipeline.request.ImageRequest r16, com.facebook.imagepipeline.request.ImageRequest.RequestLevel r17, @javax.annotation.Nullable java.lang.Object r18, @javax.annotation.Nullable com.facebook.imagepipeline.listener.RequestListener r19, @javax.annotation.Nullable java.util.Map<java.lang.String, ?> r20) {
        /*
            r14 = this;
            r1 = r14
            boolean r0 = com.facebook.imagepipeline.systrace.FrescoSystrace.isTracing()
            if (r0 == 0) goto Lc
            java.lang.String r0 = "ImagePipeline#submitFetchRequest"
            com.facebook.imagepipeline.systrace.FrescoSystrace.beginSection(r0)
        Lc:
            com.facebook.imagepipeline.producers.InternalRequestListener r0 = new com.facebook.imagepipeline.producers.InternalRequestListener
            r3 = r16
            r2 = r19
            com.facebook.imagepipeline.listener.RequestListener r2 = r14.getRequestListenerForRequest(r3, r2)
            com.facebook.imagepipeline.listener.RequestListener2 r4 = r1.mRequestListener2
            r0.<init>(r2, r4)
            com.facebook.callercontext.CallerContextVerifier r2 = r1.mCallerContextVerifier
            r4 = 0
            r7 = r18
            if (r2 == 0) goto L25
            r2.verifyCallerContext(r7, r4)
        L25:
            com.facebook.imagepipeline.request.ImageRequest$RequestLevel r2 = r16.getLowestPermittedRequestLevel()     // Catch: java.lang.Throwable -> L6c java.lang.Exception -> L6e
            r5 = r17
            com.facebook.imagepipeline.request.ImageRequest$RequestLevel r8 = com.facebook.imagepipeline.request.ImageRequest.RequestLevel.getMax(r2, r5)     // Catch: java.lang.Throwable -> L6c java.lang.Exception -> L6e
            com.facebook.imagepipeline.producers.SettableProducerContext r13 = new com.facebook.imagepipeline.producers.SettableProducerContext     // Catch: java.lang.Throwable -> L6c java.lang.Exception -> L6e
            java.lang.String r5 = r14.generateUniqueFutureId()     // Catch: java.lang.Throwable -> L6c java.lang.Exception -> L6e
            r6 = 0
            r9 = 0
            boolean r2 = r16.getProgressiveRenderingEnabled()     // Catch: java.lang.Throwable -> L6c java.lang.Exception -> L6e
            if (r2 != 0) goto L4a
            android.net.Uri r2 = r16.getSourceUri()     // Catch: java.lang.Throwable -> L6c java.lang.Exception -> L6e
            boolean r2 = com.facebook.common.util.UriUtil.isNetworkUri(r2)     // Catch: java.lang.Throwable -> L6c java.lang.Exception -> L6e
            if (r2 != 0) goto L48
            goto L4a
        L48:
            r10 = r4
            goto L4c
        L4a:
            r2 = 1
            r10 = r2
        L4c:
            com.facebook.imagepipeline.common.Priority r11 = r16.getPriority()     // Catch: java.lang.Throwable -> L6c java.lang.Exception -> L6e
            com.facebook.imagepipeline.core.ImagePipelineConfigInterface r12 = r1.mConfig     // Catch: java.lang.Throwable -> L6c java.lang.Exception -> L6e
            r2 = r13
            r3 = r16
            r4 = r5
            r5 = r6
            r6 = r0
            r7 = r18
            r2.<init>(r3, r4, r5, r6, r7, r8, r9, r10, r11, r12)     // Catch: java.lang.Throwable -> L6c java.lang.Exception -> L6e
            r2 = r15
            com.facebook.datasource.DataSource r0 = com.facebook.imagepipeline.datasource.CloseableProducerToDataSourceAdapter.create(r15, r13, r0)     // Catch: java.lang.Throwable -> L6c java.lang.Exception -> L6e
            boolean r2 = com.facebook.imagepipeline.systrace.FrescoSystrace.isTracing()
            if (r2 == 0) goto L6b
            com.facebook.imagepipeline.systrace.FrescoSystrace.endSection()
        L6b:
            return r0
        L6c:
            r0 = move-exception
            goto L7d
        L6e:
            r0 = move-exception
            com.facebook.datasource.DataSource r0 = com.facebook.datasource.DataSources.immediateFailedDataSource(r0)     // Catch: java.lang.Throwable -> L6c
            boolean r2 = com.facebook.imagepipeline.systrace.FrescoSystrace.isTracing()
            if (r2 == 0) goto L7c
            com.facebook.imagepipeline.systrace.FrescoSystrace.endSection()
        L7c:
            return r0
        L7d:
            boolean r2 = com.facebook.imagepipeline.systrace.FrescoSystrace.isTracing()
            if (r2 == 0) goto L86
            com.facebook.imagepipeline.systrace.FrescoSystrace.endSection()
        L86:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.imagepipeline.core.ImagePipeline.submitFetchRequest(com.facebook.imagepipeline.producers.Producer, com.facebook.imagepipeline.request.ImageRequest, com.facebook.imagepipeline.request.ImageRequest$RequestLevel, java.lang.Object, com.facebook.imagepipeline.listener.RequestListener, java.util.Map):com.facebook.datasource.DataSource");
    }

    public <T> DataSource<CloseableReference<T>> submitFetchRequest(Producer<CloseableReference<T>> producer, SettableProducerContext settableProducerContext, RequestListener requestListener) {
        if (FrescoSystrace.isTracing()) {
            FrescoSystrace.beginSection("ImagePipeline#submitFetchRequest");
        }
        try {
            try {
                DataSource<CloseableReference<T>> create = CloseableProducerToDataSourceAdapter.create(producer, settableProducerContext, new InternalRequestListener(requestListener, this.mRequestListener2));
                if (FrescoSystrace.isTracing()) {
                    FrescoSystrace.endSection();
                }
                return create;
            } catch (Exception e) {
                DataSource<CloseableReference<T>> immediateFailedDataSource = DataSources.immediateFailedDataSource(e);
                if (FrescoSystrace.isTracing()) {
                    FrescoSystrace.endSection();
                }
                return immediateFailedDataSource;
            }
        } catch (Throwable th) {
            if (FrescoSystrace.isTracing()) {
                FrescoSystrace.endSection();
            }
            throw th;
        }
    }

    private DataSource<Void> submitPrefetchRequest(Producer<Void> producer, ImageRequest imageRequest, ImageRequest.RequestLevel requestLevel, @Nullable Object obj, Priority priority, @Nullable RequestListener requestListener) {
        InternalRequestListener internalRequestListener = new InternalRequestListener(getRequestListenerForRequest(imageRequest, requestListener), this.mRequestListener2);
        CallerContextVerifier callerContextVerifier = this.mCallerContextVerifier;
        if (callerContextVerifier != null) {
            callerContextVerifier.verifyCallerContext(obj, true);
        }
        try {
            return ProducerToDataSourceAdapter.create(producer, new SettableProducerContext(imageRequest, generateUniqueFutureId(), internalRequestListener, obj, ImageRequest.RequestLevel.getMax(imageRequest.getLowestPermittedRequestLevel(), requestLevel), true, this.mConfig.getExperiments() != null && this.mConfig.getExperiments().getAllowProgressiveOnPrefetch() && imageRequest.getProgressiveRenderingEnabled(), priority, this.mConfig), internalRequestListener);
        } catch (Exception e) {
            return DataSources.immediateFailedDataSource(e);
        }
    }

    public RequestListener getRequestListenerForRequest(ImageRequest imageRequest, @Nullable RequestListener requestListener) {
        if (requestListener == null) {
            return imageRequest.getRequestListener() == null ? this.mRequestListener : new ForwardingRequestListener(this.mRequestListener, imageRequest.getRequestListener());
        }
        if (imageRequest.getRequestListener() == null) {
            return new ForwardingRequestListener(this.mRequestListener, requestListener);
        }
        return new ForwardingRequestListener(this.mRequestListener, requestListener, imageRequest.getRequestListener());
    }

    public RequestListener getCombinedRequestListener(@Nullable RequestListener requestListener) {
        return requestListener == null ? this.mRequestListener : new ForwardingRequestListener(this.mRequestListener, requestListener);
    }

    private Predicate<CacheKey> predicateForUri(final Uri uri) {
        return new Predicate<CacheKey>() { // from class: com.facebook.imagepipeline.core.ImagePipeline.8
            @Override // com.facebook.common.internal.Predicate
            public boolean apply(CacheKey cacheKey) {
                return cacheKey.containsUri(uri);
            }
        };
    }

    public void pause() {
        this.mThreadHandoffProducerQueue.startQueueing();
    }

    public void resume() {
        this.mThreadHandoffProducerQueue.stopQueuing();
    }

    public boolean isPaused() {
        return this.mThreadHandoffProducerQueue.isQueueing();
    }
}
