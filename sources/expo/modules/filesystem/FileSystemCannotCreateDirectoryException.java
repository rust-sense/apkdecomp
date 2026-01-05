package expo.modules.filesystem;

import expo.modules.kotlin.exception.CodedException;
import kotlin.Metadata;

/* compiled from: FileSystemExceptions.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004¨\u0006\u0005"}, d2 = {"Lexpo/modules/filesystem/FileSystemCannotCreateDirectoryException;", "Lexpo/modules/kotlin/exception/CodedException;", "uri", "Landroid/net/Uri;", "(Landroid/net/Uri;)V", "expo-file-system_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class FileSystemCannotCreateDirectoryException extends CodedException {
    /* JADX WARN: Code restructure failed: missing block: B:3:0x0015, code lost:
    
        if (r3 == null) goto L5;
     */
    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public FileSystemCannotCreateDirectoryException(android.net.Uri r3) {
        /*
            r2 = this;
            if (r3 == 0) goto L17
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "Directory '"
            r0.<init>(r1)
            r0.append(r3)
            java.lang.String r3 = "' could not be created or already exists"
            r0.append(r3)
            java.lang.String r3 = r0.toString()
            if (r3 != 0) goto L19
        L17:
            java.lang.String r3 = "Unknown error"
        L19:
            r0 = 2
            r1 = 0
            r2.<init>(r3, r1, r0, r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.filesystem.FileSystemCannotCreateDirectoryException.<init>(android.net.Uri):void");
    }
}
