package javax.portlet.filter;

import javax.portlet.ActionParameters;
import javax.portlet.ActionRequest;
import javax.portlet.ClientDataRequest;

/* loaded from: classes2.dex */
public class ActionRequestWrapper extends ClientDataRequestWrapper implements ActionRequest {
    public ActionRequestWrapper(ActionRequest actionRequest) {
        super(actionRequest);
    }

    @Override // javax.portlet.filter.ClientDataRequestWrapper, javax.portlet.filter.PortletRequestWrapper
    public ActionRequest getRequest() {
        return (ActionRequest) super.getRequest();
    }

    public void setRequest(ActionRequest actionRequest) {
        super.setRequest((ClientDataRequest) actionRequest);
    }

    @Override // javax.portlet.ActionRequest
    public ActionParameters getActionParameters() {
        return ((ActionRequest) this.wrapped).getActionParameters();
    }
}
