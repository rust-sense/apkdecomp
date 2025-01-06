package javax.portlet.filter;

import java.io.IOException;
import javax.portlet.EventRequest;
import javax.portlet.EventResponse;
import javax.portlet.PortletException;

/* loaded from: classes2.dex */
public interface EventFilter extends PortletFilter {
    void doFilter(EventRequest eventRequest, EventResponse eventResponse, FilterChain filterChain) throws IOException, PortletException;
}
