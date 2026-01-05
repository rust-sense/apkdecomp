package javax.enterprise.inject.spi;

import java.util.List;

/* loaded from: classes2.dex */
public interface AfterTypeDiscovery {
    void addAnnotatedType(AnnotatedType<?> annotatedType, String str);

    List<Class<?>> getAlternatives();

    List<Class<?>> getDecorators();

    List<Class<?>> getInterceptors();
}
