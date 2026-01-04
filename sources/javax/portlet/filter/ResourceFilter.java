package javax.portlet.filter;

import java.io.IOException;
import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

/* loaded from: classes2.dex */
public interface ResourceFilter extends PortletFilter {
    void doFilter(ResourceRequest resourceRequest, ResourceResponse resourceResponse, FilterChain filterChain) throws IOException, PortletException;
}
