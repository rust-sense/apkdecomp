package javax.servlet;

import javax.servlet.annotation.MultipartConfig;

/* loaded from: classes2.dex */
public class MultipartConfigElement {
    private int fileSizeThreshold;
    private String location;
    private long maxFileSize;
    private long maxRequestSize;

    public int getFileSizeThreshold() {
        return this.fileSizeThreshold;
    }

    public String getLocation() {
        return this.location;
    }

    public long getMaxFileSize() {
        return this.maxFileSize;
    }

    public long getMaxRequestSize() {
        return this.maxRequestSize;
    }

    public MultipartConfigElement(String str) {
        if (str == null) {
            this.location = "";
        } else {
            this.location = str;
        }
        this.maxFileSize = -1L;
        this.maxRequestSize = -1L;
        this.fileSizeThreshold = 0;
    }

    public MultipartConfigElement(String str, long j, long j2, int i) {
        if (str == null) {
            this.location = "";
        } else {
            this.location = str;
        }
        this.maxFileSize = j;
        this.maxRequestSize = j2;
        this.fileSizeThreshold = i;
    }

    public MultipartConfigElement(MultipartConfig multipartConfig) {
        this.location = multipartConfig.location();
        this.fileSizeThreshold = multipartConfig.fileSizeThreshold();
        this.maxFileSize = multipartConfig.maxFileSize();
        this.maxRequestSize = multipartConfig.maxRequestSize();
    }
}
