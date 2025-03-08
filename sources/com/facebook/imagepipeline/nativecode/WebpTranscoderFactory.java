package com.facebook.imagepipeline.nativecode;

/* loaded from: classes.dex */
public class WebpTranscoderFactory {
    private static WebpTranscoder sWebpTranscoder = null;
    public static boolean sWebpTranscoderPresent = false;

    public static WebpTranscoder getWebpTranscoder() {
        return sWebpTranscoder;
    }

    static {
        try {
            sWebpTranscoder = (WebpTranscoder) Class.forName("com.facebook.imagepipeline.nativecode.WebpTranscoderImpl").newInstance();
            sWebpTranscoderPresent = true;
        } catch (Throwable unused) {
            sWebpTranscoderPresent = false;
        }
    }
}
