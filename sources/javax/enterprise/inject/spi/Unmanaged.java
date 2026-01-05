package javax.enterprise.inject.spi;

import javax.enterprise.context.spi.CreationalContext;

/* loaded from: classes2.dex */
public class Unmanaged<T> {
    private final BeanManager beanManager;
    private final InjectionTarget<T> injectionTarget;

    public Unmanaged(BeanManager beanManager, Class<T> cls) {
        this.beanManager = beanManager;
        this.injectionTarget = beanManager.getInjectionTargetFactory(beanManager.createAnnotatedType(cls)).createInjectionTarget(null);
    }

    public Unmanaged(Class<T> cls) {
        this(CDI.current().getBeanManager(), cls);
    }

    public UnmanagedInstance<T> newInstance() {
        return new UnmanagedInstance<>(this.beanManager, this.injectionTarget);
    }

    public static class UnmanagedInstance<T> {
        private final CreationalContext<T> ctx;
        private boolean disposed;
        private final InjectionTarget<T> injectionTarget;
        private T instance;

        public T get() {
            return this.instance;
        }

        private UnmanagedInstance(BeanManager beanManager, InjectionTarget<T> injectionTarget) {
            this.disposed = false;
            this.injectionTarget = injectionTarget;
            this.ctx = beanManager.createCreationalContext(null);
        }

        public UnmanagedInstance<T> produce() {
            if (this.instance != null) {
                throw new IllegalStateException("Trying to call produce() on already constructed instance");
            }
            if (this.disposed) {
                throw new IllegalStateException("Trying to call produce() on an already disposed instance");
            }
            this.instance = this.injectionTarget.produce(this.ctx);
            return this;
        }

        public UnmanagedInstance<T> inject() {
            T t = this.instance;
            if (t == null) {
                throw new IllegalStateException("Trying to call inject() before produce() was called");
            }
            if (this.disposed) {
                throw new IllegalStateException("Trying to call inject() on already disposed instance");
            }
            this.injectionTarget.inject(t, this.ctx);
            return this;
        }

        public UnmanagedInstance<T> postConstruct() {
            T t = this.instance;
            if (t == null) {
                throw new IllegalStateException("Trying to call postConstruct() before produce() was called");
            }
            if (this.disposed) {
                throw new IllegalStateException("Trying to call postConstruct() on already disposed instance");
            }
            this.injectionTarget.postConstruct(t);
            return this;
        }

        public UnmanagedInstance<T> preDestroy() {
            T t = this.instance;
            if (t == null) {
                throw new IllegalStateException("Trying to call preDestroy() before produce() was called");
            }
            if (this.disposed) {
                throw new IllegalStateException("Trying to call preDestroy() on already disposed instance");
            }
            this.injectionTarget.preDestroy(t);
            return this;
        }

        public UnmanagedInstance<T> dispose() {
            T t = this.instance;
            if (t == null) {
                throw new IllegalStateException("Trying to call dispose() before produce() was called");
            }
            if (this.disposed) {
                throw new IllegalStateException("Trying to call dispose() on already disposed instance");
            }
            this.injectionTarget.dispose(t);
            this.ctx.release();
            return this;
        }
    }
}
