package javax.el;

import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes2.dex */
public class ImportHandler {
    private Map<String, String> classNameMap = new HashMap();
    private Map<String, Class<?>> classMap = new HashMap();
    private Map<String, String> staticNameMap = new HashMap();
    private HashSet<String> notAClass = new HashSet<>();
    private List<String> packages = new ArrayList();

    public ImportHandler() {
        importPackage("java.lang");
    }

    public void importStatic(String str) throws ELException {
        int lastIndexOf = str.lastIndexOf(46);
        if (lastIndexOf <= 0) {
            throw new ELException("The name " + str + " is not a full static member name");
        }
        this.staticNameMap.put(str.substring(lastIndexOf + 1), str.substring(0, lastIndexOf));
    }

    public void importClass(String str) throws ELException {
        int lastIndexOf = str.lastIndexOf(46);
        if (lastIndexOf <= 0) {
            throw new ELException("The name " + str + " is not a full class name");
        }
        this.classNameMap.put(str.substring(lastIndexOf + 1), str);
    }

    public void importPackage(String str) {
        this.packages.add(str);
    }

    public Class<?> resolveClass(String str) {
        String str2 = this.classNameMap.get(str);
        if (str2 != null) {
            return resolveClassFor(str2);
        }
        Iterator<String> it = this.packages.iterator();
        while (it.hasNext()) {
            String str3 = it.next() + "." + str;
            Class<?> resolveClassFor = resolveClassFor(str3);
            if (resolveClassFor != null) {
                this.classNameMap.put(str, str3);
                return resolveClassFor;
            }
        }
        return null;
    }

    public Class<?> resolveStatic(String str) {
        Class<?> resolveClassFor;
        String str2 = this.staticNameMap.get(str);
        if (str2 == null || (resolveClassFor = resolveClassFor(str2)) == null) {
            return null;
        }
        return resolveClassFor;
    }

    private Class<?> resolveClassFor(String str) {
        Class<?> cls = this.classMap.get(str);
        if (cls != null) {
            return cls;
        }
        Class<?> classFor = getClassFor(str);
        if (classFor != null) {
            checkModifiers(classFor.getModifiers());
            this.classMap.put(str, classFor);
        }
        return classFor;
    }

    private Class<?> getClassFor(String str) {
        if (this.notAClass.contains(str)) {
            return null;
        }
        try {
            return Class.forName(str, false, getClass().getClassLoader());
        } catch (ClassNotFoundException unused) {
            this.notAClass.add(str);
            return null;
        }
    }

    private void checkModifiers(int i) {
        if (Modifier.isAbstract(i) || Modifier.isInterface(i) || !Modifier.isPublic(i)) {
            throw new ELException("Imported class must be public, and cannot be abstract or an interface");
        }
    }
}
