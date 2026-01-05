package javax.enterprise.event;

import java.lang.annotation.Annotation;
import javax.enterprise.util.TypeLiteral;

/* loaded from: classes2.dex */
public interface Event<T> {
    void fire(T t);

    <U extends T> Event<U> select(Class<U> cls, Annotation... annotationArr);

    <U extends T> Event<U> select(TypeLiteral<U> typeLiteral, Annotation... annotationArr);

    Event<T> select(Annotation... annotationArr);
}
