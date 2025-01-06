package javax.enterprise.inject.spi;

/* loaded from: classes2.dex */
public interface ProcessObserverMethod<T, X> {
    void addDefinitionError(Throwable th);

    AnnotatedMethod<X> getAnnotatedMethod();

    ObserverMethod<T> getObserverMethod();
}
