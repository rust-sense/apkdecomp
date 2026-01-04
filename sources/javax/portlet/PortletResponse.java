package javax.portlet;

import java.util.Collection;
import javax.servlet.http.Cookie;
import org.w3c.dom.DOMException;
import org.w3c.dom.Element;

/* loaded from: classes2.dex */
public interface PortletResponse {
    void addProperty(String str, String str2);

    void addProperty(String str, Element element);

    void addProperty(Cookie cookie);

    Element createElement(String str) throws DOMException;

    String encodeURL(String str);

    String getNamespace();

    String getProperty(String str);

    Collection<String> getPropertyNames();

    Collection<String> getPropertyValues(String str);

    void setProperty(String str, String str2);
}
