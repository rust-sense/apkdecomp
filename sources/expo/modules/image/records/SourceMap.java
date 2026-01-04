package expo.modules.image.records;

import android.content.Context;
import android.net.Uri;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.Headers;
import com.bumptech.glide.load.model.LazyHeaders;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.signature.ApplicationVersionSignature;
import com.facebook.common.util.UriUtil;
import expo.modules.image.GlideBlurhashModel;
import expo.modules.image.GlideModel;
import expo.modules.image.GlideRawModel;
import expo.modules.image.GlideThumbhashModel;
import expo.modules.image.GlideUriModel;
import expo.modules.image.GlideUrlModel;
import expo.modules.image.ResourceIdHelper;
import expo.modules.image.okhttp.GlideUrlWithCustomCacheKey;
import expo.modules.kotlin.records.Field;
import expo.modules.kotlin.records.Record;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: SourceMap.kt */
@Metadata(d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010$\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001BS\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u0012\u0016\b\u0002\u0010\t\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\n\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\fJ\u000b\u0010\"\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010#\u001a\u00020\u0005HÆ\u0003J\t\u0010$\u001a\u00020\u0005HÆ\u0003J\t\u0010%\u001a\u00020\bHÆ\u0003J\u0017\u0010&\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\nHÆ\u0003J\u000b\u0010'\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u001a\u0010(\u001a\u0004\u0018\u00010\u00182\u0006\u0010)\u001a\u00020\u00032\u0006\u0010*\u001a\u00020+H\u0002J\u0012\u0010,\u001a\u0004\u0018\u00010\u00182\u0006\u0010*\u001a\u00020+H\u0002JW\u0010-\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\b2\u0016\b\u0002\u0010\t\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\n2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0017\u0010.\u001a\u0004\u0018\u00010/2\u0006\u0010*\u001a\u00020+H\u0000¢\u0006\u0002\b0J\u0015\u00101\u001a\u0002022\u0006\u0010*\u001a\u00020+H\u0000¢\u0006\u0002\b3J\u0013\u00104\u001a\u0002052\b\u00106\u001a\u0004\u0018\u000107HÖ\u0003J\b\u00108\u001a\u000209H\u0002J\t\u0010:\u001a\u00020\u0005HÖ\u0001J\u0006\u0010;\u001a\u000205J\b\u0010<\u001a\u000205H\u0002J\b\u0010=\u001a\u000205H\u0002J\b\u0010>\u001a\u000205H\u0002J\b\u0010?\u001a\u000205H\u0002J\b\u0010@\u001a\u000205H\u0002J\u0006\u0010A\u001a\u000205J\u0010\u0010B\u001a\u00020C2\u0006\u0010*\u001a\u00020+H\u0002J\t\u0010D\u001a\u00020\u0003HÖ\u0001R\u001e\u0010\u000b\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\r\u0010\u000e\u001a\u0004\b\u000f\u0010\u0010R*\u0010\t\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\n8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0011\u0010\u000e\u001a\u0004\b\u0012\u0010\u0013R\u001c\u0010\u0006\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0014\u0010\u000e\u001a\u0004\b\u0015\u0010\u0016R\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0019\u001a\u00020\b8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u001bR\u001c\u0010\u0007\u001a\u00020\b8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u001c\u0010\u000e\u001a\u0004\b\u001d\u0010\u001bR\u001e\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u001e\u0010\u000e\u001a\u0004\b\u001f\u0010\u0010R\u001c\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b \u0010\u000e\u001a\u0004\b!\u0010\u0016¨\u0006E"}, d2 = {"Lexpo/modules/image/records/SourceMap;", "Lexpo/modules/kotlin/records/Record;", "uri", "", "width", "", "height", "scale", "", "headers", "", "cacheKey", "(Ljava/lang/String;IIDLjava/util/Map;Ljava/lang/String;)V", "getCacheKey$annotations", "()V", "getCacheKey", "()Ljava/lang/String;", "getHeaders$annotations", "getHeaders", "()Ljava/util/Map;", "getHeight$annotations", "getHeight", "()I", "parsedUri", "Landroid/net/Uri;", "pixelCount", "getPixelCount$expo_image_release", "()D", "getScale$annotations", "getScale", "getUri$annotations", "getUri", "getWidth$annotations", "getWidth", "component1", "component2", "component3", "component4", "component5", "component6", "computeLocalUri", "stringUri", "context", "Landroid/content/Context;", "computeUri", "copy", "createGlideModel", "Lexpo/modules/image/GlideModel;", "createGlideModel$expo_image_release", "createOptions", "Lcom/bumptech/glide/request/RequestOptions;", "createOptions$expo_image_release", "equals", "", "other", "", "getCustomHeaders", "Lcom/bumptech/glide/load/model/Headers;", "hashCode", "isBlurhash", "isContentUrl", "isDataUrl", "isLocalFileUri", "isLocalResourceUri", "isResourceUri", "isThumbhash", "parseUri", "", "toString", "expo-image_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final /* data */ class SourceMap implements Record {
    private final String cacheKey;
    private final Map<String, String> headers;
    private final int height;
    private Uri parsedUri;
    private final double scale;
    private final String uri;
    private final int width;

    public SourceMap() {
        this(null, 0, 0, 0.0d, null, null, 63, null);
    }

    public static /* synthetic */ SourceMap copy$default(SourceMap sourceMap, String str, int i, int i2, double d, Map map, String str2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            str = sourceMap.uri;
        }
        if ((i3 & 2) != 0) {
            i = sourceMap.width;
        }
        int i4 = i;
        if ((i3 & 4) != 0) {
            i2 = sourceMap.height;
        }
        int i5 = i2;
        if ((i3 & 8) != 0) {
            d = sourceMap.scale;
        }
        double d2 = d;
        if ((i3 & 16) != 0) {
            map = sourceMap.headers;
        }
        Map map2 = map;
        if ((i3 & 32) != 0) {
            str2 = sourceMap.cacheKey;
        }
        return sourceMap.copy(str, i4, i5, d2, map2, str2);
    }

    @Field
    public static /* synthetic */ void getCacheKey$annotations() {
    }

    @Field
    public static /* synthetic */ void getHeaders$annotations() {
    }

    @Field
    public static /* synthetic */ void getHeight$annotations() {
    }

    @Field
    public static /* synthetic */ void getScale$annotations() {
    }

    @Field
    public static /* synthetic */ void getUri$annotations() {
    }

    @Field
    public static /* synthetic */ void getWidth$annotations() {
    }

    /* renamed from: component1, reason: from getter */
    public final String getUri() {
        return this.uri;
    }

    /* renamed from: component2, reason: from getter */
    public final int getWidth() {
        return this.width;
    }

    /* renamed from: component3, reason: from getter */
    public final int getHeight() {
        return this.height;
    }

    /* renamed from: component4, reason: from getter */
    public final double getScale() {
        return this.scale;
    }

    public final Map<String, String> component5() {
        return this.headers;
    }

    /* renamed from: component6, reason: from getter */
    public final String getCacheKey() {
        return this.cacheKey;
    }

    public final SourceMap copy(String uri, int width, int height, double scale, Map<String, String> headers, String cacheKey) {
        return new SourceMap(uri, width, height, scale, headers, cacheKey);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SourceMap)) {
            return false;
        }
        SourceMap sourceMap = (SourceMap) other;
        return Intrinsics.areEqual(this.uri, sourceMap.uri) && this.width == sourceMap.width && this.height == sourceMap.height && Double.compare(this.scale, sourceMap.scale) == 0 && Intrinsics.areEqual(this.headers, sourceMap.headers) && Intrinsics.areEqual(this.cacheKey, sourceMap.cacheKey);
    }

    public final String getCacheKey() {
        return this.cacheKey;
    }

    public final Map<String, String> getHeaders() {
        return this.headers;
    }

    public final int getHeight() {
        return this.height;
    }

    public final double getPixelCount$expo_image_release() {
        double d = this.width * this.height;
        double d2 = this.scale;
        return d * d2 * d2;
    }

    public final double getScale() {
        return this.scale;
    }

    public final String getUri() {
        return this.uri;
    }

    public final int getWidth() {
        return this.width;
    }

    public int hashCode() {
        String str = this.uri;
        int hashCode = (((((((str == null ? 0 : str.hashCode()) * 31) + this.width) * 31) + this.height) * 31) + SourceMap$$ExternalSyntheticBackport0.m(this.scale)) * 31;
        Map<String, String> map = this.headers;
        int hashCode2 = (hashCode + (map == null ? 0 : map.hashCode())) * 31;
        String str2 = this.cacheKey;
        return hashCode2 + (str2 != null ? str2.hashCode() : 0);
    }

    public String toString() {
        return "SourceMap(uri=" + this.uri + ", width=" + this.width + ", height=" + this.height + ", scale=" + this.scale + ", headers=" + this.headers + ", cacheKey=" + this.cacheKey + ")";
    }

    public SourceMap(String str, int i, int i2, double d, Map<String, String> map, String str2) {
        this.uri = str;
        this.width = i;
        this.height = i2;
        this.scale = d;
        this.headers = map;
        this.cacheKey = str2;
    }

    public /* synthetic */ SourceMap(String str, int i, int i2, double d, Map map, String str2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this((i3 & 1) != 0 ? null : str, (i3 & 2) != 0 ? 0 : i, (i3 & 4) == 0 ? i2 : 0, (i3 & 8) != 0 ? 1.0d : d, (i3 & 16) != 0 ? null : map, (i3 & 32) != 0 ? null : str2);
    }

    private final boolean isDataUrl() {
        String scheme;
        Uri uri = this.parsedUri;
        if (uri == null || (scheme = uri.getScheme()) == null) {
            return false;
        }
        return StringsKt.startsWith$default(scheme, "data", false, 2, (Object) null);
    }

    private final boolean isContentUrl() {
        String scheme;
        Uri uri = this.parsedUri;
        if (uri == null || (scheme = uri.getScheme()) == null) {
            return false;
        }
        return StringsKt.startsWith$default(scheme, UriUtil.LOCAL_CONTENT_SCHEME, false, 2, (Object) null);
    }

    private final boolean isResourceUri() {
        String scheme;
        Uri uri = this.parsedUri;
        if (uri == null || (scheme = uri.getScheme()) == null) {
            return false;
        }
        return StringsKt.startsWith$default(scheme, UriUtil.QUALIFIED_RESOURCE_SCHEME, false, 2, (Object) null);
    }

    private final boolean isLocalResourceUri() {
        String scheme;
        Uri uri = this.parsedUri;
        if (uri == null || (scheme = uri.getScheme()) == null) {
            return false;
        }
        return StringsKt.startsWith$default(scheme, UriUtil.LOCAL_RESOURCE_SCHEME, false, 2, (Object) null);
    }

    private final boolean isLocalFileUri() {
        String scheme;
        Uri uri = this.parsedUri;
        if (uri == null || (scheme = uri.getScheme()) == null) {
            return false;
        }
        return StringsKt.startsWith$default(scheme, "file", false, 2, (Object) null);
    }

    public final boolean isBlurhash() {
        String scheme;
        Uri uri = this.parsedUri;
        if (uri == null || (scheme = uri.getScheme()) == null) {
            return false;
        }
        return StringsKt.startsWith$default(scheme, "blurhash", false, 2, (Object) null);
    }

    public final boolean isThumbhash() {
        String scheme;
        Uri uri = this.parsedUri;
        if (uri == null || (scheme = uri.getScheme()) == null) {
            return false;
        }
        return StringsKt.startsWith$default(scheme, "thumbhash", false, 2, (Object) null);
    }

    private final void parseUri(Context context) {
        if (this.parsedUri == null) {
            this.parsedUri = computeUri(context);
        }
    }

    public final GlideModel createGlideModel$expo_image_release(Context context) {
        GlideUrlWithCustomCacheKey glideUrlWithCustomCacheKey;
        Intrinsics.checkNotNullParameter(context, "context");
        String str = this.uri;
        if (str == null || StringsKt.isBlank(str)) {
            return null;
        }
        parseUri(context);
        if (isContentUrl() || isDataUrl()) {
            return new GlideRawModel(this.uri);
        }
        if (isBlurhash()) {
            Uri uri = this.parsedUri;
            Intrinsics.checkNotNull(uri);
            return new GlideBlurhashModel(uri, this.width, this.height);
        }
        if (isThumbhash()) {
            Uri uri2 = this.parsedUri;
            Intrinsics.checkNotNull(uri2);
            return new GlideThumbhashModel(uri2);
        }
        if (isResourceUri()) {
            Uri uri3 = this.parsedUri;
            Intrinsics.checkNotNull(uri3);
            return new GlideUriModel(uri3);
        }
        if (isLocalResourceUri()) {
            Uri uri4 = this.parsedUri;
            Intrinsics.checkNotNull(uri4);
            String uri5 = uri4.toString();
            Intrinsics.checkNotNullExpressionValue(uri5, "toString(...)");
            Uri parse = Uri.parse(StringsKt.replace$default(uri5, "res:/", "android.resource://" + context.getPackageName() + "/", false, 4, (Object) null));
            Intrinsics.checkNotNullExpressionValue(parse, "parse(...)");
            return new GlideUriModel(parse);
        }
        if (isLocalFileUri()) {
            Uri uri6 = this.parsedUri;
            Intrinsics.checkNotNull(uri6);
            String uri7 = uri6.toString();
            Intrinsics.checkNotNullExpressionValue(uri7, "toString(...)");
            return new GlideRawModel(uri7);
        }
        if (this.cacheKey == null) {
            glideUrlWithCustomCacheKey = new GlideUrl(this.uri, getCustomHeaders());
        } else {
            glideUrlWithCustomCacheKey = new GlideUrlWithCustomCacheKey(this.uri, getCustomHeaders(), this.cacheKey);
        }
        return new GlideUrlModel(glideUrlWithCustomCacheKey);
    }

    public final RequestOptions createOptions$expo_image_release(Context context) {
        int i;
        Intrinsics.checkNotNullParameter(context, "context");
        parseUri(context);
        RequestOptions requestOptions = new RequestOptions();
        int i2 = this.width;
        if (i2 != 0 && (i = this.height) != 0) {
            double d = this.scale;
            requestOptions.override((int) (i2 * d), (int) (i * d));
        }
        if (isResourceUri()) {
            requestOptions.apply(RequestOptions.signatureOf(ApplicationVersionSignature.obtain(context)));
        }
        return requestOptions;
    }

    private final Headers getCustomHeaders() {
        if (this.headers == null) {
            Headers DEFAULT = LazyHeaders.DEFAULT;
            Intrinsics.checkNotNullExpressionValue(DEFAULT, "DEFAULT");
            return DEFAULT;
        }
        LazyHeaders.Builder builder = new LazyHeaders.Builder();
        for (Map.Entry<String, String> entry : this.headers.entrySet()) {
            builder.addHeader(entry.getKey(), entry.getValue());
        }
        LazyHeaders build = builder.build();
        Intrinsics.checkNotNullExpressionValue(build, "build(...)");
        return build;
    }

    private final Uri computeUri(Context context) {
        String str = this.uri;
        if (str == null) {
            return null;
        }
        try {
            Uri parse = Uri.parse(str);
            Intrinsics.checkNotNullExpressionValue(parse, "parse(...)");
            return parse.getScheme() == null ? computeLocalUri(str, context) : parse;
        } catch (Exception unused) {
            return computeLocalUri(str, context);
        }
    }

    private final Uri computeLocalUri(String stringUri, Context context) {
        return ResourceIdHelper.INSTANCE.getResourceUri(context, stringUri);
    }
}
