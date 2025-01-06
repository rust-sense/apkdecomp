package javax.portlet;

import java.security.Principal;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Map;
import javax.servlet.http.Cookie;

/* loaded from: classes2.dex */
public interface PortletRequest extends RenderState {
    public static final String ACTION_PHASE = "ACTION_PHASE";
    public static final String ACTION_SCOPE_ID = "javax.portlet.as";
    public static final String BASIC_AUTH = "BASIC";
    public static final String CCPP_PROFILE = "javax.portlet.ccpp";
    public static final String CLIENT_CERT_AUTH = "CLIENT_CERT";
    public static final String DIGEST_AUTH = "DIGEST";
    public static final String EVENT_PHASE = "EVENT_PHASE";
    public static final String FORM_AUTH = "FORM";
    public static final String HEADER_PHASE = "HEADER_PHASE";
    public static final String LIFECYCLE_PHASE = "javax.portlet.lifecycle_phase";
    public static final String RENDER_HEADERS = "RENDER_HEADERS";
    public static final String RENDER_MARKUP = "RENDER_MARKUP";
    public static final String RENDER_PART = "javax.portlet.render_part";
    public static final String RENDER_PHASE = "RENDER_PHASE";
    public static final String RESOURCE_PHASE = "RESOURCE_PHASE";
    public static final String USER_INFO = "javax.portlet.userinfo";

    Object getAttribute(String str);

    Enumeration<String> getAttributeNames();

    String getAuthType();

    String getContextPath();

    Cookie[] getCookies();

    Locale getLocale();

    Enumeration<Locale> getLocales();

    @Deprecated
    String getParameter(String str);

    @Deprecated
    Map<String, String[]> getParameterMap();

    @Deprecated
    Enumeration<String> getParameterNames();

    @Deprecated
    String[] getParameterValues(String str);

    PortalContext getPortalContext();

    PortletContext getPortletContext();

    PortletSession getPortletSession();

    PortletSession getPortletSession(boolean z);

    PortletPreferences getPreferences();

    @Deprecated
    Map<String, String[]> getPrivateParameterMap();

    Enumeration<String> getProperties(String str);

    String getProperty(String str);

    Enumeration<String> getPropertyNames();

    @Deprecated
    Map<String, String[]> getPublicParameterMap();

    String getRemoteUser();

    String getRequestedSessionId();

    String getResponseContentType();

    Enumeration<String> getResponseContentTypes();

    String getScheme();

    String getServerName();

    int getServerPort();

    String getUserAgent();

    Principal getUserPrincipal();

    String getWindowID();

    boolean isPortletModeAllowed(PortletMode portletMode);

    boolean isRequestedSessionIdValid();

    boolean isSecure();

    boolean isUserInRole(String str);

    boolean isWindowStateAllowed(WindowState windowState);

    void removeAttribute(String str);

    void setAttribute(String str, Object obj);

    public enum P3PUserInfos {
        USER_BDATE_YMD_YEAR("user.bdate.ymd.year"),
        USER_BDATE_YMD_MONTH("user.bdate.ymd.month"),
        USER_BDATE_YMD_DAY("user.bdate.ymd.day"),
        USER_BDATE_HMS_HOUR("user.bdate.hms.hour"),
        USER_BDATE_HMS_MINUTE("user.bdate.hms.minute"),
        USER_BDATE_HMS_SECOND("user.bdate.hms.second"),
        USER_BDATE_FRACTIONSECOND("user.bdate.fractionsecond"),
        USER_BDATE_TIMEZONE("user.bdate.timezone"),
        USER_GENDER("user.gender"),
        USER_EMPLOYER("user.employer"),
        USER_DEPARTMENT("user.department"),
        USER_JOBTITLE("user.jobtitle"),
        USER_NAME_PREFIX("user.name.prefix"),
        USER_NAME_GIVEN("user.name.given"),
        USER_NAME_FAMILY("user.name.family"),
        USER_NAME_MIDDLE("user.name.middle"),
        USER_NAME_SUFFIX("user.name.suffix"),
        USER_NAME_NICKNAME("user.name.nickName"),
        USER_LOGIN_ID("user.login.id"),
        USER_HOMEINFO_POSTAL_NAME("user.home-info.postal.name"),
        USER_HOMEINFO_POSTAL_STREET("user.home-info.postal.street"),
        USER_HOMEINFO_POSTAL_CITY("user.home-info.postal.city"),
        USER_HOMEINFO_POSTAL_STATEPROV("user.home-info.postal.stateprov"),
        USER_HOMEINFO_POSTAL_POSTALCODE("user.home-info.postal.postalcode"),
        USER_HOMEINFO_POSTAL_COUNTRY("user.home-info.postal.country"),
        USER_HOMEINFO_POSTAL_ORGANIZATION("user.home-info.postal.organization"),
        USER_HOMEINFO_TELECOM_TELEPHONE_INTCODE("user.home-info.telecom.telephone.intcode"),
        USER_HOMEINFO_TELECOM_TELEPHONE_LOCCODE("user.home-info.telecom.telephone.loccode"),
        USER_HOMEINFO_TELECOM_TELEPHONE_NUMBER("user.home-info.telecom.telephone.number"),
        USER_HOMEINFO_TELECOM_TELEPHONE_EXT("user.home-info.telecom.telephone.ext"),
        USER_HOMEINFO_TELECOM_TELEPHONE_COMMENT("user.home-info.telecom.telephone.comment"),
        USER_HOMEINFO_TELECOM_FAX_INTCODE("user.home-info.telecom.fax.intcode"),
        USER_HOMEINFO_TELECOM_FAX_LOCCODE("user.home-info.telecom.fax.loccode"),
        USER_HOMEINFO_TELECOM_FAX_NUMBER("user.home-info.telecom.fax.number"),
        USER_HOMEINFO_TELECOM_FAX_EXT("user.home-info.telecom.fax.ext"),
        USER_HOMEINFO_TELECOM_FAX_COMMENT("user.home-info.telecom.fax.comment"),
        USER_HOMEINFO_TELECOM_MOBILE_INTCODE("user.home-info.telecom.mobile.intcode"),
        USER_HOMEINFO_TELECOM_MOBILE_LOCCODE("user.home-info.telecom.mobile.loccode"),
        USER_HOMEINFO_TELECOM_MOBILE_NUMBER("user.home-info.telecom.mobile.number"),
        USER_HOMEINFO_TELECOM_MOBILE_EXT("user.home-info.telecom.mobile.ext"),
        USER_HOMEINFO_TELECOM_MOBILE_COMMENT("user.home-info.telecom.mobile.comment"),
        USER_HOMEINFO_TELECOM_PAGER_INTCODE("user.home-info.telecom.pager.intcode"),
        USER_HOMEINFO_TELECOM_PAGER_LOCCODE("user.home-info.telecom.pager.loccode"),
        USER_HOMEINFO_TELECOM_PAGER_NUMBER("user.home-info.telecom.pager.number"),
        USER_HOMEINFO_TELECOM_PAGER_EXT("user.home-info.telecom.pager.ext"),
        USER_HOMEINFO_TELECOM_PAGER_COMMENT("user.home-info.telecom.pager.comment"),
        USER_HOMEINFO_ONLINE_EMAIL("user.home-info.online.email"),
        USER_HOMEINFO_ONLINE_URI("user.home-info.online.uri"),
        USER_BUSINESSINFO_POSTAL_NAME("user.business-info.postal.name"),
        USER_BUSINESSINFO_POSTAL_STREET("user.business-info.postal.street"),
        USER_BUSINESSINFO_POSTAL_CITY("user.business-info.postal.city"),
        USER_BUSINESSINFO_POSTAL_STATEPROV("user.business-info.postal.stateprov"),
        USER_BUSINESSINFO_POSTAL_POSTALCODE("user.business-info.postal.postalcode"),
        USER_BUSINESSINFO_POSTAL_COUNTRY("user.business-info.postal.country"),
        USER_BUSINESSINFO_POSTAL_ORGANIZATION("user.business-info.postal.organization"),
        USER_BUSINESSINFO_TELECOM_TELEPHONE_INTCODE("user.business-info.telecom.telephone.intcode"),
        USER_BUSINESSINFO_TELECOM_TELEPHONE_LOCCODE("user.business-info.telecom.telephone.loccode"),
        USER_BUSINESSINFO_TELECOM_TELEPHONE_NUMBER("user.business-info.telecom.telephone.number"),
        USER_BUSINESSINFO_TELECOM_TELEPHONE_EXT("user.business-info.telecom.telephone.ext"),
        USER_BUSINESSINFO_TELECOM_TELEPHONE_COMMENT("user.business-info.telecom.telephone.comment"),
        USER_BUSINESSINFO_TELECOM_FAX_INTCODE("user.business-info.telecom.fax.intcode"),
        USER_BUSINESSINFO_TELECOM_FAX_LOCCODE("user.business-info.telecom.fax.loccode"),
        USER_BUSINESSINFO_TELECOM_FAX_NUMBER("user.business-info.telecom.fax.number"),
        USER_BUSINESSINFO_TELECOM_FAX_EXT("user.business-info.telecom.fax.ext"),
        USER_BUSINESSINFO_TELECOM_FAX_COMMENT("user.business-info.telecom.fax.comment"),
        USER_BUSINESSINFO_TELECOM_MOBILE_INTCODE("user.business-info.telecom.mobile.intcode"),
        USER_BUSINESSINFO_TELECOM_MOBILE_LOCCODE("user.business-info.telecom.mobile.loccode"),
        USER_BUSINESSINFO_TELECOM_MOBILE_NUMBER("user.business-info.telecom.mobile.number"),
        USER_BUSINESSINFO_TELECOM_MOBILE_EXT("user.business-info.telecom.mobile.ext"),
        USER_BUSINESSINFO_TELECOM_MOBILE_COMMENT("user.business-info.telecom.mobile.comment"),
        USER_BUSINESSINFO_TELECOM_PAGER_INTCODE("user.business-info.telecom.pager.intcode"),
        USER_BUSINESSINFO_TELECOM_PAGER_LOCCODE("user.business-info.telecom.pager.loccode"),
        USER_BUSINESSINFO_TELECOM_PAGER_NUMBER("user.business-info.telecom.pager.number"),
        USER_BUSINESSINFO_TELECOM_PAGER_EXT("user.business-info.telecom.pager.ext"),
        USER_BUSINESSINFO_TELECOM_PAGER_COMMENT("user.business-info.telecom.pager.comment"),
        USER_BUSINESSINFO_ONLINE_EMAIL("user.business-info.online.email"),
        USER_BUSINESSINFO_ONLINE_URI("user.business-info.online.uri");

        private final String value;

        @Override // java.lang.Enum
        public String toString() {
            return this.value;
        }

        P3PUserInfos(String str) {
            this.value = str;
        }
    }
}
