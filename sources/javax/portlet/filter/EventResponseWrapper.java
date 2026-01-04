package javax.portlet.filter;

import javax.portlet.EventRequest;
import javax.portlet.EventResponse;
import javax.portlet.StateAwareResponse;

/* loaded from: classes2.dex */
public class EventResponseWrapper extends StateAwareResponseWrapper implements EventResponse {
    public EventResponseWrapper(EventResponse eventResponse) {
        super(eventResponse);
    }

    @Override // javax.portlet.filter.StateAwareResponseWrapper, javax.portlet.filter.PortletResponseWrapper
    public EventResponse getResponse() {
        return (EventResponse) this.response;
    }

    public void setResponse(EventResponse eventResponse) {
        super.setResponse((StateAwareResponse) eventResponse);
    }

    @Override // javax.portlet.EventResponse
    @Deprecated
    public void setRenderParameters(EventRequest eventRequest) {
        ((EventResponse) this.response).setRenderParameters(eventRequest);
    }
}
