package javax.enterprise.inject.spi;

/* loaded from: classes2.dex */
public interface ProcessInjectionTarget<X> {
    void addDefinitionError(Throwable th);

    AnnotatedType<X> getAnnotatedType();

    InjectionTarget<X> getInjectionTarget();

    void setInjectionTarget(InjectionTarget<X> injectionTarget);
}
