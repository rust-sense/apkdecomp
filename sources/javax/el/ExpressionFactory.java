package javax.el;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.Properties;

/* loaded from: classes2.dex */
public abstract class ExpressionFactory {
    public abstract Object coerceToType(Object obj, Class<?> cls);

    public abstract MethodExpression createMethodExpression(ELContext eLContext, String str, Class<?> cls, Class<?>[] clsArr);

    public abstract ValueExpression createValueExpression(Object obj, Class<?> cls);

    public abstract ValueExpression createValueExpression(ELContext eLContext, String str, Class<?> cls);

    public Map<String, Method> getInitFunctionMap() {
        return null;
    }

    public ELResolver getStreamELResolver() {
        return null;
    }

    public static ExpressionFactory newInstance() {
        return newInstance(null);
    }

    public static ExpressionFactory newInstance(Properties properties) {
        return (ExpressionFactory) FactoryFinder.find("javax.el.ExpressionFactory", "com.sun.el.ExpressionFactoryImpl", properties);
    }
}
