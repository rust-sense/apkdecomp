package javax.enterprise.inject.spi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.enterprise.inject.Instance;

/* loaded from: classes2.dex */
public abstract class CDI<T> implements Instance<T> {
    protected static volatile CDIProvider configuredProvider;
    protected static volatile Set<CDIProvider> discoveredProviders;
    private static final Object lock = new Object();
    private static final Pattern nonCommentPattern = Pattern.compile("^([^#]+)");

    public abstract BeanManager getBeanManager();

    public static CDI<Object> current() {
        if (configuredProvider != null) {
            return configuredProvider.getCDI();
        }
        if (discoveredProviders == null) {
            synchronized (lock) {
                if (discoveredProviders == null) {
                    findAllProviders();
                }
            }
        }
        Iterator<CDIProvider> it = discoveredProviders.iterator();
        CDI<Object> cdi = null;
        while (it.hasNext() && (cdi = it.next().getCDI()) == null) {
        }
        if (cdi != null) {
            return cdi;
        }
        throw new IllegalStateException("Unable to access CDI");
    }

    public static void setCDIProvider(CDIProvider cDIProvider) {
        if (configuredProvider != null) {
            throw new IllegalStateException("CDIProvider has already been set");
        }
        configuredProvider = cDIProvider;
    }

    private static void findAllProviders() {
        Enumeration<URL> systemResources;
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        try {
            ClassLoader classLoader = CDI.class.getClassLoader();
            if (classLoader != null) {
                systemResources = classLoader.getResources("META-INF/services/" + CDIProvider.class.getName());
            } else {
                systemResources = ClassLoader.getSystemResources("META-INF/services/" + CDIProvider.class.getName());
            }
            HashSet hashSet = new HashSet();
            while (systemResources.hasMoreElements()) {
                InputStream openStream = systemResources.nextElement().openStream();
                try {
                    hashSet.addAll(providerNamesFromReader(new BufferedReader(new InputStreamReader(openStream))));
                    openStream.close();
                } catch (Throwable th) {
                    openStream.close();
                    throw th;
                }
            }
            Iterator it = hashSet.iterator();
            while (it.hasNext()) {
                linkedHashSet.add(Class.forName((String) it.next(), true, classLoader).newInstance());
            }
            discoveredProviders = Collections.unmodifiableSet(linkedHashSet);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        } catch (ClassNotFoundException e2) {
            throw new IllegalStateException(e2);
        } catch (IllegalAccessException e3) {
            throw new IllegalStateException(e3);
        } catch (InstantiationException e4) {
            throw new IllegalStateException(e4);
        }
    }

    private static Set<String> providerNamesFromReader(BufferedReader bufferedReader) throws IOException {
        HashSet hashSet = new HashSet();
        while (true) {
            String readLine = bufferedReader.readLine();
            if (readLine == null) {
                return hashSet;
            }
            Matcher matcher = nonCommentPattern.matcher(readLine.trim());
            if (matcher.find()) {
                hashSet.add(matcher.group().trim());
            }
        }
    }
}
