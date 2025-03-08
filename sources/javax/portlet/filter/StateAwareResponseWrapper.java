package javax.portlet.filter;

import java.io.Serializable;
import java.util.Map;
import javax.portlet.MutableRenderParameters;
import javax.portlet.PortletMode;
import javax.portlet.PortletModeException;
import javax.portlet.PortletResponse;
import javax.portlet.StateAwareResponse;
import javax.portlet.WindowState;
import javax.portlet.WindowStateException;
import javax.xml.namespace.QName;

/* loaded from: classes2.dex */
public class StateAwareResponseWrapper extends PortletResponseWrapper implements StateAwareResponse {
    public StateAwareResponseWrapper(StateAwareResponse stateAwareResponse) {
        super(stateAwareResponse);
    }

    @Override // javax.portlet.filter.PortletResponseWrapper
    public StateAwareResponse getResponse() {
        return (StateAwareResponse) this.response;
    }

    public void setResponse(StateAwareResponse stateAwareResponse) {
        super.setResponse((PortletResponse) stateAwareResponse);
    }

    @Override // javax.portlet.RenderState
    public MutableRenderParameters getRenderParameters() {
        return ((StateAwareResponse) this.response).getRenderParameters();
    }

    @Override // javax.portlet.MutableRenderState
    public void setWindowState(WindowState windowState) throws WindowStateException {
        ((StateAwareResponse) this.response).setWindowState(windowState);
    }

    @Override // javax.portlet.MutableRenderState
    public void setPortletMode(PortletMode portletMode) throws PortletModeException {
        ((StateAwareResponse) this.response).setPortletMode(portletMode);
    }

    @Override // javax.portlet.RenderState
    public PortletMode getPortletMode() {
        return ((StateAwareResponse) this.response).getPortletMode();
    }

    @Override // javax.portlet.RenderState
    public WindowState getWindowState() {
        return ((StateAwareResponse) this.response).getWindowState();
    }

    @Override // javax.portlet.StateAwareResponse
    @Deprecated
    public void setRenderParameters(Map<String, String[]> map) {
        ((StateAwareResponse) this.response).setRenderParameters(map);
    }

    @Override // javax.portlet.StateAwareResponse
    @Deprecated
    public void setRenderParameter(String str, String str2) {
        ((StateAwareResponse) this.response).setRenderParameter(str, str2);
    }

    @Override // javax.portlet.StateAwareResponse
    @Deprecated
    public void setRenderParameter(String str, String... strArr) {
        ((StateAwareResponse) this.response).setRenderParameter(str, strArr);
    }

    @Override // javax.portlet.StateAwareResponse
    public void setEvent(QName qName, Serializable serializable) {
        ((StateAwareResponse) this.response).setEvent(qName, serializable);
    }

    @Override // javax.portlet.StateAwareResponse
    public void setEvent(String str, Serializable serializable) {
        ((StateAwareResponse) this.response).setEvent(str, serializable);
    }

    @Override // javax.portlet.StateAwareResponse
    @Deprecated
    public Map<String, String[]> getRenderParameterMap() {
        return ((StateAwareResponse) this.response).getRenderParameterMap();
    }

    @Override // javax.portlet.StateAwareResponse
    @Deprecated
    public void removePublicRenderParameter(String str) {
        ((StateAwareResponse) this.response).removePublicRenderParameter(str);
    }
}
