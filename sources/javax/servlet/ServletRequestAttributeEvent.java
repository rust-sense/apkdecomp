package javax.servlet;

/* loaded from: classes2.dex */
public class ServletRequestAttributeEvent extends ServletRequestEvent {
    private static final long serialVersionUID = -1466635426192317793L;
    private String name;
    private Object value;

    public String getName() {
        return this.name;
    }

    public Object getValue() {
        return this.value;
    }

    public ServletRequestAttributeEvent(ServletContext servletContext, ServletRequest servletRequest, String str, Object obj) {
        super(servletContext, servletRequest);
        this.name = str;
        this.value = obj;
    }
}
