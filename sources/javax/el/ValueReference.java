package javax.el;

import java.io.Serializable;

/* loaded from: classes2.dex */
public class ValueReference implements Serializable {
    private Object base;
    private Object property;

    public Object getBase() {
        return this.base;
    }

    public Object getProperty() {
        return this.property;
    }

    public ValueReference(Object obj, Object obj2) {
        this.base = obj;
        this.property = obj2;
    }
}
