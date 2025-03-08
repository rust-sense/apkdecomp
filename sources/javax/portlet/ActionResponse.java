package javax.portlet;

import java.io.IOException;
import javax.portlet.MimeResponse;

/* loaded from: classes2.dex */
public interface ActionResponse extends StateAwareResponse {
    RenderURL createRedirectURL(MimeResponse.Copy copy) throws IllegalStateException;

    void sendRedirect(String str) throws IOException;

    void sendRedirect(String str, String str2) throws IOException;
}
