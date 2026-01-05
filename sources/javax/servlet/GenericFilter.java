package javax.servlet;

import java.io.Serializable;
import java.util.Enumeration;
import java.util.ResourceBundle;
import javax.servlet.Filter;

/* loaded from: classes2.dex */
public abstract class GenericFilter implements Filter, FilterConfig, Serializable {
    private static final String LSTRING_FILE = "javax.servlet.LocalStrings";
    private static final ResourceBundle lStrings = ResourceBundle.getBundle(LSTRING_FILE);
    private transient FilterConfig config;

    @Override // javax.servlet.Filter
    public /* synthetic */ void destroy() {
        Filter.CC.$default$destroy(this);
    }

    public FilterConfig getFilterConfig() {
        return this.config;
    }

    public void init() throws ServletException {
    }

    @Override // javax.servlet.FilterConfig
    public String getInitParameter(String str) {
        FilterConfig filterConfig = getFilterConfig();
        if (filterConfig == null) {
            throw new IllegalStateException(lStrings.getString("err.filter_config_not_initialized"));
        }
        return filterConfig.getInitParameter(str);
    }

    @Override // javax.servlet.FilterConfig
    public Enumeration<String> getInitParameterNames() {
        FilterConfig filterConfig = getFilterConfig();
        if (filterConfig == null) {
            throw new IllegalStateException(lStrings.getString("err.filter_config_not_initialized"));
        }
        return filterConfig.getInitParameterNames();
    }

    @Override // javax.servlet.FilterConfig
    public ServletContext getServletContext() {
        FilterConfig filterConfig = getFilterConfig();
        if (filterConfig == null) {
            throw new IllegalStateException(lStrings.getString("err.filter_config_not_initialized"));
        }
        return filterConfig.getServletContext();
    }

    @Override // javax.servlet.Filter
    public void init(FilterConfig filterConfig) throws ServletException {
        this.config = filterConfig;
        init();
    }

    @Override // javax.servlet.FilterConfig
    public String getFilterName() {
        FilterConfig filterConfig = getFilterConfig();
        if (filterConfig == null) {
            throw new IllegalStateException(lStrings.getString("err.servlet_config_not_initialized"));
        }
        return filterConfig.getFilterName();
    }
}
