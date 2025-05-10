package com.facebook.imageformat;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* loaded from: classes.dex */
public class ImageFormat {
    public static final ImageFormat UNKNOWN = new ImageFormat("UNKNOWN", null);

    @Nullable
    private final String mFileExtension;
    private final String mName;

    public interface FormatChecker {
        @Nullable
        ImageFormat determineFormat(@Nonnull byte[] bArr, int i);

        int getHeaderSize();
    }

    @Nullable
    public String getFileExtension() {
        return this.mFileExtension;
    }

    public String getName() {
        return this.mName;
    }

    public ImageFormat(String str, @Nullable String str2) {
        this.mName = str;
        this.mFileExtension = str2;
    }

    public String toString() {
        return getName();
    }
}
