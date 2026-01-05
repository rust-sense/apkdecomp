package javax.enterprise.inject.spi;

/* loaded from: classes2.dex */
public interface ProcessAnnotatedType<X> {
    AnnotatedType<X> getAnnotatedType();

    void setAnnotatedType(AnnotatedType<X> annotatedType);

    void veto();
}
