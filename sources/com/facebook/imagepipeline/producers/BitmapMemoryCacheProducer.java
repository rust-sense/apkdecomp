package com.facebook.imagepipeline.producers;

import com.facebook.cache.common.CacheKey;
import com.facebook.common.internal.ImmutableMap;
import com.facebook.common.references.CloseableReference;
import com.facebook.hermes.intl.Constants;
import com.facebook.imagepipeline.cache.CacheKeyFactory;
import com.facebook.imagepipeline.cache.MemoryCache;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.image.HasImageMetadata;
import com.facebook.imagepipeline.image.QualityInfo;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.systrace.FrescoSystrace;
import javax.annotation.Nullable;

/* loaded from: classes.dex */
public class BitmapMemoryCacheProducer implements Producer<CloseableReference<CloseableImage>> {
    public static final String EXTRA_CACHED_VALUE_FOUND = "cached_value_found";
    private static final String ORIGIN_SUBCATEGORY = "pipe_bg";
    public static final String PRODUCER_NAME = "BitmapMemoryCacheProducer";
    private final CacheKeyFactory mCacheKeyFactory;
    private final Producer<CloseableReference<CloseableImage>> mInputProducer;
    private final MemoryCache<CacheKey, CloseableImage> mMemoryCache;

    protected String getOriginSubcategory() {
        return ORIGIN_SUBCATEGORY;
    }

    protected String getProducerName() {
        return PRODUCER_NAME;
    }

    public BitmapMemoryCacheProducer(MemoryCache<CacheKey, CloseableImage> memoryCache, CacheKeyFactory cacheKeyFactory, Producer<CloseableReference<CloseableImage>> producer) {
        this.mMemoryCache = memoryCache;
        this.mCacheKeyFactory = cacheKeyFactory;
        this.mInputProducer = producer;
    }

    @Override // com.facebook.imagepipeline.producers.Producer
    public void produceResults(Consumer<CloseableReference<CloseableImage>> consumer, ProducerContext producerContext) {
        boolean isTracing;
        try {
            if (FrescoSystrace.isTracing()) {
                FrescoSystrace.beginSection("BitmapMemoryCacheProducer#produceResults");
            }
            ProducerListener2 producerListener = producerContext.getProducerListener();
            producerListener.onProducerStart(producerContext, getProducerName());
            CacheKey bitmapCacheKey = this.mCacheKeyFactory.getBitmapCacheKey(producerContext.getImageRequest(), producerContext.getCallerContext());
            CloseableReference<CloseableImage> closeableReference = producerContext.getImageRequest().isCacheEnabled(1) ? this.mMemoryCache.get(bitmapCacheKey) : null;
            if (closeableReference != null) {
                maybeSetExtrasFromCloseableImage(closeableReference.get(), producerContext);
                boolean isOfFullQuality = closeableReference.get().getQualityInfo().isOfFullQuality();
                if (isOfFullQuality) {
                    producerListener.onProducerFinishWithSuccess(producerContext, getProducerName(), producerListener.requiresExtraMap(producerContext, getProducerName()) ? ImmutableMap.of("cached_value_found", "true") : null);
                    producerListener.onUltimateProducerReached(producerContext, getProducerName(), true);
                    producerContext.putOriginExtra("memory_bitmap", getOriginSubcategory());
                    consumer.onProgressUpdate(1.0f);
                }
                consumer.onNewResult(closeableReference, BaseConsumer.simpleStatusForIsLast(isOfFullQuality));
                closeableReference.close();
                if (isOfFullQuality) {
                    if (isTracing) {
                        return;
                    } else {
                        return;
                    }
                }
            }
            if (producerContext.getLowestPermittedRequestLevel().getValue() >= ImageRequest.RequestLevel.BITMAP_MEMORY_CACHE.getValue()) {
                producerListener.onProducerFinishWithSuccess(producerContext, getProducerName(), producerListener.requiresExtraMap(producerContext, getProducerName()) ? ImmutableMap.of("cached_value_found", Constants.CASEFIRST_FALSE) : null);
                producerListener.onUltimateProducerReached(producerContext, getProducerName(), false);
                producerContext.putOriginExtra("memory_bitmap", getOriginSubcategory());
                consumer.onNewResult(null, 1);
                if (FrescoSystrace.isTracing()) {
                    FrescoSystrace.endSection();
                    return;
                }
                return;
            }
            Consumer<CloseableReference<CloseableImage>> wrapConsumer = wrapConsumer(consumer, bitmapCacheKey, producerContext.getImageRequest().isCacheEnabled(2));
            producerListener.onProducerFinishWithSuccess(producerContext, getProducerName(), producerListener.requiresExtraMap(producerContext, getProducerName()) ? ImmutableMap.of("cached_value_found", Constants.CASEFIRST_FALSE) : null);
            if (FrescoSystrace.isTracing()) {
                FrescoSystrace.beginSection("mInputProducer.produceResult");
            }
            this.mInputProducer.produceResults(wrapConsumer, producerContext);
            if (FrescoSystrace.isTracing()) {
                FrescoSystrace.endSection();
            }
            if (FrescoSystrace.isTracing()) {
                FrescoSystrace.endSection();
            }
        } finally {
            if (FrescoSystrace.isTracing()) {
                FrescoSystrace.endSection();
            }
        }
    }

    protected Consumer<CloseableReference<CloseableImage>> wrapConsumer(Consumer<CloseableReference<CloseableImage>> consumer, final CacheKey cacheKey, final boolean z) {
        return new DelegatingConsumer<CloseableReference<CloseableImage>, CloseableReference<CloseableImage>>(consumer) { // from class: com.facebook.imagepipeline.producers.BitmapMemoryCacheProducer.1
            @Override // com.facebook.imagepipeline.producers.BaseConsumer
            public void onNewResultImpl(@Nullable CloseableReference<CloseableImage> closeableReference, int i) {
                CloseableReference<CloseableImage> closeableReference2;
                boolean isTracing;
                try {
                    if (FrescoSystrace.isTracing()) {
                        FrescoSystrace.beginSection("BitmapMemoryCacheProducer#onNewResultImpl");
                    }
                    boolean isLast = isLast(i);
                    if (closeableReference == null) {
                        if (isLast) {
                            getConsumer().onNewResult(null, i);
                        }
                        if (isTracing) {
                            return;
                        } else {
                            return;
                        }
                    }
                    if (!closeableReference.get().isStateful() && !statusHasFlag(i, 8)) {
                        if (!isLast && (closeableReference2 = BitmapMemoryCacheProducer.this.mMemoryCache.get(cacheKey)) != null) {
                            try {
                                QualityInfo qualityInfo = closeableReference.get().getQualityInfo();
                                QualityInfo qualityInfo2 = closeableReference2.get().getQualityInfo();
                                if (qualityInfo2.isOfFullQuality() || qualityInfo2.getQuality() >= qualityInfo.getQuality()) {
                                    getConsumer().onNewResult(closeableReference2, i);
                                    if (FrescoSystrace.isTracing()) {
                                        FrescoSystrace.endSection();
                                        return;
                                    }
                                    return;
                                }
                            } finally {
                                CloseableReference.closeSafely(closeableReference2);
                            }
                        }
                        CloseableReference<CloseableImage> cache = z ? BitmapMemoryCacheProducer.this.mMemoryCache.cache(cacheKey, closeableReference) : null;
                        if (isLast) {
                            try {
                                getConsumer().onProgressUpdate(1.0f);
                            } finally {
                                CloseableReference.closeSafely(cache);
                            }
                        }
                        Consumer<CloseableReference<CloseableImage>> consumer2 = getConsumer();
                        if (cache != null) {
                            closeableReference = cache;
                        }
                        consumer2.onNewResult(closeableReference, i);
                        if (FrescoSystrace.isTracing()) {
                            FrescoSystrace.endSection();
                            return;
                        }
                        return;
                    }
                    getConsumer().onNewResult(closeableReference, i);
                    if (FrescoSystrace.isTracing()) {
                        FrescoSystrace.endSection();
                    }
                } finally {
                    if (FrescoSystrace.isTracing()) {
                        FrescoSystrace.endSection();
                    }
                }
            }
        };
    }

    private static void maybeSetExtrasFromCloseableImage(HasImageMetadata hasImageMetadata, ProducerContext producerContext) {
        producerContext.putExtras(hasImageMetadata.getExtras());
    }
}
