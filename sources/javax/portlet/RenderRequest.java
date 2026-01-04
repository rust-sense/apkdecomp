package javax.portlet;

/* loaded from: classes2.dex */
public interface RenderRequest extends PortletRequest {
    public static final String ETAG = "portlet.ETag";

    String getETag();
}
