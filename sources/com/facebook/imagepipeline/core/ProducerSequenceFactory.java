package com.facebook.imagepipeline.core;

import android.content.ContentResolver;
import android.net.Uri;
import android.os.Build;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.media.MediaUtils;
import com.facebook.common.memory.PooledByteBuffer;
import com.facebook.common.references.CloseableReference;
import com.facebook.common.webp.WebpSupportStatus;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.producers.AddImageTransformMetaDataProducer;
import com.facebook.imagepipeline.producers.BitmapMemoryCacheGetProducer;
import com.facebook.imagepipeline.producers.BitmapMemoryCacheKeyMultiplexProducer;
import com.facebook.imagepipeline.producers.BitmapMemoryCacheProducer;
import com.facebook.imagepipeline.producers.BitmapPrepareProducer;
import com.facebook.imagepipeline.producers.BitmapProbeProducer;
import com.facebook.imagepipeline.producers.BranchOnSeparateImagesProducer;
import com.facebook.imagepipeline.producers.CustomProducerSequenceFactory;
import com.facebook.imagepipeline.producers.DataFetchProducer;
import com.facebook.imagepipeline.producers.DecodeProducer;
import com.facebook.imagepipeline.producers.DelayProducer;
import com.facebook.imagepipeline.producers.DiskCacheReadProducer;
import com.facebook.imagepipeline.producers.DiskCacheWriteProducer;
import com.facebook.imagepipeline.producers.EncodedCacheKeyMultiplexProducer;
import com.facebook.imagepipeline.producers.EncodedProbeProducer;
import com.facebook.imagepipeline.producers.LocalAssetFetchProducer;
import com.facebook.imagepipeline.producers.LocalContentUriFetchProducer;
import com.facebook.imagepipeline.producers.LocalContentUriThumbnailFetchProducer;
import com.facebook.imagepipeline.producers.LocalExifThumbnailProducer;
import com.facebook.imagepipeline.producers.LocalFileFetchProducer;
import com.facebook.imagepipeline.producers.LocalResourceFetchProducer;
import com.facebook.imagepipeline.producers.LocalThumbnailBitmapProducer;
import com.facebook.imagepipeline.producers.LocalVideoThumbnailProducer;
import com.facebook.imagepipeline.producers.NetworkFetcher;
import com.facebook.imagepipeline.producers.PartialDiskCacheProducer;
import com.facebook.imagepipeline.producers.PostprocessedBitmapMemoryCacheProducer;
import com.facebook.imagepipeline.producers.PostprocessorProducer;
import com.facebook.imagepipeline.producers.Producer;
import com.facebook.imagepipeline.producers.QualifiedResourceFetchProducer;
import com.facebook.imagepipeline.producers.RemoveImageTransformMetaDataProducer;
import com.facebook.imagepipeline.producers.ResizeAndRotateProducer;
import com.facebook.imagepipeline.producers.SwallowResultProducer;
import com.facebook.imagepipeline.producers.ThreadHandoffProducerQueue;
import com.facebook.imagepipeline.producers.ThrottlingProducer;
import com.facebook.imagepipeline.producers.ThumbnailBranchProducer;
import com.facebook.imagepipeline.producers.ThumbnailProducer;
import com.facebook.imagepipeline.producers.WebpTranscodeProducer;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.systrace.FrescoSystrace;
import com.facebook.imagepipeline.transcoder.ImageTranscoderFactory;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ProducerSequenceFactory.kt */
@Metadata(d1 = {"\u0000\u0080\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b*\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u0000 \u0082\u00012\u00020\u0001:\u0002\u0082\u0001B\u0089\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\n\u0010\u0006\u001a\u0006\u0012\u0002\b\u00030\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\t\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\t\u0012\u0006\u0010\u000e\u001a\u00020\t\u0012\u0006\u0010\u000f\u001a\u00020\t\u0012\u0006\u0010\u0010\u001a\u00020\t\u0012\u0006\u0010\u0011\u001a\u00020\u0012\u0012\u0006\u0010\u0013\u001a\u00020\t\u0012\u0006\u0010\u0014\u001a\u00020\t\u0012\u0006\u0010\u0015\u001a\u00020\t\u0012\u000e\u0010\u0016\u001a\n\u0012\u0004\u0012\u00020\u0018\u0018\u00010\u0017¢\u0006\u0002\u0010\u0019J\u001c\u0010i\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020*0)0\u001b2\u0006\u0010j\u001a\u00020kH\u0002J(\u0010l\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020*0)0\u001b2\u0012\u0010m\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020*0)0\u001bH\u0002J\u0016\u0010n\u001a\n\u0012\u0006\u0012\u0004\u0018\u0001020\u001b2\u0006\u0010j\u001a\u00020kJ$\u0010o\u001a\n\u0012\u0006\u0012\u0004\u0018\u0001020\u001b2\u0012\u0010m\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020*0)0\u001bH\u0002J\u001a\u0010p\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020*0)0\u001b2\u0006\u0010j\u001a\u00020kJ(\u0010q\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020*0)0\u001b2\u0012\u0010m\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020*0)0\u001bH\u0002J\u0016\u0010r\u001a\n\u0012\u0006\u0012\u0004\u0018\u0001020\u001b2\u0006\u0010j\u001a\u00020kJ\u001a\u0010s\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020@0)0\u001b2\u0006\u0010j\u001a\u00020kJ(\u0010t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020*0)0\u001b2\u0012\u0010m\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020*0)0\u001bH\u0002J(\u0010u\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020*0)0\u001b2\u0012\u0010m\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020*0)0\u001bH\u0002J \u0010v\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020*0)0\u001b2\f\u0010m\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001bJ\"\u0010w\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020*0)0\u001b2\f\u0010m\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001bH\u0002J;\u0010w\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020*0)0\u001b2\f\u0010m\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001b2\u0012\u0010x\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001c0z0yH\u0002¢\u0006\u0002\u0010{J\u0018\u0010|\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001b2\n\u0010\u0006\u001a\u0006\u0012\u0002\b\u00030\u0007J\u001c\u0010}\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001b2\f\u0010m\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001bH\u0002J\u001c\u0010~\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001b2\f\u0010m\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001bH\u0002J(\u0010\u007f\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001b2\u0012\u0010x\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001c0z0yH\u0002¢\u0006\u0003\u0010\u0080\u0001J6\u0010\u0081\u0001\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001b2\f\u0010m\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001b2\u0012\u0010x\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001c0z0yH\u0002¢\u0006\u0002\u0010{R\u000e\u0010\u0015\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R#\u0010\u001a\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001c0\u001b8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u001f\u0010 \u001a\u0004\b\u001d\u0010\u001eR#\u0010!\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001c0\u001b8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b#\u0010 \u001a\u0004\b\"\u0010\u001eR#\u0010$\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001c0\u001b8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b&\u0010 \u001a\u0004\b%\u0010\u001eRH\u0010'\u001a&\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020*0)0\u001b\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020*0)0\u001b0(8\u0006@\u0006X\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b+\u0010,\u001a\u0004\b-\u0010.\"\u0004\b/\u00100RD\u00101\u001a\"\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020*0)0\u001b\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u0001020\u001b0(8\u0006@\u0006X\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b3\u0010,\u001a\u0004\b4\u0010.\"\u0004\b5\u00100R!\u00106\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001b8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b8\u0010 \u001a\u0004\b7\u0010\u001eR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0016\u001a\n\u0012\u0004\u0012\u00020\u0018\u0018\u00010\u0017X\u0082\u0004¢\u0006\u0002\n\u0000R'\u00109\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020*0)0\u001b8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b;\u0010 \u001a\u0004\b:\u0010\u001eR\u000e\u0010\u0010\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R'\u0010<\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020*0)0\u001b8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b>\u0010 \u001a\u0004\b=\u0010\u001eR'\u0010?\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020@0)0\u001b8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\bB\u0010 \u001a\u0004\bA\u0010\u001eR'\u0010C\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020*0)0\u001b8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\bE\u0010 \u001a\u0004\bD\u0010\u001eR'\u0010F\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020*0)0\u001b8GX\u0086\u0084\u0002¢\u0006\f\n\u0004\bH\u0010 \u001a\u0004\bG\u0010\u001eR-\u0010I\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020@0)0\u001b8FX\u0087\u0084\u0002¢\u0006\u0012\n\u0004\bL\u0010 \u0012\u0004\bJ\u0010,\u001a\u0004\bK\u0010\u001eR#\u0010M\u001a\n\u0012\u0006\u0012\u0004\u0018\u0001020\u001b8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\bO\u0010 \u001a\u0004\bN\u0010\u001eR'\u0010P\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020*0)0\u001b8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\bR\u0010 \u001a\u0004\bQ\u0010\u001eR'\u0010S\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020*0)0\u001b8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\bU\u0010 \u001a\u0004\bT\u0010\u001eR'\u0010V\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020*0)0\u001b8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\bX\u0010 \u001a\u0004\bW\u0010\u001eR'\u0010Y\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020@0)0\u001b8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b[\u0010 \u001a\u0004\bZ\u0010\u001eR'\u0010\\\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020*0)0\u001b8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b^\u0010 \u001a\u0004\b]\u0010\u001eR#\u0010_\u001a\n\u0012\u0006\u0012\u0004\u0018\u0001020\u001b8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\ba\u0010 \u001a\u0004\b`\u0010\u001eR\u0012\u0010\u0006\u001a\u0006\u0012\u0002\b\u00030\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000RH\u0010b\u001a&\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020*0)0\u001b\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020*0)0\u001b0(8\u0006@\u0006X\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\bc\u0010,\u001a\u0004\bd\u0010.\"\u0004\be\u00100R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R'\u0010f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020*0)0\u001b8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\bh\u0010 \u001a\u0004\bg\u0010\u001eR\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0083\u0001"}, d2 = {"Lcom/facebook/imagepipeline/core/ProducerSequenceFactory;", "", "contentResolver", "Landroid/content/ContentResolver;", "producerFactory", "Lcom/facebook/imagepipeline/core/ProducerFactory;", "networkFetcher", "Lcom/facebook/imagepipeline/producers/NetworkFetcher;", "resizeAndRotateEnabledForNetwork", "", "webpSupportEnabled", "threadHandoffProducerQueue", "Lcom/facebook/imagepipeline/producers/ThreadHandoffProducerQueue;", "downSampleEnabled", "useBitmapPrepareToDraw", "partialImageCachingEnabled", "diskCacheEnabled", "imageTranscoderFactory", "Lcom/facebook/imagepipeline/transcoder/ImageTranscoderFactory;", "isEncodedMemoryCacheProbingEnabled", "isDiskCacheProbingEnabled", "allowDelay", "customProducerSequenceFactories", "", "Lcom/facebook/imagepipeline/producers/CustomProducerSequenceFactory;", "(Landroid/content/ContentResolver;Lcom/facebook/imagepipeline/core/ProducerFactory;Lcom/facebook/imagepipeline/producers/NetworkFetcher;ZZLcom/facebook/imagepipeline/producers/ThreadHandoffProducerQueue;ZZZZLcom/facebook/imagepipeline/transcoder/ImageTranscoderFactory;ZZZLjava/util/Set;)V", "backgroundLocalContentUriFetchToEncodeMemorySequence", "Lcom/facebook/imagepipeline/producers/Producer;", "Lcom/facebook/imagepipeline/image/EncodedImage;", "getBackgroundLocalContentUriFetchToEncodeMemorySequence", "()Lcom/facebook/imagepipeline/producers/Producer;", "backgroundLocalContentUriFetchToEncodeMemorySequence$delegate", "Lkotlin/Lazy;", "backgroundLocalFileFetchToEncodeMemorySequence", "getBackgroundLocalFileFetchToEncodeMemorySequence", "backgroundLocalFileFetchToEncodeMemorySequence$delegate", "backgroundNetworkFetchToEncodedMemorySequence", "getBackgroundNetworkFetchToEncodedMemorySequence", "backgroundNetworkFetchToEncodedMemorySequence$delegate", "bitmapPrepareSequences", "", "Lcom/facebook/common/references/CloseableReference;", "Lcom/facebook/imagepipeline/image/CloseableImage;", "getBitmapPrepareSequences$annotations", "()V", "getBitmapPrepareSequences", "()Ljava/util/Map;", "setBitmapPrepareSequences", "(Ljava/util/Map;)V", "closeableImagePrefetchSequences", "Ljava/lang/Void;", "getCloseableImagePrefetchSequences$annotations", "getCloseableImagePrefetchSequences", "setCloseableImagePrefetchSequences", "commonNetworkFetchToEncodedMemorySequence", "getCommonNetworkFetchToEncodedMemorySequence", "commonNetworkFetchToEncodedMemorySequence$delegate", "dataFetchSequence", "getDataFetchSequence", "dataFetchSequence$delegate", "localAssetFetchSequence", "getLocalAssetFetchSequence", "localAssetFetchSequence$delegate", "localContentUriFetchEncodedImageProducerSequence", "Lcom/facebook/common/memory/PooledByteBuffer;", "getLocalContentUriFetchEncodedImageProducerSequence", "localContentUriFetchEncodedImageProducerSequence$delegate", "localContentUriFetchSequence", "getLocalContentUriFetchSequence", "localContentUriFetchSequence$delegate", "localContentUriThumbnailFetchSequence", "getLocalContentUriThumbnailFetchSequence", "localContentUriThumbnailFetchSequence$delegate", "localFileFetchEncodedImageProducerSequence", "getLocalFileFetchEncodedImageProducerSequence$annotations", "getLocalFileFetchEncodedImageProducerSequence", "localFileFetchEncodedImageProducerSequence$delegate", "localFileFetchToEncodedMemoryPrefetchSequence", "getLocalFileFetchToEncodedMemoryPrefetchSequence", "localFileFetchToEncodedMemoryPrefetchSequence$delegate", "localImageFileFetchSequence", "getLocalImageFileFetchSequence", "localImageFileFetchSequence$delegate", "localResourceFetchSequence", "getLocalResourceFetchSequence", "localResourceFetchSequence$delegate", "localVideoFileFetchSequence", "getLocalVideoFileFetchSequence", "localVideoFileFetchSequence$delegate", "networkFetchEncodedImageProducerSequence", "getNetworkFetchEncodedImageProducerSequence", "networkFetchEncodedImageProducerSequence$delegate", "networkFetchSequence", "getNetworkFetchSequence", "networkFetchSequence$delegate", "networkFetchToEncodedMemoryPrefetchSequence", "getNetworkFetchToEncodedMemoryPrefetchSequence", "networkFetchToEncodedMemoryPrefetchSequence$delegate", "postprocessorSequences", "getPostprocessorSequences$annotations", "getPostprocessorSequences", "setPostprocessorSequences", "qualifiedResourceFetchSequence", "getQualifiedResourceFetchSequence", "qualifiedResourceFetchSequence$delegate", "getBasicDecodedImageSequence", "imageRequest", "Lcom/facebook/imagepipeline/request/ImageRequest;", "getBitmapPrepareSequence", "inputProducer", "getDecodedImagePrefetchProducerSequence", "getDecodedImagePrefetchSequence", "getDecodedImageProducerSequence", "getDelaySequence", "getEncodedImagePrefetchProducerSequence", "getEncodedImageProducerSequence", "getPostprocessorSequence", "newBitmapCacheGetToBitmapCacheSequence", "newBitmapCacheGetToDecodeSequence", "newBitmapCacheGetToLocalTransformSequence", "thumbnailProducers", "", "Lcom/facebook/imagepipeline/producers/ThumbnailProducer;", "(Lcom/facebook/imagepipeline/producers/Producer;[Lcom/facebook/imagepipeline/producers/ThumbnailProducer;)Lcom/facebook/imagepipeline/producers/Producer;", "newCommonNetworkFetchToEncodedMemorySequence", "newDiskCacheSequence", "newEncodedCacheMultiplexToTranscodeSequence", "newLocalThumbnailProducer", "([Lcom/facebook/imagepipeline/producers/ThumbnailProducer;)Lcom/facebook/imagepipeline/producers/Producer;", "newLocalTransformationsSequence", "Companion", "imagepipeline_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class ProducerSequenceFactory {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final boolean allowDelay;

    /* renamed from: backgroundLocalContentUriFetchToEncodeMemorySequence$delegate, reason: from kotlin metadata */
    private final Lazy backgroundLocalContentUriFetchToEncodeMemorySequence;

    /* renamed from: backgroundLocalFileFetchToEncodeMemorySequence$delegate, reason: from kotlin metadata */
    private final Lazy backgroundLocalFileFetchToEncodeMemorySequence;

    /* renamed from: backgroundNetworkFetchToEncodedMemorySequence$delegate, reason: from kotlin metadata */
    private final Lazy backgroundNetworkFetchToEncodedMemorySequence;
    private Map<Producer<CloseableReference<CloseableImage>>, Producer<CloseableReference<CloseableImage>>> bitmapPrepareSequences;
    private Map<Producer<CloseableReference<CloseableImage>>, Producer<Void>> closeableImagePrefetchSequences;

    /* renamed from: commonNetworkFetchToEncodedMemorySequence$delegate, reason: from kotlin metadata */
    private final Lazy commonNetworkFetchToEncodedMemorySequence;
    private final ContentResolver contentResolver;
    private final Set<CustomProducerSequenceFactory> customProducerSequenceFactories;

    /* renamed from: dataFetchSequence$delegate, reason: from kotlin metadata */
    private final Lazy dataFetchSequence;
    private final boolean diskCacheEnabled;
    private final boolean downSampleEnabled;
    private final ImageTranscoderFactory imageTranscoderFactory;
    private final boolean isDiskCacheProbingEnabled;
    private final boolean isEncodedMemoryCacheProbingEnabled;

    /* renamed from: localAssetFetchSequence$delegate, reason: from kotlin metadata */
    private final Lazy localAssetFetchSequence;

    /* renamed from: localContentUriFetchEncodedImageProducerSequence$delegate, reason: from kotlin metadata */
    private final Lazy localContentUriFetchEncodedImageProducerSequence;

    /* renamed from: localContentUriFetchSequence$delegate, reason: from kotlin metadata */
    private final Lazy localContentUriFetchSequence;

    /* renamed from: localContentUriThumbnailFetchSequence$delegate, reason: from kotlin metadata */
    private final Lazy localContentUriThumbnailFetchSequence;

    /* renamed from: localFileFetchEncodedImageProducerSequence$delegate, reason: from kotlin metadata */
    private final Lazy localFileFetchEncodedImageProducerSequence;

    /* renamed from: localFileFetchToEncodedMemoryPrefetchSequence$delegate, reason: from kotlin metadata */
    private final Lazy localFileFetchToEncodedMemoryPrefetchSequence;

    /* renamed from: localImageFileFetchSequence$delegate, reason: from kotlin metadata */
    private final Lazy localImageFileFetchSequence;

    /* renamed from: localResourceFetchSequence$delegate, reason: from kotlin metadata */
    private final Lazy localResourceFetchSequence;

    /* renamed from: localVideoFileFetchSequence$delegate, reason: from kotlin metadata */
    private final Lazy localVideoFileFetchSequence;

    /* renamed from: networkFetchEncodedImageProducerSequence$delegate, reason: from kotlin metadata */
    private final Lazy networkFetchEncodedImageProducerSequence;

    /* renamed from: networkFetchSequence$delegate, reason: from kotlin metadata */
    private final Lazy networkFetchSequence;

    /* renamed from: networkFetchToEncodedMemoryPrefetchSequence$delegate, reason: from kotlin metadata */
    private final Lazy networkFetchToEncodedMemoryPrefetchSequence;
    private final NetworkFetcher<?> networkFetcher;
    private final boolean partialImageCachingEnabled;
    private Map<Producer<CloseableReference<CloseableImage>>, Producer<CloseableReference<CloseableImage>>> postprocessorSequences;
    private final ProducerFactory producerFactory;

    /* renamed from: qualifiedResourceFetchSequence$delegate, reason: from kotlin metadata */
    private final Lazy qualifiedResourceFetchSequence;
    private final boolean resizeAndRotateEnabledForNetwork;
    private final ThreadHandoffProducerQueue threadHandoffProducerQueue;
    private final boolean useBitmapPrepareToDraw;
    private final boolean webpSupportEnabled;

    public static /* synthetic */ void getBitmapPrepareSequences$annotations() {
    }

    public static /* synthetic */ void getCloseableImagePrefetchSequences$annotations() {
    }

    public static /* synthetic */ void getLocalFileFetchEncodedImageProducerSequence$annotations() {
    }

    public static /* synthetic */ void getPostprocessorSequences$annotations() {
    }

    public final Map<Producer<CloseableReference<CloseableImage>>, Producer<CloseableReference<CloseableImage>>> getBitmapPrepareSequences() {
        return this.bitmapPrepareSequences;
    }

    public final Map<Producer<CloseableReference<CloseableImage>>, Producer<Void>> getCloseableImagePrefetchSequences() {
        return this.closeableImagePrefetchSequences;
    }

    public final Map<Producer<CloseableReference<CloseableImage>>, Producer<CloseableReference<CloseableImage>>> getPostprocessorSequences() {
        return this.postprocessorSequences;
    }

    public final void setBitmapPrepareSequences(Map<Producer<CloseableReference<CloseableImage>>, Producer<CloseableReference<CloseableImage>>> map) {
        Intrinsics.checkNotNullParameter(map, "<set-?>");
        this.bitmapPrepareSequences = map;
    }

    public final void setCloseableImagePrefetchSequences(Map<Producer<CloseableReference<CloseableImage>>, Producer<Void>> map) {
        Intrinsics.checkNotNullParameter(map, "<set-?>");
        this.closeableImagePrefetchSequences = map;
    }

    public final void setPostprocessorSequences(Map<Producer<CloseableReference<CloseableImage>>, Producer<CloseableReference<CloseableImage>>> map) {
        Intrinsics.checkNotNullParameter(map, "<set-?>");
        this.postprocessorSequences = map;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public ProducerSequenceFactory(ContentResolver contentResolver, ProducerFactory producerFactory, NetworkFetcher<?> networkFetcher, boolean z, boolean z2, ThreadHandoffProducerQueue threadHandoffProducerQueue, boolean z3, boolean z4, boolean z5, boolean z6, ImageTranscoderFactory imageTranscoderFactory, boolean z7, boolean z8, boolean z9, Set<? extends CustomProducerSequenceFactory> set) {
        Intrinsics.checkNotNullParameter(contentResolver, "contentResolver");
        Intrinsics.checkNotNullParameter(producerFactory, "producerFactory");
        Intrinsics.checkNotNullParameter(networkFetcher, "networkFetcher");
        Intrinsics.checkNotNullParameter(threadHandoffProducerQueue, "threadHandoffProducerQueue");
        Intrinsics.checkNotNullParameter(imageTranscoderFactory, "imageTranscoderFactory");
        this.contentResolver = contentResolver;
        this.producerFactory = producerFactory;
        this.networkFetcher = networkFetcher;
        this.resizeAndRotateEnabledForNetwork = z;
        this.webpSupportEnabled = z2;
        this.threadHandoffProducerQueue = threadHandoffProducerQueue;
        this.downSampleEnabled = z3;
        this.useBitmapPrepareToDraw = z4;
        this.partialImageCachingEnabled = z5;
        this.diskCacheEnabled = z6;
        this.imageTranscoderFactory = imageTranscoderFactory;
        this.isEncodedMemoryCacheProbingEnabled = z7;
        this.isDiskCacheProbingEnabled = z8;
        this.allowDelay = z9;
        this.customProducerSequenceFactories = set;
        this.postprocessorSequences = new LinkedHashMap();
        this.closeableImagePrefetchSequences = new LinkedHashMap();
        this.bitmapPrepareSequences = new LinkedHashMap();
        this.networkFetchEncodedImageProducerSequence = LazyKt.lazy(new Function0<RemoveImageTransformMetaDataProducer>() { // from class: com.facebook.imagepipeline.core.ProducerSequenceFactory$networkFetchEncodedImageProducerSequence$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final RemoveImageTransformMetaDataProducer invoke() {
                FrescoSystrace frescoSystrace = FrescoSystrace.INSTANCE;
                ProducerSequenceFactory producerSequenceFactory = ProducerSequenceFactory.this;
                if (!FrescoSystrace.isTracing()) {
                    return new RemoveImageTransformMetaDataProducer(producerSequenceFactory.getBackgroundNetworkFetchToEncodedMemorySequence());
                }
                FrescoSystrace.beginSection("ProducerSequenceFactory#getNetworkFetchEncodedImageProducerSequence:init");
                try {
                    return new RemoveImageTransformMetaDataProducer(producerSequenceFactory.getBackgroundNetworkFetchToEncodedMemorySequence());
                } finally {
                    FrescoSystrace.endSection();
                }
            }
        });
        this.localFileFetchEncodedImageProducerSequence = LazyKt.lazy(new Function0<RemoveImageTransformMetaDataProducer>() { // from class: com.facebook.imagepipeline.core.ProducerSequenceFactory$localFileFetchEncodedImageProducerSequence$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final RemoveImageTransformMetaDataProducer invoke() {
                FrescoSystrace frescoSystrace = FrescoSystrace.INSTANCE;
                ProducerSequenceFactory producerSequenceFactory = ProducerSequenceFactory.this;
                if (!FrescoSystrace.isTracing()) {
                    return new RemoveImageTransformMetaDataProducer(producerSequenceFactory.getBackgroundLocalFileFetchToEncodeMemorySequence());
                }
                FrescoSystrace.beginSection("ProducerSequenceFactory#getLocalFileFetchEncodedImageProducerSequence:init");
                try {
                    return new RemoveImageTransformMetaDataProducer(producerSequenceFactory.getBackgroundLocalFileFetchToEncodeMemorySequence());
                } finally {
                    FrescoSystrace.endSection();
                }
            }
        });
        this.localContentUriFetchEncodedImageProducerSequence = LazyKt.lazy(new Function0<RemoveImageTransformMetaDataProducer>() { // from class: com.facebook.imagepipeline.core.ProducerSequenceFactory$localContentUriFetchEncodedImageProducerSequence$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final RemoveImageTransformMetaDataProducer invoke() {
                FrescoSystrace frescoSystrace = FrescoSystrace.INSTANCE;
                ProducerSequenceFactory producerSequenceFactory = ProducerSequenceFactory.this;
                if (!FrescoSystrace.isTracing()) {
                    return new RemoveImageTransformMetaDataProducer(producerSequenceFactory.getBackgroundLocalContentUriFetchToEncodeMemorySequence());
                }
                FrescoSystrace.beginSection("ProducerSequenceFactory#getLocalContentUriFetchEncodedImageProducerSequence:init");
                try {
                    return new RemoveImageTransformMetaDataProducer(producerSequenceFactory.getBackgroundLocalContentUriFetchToEncodeMemorySequence());
                } finally {
                    FrescoSystrace.endSection();
                }
            }
        });
        this.networkFetchSequence = LazyKt.lazy(new Function0<Producer<CloseableReference<CloseableImage>>>() { // from class: com.facebook.imagepipeline.core.ProducerSequenceFactory$networkFetchSequence$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Producer<CloseableReference<CloseableImage>> invoke() {
                FrescoSystrace frescoSystrace = FrescoSystrace.INSTANCE;
                ProducerSequenceFactory producerSequenceFactory = ProducerSequenceFactory.this;
                if (!FrescoSystrace.isTracing()) {
                    return producerSequenceFactory.newBitmapCacheGetToDecodeSequence(producerSequenceFactory.getCommonNetworkFetchToEncodedMemorySequence());
                }
                FrescoSystrace.beginSection("ProducerSequenceFactory#getNetworkFetchSequence:init");
                try {
                    return producerSequenceFactory.newBitmapCacheGetToDecodeSequence(producerSequenceFactory.getCommonNetworkFetchToEncodedMemorySequence());
                } finally {
                    FrescoSystrace.endSection();
                }
            }
        });
        this.backgroundNetworkFetchToEncodedMemorySequence = LazyKt.lazy(new Function0<Producer<EncodedImage>>() { // from class: com.facebook.imagepipeline.core.ProducerSequenceFactory$backgroundNetworkFetchToEncodedMemorySequence$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Producer<EncodedImage> invoke() {
                ProducerFactory producerFactory2;
                ThreadHandoffProducerQueue threadHandoffProducerQueue2;
                ProducerFactory producerFactory3;
                ThreadHandoffProducerQueue threadHandoffProducerQueue3;
                FrescoSystrace frescoSystrace = FrescoSystrace.INSTANCE;
                ProducerSequenceFactory producerSequenceFactory = ProducerSequenceFactory.this;
                if (!FrescoSystrace.isTracing()) {
                    producerFactory3 = producerSequenceFactory.producerFactory;
                    Producer<EncodedImage> commonNetworkFetchToEncodedMemorySequence = producerSequenceFactory.getCommonNetworkFetchToEncodedMemorySequence();
                    threadHandoffProducerQueue3 = producerSequenceFactory.threadHandoffProducerQueue;
                    return producerFactory3.newBackgroundThreadHandoffProducer(commonNetworkFetchToEncodedMemorySequence, threadHandoffProducerQueue3);
                }
                FrescoSystrace.beginSection("ProducerSequenceFactory#getBackgroundNetworkFetchToEncodedMemorySequence:init");
                try {
                    producerFactory2 = producerSequenceFactory.producerFactory;
                    Producer<EncodedImage> commonNetworkFetchToEncodedMemorySequence2 = producerSequenceFactory.getCommonNetworkFetchToEncodedMemorySequence();
                    threadHandoffProducerQueue2 = producerSequenceFactory.threadHandoffProducerQueue;
                    return producerFactory2.newBackgroundThreadHandoffProducer(commonNetworkFetchToEncodedMemorySequence2, threadHandoffProducerQueue2);
                } finally {
                    FrescoSystrace.endSection();
                }
            }
        });
        this.networkFetchToEncodedMemoryPrefetchSequence = LazyKt.lazy(new Function0<SwallowResultProducer<EncodedImage>>() { // from class: com.facebook.imagepipeline.core.ProducerSequenceFactory$networkFetchToEncodedMemoryPrefetchSequence$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final SwallowResultProducer<EncodedImage> invoke() {
                ProducerFactory producerFactory2;
                ProducerFactory producerFactory3;
                FrescoSystrace frescoSystrace = FrescoSystrace.INSTANCE;
                ProducerSequenceFactory producerSequenceFactory = ProducerSequenceFactory.this;
                if (!FrescoSystrace.isTracing()) {
                    producerFactory3 = producerSequenceFactory.producerFactory;
                    return producerFactory3.newSwallowResultProducer(producerSequenceFactory.getBackgroundNetworkFetchToEncodedMemorySequence());
                }
                FrescoSystrace.beginSection("ProducerSequenceFactory#getNetworkFetchToEncodedMemoryPrefetchSequence");
                try {
                    producerFactory2 = producerSequenceFactory.producerFactory;
                    return producerFactory2.newSwallowResultProducer(producerSequenceFactory.getBackgroundNetworkFetchToEncodedMemorySequence());
                } finally {
                    FrescoSystrace.endSection();
                }
            }
        });
        this.commonNetworkFetchToEncodedMemorySequence = LazyKt.lazy(new Function0<Producer<EncodedImage>>() { // from class: com.facebook.imagepipeline.core.ProducerSequenceFactory$commonNetworkFetchToEncodedMemorySequence$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Producer<EncodedImage> invoke() {
                NetworkFetcher<?> networkFetcher2;
                NetworkFetcher<?> networkFetcher3;
                FrescoSystrace frescoSystrace = FrescoSystrace.INSTANCE;
                ProducerSequenceFactory producerSequenceFactory = ProducerSequenceFactory.this;
                if (!FrescoSystrace.isTracing()) {
                    networkFetcher3 = producerSequenceFactory.networkFetcher;
                    return producerSequenceFactory.newCommonNetworkFetchToEncodedMemorySequence(networkFetcher3);
                }
                FrescoSystrace.beginSection("ProducerSequenceFactory#getCommonNetworkFetchToEncodedMemorySequence");
                try {
                    networkFetcher2 = producerSequenceFactory.networkFetcher;
                    return producerSequenceFactory.newCommonNetworkFetchToEncodedMemorySequence(networkFetcher2);
                } finally {
                    FrescoSystrace.endSection();
                }
            }
        });
        this.localFileFetchToEncodedMemoryPrefetchSequence = LazyKt.lazy(new Function0<SwallowResultProducer<EncodedImage>>() { // from class: com.facebook.imagepipeline.core.ProducerSequenceFactory$localFileFetchToEncodedMemoryPrefetchSequence$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final SwallowResultProducer<EncodedImage> invoke() {
                ProducerFactory producerFactory2;
                ProducerFactory producerFactory3;
                FrescoSystrace frescoSystrace = FrescoSystrace.INSTANCE;
                ProducerSequenceFactory producerSequenceFactory = ProducerSequenceFactory.this;
                if (!FrescoSystrace.isTracing()) {
                    producerFactory3 = producerSequenceFactory.producerFactory;
                    return producerFactory3.newSwallowResultProducer(producerSequenceFactory.getBackgroundLocalFileFetchToEncodeMemorySequence());
                }
                FrescoSystrace.beginSection("ProducerSequenceFactory#getLocalFileFetchToEncodedMemoryPrefetchSequence:init");
                try {
                    producerFactory2 = producerSequenceFactory.producerFactory;
                    return producerFactory2.newSwallowResultProducer(producerSequenceFactory.getBackgroundLocalFileFetchToEncodeMemorySequence());
                } finally {
                    FrescoSystrace.endSection();
                }
            }
        });
        this.backgroundLocalFileFetchToEncodeMemorySequence = LazyKt.lazy(new Function0<Producer<EncodedImage>>() { // from class: com.facebook.imagepipeline.core.ProducerSequenceFactory$backgroundLocalFileFetchToEncodeMemorySequence$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Producer<EncodedImage> invoke() {
                ProducerFactory producerFactory2;
                Producer newEncodedCacheMultiplexToTranscodeSequence;
                ProducerFactory producerFactory3;
                ThreadHandoffProducerQueue threadHandoffProducerQueue2;
                ProducerFactory producerFactory4;
                Producer newEncodedCacheMultiplexToTranscodeSequence2;
                ProducerFactory producerFactory5;
                ThreadHandoffProducerQueue threadHandoffProducerQueue3;
                FrescoSystrace frescoSystrace = FrescoSystrace.INSTANCE;
                ProducerSequenceFactory producerSequenceFactory = ProducerSequenceFactory.this;
                if (!FrescoSystrace.isTracing()) {
                    producerFactory4 = producerSequenceFactory.producerFactory;
                    LocalFileFetchProducer newLocalFileFetchProducer = producerFactory4.newLocalFileFetchProducer();
                    Intrinsics.checkNotNullExpressionValue(newLocalFileFetchProducer, "producerFactory.newLocalFileFetchProducer()");
                    newEncodedCacheMultiplexToTranscodeSequence2 = producerSequenceFactory.newEncodedCacheMultiplexToTranscodeSequence(newLocalFileFetchProducer);
                    producerFactory5 = producerSequenceFactory.producerFactory;
                    threadHandoffProducerQueue3 = producerSequenceFactory.threadHandoffProducerQueue;
                    return producerFactory5.newBackgroundThreadHandoffProducer(newEncodedCacheMultiplexToTranscodeSequence2, threadHandoffProducerQueue3);
                }
                FrescoSystrace.beginSection("ProducerSequenceFactory#getBackgroundLocalFileFetchToEncodeMemorySequence");
                try {
                    producerFactory2 = producerSequenceFactory.producerFactory;
                    LocalFileFetchProducer newLocalFileFetchProducer2 = producerFactory2.newLocalFileFetchProducer();
                    Intrinsics.checkNotNullExpressionValue(newLocalFileFetchProducer2, "producerFactory.newLocalFileFetchProducer()");
                    newEncodedCacheMultiplexToTranscodeSequence = producerSequenceFactory.newEncodedCacheMultiplexToTranscodeSequence(newLocalFileFetchProducer2);
                    producerFactory3 = producerSequenceFactory.producerFactory;
                    threadHandoffProducerQueue2 = producerSequenceFactory.threadHandoffProducerQueue;
                    return producerFactory3.newBackgroundThreadHandoffProducer(newEncodedCacheMultiplexToTranscodeSequence, threadHandoffProducerQueue2);
                } finally {
                    FrescoSystrace.endSection();
                }
            }
        });
        this.backgroundLocalContentUriFetchToEncodeMemorySequence = LazyKt.lazy(new Function0<Producer<EncodedImage>>() { // from class: com.facebook.imagepipeline.core.ProducerSequenceFactory$backgroundLocalContentUriFetchToEncodeMemorySequence$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Producer<EncodedImage> invoke() {
                ProducerFactory producerFactory2;
                Producer newEncodedCacheMultiplexToTranscodeSequence;
                ProducerFactory producerFactory3;
                ThreadHandoffProducerQueue threadHandoffProducerQueue2;
                ProducerFactory producerFactory4;
                Producer newEncodedCacheMultiplexToTranscodeSequence2;
                ProducerFactory producerFactory5;
                ThreadHandoffProducerQueue threadHandoffProducerQueue3;
                FrescoSystrace frescoSystrace = FrescoSystrace.INSTANCE;
                ProducerSequenceFactory producerSequenceFactory = ProducerSequenceFactory.this;
                if (!FrescoSystrace.isTracing()) {
                    producerFactory4 = producerSequenceFactory.producerFactory;
                    LocalContentUriFetchProducer newLocalContentUriFetchProducer = producerFactory4.newLocalContentUriFetchProducer();
                    Intrinsics.checkNotNullExpressionValue(newLocalContentUriFetchProducer, "producerFactory.newLocalContentUriFetchProducer()");
                    newEncodedCacheMultiplexToTranscodeSequence2 = producerSequenceFactory.newEncodedCacheMultiplexToTranscodeSequence(newLocalContentUriFetchProducer);
                    producerFactory5 = producerSequenceFactory.producerFactory;
                    threadHandoffProducerQueue3 = producerSequenceFactory.threadHandoffProducerQueue;
                    return producerFactory5.newBackgroundThreadHandoffProducer(newEncodedCacheMultiplexToTranscodeSequence2, threadHandoffProducerQueue3);
                }
                FrescoSystrace.beginSection("ProducerSequenceFactory#getBackgroundLocalContentUriFetchToEncodeMemorySequence:init");
                try {
                    producerFactory2 = producerSequenceFactory.producerFactory;
                    LocalContentUriFetchProducer newLocalContentUriFetchProducer2 = producerFactory2.newLocalContentUriFetchProducer();
                    Intrinsics.checkNotNullExpressionValue(newLocalContentUriFetchProducer2, "producerFactory.newLocalContentUriFetchProducer()");
                    newEncodedCacheMultiplexToTranscodeSequence = producerSequenceFactory.newEncodedCacheMultiplexToTranscodeSequence(newLocalContentUriFetchProducer2);
                    producerFactory3 = producerSequenceFactory.producerFactory;
                    threadHandoffProducerQueue2 = producerSequenceFactory.threadHandoffProducerQueue;
                    return producerFactory3.newBackgroundThreadHandoffProducer(newEncodedCacheMultiplexToTranscodeSequence, threadHandoffProducerQueue2);
                } finally {
                    FrescoSystrace.endSection();
                }
            }
        });
        this.localImageFileFetchSequence = LazyKt.lazy(new Function0<Producer<CloseableReference<CloseableImage>>>() { // from class: com.facebook.imagepipeline.core.ProducerSequenceFactory$localImageFileFetchSequence$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Producer<CloseableReference<CloseableImage>> invoke() {
                ProducerFactory producerFactory2;
                Producer<CloseableReference<CloseableImage>> newBitmapCacheGetToLocalTransformSequence;
                producerFactory2 = ProducerSequenceFactory.this.producerFactory;
                LocalFileFetchProducer newLocalFileFetchProducer = producerFactory2.newLocalFileFetchProducer();
                Intrinsics.checkNotNullExpressionValue(newLocalFileFetchProducer, "producerFactory.newLocalFileFetchProducer()");
                newBitmapCacheGetToLocalTransformSequence = ProducerSequenceFactory.this.newBitmapCacheGetToLocalTransformSequence(newLocalFileFetchProducer);
                return newBitmapCacheGetToLocalTransformSequence;
            }
        });
        this.localVideoFileFetchSequence = LazyKt.lazy(new Function0<Producer<CloseableReference<CloseableImage>>>() { // from class: com.facebook.imagepipeline.core.ProducerSequenceFactory$localVideoFileFetchSequence$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Producer<CloseableReference<CloseableImage>> invoke() {
                ProducerFactory producerFactory2;
                Producer<CloseableReference<CloseableImage>> newBitmapCacheGetToBitmapCacheSequence;
                producerFactory2 = ProducerSequenceFactory.this.producerFactory;
                LocalVideoThumbnailProducer newLocalVideoThumbnailProducer = producerFactory2.newLocalVideoThumbnailProducer();
                Intrinsics.checkNotNullExpressionValue(newLocalVideoThumbnailProducer, "producerFactory.newLocalVideoThumbnailProducer()");
                newBitmapCacheGetToBitmapCacheSequence = ProducerSequenceFactory.this.newBitmapCacheGetToBitmapCacheSequence(newLocalVideoThumbnailProducer);
                return newBitmapCacheGetToBitmapCacheSequence;
            }
        });
        this.localContentUriFetchSequence = LazyKt.lazy(new Function0<Producer<CloseableReference<CloseableImage>>>() { // from class: com.facebook.imagepipeline.core.ProducerSequenceFactory$localContentUriFetchSequence$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Producer<CloseableReference<CloseableImage>> invoke() {
                ProducerFactory producerFactory2;
                ProducerFactory producerFactory3;
                ProducerFactory producerFactory4;
                Producer<CloseableReference<CloseableImage>> newBitmapCacheGetToLocalTransformSequence;
                producerFactory2 = ProducerSequenceFactory.this.producerFactory;
                LocalContentUriFetchProducer newLocalContentUriFetchProducer = producerFactory2.newLocalContentUriFetchProducer();
                Intrinsics.checkNotNullExpressionValue(newLocalContentUriFetchProducer, "producerFactory.newLocalContentUriFetchProducer()");
                producerFactory3 = ProducerSequenceFactory.this.producerFactory;
                LocalContentUriThumbnailFetchProducer newLocalContentUriThumbnailFetchProducer = producerFactory3.newLocalContentUriThumbnailFetchProducer();
                Intrinsics.checkNotNullExpressionValue(newLocalContentUriThumbnailFetchProducer, "producerFactory.newLocal…iThumbnailFetchProducer()");
                producerFactory4 = ProducerSequenceFactory.this.producerFactory;
                LocalExifThumbnailProducer newLocalExifThumbnailProducer = producerFactory4.newLocalExifThumbnailProducer();
                Intrinsics.checkNotNullExpressionValue(newLocalExifThumbnailProducer, "producerFactory.newLocalExifThumbnailProducer()");
                newBitmapCacheGetToLocalTransformSequence = ProducerSequenceFactory.this.newBitmapCacheGetToLocalTransformSequence(newLocalContentUriFetchProducer, new ThumbnailProducer[]{newLocalContentUriThumbnailFetchProducer, newLocalExifThumbnailProducer});
                return newBitmapCacheGetToLocalTransformSequence;
            }
        });
        this.localContentUriThumbnailFetchSequence = LazyKt.lazy(new Function0<Producer<CloseableReference<CloseableImage>>>() { // from class: com.facebook.imagepipeline.core.ProducerSequenceFactory$localContentUriThumbnailFetchSequence$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Producer<CloseableReference<CloseableImage>> invoke() {
                ProducerFactory producerFactory2;
                Producer<CloseableReference<CloseableImage>> newBitmapCacheGetToBitmapCacheSequence;
                if (Build.VERSION.SDK_INT >= 29) {
                    ProducerSequenceFactory producerSequenceFactory = ProducerSequenceFactory.this;
                    producerFactory2 = producerSequenceFactory.producerFactory;
                    LocalThumbnailBitmapProducer newLocalThumbnailBitmapProducer = producerFactory2.newLocalThumbnailBitmapProducer();
                    Intrinsics.checkNotNullExpressionValue(newLocalThumbnailBitmapProducer, "producerFactory.newLocalThumbnailBitmapProducer()");
                    newBitmapCacheGetToBitmapCacheSequence = producerSequenceFactory.newBitmapCacheGetToBitmapCacheSequence(newLocalThumbnailBitmapProducer);
                    return newBitmapCacheGetToBitmapCacheSequence;
                }
                throw new Throwable("Unreachable exception. Just to make linter happy for the lazy block.");
            }
        });
        this.qualifiedResourceFetchSequence = LazyKt.lazy(new Function0<Producer<CloseableReference<CloseableImage>>>() { // from class: com.facebook.imagepipeline.core.ProducerSequenceFactory$qualifiedResourceFetchSequence$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Producer<CloseableReference<CloseableImage>> invoke() {
                ProducerFactory producerFactory2;
                Producer<CloseableReference<CloseableImage>> newBitmapCacheGetToLocalTransformSequence;
                producerFactory2 = ProducerSequenceFactory.this.producerFactory;
                QualifiedResourceFetchProducer newQualifiedResourceFetchProducer = producerFactory2.newQualifiedResourceFetchProducer();
                Intrinsics.checkNotNullExpressionValue(newQualifiedResourceFetchProducer, "producerFactory.newQuali…edResourceFetchProducer()");
                newBitmapCacheGetToLocalTransformSequence = ProducerSequenceFactory.this.newBitmapCacheGetToLocalTransformSequence(newQualifiedResourceFetchProducer);
                return newBitmapCacheGetToLocalTransformSequence;
            }
        });
        this.localResourceFetchSequence = LazyKt.lazy(new Function0<Producer<CloseableReference<CloseableImage>>>() { // from class: com.facebook.imagepipeline.core.ProducerSequenceFactory$localResourceFetchSequence$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Producer<CloseableReference<CloseableImage>> invoke() {
                ProducerFactory producerFactory2;
                Producer<CloseableReference<CloseableImage>> newBitmapCacheGetToLocalTransformSequence;
                producerFactory2 = ProducerSequenceFactory.this.producerFactory;
                LocalResourceFetchProducer newLocalResourceFetchProducer = producerFactory2.newLocalResourceFetchProducer();
                Intrinsics.checkNotNullExpressionValue(newLocalResourceFetchProducer, "producerFactory.newLocalResourceFetchProducer()");
                newBitmapCacheGetToLocalTransformSequence = ProducerSequenceFactory.this.newBitmapCacheGetToLocalTransformSequence(newLocalResourceFetchProducer);
                return newBitmapCacheGetToLocalTransformSequence;
            }
        });
        this.localAssetFetchSequence = LazyKt.lazy(new Function0<Producer<CloseableReference<CloseableImage>>>() { // from class: com.facebook.imagepipeline.core.ProducerSequenceFactory$localAssetFetchSequence$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Producer<CloseableReference<CloseableImage>> invoke() {
                ProducerFactory producerFactory2;
                Producer<CloseableReference<CloseableImage>> newBitmapCacheGetToLocalTransformSequence;
                producerFactory2 = ProducerSequenceFactory.this.producerFactory;
                LocalAssetFetchProducer newLocalAssetFetchProducer = producerFactory2.newLocalAssetFetchProducer();
                Intrinsics.checkNotNullExpressionValue(newLocalAssetFetchProducer, "producerFactory.newLocalAssetFetchProducer()");
                newBitmapCacheGetToLocalTransformSequence = ProducerSequenceFactory.this.newBitmapCacheGetToLocalTransformSequence(newLocalAssetFetchProducer);
                return newBitmapCacheGetToLocalTransformSequence;
            }
        });
        this.dataFetchSequence = LazyKt.lazy(new Function0<Producer<CloseableReference<CloseableImage>>>() { // from class: com.facebook.imagepipeline.core.ProducerSequenceFactory$dataFetchSequence$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Producer<CloseableReference<CloseableImage>> invoke() {
                ProducerFactory producerFactory2;
                ProducerFactory producerFactory3;
                ImageTranscoderFactory imageTranscoderFactory2;
                boolean z10;
                ProducerFactory producerFactory4;
                producerFactory2 = ProducerSequenceFactory.this.producerFactory;
                DataFetchProducer newDataFetchProducer = producerFactory2.newDataFetchProducer();
                Intrinsics.checkNotNullExpressionValue(newDataFetchProducer, "producerFactory.newDataFetchProducer()");
                WebpTranscodeProducer webpTranscodeProducer = newDataFetchProducer;
                if (WebpSupportStatus.sIsWebpSupportRequired) {
                    z10 = ProducerSequenceFactory.this.webpSupportEnabled;
                    if (!z10 || WebpSupportStatus.sWebpBitmapFactory == null) {
                        producerFactory4 = ProducerSequenceFactory.this.producerFactory;
                        WebpTranscodeProducer newWebpTranscodeProducer = producerFactory4.newWebpTranscodeProducer(webpTranscodeProducer);
                        Intrinsics.checkNotNullExpressionValue(newWebpTranscodeProducer, "producerFactory.newWebpT…deProducer(inputProducer)");
                        webpTranscodeProducer = newWebpTranscodeProducer;
                    }
                }
                AddImageTransformMetaDataProducer newAddImageTransformMetaDataProducer = ProducerFactory.newAddImageTransformMetaDataProducer(webpTranscodeProducer);
                Intrinsics.checkNotNullExpressionValue(newAddImageTransformMetaDataProducer, "newAddImageTransformMeta…taProducer(inputProducer)");
                producerFactory3 = ProducerSequenceFactory.this.producerFactory;
                imageTranscoderFactory2 = ProducerSequenceFactory.this.imageTranscoderFactory;
                ResizeAndRotateProducer newResizeAndRotateProducer = producerFactory3.newResizeAndRotateProducer(newAddImageTransformMetaDataProducer, true, imageTranscoderFactory2);
                Intrinsics.checkNotNullExpressionValue(newResizeAndRotateProducer, "producerFactory.newResiz…, imageTranscoderFactory)");
                return ProducerSequenceFactory.this.newBitmapCacheGetToDecodeSequence(newResizeAndRotateProducer);
            }
        });
    }

    public final Producer<CloseableReference<PooledByteBuffer>> getEncodedImageProducerSequence(ImageRequest imageRequest) {
        Producer<CloseableReference<PooledByteBuffer>> networkFetchEncodedImageProducerSequence;
        Intrinsics.checkNotNullParameter(imageRequest, "imageRequest");
        FrescoSystrace frescoSystrace = FrescoSystrace.INSTANCE;
        if (!FrescoSystrace.isTracing()) {
            Companion companion = INSTANCE;
            companion.validateEncodedImageRequest(imageRequest);
            Uri sourceUri = imageRequest.getSourceUri();
            Intrinsics.checkNotNullExpressionValue(sourceUri, "imageRequest.sourceUri");
            int sourceUriType = imageRequest.getSourceUriType();
            if (sourceUriType == 0) {
                return getNetworkFetchEncodedImageProducerSequence();
            }
            if (sourceUriType == 2 || sourceUriType == 3) {
                return getLocalFileFetchEncodedImageProducerSequence();
            }
            if (sourceUriType == 4) {
                return getLocalContentUriFetchEncodedImageProducerSequence();
            }
            throw new IllegalArgumentException("Unsupported uri scheme for encoded image fetch! Uri is: " + companion.getShortenedUriString(sourceUri));
        }
        FrescoSystrace.beginSection("ProducerSequenceFactory#getEncodedImageProducerSequence");
        try {
            Companion companion2 = INSTANCE;
            companion2.validateEncodedImageRequest(imageRequest);
            Uri sourceUri2 = imageRequest.getSourceUri();
            Intrinsics.checkNotNullExpressionValue(sourceUri2, "imageRequest.sourceUri");
            int sourceUriType2 = imageRequest.getSourceUriType();
            if (sourceUriType2 == 0) {
                networkFetchEncodedImageProducerSequence = getNetworkFetchEncodedImageProducerSequence();
            } else if (sourceUriType2 == 2 || sourceUriType2 == 3) {
                networkFetchEncodedImageProducerSequence = getLocalFileFetchEncodedImageProducerSequence();
            } else {
                if (sourceUriType2 != 4) {
                    throw new IllegalArgumentException("Unsupported uri scheme for encoded image fetch! Uri is: " + companion2.getShortenedUriString(sourceUri2));
                }
                networkFetchEncodedImageProducerSequence = getLocalContentUriFetchEncodedImageProducerSequence();
            }
            return networkFetchEncodedImageProducerSequence;
        } finally {
            FrescoSystrace.endSection();
        }
    }

    public final Producer<CloseableReference<PooledByteBuffer>> getNetworkFetchEncodedImageProducerSequence() {
        return (Producer) this.networkFetchEncodedImageProducerSequence.getValue();
    }

    public final Producer<CloseableReference<PooledByteBuffer>> getLocalFileFetchEncodedImageProducerSequence() {
        return (Producer) this.localFileFetchEncodedImageProducerSequence.getValue();
    }

    public final Producer<CloseableReference<PooledByteBuffer>> getLocalContentUriFetchEncodedImageProducerSequence() {
        return (Producer) this.localContentUriFetchEncodedImageProducerSequence.getValue();
    }

    public final Producer<Void> getEncodedImagePrefetchProducerSequence(ImageRequest imageRequest) {
        Intrinsics.checkNotNullParameter(imageRequest, "imageRequest");
        Companion companion = INSTANCE;
        companion.validateEncodedImageRequest(imageRequest);
        int sourceUriType = imageRequest.getSourceUriType();
        if (sourceUriType == 0) {
            return getNetworkFetchToEncodedMemoryPrefetchSequence();
        }
        if (sourceUriType == 2 || sourceUriType == 3) {
            return getLocalFileFetchToEncodedMemoryPrefetchSequence();
        }
        Uri sourceUri = imageRequest.getSourceUri();
        Intrinsics.checkNotNullExpressionValue(sourceUri, "imageRequest.sourceUri");
        throw new IllegalArgumentException("Unsupported uri scheme for encoded image fetch! Uri is: " + companion.getShortenedUriString(sourceUri));
    }

    public final Producer<CloseableReference<CloseableImage>> getDecodedImageProducerSequence(ImageRequest imageRequest) {
        Intrinsics.checkNotNullParameter(imageRequest, "imageRequest");
        FrescoSystrace frescoSystrace = FrescoSystrace.INSTANCE;
        if (!FrescoSystrace.isTracing()) {
            Producer<CloseableReference<CloseableImage>> basicDecodedImageSequence = getBasicDecodedImageSequence(imageRequest);
            if (imageRequest.getPostprocessor() != null) {
                basicDecodedImageSequence = getPostprocessorSequence(basicDecodedImageSequence);
            }
            if (this.useBitmapPrepareToDraw) {
                basicDecodedImageSequence = getBitmapPrepareSequence(basicDecodedImageSequence);
            }
            return (!this.allowDelay || imageRequest.getDelayMs() <= 0) ? basicDecodedImageSequence : getDelaySequence(basicDecodedImageSequence);
        }
        FrescoSystrace.beginSection("ProducerSequenceFactory#getDecodedImageProducerSequence");
        try {
            Producer<CloseableReference<CloseableImage>> basicDecodedImageSequence2 = getBasicDecodedImageSequence(imageRequest);
            if (imageRequest.getPostprocessor() != null) {
                basicDecodedImageSequence2 = getPostprocessorSequence(basicDecodedImageSequence2);
            }
            if (this.useBitmapPrepareToDraw) {
                basicDecodedImageSequence2 = getBitmapPrepareSequence(basicDecodedImageSequence2);
            }
            if (this.allowDelay && imageRequest.getDelayMs() > 0) {
                basicDecodedImageSequence2 = getDelaySequence(basicDecodedImageSequence2);
            }
            return basicDecodedImageSequence2;
        } finally {
            FrescoSystrace.endSection();
        }
    }

    public final Producer<Void> getDecodedImagePrefetchProducerSequence(ImageRequest imageRequest) {
        Intrinsics.checkNotNullParameter(imageRequest, "imageRequest");
        Producer<CloseableReference<CloseableImage>> basicDecodedImageSequence = getBasicDecodedImageSequence(imageRequest);
        if (this.useBitmapPrepareToDraw) {
            basicDecodedImageSequence = getBitmapPrepareSequence(basicDecodedImageSequence);
        }
        return getDecodedImagePrefetchSequence(basicDecodedImageSequence);
    }

    private final Producer<CloseableReference<CloseableImage>> getBasicDecodedImageSequence(ImageRequest imageRequest) {
        Producer<CloseableReference<CloseableImage>> networkFetchSequence;
        FrescoSystrace frescoSystrace = FrescoSystrace.INSTANCE;
        if (!FrescoSystrace.isTracing()) {
            Uri sourceUri = imageRequest.getSourceUri();
            Intrinsics.checkNotNullExpressionValue(sourceUri, "imageRequest.sourceUri");
            if (sourceUri == null) {
                throw new IllegalStateException("Uri is null.".toString());
            }
            int sourceUriType = imageRequest.getSourceUriType();
            if (sourceUriType == 0) {
                return getNetworkFetchSequence();
            }
            switch (sourceUriType) {
                case 2:
                    return getLocalVideoFileFetchSequence();
                case 3:
                    return getLocalImageFileFetchSequence();
                case 4:
                    return imageRequest.getLoadThumbnailOnlyForAndroidSdkAboveQ() ? getLocalContentUriThumbnailFetchSequence() : MediaUtils.isVideo(this.contentResolver.getType(sourceUri)) ? getLocalVideoFileFetchSequence() : getLocalContentUriFetchSequence();
                case 5:
                    return getLocalAssetFetchSequence();
                case 6:
                    return getLocalResourceFetchSequence();
                case 7:
                    return getDataFetchSequence();
                case 8:
                    return getQualifiedResourceFetchSequence();
                default:
                    Set<CustomProducerSequenceFactory> set = this.customProducerSequenceFactories;
                    if (set != null) {
                        Iterator<CustomProducerSequenceFactory> it = set.iterator();
                        while (it.hasNext()) {
                            Producer<CloseableReference<CloseableImage>> customDecodedImageSequence = it.next().getCustomDecodedImageSequence(imageRequest, this);
                            if (customDecodedImageSequence != null) {
                                return customDecodedImageSequence;
                            }
                        }
                    }
                    throw new IllegalArgumentException("Unsupported uri scheme! Uri is: " + INSTANCE.getShortenedUriString(sourceUri));
            }
        }
        FrescoSystrace.beginSection("ProducerSequenceFactory#getBasicDecodedImageSequence");
        try {
            Uri sourceUri2 = imageRequest.getSourceUri();
            Intrinsics.checkNotNullExpressionValue(sourceUri2, "imageRequest.sourceUri");
            if (sourceUri2 == null) {
                throw new IllegalStateException("Uri is null.".toString());
            }
            int sourceUriType2 = imageRequest.getSourceUriType();
            if (sourceUriType2 != 0) {
                switch (sourceUriType2) {
                    case 2:
                        networkFetchSequence = getLocalVideoFileFetchSequence();
                        break;
                    case 3:
                        networkFetchSequence = getLocalImageFileFetchSequence();
                        break;
                    case 4:
                        if (!imageRequest.getLoadThumbnailOnlyForAndroidSdkAboveQ()) {
                            if (!MediaUtils.isVideo(this.contentResolver.getType(sourceUri2))) {
                                networkFetchSequence = getLocalContentUriFetchSequence();
                                break;
                            } else {
                                return getLocalVideoFileFetchSequence();
                            }
                        } else {
                            return getLocalContentUriThumbnailFetchSequence();
                        }
                    case 5:
                        networkFetchSequence = getLocalAssetFetchSequence();
                        break;
                    case 6:
                        networkFetchSequence = getLocalResourceFetchSequence();
                        break;
                    case 7:
                        networkFetchSequence = getDataFetchSequence();
                        break;
                    case 8:
                        networkFetchSequence = getQualifiedResourceFetchSequence();
                        break;
                    default:
                        Set<CustomProducerSequenceFactory> set2 = this.customProducerSequenceFactories;
                        if (set2 != null) {
                            Iterator<CustomProducerSequenceFactory> it2 = set2.iterator();
                            while (it2.hasNext()) {
                                Producer<CloseableReference<CloseableImage>> customDecodedImageSequence2 = it2.next().getCustomDecodedImageSequence(imageRequest, this);
                                if (customDecodedImageSequence2 != null) {
                                    return customDecodedImageSequence2;
                                }
                            }
                        }
                        throw new IllegalArgumentException("Unsupported uri scheme! Uri is: " + INSTANCE.getShortenedUriString(sourceUri2));
                }
            } else {
                networkFetchSequence = getNetworkFetchSequence();
            }
            return networkFetchSequence;
        } finally {
            FrescoSystrace.endSection();
        }
    }

    public final Producer<CloseableReference<CloseableImage>> getNetworkFetchSequence() {
        return (Producer) this.networkFetchSequence.getValue();
    }

    public final Producer<EncodedImage> getBackgroundNetworkFetchToEncodedMemorySequence() {
        Object value = this.backgroundNetworkFetchToEncodedMemorySequence.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-backgroundNetworkFe…codedMemorySequence>(...)");
        return (Producer) value;
    }

    public final Producer<Void> getNetworkFetchToEncodedMemoryPrefetchSequence() {
        Object value = this.networkFetchToEncodedMemoryPrefetchSequence.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-networkFetchToEncod…oryPrefetchSequence>(...)");
        return (Producer) value;
    }

    public final Producer<EncodedImage> getCommonNetworkFetchToEncodedMemorySequence() {
        return (Producer) this.commonNetworkFetchToEncodedMemorySequence.getValue();
    }

    public final synchronized Producer<EncodedImage> newCommonNetworkFetchToEncodedMemorySequence(NetworkFetcher<?> networkFetcher) {
        Intrinsics.checkNotNullParameter(networkFetcher, "networkFetcher");
        FrescoSystrace frescoSystrace = FrescoSystrace.INSTANCE;
        boolean z = true;
        if (!FrescoSystrace.isTracing()) {
            Producer<EncodedImage> newNetworkFetchProducer = this.producerFactory.newNetworkFetchProducer(networkFetcher);
            Intrinsics.checkNotNullExpressionValue(newNetworkFetchProducer, "producerFactory.newNetwo…hProducer(networkFetcher)");
            AddImageTransformMetaDataProducer newAddImageTransformMetaDataProducer = ProducerFactory.newAddImageTransformMetaDataProducer(newEncodedCacheMultiplexToTranscodeSequence(newNetworkFetchProducer));
            Intrinsics.checkNotNullExpressionValue(newAddImageTransformMetaDataProducer, "newAddImageTransformMeta…taProducer(inputProducer)");
            AddImageTransformMetaDataProducer addImageTransformMetaDataProducer = newAddImageTransformMetaDataProducer;
            ProducerFactory producerFactory = this.producerFactory;
            if (!this.resizeAndRotateEnabledForNetwork || this.downSampleEnabled) {
                z = false;
            }
            ResizeAndRotateProducer newResizeAndRotateProducer = producerFactory.newResizeAndRotateProducer(addImageTransformMetaDataProducer, z, this.imageTranscoderFactory);
            Intrinsics.checkNotNullExpressionValue(newResizeAndRotateProducer, "producerFactory.newResiz…  imageTranscoderFactory)");
            ResizeAndRotateProducer networkFetchToEncodedMemorySequence = newResizeAndRotateProducer;
            Intrinsics.checkNotNullExpressionValue(networkFetchToEncodedMemorySequence, "networkFetchToEncodedMemorySequence");
            return networkFetchToEncodedMemorySequence;
        }
        FrescoSystrace.beginSection("ProducerSequenceFactory#createCommonNetworkFetchToEncodedMemorySequence");
        try {
            Producer<EncodedImage> newNetworkFetchProducer2 = this.producerFactory.newNetworkFetchProducer(networkFetcher);
            Intrinsics.checkNotNullExpressionValue(newNetworkFetchProducer2, "producerFactory.newNetwo…hProducer(networkFetcher)");
            AddImageTransformMetaDataProducer newAddImageTransformMetaDataProducer2 = ProducerFactory.newAddImageTransformMetaDataProducer(newEncodedCacheMultiplexToTranscodeSequence(newNetworkFetchProducer2));
            Intrinsics.checkNotNullExpressionValue(newAddImageTransformMetaDataProducer2, "newAddImageTransformMeta…taProducer(inputProducer)");
            AddImageTransformMetaDataProducer addImageTransformMetaDataProducer2 = newAddImageTransformMetaDataProducer2;
            ProducerFactory producerFactory2 = this.producerFactory;
            if (!this.resizeAndRotateEnabledForNetwork || this.downSampleEnabled) {
                z = false;
            }
            ResizeAndRotateProducer newResizeAndRotateProducer2 = producerFactory2.newResizeAndRotateProducer(addImageTransformMetaDataProducer2, z, this.imageTranscoderFactory);
            Intrinsics.checkNotNullExpressionValue(newResizeAndRotateProducer2, "producerFactory.newResiz…  imageTranscoderFactory)");
            ResizeAndRotateProducer networkFetchToEncodedMemorySequence2 = newResizeAndRotateProducer2;
            Intrinsics.checkNotNullExpressionValue(networkFetchToEncodedMemorySequence2, "networkFetchToEncodedMemorySequence");
            return networkFetchToEncodedMemorySequence2;
        } finally {
            FrescoSystrace.endSection();
        }
    }

    public final Producer<Void> getLocalFileFetchToEncodedMemoryPrefetchSequence() {
        Object value = this.localFileFetchToEncodedMemoryPrefetchSequence.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-localFileFetchToEnc…oryPrefetchSequence>(...)");
        return (Producer) value;
    }

    public final Producer<EncodedImage> getBackgroundLocalFileFetchToEncodeMemorySequence() {
        Object value = this.backgroundLocalFileFetchToEncodeMemorySequence.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-backgroundLocalFile…ncodeMemorySequence>(...)");
        return (Producer) value;
    }

    public final Producer<EncodedImage> getBackgroundLocalContentUriFetchToEncodeMemorySequence() {
        Object value = this.backgroundLocalContentUriFetchToEncodeMemorySequence.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-backgroundLocalCont…ncodeMemorySequence>(...)");
        return (Producer) value;
    }

    public final Producer<CloseableReference<CloseableImage>> getLocalImageFileFetchSequence() {
        return (Producer) this.localImageFileFetchSequence.getValue();
    }

    public final Producer<CloseableReference<CloseableImage>> getLocalVideoFileFetchSequence() {
        return (Producer) this.localVideoFileFetchSequence.getValue();
    }

    public final Producer<CloseableReference<CloseableImage>> getLocalContentUriFetchSequence() {
        return (Producer) this.localContentUriFetchSequence.getValue();
    }

    public final Producer<CloseableReference<CloseableImage>> getLocalContentUriThumbnailFetchSequence() {
        return (Producer) this.localContentUriThumbnailFetchSequence.getValue();
    }

    public final Producer<CloseableReference<CloseableImage>> getQualifiedResourceFetchSequence() {
        return (Producer) this.qualifiedResourceFetchSequence.getValue();
    }

    public final Producer<CloseableReference<CloseableImage>> getLocalResourceFetchSequence() {
        return (Producer) this.localResourceFetchSequence.getValue();
    }

    public final Producer<CloseableReference<CloseableImage>> getLocalAssetFetchSequence() {
        return (Producer) this.localAssetFetchSequence.getValue();
    }

    public final Producer<CloseableReference<CloseableImage>> getDataFetchSequence() {
        return (Producer) this.dataFetchSequence.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Producer<CloseableReference<CloseableImage>> newBitmapCacheGetToLocalTransformSequence(Producer<EncodedImage> inputProducer) {
        LocalExifThumbnailProducer newLocalExifThumbnailProducer = this.producerFactory.newLocalExifThumbnailProducer();
        Intrinsics.checkNotNullExpressionValue(newLocalExifThumbnailProducer, "producerFactory.newLocalExifThumbnailProducer()");
        return newBitmapCacheGetToLocalTransformSequence(inputProducer, new ThumbnailProducer[]{newLocalExifThumbnailProducer});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Producer<CloseableReference<CloseableImage>> newBitmapCacheGetToLocalTransformSequence(Producer<EncodedImage> inputProducer, ThumbnailProducer<EncodedImage>[] thumbnailProducers) {
        return newBitmapCacheGetToDecodeSequence(newLocalTransformationsSequence(newEncodedCacheMultiplexToTranscodeSequence(inputProducer), thumbnailProducers));
    }

    public final Producer<CloseableReference<CloseableImage>> newBitmapCacheGetToDecodeSequence(Producer<EncodedImage> inputProducer) {
        Intrinsics.checkNotNullParameter(inputProducer, "inputProducer");
        FrescoSystrace frescoSystrace = FrescoSystrace.INSTANCE;
        if (!FrescoSystrace.isTracing()) {
            DecodeProducer newDecodeProducer = this.producerFactory.newDecodeProducer(inputProducer);
            Intrinsics.checkNotNullExpressionValue(newDecodeProducer, "producerFactory.newDecodeProducer(inputProducer)");
            return newBitmapCacheGetToBitmapCacheSequence(newDecodeProducer);
        }
        FrescoSystrace.beginSection("ProducerSequenceFactory#newBitmapCacheGetToDecodeSequence");
        try {
            DecodeProducer newDecodeProducer2 = this.producerFactory.newDecodeProducer(inputProducer);
            Intrinsics.checkNotNullExpressionValue(newDecodeProducer2, "producerFactory.newDecodeProducer(inputProducer)");
            return newBitmapCacheGetToBitmapCacheSequence(newDecodeProducer2);
        } finally {
            FrescoSystrace.endSection();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Producer<EncodedImage> newEncodedCacheMultiplexToTranscodeSequence(Producer<EncodedImage> inputProducer) {
        if (WebpSupportStatus.sIsWebpSupportRequired && (!this.webpSupportEnabled || WebpSupportStatus.sWebpBitmapFactory == null)) {
            WebpTranscodeProducer newWebpTranscodeProducer = this.producerFactory.newWebpTranscodeProducer(inputProducer);
            Intrinsics.checkNotNullExpressionValue(newWebpTranscodeProducer, "producerFactory.newWebpTranscodeProducer(ip)");
            inputProducer = newWebpTranscodeProducer;
        }
        if (this.diskCacheEnabled) {
            inputProducer = newDiskCacheSequence(inputProducer);
        }
        Producer<EncodedImage> newEncodedMemoryCacheProducer = this.producerFactory.newEncodedMemoryCacheProducer(inputProducer);
        Intrinsics.checkNotNullExpressionValue(newEncodedMemoryCacheProducer, "producerFactory.newEncodedMemoryCacheProducer(ip)");
        if (this.isDiskCacheProbingEnabled) {
            EncodedProbeProducer newEncodedProbeProducer = this.producerFactory.newEncodedProbeProducer(newEncodedMemoryCacheProducer);
            Intrinsics.checkNotNullExpressionValue(newEncodedProbeProducer, "producerFactory.newEncod…codedMemoryCacheProducer)");
            EncodedCacheKeyMultiplexProducer newEncodedCacheKeyMultiplexProducer = this.producerFactory.newEncodedCacheKeyMultiplexProducer(newEncodedProbeProducer);
            Intrinsics.checkNotNullExpressionValue(newEncodedCacheKeyMultiplexProducer, "producerFactory.newEncod…exProducer(probeProducer)");
            return newEncodedCacheKeyMultiplexProducer;
        }
        EncodedCacheKeyMultiplexProducer newEncodedCacheKeyMultiplexProducer2 = this.producerFactory.newEncodedCacheKeyMultiplexProducer(newEncodedMemoryCacheProducer);
        Intrinsics.checkNotNullExpressionValue(newEncodedCacheKeyMultiplexProducer2, "producerFactory.newEncod…codedMemoryCacheProducer)");
        return newEncodedCacheKeyMultiplexProducer2;
    }

    private final Producer<EncodedImage> newDiskCacheSequence(Producer<EncodedImage> inputProducer) {
        DiskCacheWriteProducer newDiskCacheWriteProducer;
        DiskCacheWriteProducer newDiskCacheWriteProducer2;
        FrescoSystrace frescoSystrace = FrescoSystrace.INSTANCE;
        if (!FrescoSystrace.isTracing()) {
            if (this.partialImageCachingEnabled) {
                PartialDiskCacheProducer newPartialDiskCacheProducer = this.producerFactory.newPartialDiskCacheProducer(inputProducer);
                Intrinsics.checkNotNullExpressionValue(newPartialDiskCacheProducer, "producerFactory.newParti…heProducer(inputProducer)");
                newDiskCacheWriteProducer2 = this.producerFactory.newDiskCacheWriteProducer(newPartialDiskCacheProducer);
            } else {
                newDiskCacheWriteProducer2 = this.producerFactory.newDiskCacheWriteProducer(inputProducer);
            }
            Intrinsics.checkNotNullExpressionValue(newDiskCacheWriteProducer2, "if (partialImageCachingE…utProducer)\n            }");
            DiskCacheReadProducer newDiskCacheReadProducer = this.producerFactory.newDiskCacheReadProducer(newDiskCacheWriteProducer2);
            Intrinsics.checkNotNullExpressionValue(newDiskCacheReadProducer, "producerFactory.newDiskC…ducer(cacheWriteProducer)");
            return newDiskCacheReadProducer;
        }
        FrescoSystrace.beginSection("ProducerSequenceFactory#newDiskCacheSequence");
        try {
            if (this.partialImageCachingEnabled) {
                PartialDiskCacheProducer newPartialDiskCacheProducer2 = this.producerFactory.newPartialDiskCacheProducer(inputProducer);
                Intrinsics.checkNotNullExpressionValue(newPartialDiskCacheProducer2, "producerFactory.newParti…heProducer(inputProducer)");
                newDiskCacheWriteProducer = this.producerFactory.newDiskCacheWriteProducer(newPartialDiskCacheProducer2);
            } else {
                newDiskCacheWriteProducer = this.producerFactory.newDiskCacheWriteProducer(inputProducer);
            }
            Intrinsics.checkNotNullExpressionValue(newDiskCacheWriteProducer, "if (partialImageCachingE…utProducer)\n            }");
            DiskCacheReadProducer newDiskCacheReadProducer2 = this.producerFactory.newDiskCacheReadProducer(newDiskCacheWriteProducer);
            Intrinsics.checkNotNullExpressionValue(newDiskCacheReadProducer2, "producerFactory.newDiskC…ducer(cacheWriteProducer)");
            return newDiskCacheReadProducer2;
        } finally {
            FrescoSystrace.endSection();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Producer<CloseableReference<CloseableImage>> newBitmapCacheGetToBitmapCacheSequence(Producer<CloseableReference<CloseableImage>> inputProducer) {
        BitmapMemoryCacheProducer newBitmapMemoryCacheProducer = this.producerFactory.newBitmapMemoryCacheProducer(inputProducer);
        Intrinsics.checkNotNullExpressionValue(newBitmapMemoryCacheProducer, "producerFactory.newBitma…heProducer(inputProducer)");
        BitmapMemoryCacheKeyMultiplexProducer newBitmapMemoryCacheKeyMultiplexProducer = this.producerFactory.newBitmapMemoryCacheKeyMultiplexProducer(newBitmapMemoryCacheProducer);
        Intrinsics.checkNotNullExpressionValue(newBitmapMemoryCacheKeyMultiplexProducer, "producerFactory.newBitma…itmapMemoryCacheProducer)");
        Producer<CloseableReference<CloseableImage>> newBackgroundThreadHandoffProducer = this.producerFactory.newBackgroundThreadHandoffProducer(newBitmapMemoryCacheKeyMultiplexProducer, this.threadHandoffProducerQueue);
        Intrinsics.checkNotNullExpressionValue(newBackgroundThreadHandoffProducer, "producerFactory.newBackg…readHandoffProducerQueue)");
        if (this.isEncodedMemoryCacheProbingEnabled || this.isDiskCacheProbingEnabled) {
            BitmapMemoryCacheGetProducer newBitmapMemoryCacheGetProducer = this.producerFactory.newBitmapMemoryCacheGetProducer(newBackgroundThreadHandoffProducer);
            Intrinsics.checkNotNullExpressionValue(newBitmapMemoryCacheGetProducer, "producerFactory.newBitma…er(threadHandoffProducer)");
            BitmapProbeProducer newBitmapProbeProducer = this.producerFactory.newBitmapProbeProducer(newBitmapMemoryCacheGetProducer);
            Intrinsics.checkNotNullExpressionValue(newBitmapProbeProducer, "producerFactory.newBitma…apMemoryCacheGetProducer)");
            return newBitmapProbeProducer;
        }
        BitmapMemoryCacheGetProducer newBitmapMemoryCacheGetProducer2 = this.producerFactory.newBitmapMemoryCacheGetProducer(newBackgroundThreadHandoffProducer);
        Intrinsics.checkNotNullExpressionValue(newBitmapMemoryCacheGetProducer2, "producerFactory.newBitma…er(threadHandoffProducer)");
        return newBitmapMemoryCacheGetProducer2;
    }

    private final Producer<EncodedImage> newLocalTransformationsSequence(Producer<EncodedImage> inputProducer, ThumbnailProducer<EncodedImage>[] thumbnailProducers) {
        AddImageTransformMetaDataProducer newAddImageTransformMetaDataProducer = ProducerFactory.newAddImageTransformMetaDataProducer(inputProducer);
        Intrinsics.checkNotNullExpressionValue(newAddImageTransformMetaDataProducer, "newAddImageTransformMeta…taProducer(inputProducer)");
        ResizeAndRotateProducer newResizeAndRotateProducer = this.producerFactory.newResizeAndRotateProducer(newAddImageTransformMetaDataProducer, true, this.imageTranscoderFactory);
        Intrinsics.checkNotNullExpressionValue(newResizeAndRotateProducer, "producerFactory.newResiz…, imageTranscoderFactory)");
        ThrottlingProducer newThrottlingProducer = this.producerFactory.newThrottlingProducer(newResizeAndRotateProducer);
        Intrinsics.checkNotNullExpressionValue(newThrottlingProducer, "producerFactory.newThrot…ducer(localImageProducer)");
        BranchOnSeparateImagesProducer newBranchOnSeparateImagesProducer = ProducerFactory.newBranchOnSeparateImagesProducer(newLocalThumbnailProducer(thumbnailProducers), newThrottlingProducer);
        Intrinsics.checkNotNullExpressionValue(newBranchOnSeparateImagesProducer, "newBranchOnSeparateImage…lImageThrottlingProducer)");
        return newBranchOnSeparateImagesProducer;
    }

    private final Producer<EncodedImage> newLocalThumbnailProducer(ThumbnailProducer<EncodedImage>[] thumbnailProducers) {
        ThumbnailBranchProducer newThumbnailBranchProducer = this.producerFactory.newThumbnailBranchProducer(thumbnailProducers);
        Intrinsics.checkNotNullExpressionValue(newThumbnailBranchProducer, "producerFactory.newThumb…ducer(thumbnailProducers)");
        ResizeAndRotateProducer newResizeAndRotateProducer = this.producerFactory.newResizeAndRotateProducer(newThumbnailBranchProducer, true, this.imageTranscoderFactory);
        Intrinsics.checkNotNullExpressionValue(newResizeAndRotateProducer, "producerFactory.newResiz…, imageTranscoderFactory)");
        return newResizeAndRotateProducer;
    }

    private final synchronized Producer<CloseableReference<CloseableImage>> getPostprocessorSequence(Producer<CloseableReference<CloseableImage>> inputProducer) {
        PostprocessedBitmapMemoryCacheProducer postprocessedBitmapMemoryCacheProducer;
        postprocessedBitmapMemoryCacheProducer = this.postprocessorSequences.get(inputProducer);
        if (postprocessedBitmapMemoryCacheProducer == null) {
            PostprocessorProducer newPostprocessorProducer = this.producerFactory.newPostprocessorProducer(inputProducer);
            Intrinsics.checkNotNullExpressionValue(newPostprocessorProducer, "producerFactory.newPostp…orProducer(inputProducer)");
            postprocessedBitmapMemoryCacheProducer = this.producerFactory.newPostprocessorBitmapMemoryCacheProducer(newPostprocessorProducer);
            this.postprocessorSequences.put(inputProducer, postprocessedBitmapMemoryCacheProducer);
        }
        return postprocessedBitmapMemoryCacheProducer;
    }

    private final synchronized Producer<Void> getDecodedImagePrefetchSequence(Producer<CloseableReference<CloseableImage>> inputProducer) {
        SwallowResultProducer swallowResultProducer;
        swallowResultProducer = this.closeableImagePrefetchSequences.get(inputProducer);
        if (swallowResultProducer == null) {
            swallowResultProducer = this.producerFactory.newSwallowResultProducer(inputProducer);
            this.closeableImagePrefetchSequences.put(inputProducer, swallowResultProducer);
        }
        return swallowResultProducer;
    }

    private final synchronized Producer<CloseableReference<CloseableImage>> getBitmapPrepareSequence(Producer<CloseableReference<CloseableImage>> inputProducer) {
        BitmapPrepareProducer bitmapPrepareProducer;
        bitmapPrepareProducer = this.bitmapPrepareSequences.get(inputProducer);
        if (bitmapPrepareProducer == null) {
            bitmapPrepareProducer = this.producerFactory.newBitmapPrepareProducer(inputProducer);
            this.bitmapPrepareSequences.put(inputProducer, bitmapPrepareProducer);
        }
        return bitmapPrepareProducer;
    }

    private final synchronized Producer<CloseableReference<CloseableImage>> getDelaySequence(Producer<CloseableReference<CloseableImage>> inputProducer) {
        DelayProducer newDelayProducer;
        newDelayProducer = this.producerFactory.newDelayProducer(inputProducer);
        Intrinsics.checkNotNullExpressionValue(newDelayProducer, "producerFactory.newDelayProducer(inputProducer)");
        return newDelayProducer;
    }

    /* compiled from: ProducerSequenceFactory.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0002J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0002¨\u0006\u000b"}, d2 = {"Lcom/facebook/imagepipeline/core/ProducerSequenceFactory$Companion;", "", "()V", "getShortenedUriString", "", "uri", "Landroid/net/Uri;", "validateEncodedImageRequest", "", "imageRequest", "Lcom/facebook/imagepipeline/request/ImageRequest;", "imagepipeline_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void validateEncodedImageRequest(ImageRequest imageRequest) {
            Preconditions.checkArgument(Boolean.valueOf(imageRequest.getLowestPermittedRequestLevel().getValue() <= ImageRequest.RequestLevel.ENCODED_MEMORY_CACHE.getValue()));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final String getShortenedUriString(Uri uri) {
            String uri2 = uri.toString();
            Intrinsics.checkNotNullExpressionValue(uri2, "uri.toString()");
            if (uri2.length() <= 30) {
                return uri2;
            }
            StringBuilder sb = new StringBuilder();
            String substring = uri2.substring(0, 30);
            Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String…ing(startIndex, endIndex)");
            sb.append(substring);
            sb.append("...");
            return sb.toString();
        }
    }
}
