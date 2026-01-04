package javax.portlet.filter;

import java.io.IOException;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

/* loaded from: classes2.dex */
public interface RenderFilter extends PortletFilter {
    void doFilter(RenderRequest renderRequest, RenderResponse renderResponse, FilterChain filterChain) throws IOException, PortletException;
}
