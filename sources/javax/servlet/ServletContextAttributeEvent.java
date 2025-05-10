package javax.servlet;

/* loaded from: classes2.dex */
public class ServletContextAttributeEvent extends ServletContextEvent {
    private static final long serialVersionUID = -5804680734245618303L;
    private String name;
    private Object value;

    public String getName() {
        return this.name;
    }

    public Object getValue() {
        return this.value;
    }

    public ServletContextAttributeEvent(ServletContext servletContext, String str, Object obj) {
        super(servletContext);
        this.name = str;
        this.value = obj;
    }
}
