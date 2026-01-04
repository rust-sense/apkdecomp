package javax.servlet.http;

import java.util.Enumeration;

@Deprecated
/* loaded from: classes2.dex */
public interface HttpSessionContext {
    @Deprecated
    Enumeration<String> getIds();

    @Deprecated
    HttpSession getSession(String str);
}
