package javax.portlet;

/* loaded from: classes2.dex */
public interface ResourceURL extends BaseURL {
    public static final String FULL = "cacheLevelFull";
    public static final String PAGE = "cacheLevelPage";
    public static final String PORTLET = "cacheLevelPortlet";
    public static final String SHARED = "javax.portlet.shared";

    String getCacheability();

    String getResourceID();

    MutableResourceParameters getResourceParameters();

    void setCacheability(String str);

    void setResourceID(String str);
}
