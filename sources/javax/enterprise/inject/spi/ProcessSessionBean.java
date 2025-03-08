package javax.enterprise.inject.spi;

/* loaded from: classes2.dex */
public interface ProcessSessionBean<X> extends ProcessManagedBean<Object> {
    String getEjbName();

    SessionBeanType getSessionBeanType();
}
