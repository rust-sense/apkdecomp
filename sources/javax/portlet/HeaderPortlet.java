package javax.portlet;

import java.io.IOException;

/* loaded from: classes2.dex */
public interface HeaderPortlet {
    void renderHeaders(HeaderRequest headerRequest, HeaderResponse headerResponse) throws PortletException, IOException;
}
