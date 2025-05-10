package javax.enterprise.inject.spi;

/* loaded from: classes2.dex */
public interface InjectionTargetFactory<T> {
    InjectionTarget<T> createInjectionTarget(Bean<T> bean);
}
