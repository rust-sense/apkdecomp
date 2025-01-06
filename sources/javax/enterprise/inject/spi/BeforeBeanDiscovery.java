package javax.enterprise.inject.spi;

import java.lang.annotation.Annotation;

/* loaded from: classes2.dex */
public interface BeforeBeanDiscovery {
    void addAnnotatedType(AnnotatedType<?> annotatedType);

    void addAnnotatedType(AnnotatedType<?> annotatedType, String str);

    void addInterceptorBinding(Class<? extends Annotation> cls, Annotation... annotationArr);

    void addInterceptorBinding(AnnotatedType<? extends Annotation> annotatedType);

    void addQualifier(Class<? extends Annotation> cls);

    void addQualifier(AnnotatedType<? extends Annotation> annotatedType);

    void addScope(Class<? extends Annotation> cls, boolean z, boolean z2);

    void addStereotype(Class<? extends Annotation> cls, Annotation... annotationArr);
}
