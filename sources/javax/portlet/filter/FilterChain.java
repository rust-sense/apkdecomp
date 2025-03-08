package javax.portlet.filter;

import java.io.IOException;
import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.EventRequest;
import javax.portlet.EventResponse;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

/* loaded from: classes2.dex */
public interface FilterChain {
    void doFilter(ActionRequest actionRequest, ActionResponse actionResponse) throws IOException, PortletException;

    void doFilter(EventRequest eventRequest, EventResponse eventResponse) throws IOException, PortletException;

    void doFilter(RenderRequest renderRequest, RenderResponse renderResponse) throws IOException, PortletException;

    void doFilter(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws IOException, PortletException;
}
