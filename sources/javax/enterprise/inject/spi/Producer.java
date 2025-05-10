package javax.enterprise.inject.spi;

import java.util.Set;
import javax.enterprise.context.spi.CreationalContext;

/* loaded from: classes2.dex */
public interface Producer<T> {
    void dispose(T t);

    Set<InjectionPoint> getInjectionPoints();

    T produce(CreationalContext<T> creationalContext);
}
