package javax.enterprise.inject.spi;

/* loaded from: classes2.dex */
public interface ProcessInjectionPoint<T, X> {
    void addDefinitionError(Throwable th);

    InjectionPoint getInjectionPoint();

    void setInjectionPoint(InjectionPoint injectionPoint);
}
