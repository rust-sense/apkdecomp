package javax.interceptor;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Map;

/* loaded from: classes2.dex */
public interface InvocationContext {
    Constructor<?> getConstructor();

    Map<String, Object> getContextData();

    Method getMethod();

    Object[] getParameters();

    Object getTarget();

    Object getTimer();

    Object proceed() throws Exception;

    void setParameters(Object[] objArr);
}
