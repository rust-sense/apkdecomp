package javax.servlet;

import java.io.IOException;

/* loaded from: classes2.dex */
public interface Filter {

    /* renamed from: javax.servlet.Filter$-CC, reason: invalid class name */
    public final /* synthetic */ class CC {
        public static void $default$destroy(Filter _this) {
        }

        public static void $default$init(Filter _this, FilterConfig filterConfig) throws ServletException {
        }
    }

    void destroy();

    void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException;

    void init(FilterConfig filterConfig) throws ServletException;
}
