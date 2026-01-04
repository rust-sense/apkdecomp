package javax.enterprise.inject.spi;

import java.util.Set;

/* loaded from: classes2.dex */
public interface AnnotatedType<X> extends Annotated {
    Set<AnnotatedConstructor<X>> getConstructors();

    Set<AnnotatedField<? super X>> getFields();

    Class<X> getJavaClass();

    Set<AnnotatedMethod<? super X>> getMethods();
}
