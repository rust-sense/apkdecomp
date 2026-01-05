package javax.enterprise.inject.spi;

import java.lang.reflect.Field;

/* loaded from: classes2.dex */
public interface AnnotatedField<X> extends AnnotatedMember<X> {
    @Override // javax.enterprise.inject.spi.AnnotatedMember
    Field getJavaMember();
}
