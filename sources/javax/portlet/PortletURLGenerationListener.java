package javax.portlet;

import javax.portlet.ActionURL;
import javax.portlet.PortletURL;
import javax.portlet.RenderURL;

/* loaded from: classes2.dex */
public interface PortletURLGenerationListener<T extends PortletURL & RenderURL, U extends PortletURL & ActionURL> {
    void filterActionURL(U u);

    void filterRenderURL(T t);

    void filterResourceURL(ResourceURL resourceURL);
}
