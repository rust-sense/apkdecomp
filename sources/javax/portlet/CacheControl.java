package javax.portlet;

/* loaded from: classes2.dex */
public interface CacheControl {
    String getETag();

    int getExpirationTime();

    boolean isPublicScope();

    void setETag(String str);

    void setExpirationTime(int i);

    void setPublicScope(boolean z);

    void setUseCachedContent(boolean z);

    boolean useCachedContent();
}
