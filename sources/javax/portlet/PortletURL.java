package javax.portlet;

import javax.portlet.annotations.PortletSerializable;

/* loaded from: classes2.dex */
public interface PortletURL extends BaseURL, MutableRenderState {
    @Deprecated
    void removePublicRenderParameter(String str);

    void setBeanParameter(PortletSerializable portletSerializable);
}
