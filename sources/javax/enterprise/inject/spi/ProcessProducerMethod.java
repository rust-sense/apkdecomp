package javax.enterprise.inject.spi;

/* loaded from: classes2.dex */
public interface ProcessProducerMethod<T, X> extends ProcessBean<X> {
    AnnotatedParameter<T> getAnnotatedDisposedParameter();

    AnnotatedMethod<T> getAnnotatedProducerMethod();
}
