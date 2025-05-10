package com.facebook.imagepipeline.nativecode;

import com.facebook.imageformat.ImageFormat;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import kotlin.Metadata;

/* compiled from: WebpTranscoder.kt */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J \u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH&J\u0018\u0010\u000e\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH&¨\u0006\u000f"}, d2 = {"Lcom/facebook/imagepipeline/nativecode/WebpTranscoder;", "", "isWebpNativelySupported", "", "webpFormat", "Lcom/facebook/imageformat/ImageFormat;", "transcodeWebpToJpeg", "", "inputStream", "Ljava/io/InputStream;", "outputStream", "Ljava/io/OutputStream;", "quality", "", "transcodeWebpToPng", "imagepipeline-base_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public interface WebpTranscoder {
    boolean isWebpNativelySupported(ImageFormat webpFormat);

    void transcodeWebpToJpeg(InputStream inputStream, OutputStream outputStream, int quality) throws IOException;

    void transcodeWebpToPng(InputStream inputStream, OutputStream outputStream) throws IOException;
}
