package javax.portlet.filter;

import java.io.IOException;
import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;

/* loaded from: classes2.dex */
public interface ActionFilter extends PortletFilter {
    void doFilter(ActionRequest actionRequest, ActionResponse actionResponse, FilterChain filterChain) throws IOException, PortletException;
}
