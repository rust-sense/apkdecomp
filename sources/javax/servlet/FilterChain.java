package javax.servlet;

import java.io.IOException;

/* loaded from: classes2.dex */
public interface FilterChain {
    void doFilter(ServletRequest servletRequest, ServletResponse servletResponse) throws IOException, ServletException;
}
