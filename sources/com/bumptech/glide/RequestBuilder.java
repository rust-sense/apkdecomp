package com.bumptech.glide;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.widget.ImageView;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.ErrorRequestCoordinator;
import com.bumptech.glide.request.FutureTarget;
import com.bumptech.glide.request.Request;
import com.bumptech.glide.request.RequestCoordinator;
import com.bumptech.glide.request.RequestFutureTarget;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.SingleRequest;
import com.bumptech.glide.request.ThumbnailRequestCoordinator;
import com.bumptech.glide.request.target.PreloadTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.target.ViewTarget;
import com.bumptech.glide.signature.AndroidResourceSignature;
import com.bumptech.glide.util.Executors;
import com.bumptech.glide.util.Preconditions;
import com.bumptech.glide.util.Util;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executor;

/* loaded from: classes.dex */
public class RequestBuilder<TranscodeType> extends BaseRequestOptions<RequestBuilder<TranscodeType>> implements Cloneable, ModelTypes<RequestBuilder<TranscodeType>> {
    protected static final RequestOptions DOWNLOAD_ONLY_OPTIONS = new RequestOptions().diskCacheStrategy(DiskCacheStrategy.DATA).priority(Priority.LOW).skipMemoryCache(true);
    private final Context context;
    private RequestBuilder<TranscodeType> errorBuilder;
    private final Glide glide;
    private final GlideContext glideContext;
    private boolean isDefaultTransitionOptionsSet;
    private boolean isModelSet;
    private boolean isThumbnailBuilt;
    private Object model;
    private List<RequestListener<TranscodeType>> requestListeners;
    private final RequestManager requestManager;
    private Float thumbSizeMultiplier;
    private RequestBuilder<TranscodeType> thumbnailBuilder;
    private final Class<TranscodeType> transcodeClass;
    private TransitionOptions<?, ? super TranscodeType> transitionOptions;

    @Override // com.bumptech.glide.request.BaseRequestOptions
    public /* bridge */ /* synthetic */ BaseRequestOptions apply(BaseRequestOptions baseRequestOptions) {
        return apply((BaseRequestOptions<?>) baseRequestOptions);
    }

    protected RequestBuilder(Glide glide, RequestManager requestManager, Class<TranscodeType> cls, Context context) {
        this.isDefaultTransitionOptionsSet = true;
        this.glide = glide;
        this.requestManager = requestManager;
        this.transcodeClass = cls;
        this.context = context;
        this.transitionOptions = requestManager.getDefaultTransitionOptions(cls);
        this.glideContext = glide.getGlideContext();
        initRequestListeners(requestManager.getDefaultRequestListeners());
        apply((BaseRequestOptions<?>) requestManager.getDefaultRequestOptions());
    }

    protected RequestBuilder(Class<TranscodeType> cls, RequestBuilder<?> requestBuilder) {
        this(requestBuilder.glide, requestBuilder.requestManager, cls, requestBuilder.context);
        this.model = requestBuilder.model;
        this.isModelSet = requestBuilder.isModelSet;
        apply((BaseRequestOptions<?>) requestBuilder);
    }

    private void initRequestListeners(List<RequestListener<Object>> list) {
        Iterator<RequestListener<Object>> it = list.iterator();
        while (it.hasNext()) {
            addListener((RequestListener) it.next());
        }
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    public RequestBuilder<TranscodeType> apply(BaseRequestOptions<?> baseRequestOptions) {
        Preconditions.checkNotNull(baseRequestOptions);
        return (RequestBuilder) super.apply(baseRequestOptions);
    }

    public RequestBuilder<TranscodeType> transition(TransitionOptions<?, ? super TranscodeType> transitionOptions) {
        if (isAutoCloneEnabled()) {
            return mo192clone().transition(transitionOptions);
        }
        this.transitionOptions = (TransitionOptions) Preconditions.checkNotNull(transitionOptions);
        this.isDefaultTransitionOptionsSet = false;
        return selfOrThrowIfLocked();
    }

    public RequestBuilder<TranscodeType> listener(RequestListener<TranscodeType> requestListener) {
        if (isAutoCloneEnabled()) {
            return mo192clone().listener(requestListener);
        }
        this.requestListeners = null;
        return addListener(requestListener);
    }

    public RequestBuilder<TranscodeType> addListener(RequestListener<TranscodeType> requestListener) {
        if (isAutoCloneEnabled()) {
            return mo192clone().addListener(requestListener);
        }
        if (requestListener != null) {
            if (this.requestListeners == null) {
                this.requestListeners = new ArrayList();
            }
            this.requestListeners.add(requestListener);
        }
        return selfOrThrowIfLocked();
    }

    public RequestBuilder<TranscodeType> error(RequestBuilder<TranscodeType> requestBuilder) {
        if (isAutoCloneEnabled()) {
            return mo192clone().error((RequestBuilder) requestBuilder);
        }
        this.errorBuilder = requestBuilder;
        return selfOrThrowIfLocked();
    }

    public RequestBuilder<TranscodeType> error(Object obj) {
        if (obj == null) {
            return error((RequestBuilder) null);
        }
        return error((RequestBuilder) cloneWithNullErrorAndThumbnail().load(obj));
    }

    private RequestBuilder<TranscodeType> cloneWithNullErrorAndThumbnail() {
        return mo192clone().error((RequestBuilder) null).thumbnail((RequestBuilder) null);
    }

    public RequestBuilder<TranscodeType> thumbnail(RequestBuilder<TranscodeType> requestBuilder) {
        if (isAutoCloneEnabled()) {
            return mo192clone().thumbnail(requestBuilder);
        }
        this.thumbnailBuilder = requestBuilder;
        return selfOrThrowIfLocked();
    }

    public RequestBuilder<TranscodeType> thumbnail(RequestBuilder<TranscodeType>... requestBuilderArr) {
        if (requestBuilderArr == null || requestBuilderArr.length == 0) {
            return thumbnail((RequestBuilder) null);
        }
        return thumbnail(Arrays.asList(requestBuilderArr));
    }

    public RequestBuilder<TranscodeType> thumbnail(List<RequestBuilder<TranscodeType>> list) {
        RequestBuilder<TranscodeType> requestBuilder = null;
        if (list == null || list.isEmpty()) {
            return thumbnail((RequestBuilder) null);
        }
        for (int size = list.size() - 1; size >= 0; size--) {
            RequestBuilder<TranscodeType> requestBuilder2 = list.get(size);
            if (requestBuilder2 != null) {
                requestBuilder = requestBuilder == null ? requestBuilder2 : requestBuilder2.thumbnail(requestBuilder);
            }
        }
        return thumbnail(requestBuilder);
    }

    @Deprecated
    public RequestBuilder<TranscodeType> thumbnail(float f) {
        if (isAutoCloneEnabled()) {
            return mo192clone().thumbnail(f);
        }
        if (f < 0.0f || f > 1.0f) {
            throw new IllegalArgumentException("sizeMultiplier must be between 0 and 1");
        }
        this.thumbSizeMultiplier = Float.valueOf(f);
        return selfOrThrowIfLocked();
    }

    @Override // com.bumptech.glide.ModelTypes
    public RequestBuilder<TranscodeType> load(Object obj) {
        return loadGeneric(obj);
    }

    private RequestBuilder<TranscodeType> loadGeneric(Object obj) {
        if (isAutoCloneEnabled()) {
            return mo192clone().loadGeneric(obj);
        }
        this.model = obj;
        this.isModelSet = true;
        return selfOrThrowIfLocked();
    }

    @Override // com.bumptech.glide.ModelTypes
    public RequestBuilder<TranscodeType> load(Bitmap bitmap) {
        return loadGeneric(bitmap).apply((BaseRequestOptions<?>) RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE));
    }

    @Override // com.bumptech.glide.ModelTypes
    public RequestBuilder<TranscodeType> load(Drawable drawable) {
        return loadGeneric(drawable).apply((BaseRequestOptions<?>) RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE));
    }

    @Override // com.bumptech.glide.ModelTypes
    public RequestBuilder<TranscodeType> load(String str) {
        return loadGeneric(str);
    }

    @Override // com.bumptech.glide.ModelTypes
    public RequestBuilder<TranscodeType> load(Uri uri) {
        return loadGeneric(uri);
    }

    @Override // com.bumptech.glide.ModelTypes
    public RequestBuilder<TranscodeType> load(File file) {
        return loadGeneric(file);
    }

    @Override // com.bumptech.glide.ModelTypes
    public RequestBuilder<TranscodeType> load(Integer num) {
        return loadGeneric(num).apply((BaseRequestOptions<?>) RequestOptions.signatureOf(AndroidResourceSignature.obtain(this.context)));
    }

    @Override // com.bumptech.glide.ModelTypes
    @Deprecated
    public RequestBuilder<TranscodeType> load(URL url) {
        return loadGeneric(url);
    }

    @Override // com.bumptech.glide.ModelTypes
    public RequestBuilder<TranscodeType> load(byte[] bArr) {
        RequestBuilder<TranscodeType> loadGeneric = loadGeneric(bArr);
        if (!loadGeneric.isDiskCacheStrategySet()) {
            loadGeneric = loadGeneric.apply((BaseRequestOptions<?>) RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE));
        }
        return !loadGeneric.isSkipMemoryCacheSet() ? loadGeneric.apply((BaseRequestOptions<?>) RequestOptions.skipMemoryCacheOf(true)) : loadGeneric;
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    /* renamed from: clone, reason: merged with bridge method [inline-methods] */
    public RequestBuilder<TranscodeType> mo192clone() {
        RequestBuilder<TranscodeType> requestBuilder = (RequestBuilder) super.mo192clone();
        requestBuilder.transitionOptions = (TransitionOptions<?, ? super TranscodeType>) requestBuilder.transitionOptions.m193clone();
        if (requestBuilder.requestListeners != null) {
            requestBuilder.requestListeners = new ArrayList(requestBuilder.requestListeners);
        }
        RequestBuilder<TranscodeType> requestBuilder2 = requestBuilder.thumbnailBuilder;
        if (requestBuilder2 != null) {
            requestBuilder.thumbnailBuilder = requestBuilder2.mo192clone();
        }
        RequestBuilder<TranscodeType> requestBuilder3 = requestBuilder.errorBuilder;
        if (requestBuilder3 != null) {
            requestBuilder.errorBuilder = requestBuilder3.mo192clone();
        }
        return requestBuilder;
    }

    public <Y extends Target<TranscodeType>> Y into(Y y) {
        return (Y) into(y, null, Executors.mainThreadExecutor());
    }

    <Y extends Target<TranscodeType>> Y into(Y y, RequestListener<TranscodeType> requestListener, Executor executor) {
        return (Y) into(y, requestListener, this, executor);
    }

    private <Y extends Target<TranscodeType>> Y into(Y y, RequestListener<TranscodeType> requestListener, BaseRequestOptions<?> baseRequestOptions, Executor executor) {
        Preconditions.checkNotNull(y);
        if (!this.isModelSet) {
            throw new IllegalArgumentException("You must call #load() before calling #into()");
        }
        Request buildRequest = buildRequest(y, requestListener, baseRequestOptions, executor);
        Request request = y.getRequest();
        if (buildRequest.isEquivalentTo(request) && !isSkipMemoryCacheWithCompletePreviousRequest(baseRequestOptions, request)) {
            if (!((Request) Preconditions.checkNotNull(request)).isRunning()) {
                request.begin();
            }
            return y;
        }
        this.requestManager.clear((Target<?>) y);
        y.setRequest(buildRequest);
        this.requestManager.track(y, buildRequest);
        return y;
    }

    private boolean isSkipMemoryCacheWithCompletePreviousRequest(BaseRequestOptions<?> baseRequestOptions, Request request) {
        return !baseRequestOptions.isMemoryCacheable() && request.isComplete();
    }

    public ViewTarget<ImageView, TranscodeType> into(ImageView imageView) {
        RequestBuilder<TranscodeType> requestBuilder;
        Util.assertMainThread();
        Preconditions.checkNotNull(imageView);
        if (!isTransformationSet() && isTransformationAllowed() && imageView.getScaleType() != null) {
            switch (AnonymousClass1.$SwitchMap$android$widget$ImageView$ScaleType[imageView.getScaleType().ordinal()]) {
                case 1:
                    requestBuilder = mo192clone().optionalCenterCrop();
                    break;
                case 2:
                    requestBuilder = mo192clone().optionalCenterInside();
                    break;
                case 3:
                case 4:
                case 5:
                    requestBuilder = mo192clone().optionalFitCenter();
                    break;
                case 6:
                    requestBuilder = mo192clone().optionalCenterInside();
                    break;
            }
            return (ViewTarget) into(this.glideContext.buildImageViewTarget(imageView, this.transcodeClass), null, requestBuilder, Executors.mainThreadExecutor());
        }
        requestBuilder = this;
        return (ViewTarget) into(this.glideContext.buildImageViewTarget(imageView, this.transcodeClass), null, requestBuilder, Executors.mainThreadExecutor());
    }

    @Deprecated
    public FutureTarget<TranscodeType> into(int i, int i2) {
        return submit(i, i2);
    }

    public FutureTarget<TranscodeType> submit() {
        return submit(Integer.MIN_VALUE, Integer.MIN_VALUE);
    }

    public FutureTarget<TranscodeType> submit(int i, int i2) {
        RequestFutureTarget requestFutureTarget = new RequestFutureTarget(i, i2);
        return (FutureTarget) into(requestFutureTarget, requestFutureTarget, Executors.directExecutor());
    }

    public Target<TranscodeType> preload(int i, int i2) {
        return into((RequestBuilder<TranscodeType>) PreloadTarget.obtain(this.requestManager, i, i2));
    }

    public Target<TranscodeType> preload() {
        return preload(Integer.MIN_VALUE, Integer.MIN_VALUE);
    }

    @Deprecated
    public <Y extends Target<File>> Y downloadOnly(Y y) {
        return (Y) getDownloadOnlyRequest().into((RequestBuilder<File>) y);
    }

    @Deprecated
    public FutureTarget<File> downloadOnly(int i, int i2) {
        return getDownloadOnlyRequest().submit(i, i2);
    }

    protected RequestBuilder<File> getDownloadOnlyRequest() {
        return new RequestBuilder(File.class, this).apply((BaseRequestOptions<?>) DOWNLOAD_ONLY_OPTIONS);
    }

    /* renamed from: com.bumptech.glide.RequestBuilder$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$android$widget$ImageView$ScaleType;
        static final /* synthetic */ int[] $SwitchMap$com$bumptech$glide$Priority;

        static {
            int[] iArr = new int[Priority.values().length];
            $SwitchMap$com$bumptech$glide$Priority = iArr;
            try {
                iArr[Priority.LOW.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$bumptech$glide$Priority[Priority.NORMAL.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$bumptech$glide$Priority[Priority.HIGH.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$bumptech$glide$Priority[Priority.IMMEDIATE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            int[] iArr2 = new int[ImageView.ScaleType.values().length];
            $SwitchMap$android$widget$ImageView$ScaleType = iArr2;
            try {
                iArr2[ImageView.ScaleType.CENTER_CROP.ordinal()] = 1;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$android$widget$ImageView$ScaleType[ImageView.ScaleType.CENTER_INSIDE.ordinal()] = 2;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$android$widget$ImageView$ScaleType[ImageView.ScaleType.FIT_CENTER.ordinal()] = 3;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$android$widget$ImageView$ScaleType[ImageView.ScaleType.FIT_START.ordinal()] = 4;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$android$widget$ImageView$ScaleType[ImageView.ScaleType.FIT_END.ordinal()] = 5;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$android$widget$ImageView$ScaleType[ImageView.ScaleType.FIT_XY.ordinal()] = 6;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$android$widget$ImageView$ScaleType[ImageView.ScaleType.CENTER.ordinal()] = 7;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$android$widget$ImageView$ScaleType[ImageView.ScaleType.MATRIX.ordinal()] = 8;
            } catch (NoSuchFieldError unused12) {
            }
        }
    }

    private Priority getThumbnailPriority(Priority priority) {
        int i = AnonymousClass1.$SwitchMap$com$bumptech$glide$Priority[priority.ordinal()];
        if (i == 1) {
            return Priority.NORMAL;
        }
        if (i == 2) {
            return Priority.HIGH;
        }
        if (i == 3 || i == 4) {
            return Priority.IMMEDIATE;
        }
        throw new IllegalArgumentException("unknown priority: " + getPriority());
    }

    private Request buildRequest(Target<TranscodeType> target, RequestListener<TranscodeType> requestListener, BaseRequestOptions<?> baseRequestOptions, Executor executor) {
        return buildRequestRecursive(new Object(), target, requestListener, null, this.transitionOptions, baseRequestOptions.getPriority(), baseRequestOptions.getOverrideWidth(), baseRequestOptions.getOverrideHeight(), baseRequestOptions, executor);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private Request buildRequestRecursive(Object obj, Target<TranscodeType> target, RequestListener<TranscodeType> requestListener, RequestCoordinator requestCoordinator, TransitionOptions<?, ? super TranscodeType> transitionOptions, Priority priority, int i, int i2, BaseRequestOptions<?> baseRequestOptions, Executor executor) {
        RequestCoordinator requestCoordinator2;
        RequestCoordinator requestCoordinator3;
        if (this.errorBuilder != null) {
            requestCoordinator3 = new ErrorRequestCoordinator(obj, requestCoordinator);
            requestCoordinator2 = requestCoordinator3;
        } else {
            requestCoordinator2 = null;
            requestCoordinator3 = requestCoordinator;
        }
        Request buildThumbnailRequestRecursive = buildThumbnailRequestRecursive(obj, target, requestListener, requestCoordinator3, transitionOptions, priority, i, i2, baseRequestOptions, executor);
        if (requestCoordinator2 == null) {
            return buildThumbnailRequestRecursive;
        }
        int overrideWidth = this.errorBuilder.getOverrideWidth();
        int overrideHeight = this.errorBuilder.getOverrideHeight();
        if (Util.isValidDimensions(i, i2) && !this.errorBuilder.isValidOverride()) {
            overrideWidth = baseRequestOptions.getOverrideWidth();
            overrideHeight = baseRequestOptions.getOverrideHeight();
        }
        RequestBuilder<TranscodeType> requestBuilder = this.errorBuilder;
        ErrorRequestCoordinator errorRequestCoordinator = requestCoordinator2;
        errorRequestCoordinator.setRequests(buildThumbnailRequestRecursive, requestBuilder.buildRequestRecursive(obj, target, requestListener, errorRequestCoordinator, requestBuilder.transitionOptions, requestBuilder.getPriority(), overrideWidth, overrideHeight, this.errorBuilder, executor));
        return errorRequestCoordinator;
    }

    /* JADX WARN: Type inference failed for: r0v5, types: [com.bumptech.glide.request.BaseRequestOptions] */
    private Request buildThumbnailRequestRecursive(Object obj, Target<TranscodeType> target, RequestListener<TranscodeType> requestListener, RequestCoordinator requestCoordinator, TransitionOptions<?, ? super TranscodeType> transitionOptions, Priority priority, int i, int i2, BaseRequestOptions<?> baseRequestOptions, Executor executor) {
        Priority thumbnailPriority;
        RequestBuilder<TranscodeType> requestBuilder = this.thumbnailBuilder;
        if (requestBuilder == null) {
            if (this.thumbSizeMultiplier != null) {
                ThumbnailRequestCoordinator thumbnailRequestCoordinator = new ThumbnailRequestCoordinator(obj, requestCoordinator);
                thumbnailRequestCoordinator.setRequests(obtainRequest(obj, target, requestListener, baseRequestOptions, thumbnailRequestCoordinator, transitionOptions, priority, i, i2, executor), obtainRequest(obj, target, requestListener, baseRequestOptions.mo192clone().sizeMultiplier(this.thumbSizeMultiplier.floatValue()), thumbnailRequestCoordinator, transitionOptions, getThumbnailPriority(priority), i, i2, executor));
                return thumbnailRequestCoordinator;
            }
            return obtainRequest(obj, target, requestListener, baseRequestOptions, requestCoordinator, transitionOptions, priority, i, i2, executor);
        }
        if (this.isThumbnailBuilt) {
            throw new IllegalStateException("You cannot use a request as both the main request and a thumbnail, consider using clone() on the request(s) passed to thumbnail()");
        }
        TransitionOptions<?, ? super TranscodeType> transitionOptions2 = requestBuilder.isDefaultTransitionOptionsSet ? transitionOptions : requestBuilder.transitionOptions;
        if (requestBuilder.isPrioritySet()) {
            thumbnailPriority = this.thumbnailBuilder.getPriority();
        } else {
            thumbnailPriority = getThumbnailPriority(priority);
        }
        Priority priority2 = thumbnailPriority;
        int overrideWidth = this.thumbnailBuilder.getOverrideWidth();
        int overrideHeight = this.thumbnailBuilder.getOverrideHeight();
        if (Util.isValidDimensions(i, i2) && !this.thumbnailBuilder.isValidOverride()) {
            overrideWidth = baseRequestOptions.getOverrideWidth();
            overrideHeight = baseRequestOptions.getOverrideHeight();
        }
        ThumbnailRequestCoordinator thumbnailRequestCoordinator2 = new ThumbnailRequestCoordinator(obj, requestCoordinator);
        Request obtainRequest = obtainRequest(obj, target, requestListener, baseRequestOptions, thumbnailRequestCoordinator2, transitionOptions, priority, i, i2, executor);
        this.isThumbnailBuilt = true;
        RequestBuilder<TranscodeType> requestBuilder2 = this.thumbnailBuilder;
        Request buildRequestRecursive = requestBuilder2.buildRequestRecursive(obj, target, requestListener, thumbnailRequestCoordinator2, transitionOptions2, priority2, overrideWidth, overrideHeight, requestBuilder2, executor);
        this.isThumbnailBuilt = false;
        thumbnailRequestCoordinator2.setRequests(obtainRequest, buildRequestRecursive);
        return thumbnailRequestCoordinator2;
    }

    private Request obtainRequest(Object obj, Target<TranscodeType> target, RequestListener<TranscodeType> requestListener, BaseRequestOptions<?> baseRequestOptions, RequestCoordinator requestCoordinator, TransitionOptions<?, ? super TranscodeType> transitionOptions, Priority priority, int i, int i2, Executor executor) {
        Context context = this.context;
        GlideContext glideContext = this.glideContext;
        return SingleRequest.obtain(context, glideContext, obj, this.model, this.transcodeClass, baseRequestOptions, i, i2, priority, target, requestListener, this.requestListeners, requestCoordinator, glideContext.getEngine(), transitionOptions.getTransitionFactory(), executor);
    }
}
