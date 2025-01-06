package javax.portlet;

import java.util.Set;

/* loaded from: classes2.dex */
public interface MutablePortletParameters extends PortletParameters, Mutable {
    MutablePortletParameters add(PortletParameters portletParameters);

    void clear();

    @Override // javax.portlet.PortletParameters
    Set<String> getNames();

    boolean removeParameter(String str);

    MutablePortletParameters set(PortletParameters portletParameters);

    String setValue(String str, String str2);

    String[] setValues(String str, String... strArr);
}
