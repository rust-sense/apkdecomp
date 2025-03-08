package javax.servlet.http;

import com.google.common.net.HttpHeaders;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.MessageFormat;
import java.util.Enumeration;
import java.util.ResourceBundle;
import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

/* loaded from: classes2.dex */
public abstract class HttpServlet extends GenericServlet {
    private static final String HEADER_IFMODSINCE = "If-Modified-Since";
    private static final String HEADER_LASTMOD = "Last-Modified";
    private static final String METHOD_DELETE = "DELETE";
    private static final String METHOD_GET = "GET";
    private static final String METHOD_HEAD = "HEAD";
    private static final String METHOD_OPTIONS = "OPTIONS";
    private static final String METHOD_POST = "POST";
    private static final String METHOD_PUT = "PUT";
    private static final String METHOD_TRACE = "TRACE";
    private static final String LSTRING_FILE = "javax.servlet.http.LocalStrings";
    private static ResourceBundle lStrings = ResourceBundle.getBundle(LSTRING_FILE);

    protected long getLastModified(HttpServletRequest httpServletRequest) {
        return -1L;
    }

    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        String protocol = httpServletRequest.getProtocol();
        String string = lStrings.getString("http.method_get_not_supported");
        if (protocol.endsWith("1.1")) {
            httpServletResponse.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, string);
        } else {
            httpServletResponse.sendError(400, string);
        }
    }

    protected void doHead(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        NoBodyResponse noBodyResponse = new NoBodyResponse(httpServletResponse);
        doGet(httpServletRequest, noBodyResponse);
        noBodyResponse.setContentLength();
    }

    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        String protocol = httpServletRequest.getProtocol();
        String string = lStrings.getString("http.method_post_not_supported");
        if (protocol.endsWith("1.1")) {
            httpServletResponse.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, string);
        } else {
            httpServletResponse.sendError(400, string);
        }
    }

    protected void doPut(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        String protocol = httpServletRequest.getProtocol();
        String string = lStrings.getString("http.method_put_not_supported");
        if (protocol.endsWith("1.1")) {
            httpServletResponse.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, string);
        } else {
            httpServletResponse.sendError(400, string);
        }
    }

    protected void doDelete(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        String protocol = httpServletRequest.getProtocol();
        String string = lStrings.getString("http.method_delete_not_supported");
        if (protocol.endsWith("1.1")) {
            httpServletResponse.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, string);
        } else {
            httpServletResponse.sendError(400, string);
        }
    }

    private Method[] getAllDeclaredMethods(Class<? extends HttpServlet> cls) {
        Method[] methodArr = null;
        while (!cls.equals(HttpServlet.class)) {
            Method[] declaredMethods = cls.getDeclaredMethods();
            if (methodArr == null || methodArr.length <= 0) {
                methodArr = declaredMethods;
            } else {
                Method[] methodArr2 = new Method[declaredMethods.length + methodArr.length];
                System.arraycopy(declaredMethods, 0, methodArr2, 0, declaredMethods.length);
                System.arraycopy(methodArr, 0, methodArr2, declaredMethods.length, methodArr.length);
                methodArr = methodArr2;
            }
            cls = cls.getSuperclass();
        }
        return methodArr != null ? methodArr : new Method[0];
    }

    protected void doOptions(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        boolean z = false;
        boolean z2 = false;
        boolean z3 = false;
        boolean z4 = false;
        boolean z5 = false;
        for (Method method : getAllDeclaredMethods(getClass())) {
            String name = method.getName();
            if (name.equals("doGet")) {
                z = true;
                z2 = true;
            } else if (name.equals("doPost")) {
                z3 = true;
            } else if (name.equals("doPut")) {
                z4 = true;
            } else if (name.equals("doDelete")) {
                z5 = true;
            }
        }
        StringBuilder sb = new StringBuilder();
        if (z) {
            sb.append("GET");
        }
        if (z2) {
            if (sb.length() > 0) {
                sb.append(", ");
            }
            sb.append(METHOD_HEAD);
        }
        if (z3) {
            if (sb.length() > 0) {
                sb.append(", ");
            }
            sb.append("POST");
        }
        if (z4) {
            if (sb.length() > 0) {
                sb.append(", ");
            }
            sb.append(METHOD_PUT);
        }
        if (z5) {
            if (sb.length() > 0) {
                sb.append(", ");
            }
            sb.append(METHOD_DELETE);
        }
        if (sb.length() > 0) {
            sb.append(", ");
        }
        sb.append(METHOD_TRACE);
        if (sb.length() > 0) {
            sb.append(", ");
        }
        sb.append(METHOD_OPTIONS);
        httpServletResponse.setHeader(HttpHeaders.ALLOW, sb.toString());
    }

    protected void doTrace(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        StringBuilder sb = new StringBuilder("TRACE ");
        sb.append(httpServletRequest.getRequestURI());
        sb.append(StringUtils.SPACE);
        sb.append(httpServletRequest.getProtocol());
        Enumeration<String> headerNames = httpServletRequest.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String nextElement = headerNames.nextElement();
            sb.append(IOUtils.LINE_SEPARATOR_WINDOWS);
            sb.append(nextElement);
            sb.append(": ");
            sb.append(httpServletRequest.getHeader(nextElement));
        }
        sb.append(IOUtils.LINE_SEPARATOR_WINDOWS);
        int length = sb.length();
        httpServletResponse.setContentType("message/http");
        httpServletResponse.setContentLength(length);
        httpServletResponse.getOutputStream().print(sb.toString());
    }

    protected void service(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        String method = httpServletRequest.getMethod();
        if (method.equals("GET")) {
            long lastModified = getLastModified(httpServletRequest);
            if (lastModified == -1) {
                doGet(httpServletRequest, httpServletResponse);
                return;
            } else if (httpServletRequest.getDateHeader("If-Modified-Since") < lastModified) {
                maybeSetLastModified(httpServletResponse, lastModified);
                doGet(httpServletRequest, httpServletResponse);
                return;
            } else {
                httpServletResponse.setStatus(HttpServletResponse.SC_NOT_MODIFIED);
                return;
            }
        }
        if (method.equals(METHOD_HEAD)) {
            maybeSetLastModified(httpServletResponse, getLastModified(httpServletRequest));
            doHead(httpServletRequest, httpServletResponse);
            return;
        }
        if (method.equals("POST")) {
            doPost(httpServletRequest, httpServletResponse);
            return;
        }
        if (method.equals(METHOD_PUT)) {
            doPut(httpServletRequest, httpServletResponse);
            return;
        }
        if (method.equals(METHOD_DELETE)) {
            doDelete(httpServletRequest, httpServletResponse);
            return;
        }
        if (method.equals(METHOD_OPTIONS)) {
            doOptions(httpServletRequest, httpServletResponse);
        } else if (method.equals(METHOD_TRACE)) {
            doTrace(httpServletRequest, httpServletResponse);
        } else {
            httpServletResponse.sendError(HttpServletResponse.SC_NOT_IMPLEMENTED, MessageFormat.format(lStrings.getString("http.method_not_implemented"), method));
        }
    }

    private void maybeSetLastModified(HttpServletResponse httpServletResponse, long j) {
        if (!httpServletResponse.containsHeader("Last-Modified") && j >= 0) {
            httpServletResponse.setDateHeader("Last-Modified", j);
        }
    }

    @Override // javax.servlet.GenericServlet, javax.servlet.Servlet
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        if (!(servletRequest instanceof HttpServletRequest) || !(servletResponse instanceof HttpServletResponse)) {
            throw new ServletException("non-HTTP request or response");
        }
        service((HttpServletRequest) servletRequest, (HttpServletResponse) servletResponse);
    }
}
