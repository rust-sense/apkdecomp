package expo.modules.image.records;

import expo.modules.kotlin.records.Field;
import expo.modules.kotlin.records.Record;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: events.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0016\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0086\b\u0018\u00002\u00020\u0001B/\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0005HÆ\u0003J\u000b\u0010\u001b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\u001c\u001a\u00020\tHÆ\u0003J=\u0010\u001d\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\b\u001a\u00020\tHÆ\u0001J\u0013\u0010\u001e\u001a\u00020\t2\b\u0010\u001f\u001a\u0004\u0018\u00010 HÖ\u0003J\t\u0010!\u001a\u00020\u0005HÖ\u0001J\t\u0010\"\u001a\u00020\u0003HÖ\u0001R\u001c\u0010\u0006\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000eR\u001c\u0010\b\u001a\u00020\t8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u000f\u0010\f\u001a\u0004\b\b\u0010\u0010R\u001e\u0010\u0007\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0011\u0010\f\u001a\u0004\b\u0012\u0010\u0013R\u001c\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0014\u0010\f\u001a\u0004\b\u0015\u0010\u0013R\u001c\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0016\u0010\f\u001a\u0004\b\u0017\u0010\u000e¨\u0006#"}, d2 = {"Lexpo/modules/image/records/ImageSource;", "Lexpo/modules/kotlin/records/Record;", "url", "", "width", "", "height", "mediaType", "isAnimated", "", "(Ljava/lang/String;IILjava/lang/String;Z)V", "getHeight$annotations", "()V", "getHeight", "()I", "isAnimated$annotations", "()Z", "getMediaType$annotations", "getMediaType", "()Ljava/lang/String;", "getUrl$annotations", "getUrl", "getWidth$annotations", "getWidth", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "other", "", "hashCode", "toString", "expo-image_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final /* data */ class ImageSource implements Record {
    private final int height;
    private final boolean isAnimated;
    private final String mediaType;
    private final String url;
    private final int width;

    public static /* synthetic */ ImageSource copy$default(ImageSource imageSource, String str, int i, int i2, String str2, boolean z, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            str = imageSource.url;
        }
        if ((i3 & 2) != 0) {
            i = imageSource.width;
        }
        int i4 = i;
        if ((i3 & 4) != 0) {
            i2 = imageSource.height;
        }
        int i5 = i2;
        if ((i3 & 8) != 0) {
            str2 = imageSource.mediaType;
        }
        String str3 = str2;
        if ((i3 & 16) != 0) {
            z = imageSource.isAnimated;
        }
        return imageSource.copy(str, i4, i5, str3, z);
    }

    @Field
    public static /* synthetic */ void getHeight$annotations() {
    }

    @Field
    public static /* synthetic */ void getMediaType$annotations() {
    }

    @Field
    public static /* synthetic */ void getUrl$annotations() {
    }

    @Field
    public static /* synthetic */ void getWidth$annotations() {
    }

    @Field
    public static /* synthetic */ void isAnimated$annotations() {
    }

    /* renamed from: component1, reason: from getter */
    public final String getUrl() {
        return this.url;
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
    public final String getMediaType() {
        return this.mediaType;
    }

    /* renamed from: component5, reason: from getter */
    public final boolean getIsAnimated() {
        return this.isAnimated;
    }

    public final ImageSource copy(String url, int width, int height, String mediaType, boolean isAnimated) {
        Intrinsics.checkNotNullParameter(url, "url");
        return new ImageSource(url, width, height, mediaType, isAnimated);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ImageSource)) {
            return false;
        }
        ImageSource imageSource = (ImageSource) other;
        return Intrinsics.areEqual(this.url, imageSource.url) && this.width == imageSource.width && this.height == imageSource.height && Intrinsics.areEqual(this.mediaType, imageSource.mediaType) && this.isAnimated == imageSource.isAnimated;
    }

    public final int getHeight() {
        return this.height;
    }

    public final String getMediaType() {
        return this.mediaType;
    }

    public final String getUrl() {
        return this.url;
    }

    public final int getWidth() {
        return this.width;
    }

    public int hashCode() {
        int hashCode = ((((this.url.hashCode() * 31) + this.width) * 31) + this.height) * 31;
        String str = this.mediaType;
        return ((hashCode + (str == null ? 0 : str.hashCode())) * 31) + SourceMap$$ExternalSyntheticBackport0.m(this.isAnimated);
    }

    public final boolean isAnimated() {
        return this.isAnimated;
    }

    public String toString() {
        return "ImageSource(url=" + this.url + ", width=" + this.width + ", height=" + this.height + ", mediaType=" + this.mediaType + ", isAnimated=" + this.isAnimated + ")";
    }

    public ImageSource(String url, int i, int i2, String str, boolean z) {
        Intrinsics.checkNotNullParameter(url, "url");
        this.url = url;
        this.width = i;
        this.height = i2;
        this.mediaType = str;
        this.isAnimated = z;
    }
}
