package com.facebook.imagepipeline.cache;

import bolts.Task;
import com.facebook.binaryresource.BinaryResource;
import com.facebook.cache.common.CacheKey;
import com.facebook.cache.common.WriterCallback;
import com.facebook.cache.disk.FileCache;
import com.facebook.common.logging.FLog;
import com.facebook.common.memory.PooledByteBuffer;
import com.facebook.common.memory.PooledByteBufferFactory;
import com.facebook.common.memory.PooledByteStreams;
import com.facebook.common.references.CloseableReference;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.instrumentation.FrescoInstrumenter;
import com.facebook.imagepipeline.systrace.FrescoSystrace;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BufferedDiskCache.kt */
@Metadata(d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 /2\u00020\u0001:\u0001/B5\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\t\u0012\u0006\u0010\u000b\u001a\u00020\f¢\u0006\u0002\u0010\rJ\u000e\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017J\u0010\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u0016\u001a\u00020\u0017H\u0002J\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001bJ\u0014\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00190\u001b2\u0006\u0010\u0016\u001a\u00020\u0017J\u0016\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00190\u001b2\u0006\u0010\u0016\u001a\u00020\u0017H\u0002J\u000e\u0010\u001f\u001a\u00020\u00192\u0006\u0010\u0016\u001a\u00020\u0017J\u000e\u0010 \u001a\u00020\u00192\u0006\u0010\u0016\u001a\u00020\u0017J\u001e\u0010!\u001a\b\u0012\u0004\u0012\u00020\"0\u001b2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010#\u001a\u00020\"H\u0002J\u001f\u0010$\u001a\b\u0012\u0004\u0012\u00020\"0\u001b2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010%\u001a\u00020&H\u0086\u0002J\u001e\u0010'\u001a\b\u0012\u0004\u0012\u00020\"0\u001b2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010%\u001a\u00020&H\u0002J\u0014\u0010(\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001b2\u0006\u0010\u0016\u001a\u00020\u0017J\u0016\u0010)\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010*\u001a\u00020\"J\u0012\u0010+\u001a\u0004\u0018\u00010,2\u0006\u0010\u0016\u001a\u00020\u0017H\u0002J\u0014\u0010-\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001b2\u0006\u0010\u0016\u001a\u00020\u0017J\u001a\u0010.\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\b\u0010*\u001a\u0004\u0018\u00010\"H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u000e\u001a\u00020\u000f8F¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u00060"}, d2 = {"Lcom/facebook/imagepipeline/cache/BufferedDiskCache;", "", "fileCache", "Lcom/facebook/cache/disk/FileCache;", "pooledByteBufferFactory", "Lcom/facebook/common/memory/PooledByteBufferFactory;", "pooledByteStreams", "Lcom/facebook/common/memory/PooledByteStreams;", "readExecutor", "Ljava/util/concurrent/Executor;", "writeExecutor", "imageCacheStatsTracker", "Lcom/facebook/imagepipeline/cache/ImageCacheStatsTracker;", "(Lcom/facebook/cache/disk/FileCache;Lcom/facebook/common/memory/PooledByteBufferFactory;Lcom/facebook/common/memory/PooledByteStreams;Ljava/util/concurrent/Executor;Ljava/util/concurrent/Executor;Lcom/facebook/imagepipeline/cache/ImageCacheStatsTracker;)V", "size", "", "getSize", "()J", "stagingArea", "Lcom/facebook/imagepipeline/cache/StagingArea;", "addKeyForAsyncProbing", "", "key", "Lcom/facebook/cache/common/CacheKey;", "checkInStagingAreaAndFileCache", "", "clearAll", "Lbolts/Task;", "Ljava/lang/Void;", "contains", "containsAsync", "containsSync", "diskCheckSync", "foundPinnedImage", "Lcom/facebook/imagepipeline/image/EncodedImage;", "pinnedImage", "get", "isCancelled", "Ljava/util/concurrent/atomic/AtomicBoolean;", "getAsync", "probe", "put", "encodedImage", "readFromDiskCache", "Lcom/facebook/common/memory/PooledByteBuffer;", "remove", "writeToDiskCache", "Companion", "imagepipeline_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class BufferedDiskCache {
    private static final Class<?> TAG = BufferedDiskCache.class;
    private final FileCache fileCache;
    private final ImageCacheStatsTracker imageCacheStatsTracker;
    private final PooledByteBufferFactory pooledByteBufferFactory;
    private final PooledByteStreams pooledByteStreams;
    private final Executor readExecutor;
    private final StagingArea stagingArea;
    private final Executor writeExecutor;

    public BufferedDiskCache(FileCache fileCache, PooledByteBufferFactory pooledByteBufferFactory, PooledByteStreams pooledByteStreams, Executor readExecutor, Executor writeExecutor, ImageCacheStatsTracker imageCacheStatsTracker) {
        Intrinsics.checkNotNullParameter(fileCache, "fileCache");
        Intrinsics.checkNotNullParameter(pooledByteBufferFactory, "pooledByteBufferFactory");
        Intrinsics.checkNotNullParameter(pooledByteStreams, "pooledByteStreams");
        Intrinsics.checkNotNullParameter(readExecutor, "readExecutor");
        Intrinsics.checkNotNullParameter(writeExecutor, "writeExecutor");
        Intrinsics.checkNotNullParameter(imageCacheStatsTracker, "imageCacheStatsTracker");
        this.fileCache = fileCache;
        this.pooledByteBufferFactory = pooledByteBufferFactory;
        this.pooledByteStreams = pooledByteStreams;
        this.readExecutor = readExecutor;
        this.writeExecutor = writeExecutor;
        this.imageCacheStatsTracker = imageCacheStatsTracker;
        StagingArea stagingArea = StagingArea.getInstance();
        Intrinsics.checkNotNullExpressionValue(stagingArea, "getInstance()");
        this.stagingArea = stagingArea;
    }

    public final boolean containsSync(CacheKey key) {
        Intrinsics.checkNotNullParameter(key, "key");
        return this.stagingArea.containsKey(key) || this.fileCache.hasKeySync(key);
    }

    public final Task<Boolean> contains(CacheKey key) {
        Intrinsics.checkNotNullParameter(key, "key");
        if (containsSync(key)) {
            Task<Boolean> forResult = Task.forResult(true);
            Intrinsics.checkNotNullExpressionValue(forResult, "{\n        Task.forResult(true)\n      }");
            return forResult;
        }
        return containsAsync(key);
    }

    private final Task<Boolean> containsAsync(final CacheKey key) {
        try {
            final Object onBeforeSubmitWork = FrescoInstrumenter.onBeforeSubmitWork("BufferedDiskCache_containsAsync");
            Task<Boolean> call = Task.call(new Callable() { // from class: com.facebook.imagepipeline.cache.BufferedDiskCache$$ExternalSyntheticLambda5
                @Override // java.util.concurrent.Callable
                public final Object call() {
                    Boolean containsAsync$lambda$0;
                    containsAsync$lambda$0 = BufferedDiskCache.containsAsync$lambda$0(onBeforeSubmitWork, this, key);
                    return containsAsync$lambda$0;
                }
            }, this.readExecutor);
            Intrinsics.checkNotNullExpressionValue(call, "{\n      val token = Fres…      readExecutor)\n    }");
            return call;
        } catch (Exception e) {
            FLog.w(TAG, e, "Failed to schedule disk-cache read for %s", key.getUriString());
            Task<Boolean> forError = Task.forError(e);
            Intrinsics.checkNotNullExpressionValue(forError, "{\n      // Log failure\n …forError(exception)\n    }");
            return forError;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Boolean containsAsync$lambda$0(Object obj, BufferedDiskCache this$0, CacheKey key) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(key, "$key");
        Object onBeginWork = FrescoInstrumenter.onBeginWork(obj, null);
        try {
            return Boolean.valueOf(this$0.checkInStagingAreaAndFileCache(key));
        } finally {
        }
    }

    public final boolean diskCheckSync(CacheKey key) {
        Intrinsics.checkNotNullParameter(key, "key");
        if (containsSync(key)) {
            return true;
        }
        return checkInStagingAreaAndFileCache(key);
    }

    public final Task<EncodedImage> get(CacheKey key, AtomicBoolean isCancelled) {
        Task<EncodedImage> async;
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(isCancelled, "isCancelled");
        try {
            if (FrescoSystrace.isTracing()) {
                FrescoSystrace.beginSection("BufferedDiskCache#get");
            }
            EncodedImage encodedImage = this.stagingArea.get(key);
            if (encodedImage == null || (async = foundPinnedImage(key, encodedImage)) == null) {
                async = getAsync(key, isCancelled);
            }
            return async;
        } finally {
            if (FrescoSystrace.isTracing()) {
                FrescoSystrace.endSection();
            }
        }
    }

    public final Task<Void> probe(final CacheKey key) {
        Intrinsics.checkNotNullParameter(key, "key");
        try {
            final Object onBeforeSubmitWork = FrescoInstrumenter.onBeforeSubmitWork("BufferedDiskCache_probe");
            Task<Void> call = Task.call(new Callable() { // from class: com.facebook.imagepipeline.cache.BufferedDiskCache$$ExternalSyntheticLambda6
                @Override // java.util.concurrent.Callable
                public final Object call() {
                    Void probe$lambda$2;
                    probe$lambda$2 = BufferedDiskCache.probe$lambda$2(onBeforeSubmitWork, this, key);
                    return probe$lambda$2;
                }
            }, this.writeExecutor);
            Intrinsics.checkNotNullExpressionValue(call, "{\n      val token = Fres…     writeExecutor)\n    }");
            return call;
        } catch (Exception e) {
            FLog.w(TAG, e, "Failed to schedule disk-cache probe for %s", key.getUriString());
            Task<Void> forError = Task.forError(e);
            Intrinsics.checkNotNullExpressionValue(forError, "{\n      FLog.w(TAG, exce…forError(exception)\n    }");
            return forError;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Void probe$lambda$2(Object obj, BufferedDiskCache this$0, CacheKey key) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(key, "$key");
        Object onBeginWork = FrescoInstrumenter.onBeginWork(obj, null);
        try {
            this$0.fileCache.probe(key);
            return null;
        } finally {
            FrescoInstrumenter.onEndWork(onBeginWork);
        }
    }

    public final void addKeyForAsyncProbing(CacheKey key) {
        Intrinsics.checkNotNullParameter(key, "key");
        this.fileCache.probe(key);
    }

    private final boolean checkInStagingAreaAndFileCache(CacheKey key) {
        EncodedImage encodedImage = this.stagingArea.get(key);
        if (encodedImage != null) {
            encodedImage.close();
            FLog.v(TAG, "Found image for %s in staging area", key.getUriString());
            this.imageCacheStatsTracker.onStagingAreaHit(key);
            return true;
        }
        FLog.v(TAG, "Did not find image for %s in staging area", key.getUriString());
        this.imageCacheStatsTracker.onStagingAreaMiss(key);
        try {
            return this.fileCache.hasKey(key);
        } catch (Exception unused) {
            return false;
        }
    }

    private final Task<EncodedImage> getAsync(final CacheKey key, final AtomicBoolean isCancelled) {
        try {
            final Object onBeforeSubmitWork = FrescoInstrumenter.onBeforeSubmitWork("BufferedDiskCache_getAsync");
            Task<EncodedImage> call = Task.call(new Callable() { // from class: com.facebook.imagepipeline.cache.BufferedDiskCache$$ExternalSyntheticLambda0
                @Override // java.util.concurrent.Callable
                public final Object call() {
                    EncodedImage async$lambda$3;
                    async$lambda$3 = BufferedDiskCache.getAsync$lambda$3(onBeforeSubmitWork, isCancelled, this, key);
                    return async$lambda$3;
                }
            }, this.readExecutor);
            Intrinsics.checkNotNullExpressionValue(call, "{\n      val token = Fres…      readExecutor)\n    }");
            return call;
        } catch (Exception e) {
            FLog.w(TAG, e, "Failed to schedule disk-cache read for %s", key.getUriString());
            Task<EncodedImage> forError = Task.forError(e);
            Intrinsics.checkNotNullExpressionValue(forError, "{\n      // Log failure\n …forError(exception)\n    }");
            return forError;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final EncodedImage getAsync$lambda$3(Object obj, AtomicBoolean isCancelled, BufferedDiskCache this$0, CacheKey key) {
        Intrinsics.checkNotNullParameter(isCancelled, "$isCancelled");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(key, "$key");
        Object onBeginWork = FrescoInstrumenter.onBeginWork(obj, null);
        try {
            if (isCancelled.get()) {
                throw new CancellationException();
            }
            EncodedImage encodedImage = this$0.stagingArea.get(key);
            if (encodedImage != null) {
                FLog.v(TAG, "Found image for %s in staging area", key.getUriString());
                this$0.imageCacheStatsTracker.onStagingAreaHit(key);
            } else {
                FLog.v(TAG, "Did not find image for %s in staging area", key.getUriString());
                this$0.imageCacheStatsTracker.onStagingAreaMiss(key);
                try {
                    PooledByteBuffer readFromDiskCache = this$0.readFromDiskCache(key);
                    if (readFromDiskCache == null) {
                        return null;
                    }
                    CloseableReference of = CloseableReference.of(readFromDiskCache);
                    Intrinsics.checkNotNullExpressionValue(of, "of(buffer)");
                    try {
                        encodedImage = new EncodedImage((CloseableReference<PooledByteBuffer>) of);
                    } finally {
                        CloseableReference.closeSafely((CloseableReference<?>) of);
                    }
                } catch (Exception unused) {
                    return null;
                }
            }
            if (!Thread.interrupted()) {
                return encodedImage;
            }
            FLog.v(TAG, "Host thread was interrupted, decreasing reference count");
            encodedImage.close();
            throw new InterruptedException();
        } catch (Throwable th) {
            try {
                FrescoInstrumenter.markFailure(obj, th);
                throw th;
            } finally {
                FrescoInstrumenter.onEndWork(onBeginWork);
            }
        }
    }

    public final void put(final CacheKey key, EncodedImage encodedImage) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(encodedImage, "encodedImage");
        try {
            if (FrescoSystrace.isTracing()) {
                FrescoSystrace.beginSection("BufferedDiskCache#put");
            }
            if (!EncodedImage.isValid(encodedImage)) {
                throw new IllegalStateException("Check failed.".toString());
            }
            this.stagingArea.put(key, encodedImage);
            final EncodedImage cloneOrNull = EncodedImage.cloneOrNull(encodedImage);
            try {
                final Object onBeforeSubmitWork = FrescoInstrumenter.onBeforeSubmitWork("BufferedDiskCache_putAsync");
                this.writeExecutor.execute(new Runnable() { // from class: com.facebook.imagepipeline.cache.BufferedDiskCache$$ExternalSyntheticLambda4
                    @Override // java.lang.Runnable
                    public final void run() {
                        BufferedDiskCache.put$lambda$4(onBeforeSubmitWork, this, key, cloneOrNull);
                    }
                });
            } catch (Exception e) {
                FLog.w(TAG, e, "Failed to schedule disk-cache write for %s", key.getUriString());
                this.stagingArea.remove(key, encodedImage);
                EncodedImage.closeSafely(cloneOrNull);
            }
        } finally {
            if (FrescoSystrace.isTracing()) {
                FrescoSystrace.endSection();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void put$lambda$4(Object obj, BufferedDiskCache this$0, CacheKey key, EncodedImage encodedImage) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(key, "$key");
        Object onBeginWork = FrescoInstrumenter.onBeginWork(obj, null);
        try {
            this$0.writeToDiskCache(key, encodedImage);
        } finally {
        }
    }

    public final Task<Void> remove(final CacheKey key) {
        Intrinsics.checkNotNullParameter(key, "key");
        this.stagingArea.remove(key);
        try {
            final Object onBeforeSubmitWork = FrescoInstrumenter.onBeforeSubmitWork("BufferedDiskCache_remove");
            Task<Void> call = Task.call(new Callable() { // from class: com.facebook.imagepipeline.cache.BufferedDiskCache$$ExternalSyntheticLambda2
                @Override // java.util.concurrent.Callable
                public final Object call() {
                    Void remove$lambda$5;
                    remove$lambda$5 = BufferedDiskCache.remove$lambda$5(onBeforeSubmitWork, this, key);
                    return remove$lambda$5;
                }
            }, this.writeExecutor);
            Intrinsics.checkNotNullExpressionValue(call, "{\n      val token = Fres…     writeExecutor)\n    }");
            return call;
        } catch (Exception e) {
            FLog.w(TAG, e, "Failed to schedule disk-cache remove for %s", key.getUriString());
            Task<Void> forError = Task.forError(e);
            Intrinsics.checkNotNullExpressionValue(forError, "{\n      // Log failure\n …forError(exception)\n    }");
            return forError;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Void remove$lambda$5(Object obj, BufferedDiskCache this$0, CacheKey key) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(key, "$key");
        Object onBeginWork = FrescoInstrumenter.onBeginWork(obj, null);
        try {
            this$0.stagingArea.remove(key);
            this$0.fileCache.remove(key);
            return null;
        } finally {
        }
    }

    public final Task<Void> clearAll() {
        this.stagingArea.clearAll();
        final Object onBeforeSubmitWork = FrescoInstrumenter.onBeforeSubmitWork("BufferedDiskCache_clearAll");
        try {
            Task<Void> call = Task.call(new Callable() { // from class: com.facebook.imagepipeline.cache.BufferedDiskCache$$ExternalSyntheticLambda1
                @Override // java.util.concurrent.Callable
                public final Object call() {
                    Void clearAll$lambda$6;
                    clearAll$lambda$6 = BufferedDiskCache.clearAll$lambda$6(onBeforeSubmitWork, this);
                    return clearAll$lambda$6;
                }
            }, this.writeExecutor);
            Intrinsics.checkNotNullExpressionValue(call, "{\n      Task.call(\n     …     writeExecutor)\n    }");
            return call;
        } catch (Exception e) {
            FLog.w(TAG, e, "Failed to schedule disk-cache clear", new Object[0]);
            Task<Void> forError = Task.forError(e);
            Intrinsics.checkNotNullExpressionValue(forError, "{\n      // Log failure\n …forError(exception)\n    }");
            return forError;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Void clearAll$lambda$6(Object obj, BufferedDiskCache this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Object onBeginWork = FrescoInstrumenter.onBeginWork(obj, null);
        try {
            this$0.stagingArea.clearAll();
            this$0.fileCache.clearAll();
            return null;
        } finally {
        }
    }

    public final long getSize() {
        return this.fileCache.getSize();
    }

    private final Task<EncodedImage> foundPinnedImage(CacheKey key, EncodedImage pinnedImage) {
        FLog.v(TAG, "Found image for %s in staging area", key.getUriString());
        this.imageCacheStatsTracker.onStagingAreaHit(key);
        Task<EncodedImage> forResult = Task.forResult(pinnedImage);
        Intrinsics.checkNotNullExpressionValue(forResult, "forResult(pinnedImage)");
        return forResult;
    }

    private final PooledByteBuffer readFromDiskCache(CacheKey key) throws IOException {
        try {
            Class<?> cls = TAG;
            FLog.v(cls, "Disk cache read for %s", key.getUriString());
            BinaryResource resource = this.fileCache.getResource(key);
            if (resource == null) {
                FLog.v(cls, "Disk cache miss for %s", key.getUriString());
                this.imageCacheStatsTracker.onDiskCacheMiss(key);
                return null;
            }
            FLog.v(cls, "Found entry in disk cache for %s", key.getUriString());
            this.imageCacheStatsTracker.onDiskCacheHit(key);
            InputStream openStream = resource.openStream();
            try {
                PooledByteBuffer newByteBuffer = this.pooledByteBufferFactory.newByteBuffer(openStream, (int) resource.size());
                openStream.close();
                FLog.v(cls, "Successful read from disk cache for %s", key.getUriString());
                return newByteBuffer;
            } catch (Throwable th) {
                openStream.close();
                throw th;
            }
        } catch (IOException e) {
            FLog.w(TAG, e, "Exception reading from cache for %s", key.getUriString());
            this.imageCacheStatsTracker.onDiskCacheGetFail(key);
            throw e;
        }
    }

    private final void writeToDiskCache(CacheKey key, final EncodedImage encodedImage) {
        Class<?> cls = TAG;
        FLog.v(cls, "About to write to disk-cache for key %s", key.getUriString());
        try {
            this.fileCache.insert(key, new WriterCallback() { // from class: com.facebook.imagepipeline.cache.BufferedDiskCache$$ExternalSyntheticLambda3
                @Override // com.facebook.cache.common.WriterCallback
                public final void write(OutputStream outputStream) {
                    BufferedDiskCache.writeToDiskCache$lambda$7(EncodedImage.this, this, outputStream);
                }
            });
            this.imageCacheStatsTracker.onDiskCachePut(key);
            FLog.v(cls, "Successful disk-cache write for key %s", key.getUriString());
        } catch (IOException e) {
            FLog.w(TAG, e, "Failed to write to disk-cache for key %s", key.getUriString());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void writeToDiskCache$lambda$7(EncodedImage encodedImage, BufferedDiskCache this$0, OutputStream os) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(os, "os");
        Intrinsics.checkNotNull(encodedImage);
        InputStream inputStream = encodedImage.getInputStream();
        if (inputStream == null) {
            throw new IllegalStateException("Required value was null.".toString());
        }
        this$0.pooledByteStreams.copy(inputStream, os);
    }
}
