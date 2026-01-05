package javax.portlet;

import java.io.Serializable;
import java.util.Map;
import javax.xml.namespace.QName;

/* loaded from: classes2.dex */
public interface StateAwareResponse extends PortletResponse, MutableRenderState {
    @Deprecated
    Map<String, String[]> getRenderParameterMap();

    @Deprecated
    void removePublicRenderParameter(String str);

    void setEvent(String str, Serializable serializable);

    void setEvent(QName qName, Serializable serializable);

    @Deprecated
    void setRenderParameter(String str, String str2);

    @Deprecated
    void setRenderParameter(String str, String... strArr);

    @Deprecated
    void setRenderParameters(Map<String, String[]> map);
}
