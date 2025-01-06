package expo.modules.kotlin.devtools.cdp;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: CdpNetworkTypes.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000b\b\u0086\u0081\u0002\u0018\u0000 \r2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\rB\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\f¨\u0006\u000e"}, d2 = {"Lexpo/modules/kotlin/devtools/cdp/ResourceType;", "", "value", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getValue", "()Ljava/lang/String;", "IMAGE", "MEDIA", "FONT", "SCRIPT", "FETCH", "OTHER", "Companion", "expo-modules-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class ResourceType {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ ResourceType[] $VALUES;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE;
    private final String value;
    public static final ResourceType IMAGE = new ResourceType("IMAGE", 0, "Image");
    public static final ResourceType MEDIA = new ResourceType("MEDIA", 1, "Media");
    public static final ResourceType FONT = new ResourceType("FONT", 2, "Font");
    public static final ResourceType SCRIPT = new ResourceType("SCRIPT", 3, "Script");
    public static final ResourceType FETCH = new ResourceType("FETCH", 4, "Fetch");
    public static final ResourceType OTHER = new ResourceType("OTHER", 5, "Other");

    private static final /* synthetic */ ResourceType[] $values() {
        return new ResourceType[]{IMAGE, MEDIA, FONT, SCRIPT, FETCH, OTHER};
    }

    public static EnumEntries<ResourceType> getEntries() {
        return $ENTRIES;
    }

    public static ResourceType valueOf(String str) {
        return (ResourceType) Enum.valueOf(ResourceType.class, str);
    }

    public static ResourceType[] values() {
        return (ResourceType[]) $VALUES.clone();
    }

    public final String getValue() {
        return this.value;
    }

    private ResourceType(String str, int i, String str2) {
        this.value = str2;
    }

    static {
        ResourceType[] $values = $values();
        $VALUES = $values;
        $ENTRIES = EnumEntriesKt.enumEntries($values);
        INSTANCE = new Companion(null);
    }

    /* compiled from: CdpNetworkTypes.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lexpo/modules/kotlin/devtools/cdp/ResourceType$Companion;", "", "()V", "fromMimeType", "Lexpo/modules/kotlin/devtools/cdp/ResourceType;", "mimeType", "", "expo-modules-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final ResourceType fromMimeType(String mimeType) {
            Intrinsics.checkNotNullParameter(mimeType, "mimeType");
            if (StringsKt.startsWith$default(mimeType, "image/", false, 2, (Object) null)) {
                return ResourceType.IMAGE;
            }
            if (StringsKt.startsWith$default(mimeType, "audio", false, 2, (Object) null) || StringsKt.startsWith$default(mimeType, "video", false, 2, (Object) null)) {
                return ResourceType.MEDIA;
            }
            return StringsKt.startsWith$default(mimeType, "font", false, 2, (Object) null) ? ResourceType.FONT : ResourceType.OTHER;
        }
    }
}
