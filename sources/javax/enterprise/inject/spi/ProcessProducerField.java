package javax.enterprise.inject.spi;

/* loaded from: classes2.dex */
public interface ProcessProducerField<T, X> extends ProcessBean<X> {
    AnnotatedParameter<T> getAnnotatedDisposedParameter();

    AnnotatedField<T> getAnnotatedProducerField();
}
