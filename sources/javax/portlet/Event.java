package javax.portlet;

import java.io.Serializable;
import javax.xml.namespace.QName;

/* loaded from: classes2.dex */
public interface Event {
    String getName();

    QName getQName();

    Serializable getValue();
}
