package javax.enterprise.inject.spi;

import java.util.List;

/* loaded from: classes2.dex */
public interface AnnotatedCallable<X> extends AnnotatedMember<X> {
    List<AnnotatedParameter<X>> getParameters();
}
