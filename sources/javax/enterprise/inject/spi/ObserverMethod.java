package javax.enterprise.inject.spi;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Set;
import javax.enterprise.event.Reception;
import javax.enterprise.event.TransactionPhase;

/* loaded from: classes2.dex */
public interface ObserverMethod<T> {
    Class<?> getBeanClass();

    Set<Annotation> getObservedQualifiers();

    Type getObservedType();

    Reception getReception();

    TransactionPhase getTransactionPhase();

    void notify(T t);
}
