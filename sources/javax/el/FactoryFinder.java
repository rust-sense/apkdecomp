package javax.el;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.util.Properties;

/* loaded from: classes2.dex */
class FactoryFinder {
    FactoryFinder() {
    }

    private static Object newInstance(String str, ClassLoader classLoader, Properties properties) {
        Class<?> loadClass;
        Constructor<?> constructor;
        try {
            try {
                if (classLoader == null) {
                    loadClass = Class.forName(str);
                } else {
                    loadClass = classLoader.loadClass(str);
                }
                if (properties != null) {
                    try {
                        constructor = loadClass.getConstructor(Properties.class);
                    } catch (Exception unused) {
                        constructor = null;
                    }
                    if (constructor != null) {
                        return constructor.newInstance(properties);
                    }
                }
                return loadClass.newInstance();
            } catch (ClassNotFoundException e) {
                throw new ELException("Provider " + str + " not found", e);
            }
        } catch (Exception e2) {
            throw new ELException("Provider " + str + " could not be instantiated: " + e2, e2);
        }
    }

    static Object find(String str, String str2, Properties properties) {
        InputStream resourceAsStream;
        try {
            ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
            String str3 = "META-INF/services/" + str;
            try {
                if (contextClassLoader == null) {
                    resourceAsStream = ClassLoader.getSystemResourceAsStream(str3);
                } else {
                    resourceAsStream = contextClassLoader.getResourceAsStream(str3);
                }
                if (resourceAsStream != null) {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(resourceAsStream, "UTF-8"));
                    String readLine = bufferedReader.readLine();
                    bufferedReader.close();
                    if (readLine != null && !"".equals(readLine)) {
                        return newInstance(readLine, contextClassLoader, properties);
                    }
                }
            } catch (Exception unused) {
            }
            try {
                File file = new File(System.getProperty("java.home") + File.separator + "lib" + File.separator + "el.properties");
                if (file.exists()) {
                    Properties properties2 = new Properties();
                    properties2.load(new FileInputStream(file));
                    return newInstance(properties2.getProperty(str), contextClassLoader, properties);
                }
            } catch (Exception unused2) {
            }
            try {
                String property = System.getProperty(str);
                if (property != null) {
                    return newInstance(property, contextClassLoader, properties);
                }
            } catch (SecurityException unused3) {
            }
            if (str2 == null) {
                throw new ELException("Provider for " + str + " cannot be found", null);
            }
            return newInstance(str2, contextClassLoader, properties);
        } catch (Exception e) {
            throw new ELException(e.toString(), e);
        }
    }
}
