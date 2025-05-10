package javax.portlet.filter;

import java.io.IOException;
import javax.portlet.HeaderRequest;
import javax.portlet.HeaderResponse;
import javax.portlet.PortletException;

/* loaded from: classes2.dex */
public interface HeaderFilter extends PortletFilter {
    void doFilter(HeaderRequest headerRequest, HeaderResponse headerResponse, HeaderFilterChain headerFilterChain) throws IOException, PortletException;
}
