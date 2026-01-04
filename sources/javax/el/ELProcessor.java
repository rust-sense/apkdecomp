package javax.el;

import io.sentry.profilemeasurements.ProfileMeasurement;
import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/* loaded from: classes2.dex */
public class ELProcessor {
    private ELManager elManager = new ELManager();
    private ExpressionFactory factory = ELManager.getExpressionFactory();

    public ELManager getELManager() {
        return this.elManager;
    }

    public Object eval(String str) {
        return getValue(str, Object.class);
    }

    public Object getValue(String str, Class<?> cls) {
        return this.factory.createValueExpression(this.elManager.getELContext(), bracket(str), cls).getValue(this.elManager.getELContext());
    }

    public void setValue(String str, Object obj) {
        this.factory.createValueExpression(this.elManager.getELContext(), bracket(str), Object.class).setValue(this.elManager.getELContext(), obj);
    }

    public void setVariable(String str, String str2) {
        this.elManager.setVariable(str, this.factory.createValueExpression(this.elManager.getELContext(), bracket(str2), Object.class));
    }

    public void defineFunction(String str, String str2, String str3, String str4) throws ClassNotFoundException, NoSuchMethodException {
        Method declaredMethod;
        if (str == null || str2 == null || str3 == null || str4 == null) {
            throw new NullPointerException("Null argument for defineFunction");
        }
        ClassLoader classLoader = getClass().getClassLoader();
        int i = 0;
        Class<?> cls = Class.forName(str3, false, classLoader);
        int indexOf = str4.indexOf(40);
        if (indexOf < 0) {
            Method[] declaredMethods = cls.getDeclaredMethods();
            int length = declaredMethods.length;
            declaredMethod = null;
            while (i < length) {
                Method method = declaredMethods[i];
                if (method.getName().equals(str4)) {
                    declaredMethod = method;
                }
                i++;
            }
            if (declaredMethod == null) {
                throw new NoSuchMethodException();
            }
        } else {
            int indexOf2 = str4.indexOf(32);
            if (indexOf2 < 0) {
                throw new NoSuchMethodException("Bad method singnature: " + str4);
            }
            String trim = str4.substring(indexOf2 + 1, indexOf).trim();
            int i2 = indexOf + 1;
            int indexOf3 = str4.indexOf(41, i2);
            if (indexOf3 < 0) {
                throw new NoSuchMethodException("Bad method singnature: " + str4);
            }
            String[] split = str4.substring(i2, indexOf3).split(",");
            Class<?>[] clsArr = new Class[split.length];
            while (i < split.length) {
                clsArr[i] = toClass(split[i], classLoader);
                i++;
            }
            declaredMethod = cls.getDeclaredMethod(trim, clsArr);
        }
        if (!Modifier.isStatic(declaredMethod.getModifiers())) {
            throw new NoSuchMethodException("The method specified in defineFunction must be static: " + declaredMethod);
        }
        if (str2.equals("")) {
            str2 = str4;
        }
        this.elManager.mapFunction(str, str2, declaredMethod);
    }

    public void defineFunction(String str, String str2, Method method) throws NoSuchMethodException {
        if (str == null || str2 == null || method == null) {
            throw new NullPointerException("Null argument for defineFunction");
        }
        if (!Modifier.isStatic(method.getModifiers())) {
            throw new NoSuchMethodException("The method specified in defineFunction must be static: " + method);
        }
        if (str2.equals("")) {
            str2 = method.getName();
        }
        this.elManager.mapFunction(str, str2, method);
    }

    public void defineBean(String str, Object obj) {
        this.elManager.defineBean(str, obj);
    }

    private static Class<?> toClass(String str, ClassLoader classLoader) throws ClassNotFoundException {
        Class<?> loadClass;
        int indexOf = str.indexOf(91);
        int i = 0;
        if (indexOf > 0) {
            int i2 = 0;
            for (int i3 = 0; i3 < str.length(); i3++) {
                if (str.charAt(i3) == '[') {
                    i2++;
                }
            }
            str = str.substring(0, indexOf);
            i = i2;
        }
        if ("boolean".equals(str)) {
            loadClass = Boolean.TYPE;
        } else if ("char".equals(str)) {
            loadClass = Character.TYPE;
        } else if (ProfileMeasurement.UNIT_BYTES.equals(str)) {
            loadClass = Byte.TYPE;
        } else if ("short".equals(str)) {
            loadClass = Short.TYPE;
        } else if ("int".equals(str)) {
            loadClass = Integer.TYPE;
        } else if ("long".equals(str)) {
            loadClass = Long.TYPE;
        } else if ("float".equals(str)) {
            loadClass = Float.TYPE;
        } else if ("double".equals(str)) {
            loadClass = Double.TYPE;
        } else {
            loadClass = classLoader.loadClass(str);
        }
        if (i == 0) {
            return loadClass;
        }
        if (i == 1) {
            return Array.newInstance(loadClass, 1).getClass();
        }
        return Array.newInstance(loadClass, new int[i]).getClass();
    }

    private String bracket(String str) {
        return "${" + str + '}';
    }
}
