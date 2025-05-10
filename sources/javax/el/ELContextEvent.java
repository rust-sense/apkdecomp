package javax.el;

import java.util.EventObject;

/* loaded from: classes2.dex */
public class ELContextEvent extends EventObject {
    public ELContextEvent(ELContext eLContext) {
        super(eLContext);
    }

    public ELContext getELContext() {
        return (ELContext) getSource();
    }
}
