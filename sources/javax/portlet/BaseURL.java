package javax.portlet;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

/* loaded from: classes2.dex */
public interface BaseURL extends RenderState {
    void addProperty(String str, String str2);

    Appendable append(Appendable appendable) throws IOException;

    Appendable append(Appendable appendable, boolean z) throws IOException;

    @Deprecated
    Map<String, String[]> getParameterMap();

    @Deprecated
    void setParameter(String str, String str2);

    @Deprecated
    void setParameter(String str, String... strArr);

    @Deprecated
    void setParameters(Map<String, String[]> map);

    void setProperty(String str, String str2);

    void setSecure(boolean z) throws PortletSecurityException;

    String toString();

    void write(Writer writer) throws IOException;

    void write(Writer writer, boolean z) throws IOException;
}
