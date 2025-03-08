package javax.portlet;

import java.util.Collection;

/* loaded from: classes2.dex */
public interface RenderResponse extends MimeResponse {
    @Override // javax.portlet.MimeResponse
    void setContentType(String str);

    void setNextPossiblePortletModes(Collection<? extends PortletMode> collection);

    @Deprecated
    void setTitle(String str);
}
