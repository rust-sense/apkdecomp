package expo.modules.filesystem;

import androidx.browser.trusted.sharing.ShareTarget;
import expo.modules.kotlin.types.Enumerable;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: FileSystemRecords.kt */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u00012\u00020\u0002B\u000f\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\n¨\u0006\u000b"}, d2 = {"Lexpo/modules/filesystem/HttpMethod;", "", "Lexpo/modules/kotlin/types/Enumerable;", "value", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getValue", "()Ljava/lang/String;", ShareTarget.METHOD_POST, "PUT", "PATCH", "expo-file-system_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class HttpMethod implements Enumerable {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ HttpMethod[] $VALUES;
    private final String value;
    public static final HttpMethod POST = new HttpMethod(ShareTarget.METHOD_POST, 0, ShareTarget.METHOD_POST);
    public static final HttpMethod PUT = new HttpMethod("PUT", 1, "PUT");
    public static final HttpMethod PATCH = new HttpMethod("PATCH", 2, "PATCH");

    private static final /* synthetic */ HttpMethod[] $values() {
        return new HttpMethod[]{POST, PUT, PATCH};
    }

    public static EnumEntries<HttpMethod> getEntries() {
        return $ENTRIES;
    }

    public static HttpMethod valueOf(String str) {
        return (HttpMethod) Enum.valueOf(HttpMethod.class, str);
    }

    public static HttpMethod[] values() {
        return (HttpMethod[]) $VALUES.clone();
    }

    public final String getValue() {
        return this.value;
    }

    private HttpMethod(String str, int i, String str2) {
        this.value = str2;
    }

    static {
        HttpMethod[] $values = $values();
        $VALUES = $values;
        $ENTRIES = EnumEntriesKt.enumEntries($values);
    }
}
