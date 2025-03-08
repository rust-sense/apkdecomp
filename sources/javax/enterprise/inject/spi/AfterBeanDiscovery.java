package javax.enterprise.inject.spi;

import javax.enterprise.context.spi.Context;

/* loaded from: classes2.dex */
public interface AfterBeanDiscovery {
    void addBean(Bean<?> bean);

    void addContext(Context context);

    void addDefinitionError(Throwable th);

    void addObserverMethod(ObserverMethod<?> observerMethod);

    <T> AnnotatedType<T> getAnnotatedType(Class<T> cls, String str);

    <T> Iterable<AnnotatedType<T>> getAnnotatedTypes(Class<T> cls);
}
