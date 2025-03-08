package javax.enterprise.context.spi;

/* loaded from: classes2.dex */
public interface CreationalContext<T> {
    void push(T t);

    void release();
}
