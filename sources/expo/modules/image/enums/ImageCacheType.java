package expo.modules.image.enums;

import com.bumptech.glide.load.DataSource;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: ImageCacheType.kt */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\u0081\u0002\u0018\u0000 \n2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\nB\u001b\b\u0002\u0012\u0012\u0010\u0002\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00040\u0003\"\u00020\u0004¢\u0006\u0002\u0010\u0005R\u0018\u0010\u0002\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\t¨\u0006\u000b"}, d2 = {"Lexpo/modules/image/enums/ImageCacheType;", "", "dataSources", "", "Lcom/bumptech/glide/load/DataSource;", "(Ljava/lang/String;I[Lcom/bumptech/glide/load/DataSource;)V", "[Lcom/bumptech/glide/load/DataSource;", "NONE", "DISK", "MEMORY", "Companion", "expo-image_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class ImageCacheType {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ ImageCacheType[] $VALUES;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE;
    private final DataSource[] dataSources;
    public static final ImageCacheType NONE = new ImageCacheType("NONE", 0, DataSource.LOCAL, DataSource.REMOTE);
    public static final ImageCacheType DISK = new ImageCacheType("DISK", 1, DataSource.DATA_DISK_CACHE, DataSource.RESOURCE_DISK_CACHE);
    public static final ImageCacheType MEMORY = new ImageCacheType("MEMORY", 2, DataSource.MEMORY_CACHE);

    private static final /* synthetic */ ImageCacheType[] $values() {
        return new ImageCacheType[]{NONE, DISK, MEMORY};
    }

    public static EnumEntries<ImageCacheType> getEntries() {
        return $ENTRIES;
    }

    public static ImageCacheType valueOf(String str) {
        return (ImageCacheType) Enum.valueOf(ImageCacheType.class, str);
    }

    public static ImageCacheType[] values() {
        return (ImageCacheType[]) $VALUES.clone();
    }

    private ImageCacheType(String str, int i, DataSource... dataSourceArr) {
        this.dataSources = dataSourceArr;
    }

    static {
        ImageCacheType[] $values = $values();
        $VALUES = $values;
        $ENTRIES = EnumEntriesKt.enumEntries($values);
        INSTANCE = new Companion(null);
    }

    /* compiled from: ImageCacheType.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lexpo/modules/image/enums/ImageCacheType$Companion;", "", "()V", "fromNativeValue", "Lexpo/modules/image/enums/ImageCacheType;", "value", "Lcom/bumptech/glide/load/DataSource;", "expo-image_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final ImageCacheType fromNativeValue(DataSource value) {
            Object obj;
            Intrinsics.checkNotNullParameter(value, "value");
            Iterator<E> it = ImageCacheType.getEntries().iterator();
            while (true) {
                if (!it.hasNext()) {
                    obj = null;
                    break;
                }
                obj = it.next();
                if (ArraysKt.contains(((ImageCacheType) obj).dataSources, value)) {
                    break;
                }
            }
            ImageCacheType imageCacheType = (ImageCacheType) obj;
            return imageCacheType == null ? ImageCacheType.NONE : imageCacheType;
        }
    }
}
