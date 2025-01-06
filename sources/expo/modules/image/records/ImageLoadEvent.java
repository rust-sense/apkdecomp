package expo.modules.image.records;

import expo.modules.kotlin.records.Field;
import expo.modules.kotlin.records.Record;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: events.kt */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001J\t\u0010\u0017\u001a\u00020\u0003HÖ\u0001R\u001c\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0007\u0010\b\u001a\u0004\b\t\u0010\nR\u001c\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u000b\u0010\b\u001a\u0004\b\f\u0010\r¨\u0006\u0018"}, d2 = {"Lexpo/modules/image/records/ImageLoadEvent;", "Lexpo/modules/kotlin/records/Record;", "cacheType", "", "source", "Lexpo/modules/image/records/ImageSource;", "(Ljava/lang/String;Lexpo/modules/image/records/ImageSource;)V", "getCacheType$annotations", "()V", "getCacheType", "()Ljava/lang/String;", "getSource$annotations", "getSource", "()Lexpo/modules/image/records/ImageSource;", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "expo-image_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final /* data */ class ImageLoadEvent implements Record {
    private final String cacheType;
    private final ImageSource source;

    public static /* synthetic */ ImageLoadEvent copy$default(ImageLoadEvent imageLoadEvent, String str, ImageSource imageSource, int i, Object obj) {
        if ((i & 1) != 0) {
            str = imageLoadEvent.cacheType;
        }
        if ((i & 2) != 0) {
            imageSource = imageLoadEvent.source;
        }
        return imageLoadEvent.copy(str, imageSource);
    }

    @Field
    public static /* synthetic */ void getCacheType$annotations() {
    }

    @Field
    public static /* synthetic */ void getSource$annotations() {
    }

    /* renamed from: component1, reason: from getter */
    public final String getCacheType() {
        return this.cacheType;
    }

    /* renamed from: component2, reason: from getter */
    public final ImageSource getSource() {
        return this.source;
    }

    public final ImageLoadEvent copy(String cacheType, ImageSource source) {
        Intrinsics.checkNotNullParameter(cacheType, "cacheType");
        Intrinsics.checkNotNullParameter(source, "source");
        return new ImageLoadEvent(cacheType, source);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ImageLoadEvent)) {
            return false;
        }
        ImageLoadEvent imageLoadEvent = (ImageLoadEvent) other;
        return Intrinsics.areEqual(this.cacheType, imageLoadEvent.cacheType) && Intrinsics.areEqual(this.source, imageLoadEvent.source);
    }

    public final String getCacheType() {
        return this.cacheType;
    }

    public final ImageSource getSource() {
        return this.source;
    }

    public int hashCode() {
        return (this.cacheType.hashCode() * 31) + this.source.hashCode();
    }

    public String toString() {
        return "ImageLoadEvent(cacheType=" + this.cacheType + ", source=" + this.source + ")";
    }

    public ImageLoadEvent(String cacheType, ImageSource source) {
        Intrinsics.checkNotNullParameter(cacheType, "cacheType");
        Intrinsics.checkNotNullParameter(source, "source");
        this.cacheType = cacheType;
        this.source = source;
    }
}
