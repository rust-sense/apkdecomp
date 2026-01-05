package javax.servlet.http;

import java.io.IOException;
import java.security.Principal;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;

/* loaded from: classes2.dex */
public interface HttpServletRequest extends ServletRequest {
    public static final String BASIC_AUTH = "BASIC";
    public static final String CLIENT_CERT_AUTH = "CLIENT_CERT";
    public static final String DIGEST_AUTH = "DIGEST";
    public static final String FORM_AUTH = "FORM";

    boolean authenticate(HttpServletResponse httpServletResponse) throws IOException, ServletException;

    String changeSessionId();

    String getAuthType();

    String getContextPath();

    Cookie[] getCookies();

    long getDateHeader(String str);

    String getHeader(String str);

    Enumeration<String> getHeaderNames();

    Enumeration<String> getHeaders(String str);

    HttpServletMapping getHttpServletMapping();

    int getIntHeader(String str);

    String getMethod();

    Part getPart(String str) throws IOException, ServletException;

    Collection<Part> getParts() throws IOException, ServletException;

    String getPathInfo();

    String getPathTranslated();

    String getQueryString();

    String getRemoteUser();

    String getRequestURI();

    StringBuffer getRequestURL();

    String getRequestedSessionId();

    String getServletPath();

    HttpSession getSession();

    HttpSession getSession(boolean z);

    Map<String, String> getTrailerFields();

    Principal getUserPrincipal();

    boolean isRequestedSessionIdFromCookie();

    boolean isRequestedSessionIdFromURL();

    @Deprecated
    boolean isRequestedSessionIdFromUrl();

    boolean isRequestedSessionIdValid();

    boolean isTrailerFieldsReady();

    boolean isUserInRole(String str);

    void login(String str, String str2) throws ServletException;

    void logout() throws ServletException;

    PushBuilder newPushBuilder();

    <T extends HttpUpgradeHandler> T upgrade(Class<T> cls) throws IOException, ServletException;

    /* renamed from: javax.servlet.http.HttpServletRequest$-CC, reason: invalid class name */
    public final /* synthetic */ class CC {
        public static boolean $default$isTrailerFieldsReady(HttpServletRequest _this) {
            return true;
        }

        public static PushBuilder $default$newPushBuilder(HttpServletRequest _this) {
            return null;
        }

        public static HttpServletMapping $default$getHttpServletMapping(final HttpServletRequest _this) {
            return new HttpServletMapping() { // from class: javax.servlet.http.HttpServletRequest.1
                @Override // javax.servlet.http.HttpServletMapping
                public MappingMatch getMappingMatch() {
                    return null;
                }

                @Override // javax.servlet.http.HttpServletMapping
                public String getMatchValue() {
                    return "";
                }

                @Override // javax.servlet.http.HttpServletMapping
                public String getPattern() {
                    return "";
                }

                @Override // javax.servlet.http.HttpServletMapping
                public String getServletName() {
                    return "";
                }

                public String toString() {
                    return "MappingImpl{matchValue=" + getMatchValue() + ", pattern=" + getPattern() + ", servletName=" + getServletName() + ", mappingMatch=" + getMappingMatch() + "} HttpServletRequest {" + HttpServletRequest.this.toString() + '}';
                }
            };
        }
    }
}
