package javax.portlet.filter;

import javax.portlet.Event;
import javax.portlet.EventRequest;
import javax.portlet.PortletRequest;

/* loaded from: classes2.dex */
public class EventRequestWrapper extends PortletRequestWrapper implements EventRequest {
    public EventRequestWrapper(EventRequest eventRequest) {
        super(eventRequest);
    }

    @Override // javax.portlet.filter.PortletRequestWrapper
    public EventRequest getRequest() {
        return (EventRequest) super.getRequest();
    }

    public void setRequest(EventRequest eventRequest) {
        super.setRequest((PortletRequest) eventRequest);
    }

    @Override // javax.portlet.EventRequest
    public Event getEvent() {
        return ((EventRequest) this.wrapped).getEvent();
    }

    @Override // javax.portlet.EventRequest
    public String getMethod() {
        return ((EventRequest) this.wrapped).getMethod();
    }
}
