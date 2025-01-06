package javax.portlet;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Map;

/* loaded from: classes2.dex */
public interface PortletPreferences {
    Map<String, String[]> getMap();

    Enumeration<String> getNames();

    String getValue(String str, String str2);

    String[] getValues(String str, String[] strArr);

    boolean isReadOnly(String str);

    void reset(String str) throws ReadOnlyException;

    void setValue(String str, String str2) throws ReadOnlyException;

    void setValues(String str, String... strArr) throws ReadOnlyException;

    void store() throws IOException, ValidatorException;
}
