package javax.enterprise.inject.spi;

import java.lang.reflect.Member;

/* loaded from: classes2.dex */
public interface AnnotatedMember<X> extends Annotated {
    AnnotatedType<X> getDeclaringType();

    Member getJavaMember();

    boolean isStatic();
}
