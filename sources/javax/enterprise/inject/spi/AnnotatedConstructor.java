package javax.enterprise.inject.spi;

import java.lang.reflect.Constructor;

/* loaded from: classes2.dex */
public interface AnnotatedConstructor<X> extends AnnotatedCallable<X> {
    @Override // javax.enterprise.inject.spi.AnnotatedMember
    Constructor<X> getJavaMember();
}
