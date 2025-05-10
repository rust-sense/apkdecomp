package expo.modules.gl.utils;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/* loaded from: classes2.dex */
public class FileSystemUtils {
    public static File ensureDirExists(File file) throws IOException {
        if (file.isDirectory() || file.mkdirs()) {
            return file;
        }
        throw new IOException("Couldn't create directory '" + file + "'");
    }

    public static String generateOutputPath(File file, String str, String str2) throws IOException {
        File file2 = new File(file + File.separator + str);
        ensureDirExists(file2);
        return file2 + File.separator + UUID.randomUUID().toString() + str2;
    }
}
