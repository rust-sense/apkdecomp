package javax.enterprise.context.spi;

/* loaded from: classes2.dex */
public interface Contextual<T> {
    T create(CreationalContext<T> creationalContext);

    void destroy(T t, CreationalContext<T> creationalContext);
}
