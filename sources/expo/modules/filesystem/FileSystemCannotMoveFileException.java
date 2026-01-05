package expo.modules.filesystem;

import android.net.Uri;
import expo.modules.kotlin.exception.CodedException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: FileSystemExceptions.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005¨\u0006\u0006"}, d2 = {"Lexpo/modules/filesystem/FileSystemCannotMoveFileException;", "Lexpo/modules/kotlin/exception/CodedException;", "fromUri", "Landroid/net/Uri;", "toUri", "(Landroid/net/Uri;Landroid/net/Uri;)V", "expo-file-system_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class FileSystemCannotMoveFileException extends CodedException {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FileSystemCannotMoveFileException(Uri fromUri, Uri toUri) {
        super("File '" + fromUri + "' could not be moved to '" + toUri + "'", null, 2, null);
        Intrinsics.checkNotNullParameter(fromUri, "fromUri");
        Intrinsics.checkNotNullParameter(toUri, "toUri");
    }
}
