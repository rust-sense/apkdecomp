package expo.modules.image;

import androidx.exifinterface.media.ExifInterface;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.RequestOptions;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: GlideExtensions.kt */
@Metadata(d1 = {"\u0000*\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a&\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u001ab\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0006*\b\u0012\u0004\u0012\u0002H\u00020\u00012\b\u0010\u0007\u001a\u0004\u0018\u0001H\u00062)\u0010\b\u001a%\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u0001\u0012\u0004\u0012\u0002H\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00010\t¢\u0006\u0002\b\nH\u0086\bø\u0001\u0000¢\u0006\u0002\u0010\u000b\u001aI\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0006\u0010\f\u001a\u00020\r2#\u0010\b\u001a\u001f\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00010\u000e¢\u0006\u0002\b\n\u001aD\u0010\u0005\u001a\u00020\u0004\"\u0004\b\u0000\u0010\u0002*\u00020\u00042\b\u0010\u0007\u001a\u0004\u0018\u0001H\u00022\u001d\u0010\b\u001a\u0019\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u00040\t¢\u0006\u0002\b\nH\u0086\bø\u0001\u0000¢\u0006\u0002\u0010\u000f\u001a1\u0010\u0005\u001a\u00020\u0004*\u00020\u00042\u0006\u0010\f\u001a\u00020\r2\u0017\u0010\b\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u000e¢\u0006\u0002\b\nH\u0086\bø\u0001\u0000\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u0010"}, d2 = {"apply", "Lcom/bumptech/glide/RequestBuilder;", ExifInterface.GPS_DIRECTION_TRUE, "options", "Lcom/bumptech/glide/request/RequestOptions;", "customize", "P", "value", "block", "Lkotlin/Function2;", "Lkotlin/ExtensionFunctionType;", "(Lcom/bumptech/glide/RequestBuilder;Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)Lcom/bumptech/glide/RequestBuilder;", "when", "", "Lkotlin/Function1;", "(Lcom/bumptech/glide/request/RequestOptions;Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)Lcom/bumptech/glide/request/RequestOptions;", "expo-image_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class GlideExtensionsKt {
    public static final <T> RequestBuilder<T> customize(RequestBuilder<T> requestBuilder, boolean z, Function1<? super RequestBuilder<T>, ? extends RequestBuilder<T>> block) {
        Intrinsics.checkNotNullParameter(requestBuilder, "<this>");
        Intrinsics.checkNotNullParameter(block, "block");
        return !z ? requestBuilder : block.invoke(requestBuilder);
    }

    public static final <T, P> RequestBuilder<T> customize(RequestBuilder<T> requestBuilder, P p, Function2<? super RequestBuilder<T>, ? super P, ? extends RequestBuilder<T>> block) {
        Intrinsics.checkNotNullParameter(requestBuilder, "<this>");
        Intrinsics.checkNotNullParameter(block, "block");
        return p == null ? requestBuilder : block.invoke(requestBuilder, p);
    }

    public static final RequestOptions customize(RequestOptions requestOptions, boolean z, Function1<? super RequestOptions, ? extends RequestOptions> block) {
        Intrinsics.checkNotNullParameter(requestOptions, "<this>");
        Intrinsics.checkNotNullParameter(block, "block");
        return !z ? requestOptions : block.invoke(requestOptions);
    }

    public static final <T> RequestOptions customize(RequestOptions requestOptions, T t, Function2<? super RequestOptions, ? super T, ? extends RequestOptions> block) {
        Intrinsics.checkNotNullParameter(requestOptions, "<this>");
        Intrinsics.checkNotNullParameter(block, "block");
        return t == null ? requestOptions : block.invoke(requestOptions, t);
    }

    public static final <T> RequestBuilder<T> apply(RequestBuilder<T> requestBuilder, RequestOptions requestOptions) {
        Intrinsics.checkNotNullParameter(requestBuilder, "<this>");
        if (requestOptions == null) {
            return requestBuilder;
        }
        RequestBuilder<T> apply = requestBuilder.apply((BaseRequestOptions<?>) requestOptions);
        Intrinsics.checkNotNullExpressionValue(apply, "apply(...)");
        return apply;
    }
}
