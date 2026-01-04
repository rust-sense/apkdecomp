package javax.portlet;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;

/* loaded from: classes2.dex */
public class ValidatorException extends PortletException {
    private static final long serialVersionUID = 1;
    private transient ArrayList<String> failedKeyVector;

    public ValidatorException(String str, Collection<String> collection) {
        super(str);
        ArrayList<String> arrayList = new ArrayList<>();
        this.failedKeyVector = arrayList;
        if (collection != null) {
            arrayList.addAll(collection);
        }
    }

    public ValidatorException(String str, Throwable th, Collection<String> collection) {
        super(str, th);
        ArrayList<String> arrayList = new ArrayList<>();
        this.failedKeyVector = arrayList;
        if (collection != null) {
            arrayList.addAll(collection);
        }
    }

    public ValidatorException(Throwable th, Collection<String> collection) {
        super(th);
        ArrayList<String> arrayList = new ArrayList<>();
        this.failedKeyVector = arrayList;
        if (collection != null) {
            arrayList.addAll(collection);
        }
    }

    public Enumeration<String> getFailedKeys() {
        return Collections.enumeration(this.failedKeyVector);
    }
}
