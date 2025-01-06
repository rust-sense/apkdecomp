package javax.enterprise.inject.spi;

/* loaded from: classes2.dex */
public interface ProcessBean<X> {
    void addDefinitionError(Throwable th);

    Annotated getAnnotated();

    Bean<X> getBean();
}
