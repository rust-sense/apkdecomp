package javax.portlet;

import java.util.Set;

/* loaded from: classes2.dex */
public interface PortletParameters {
    /* renamed from: clone */
    MutablePortletParameters mo863clone();

    Set<String> getNames();

    String getValue(String str);

    String[] getValues(String str);

    boolean isEmpty();

    int size();
}
