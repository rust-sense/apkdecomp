package javax.el;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes2.dex */
public class StandardELContext extends ELContext {
    private Map<String, Object> beans;
    private CompositeELResolver customResolvers;
    private ELContext delegate;
    private ELResolver elResolver;
    private FunctionMapper functionMapper;
    private Map<String, Method> initFunctionMap;
    private ELResolver streamELResolver;
    private VariableMapper variableMapper;

    Map<String, Object> getBeans() {
        return this.beans;
    }

    public StandardELContext(ExpressionFactory expressionFactory) {
        this.delegate = null;
        this.beans = new HashMap();
        this.streamELResolver = expressionFactory.getStreamELResolver();
        this.initFunctionMap = expressionFactory.getInitFunctionMap();
    }

    public StandardELContext(ELContext eLContext) {
        this.delegate = null;
        this.beans = new HashMap();
        this.delegate = eLContext;
        CompositeELResolver compositeELResolver = new CompositeELResolver();
        compositeELResolver.add(new BeanNameELResolver(new LocalBeanNameResolver()));
        CompositeELResolver compositeELResolver2 = new CompositeELResolver();
        this.customResolvers = compositeELResolver2;
        compositeELResolver.add(compositeELResolver2);
        compositeELResolver.add(eLContext.getELResolver());
        this.elResolver = compositeELResolver;
        this.functionMapper = eLContext.getFunctionMapper();
        this.variableMapper = eLContext.getVariableMapper();
        setLocale(eLContext.getLocale());
    }

    @Override // javax.el.ELContext
    public void putContext(Class cls, Object obj) {
        ELContext eLContext = this.delegate;
        if (eLContext != null) {
            eLContext.putContext(cls, obj);
        } else {
            super.putContext(cls, obj);
        }
    }

    @Override // javax.el.ELContext
    public Object getContext(Class cls) {
        ELContext eLContext = this.delegate;
        if (eLContext != null) {
            return eLContext.getContext(cls);
        }
        return super.getContext(cls);
    }

    @Override // javax.el.ELContext
    public ELResolver getELResolver() {
        if (this.elResolver == null) {
            CompositeELResolver compositeELResolver = new CompositeELResolver();
            compositeELResolver.add(new BeanNameELResolver(new LocalBeanNameResolver()));
            CompositeELResolver compositeELResolver2 = new CompositeELResolver();
            this.customResolvers = compositeELResolver2;
            compositeELResolver.add(compositeELResolver2);
            ELResolver eLResolver = this.streamELResolver;
            if (eLResolver != null) {
                compositeELResolver.add(eLResolver);
            }
            compositeELResolver.add(new StaticFieldELResolver());
            compositeELResolver.add(new MapELResolver());
            compositeELResolver.add(new ResourceBundleELResolver());
            compositeELResolver.add(new ListELResolver());
            compositeELResolver.add(new ArrayELResolver());
            compositeELResolver.add(new BeanELResolver());
            this.elResolver = compositeELResolver;
        }
        return this.elResolver;
    }

    public void addELResolver(ELResolver eLResolver) {
        getELResolver();
        this.customResolvers.add(eLResolver);
    }

    @Override // javax.el.ELContext
    public FunctionMapper getFunctionMapper() {
        if (this.functionMapper == null) {
            this.functionMapper = new DefaultFunctionMapper(this.initFunctionMap);
        }
        return this.functionMapper;
    }

    @Override // javax.el.ELContext
    public VariableMapper getVariableMapper() {
        if (this.variableMapper == null) {
            this.variableMapper = new DefaultVariableMapper();
        }
        return this.variableMapper;
    }

    private static class DefaultFunctionMapper extends FunctionMapper {
        private Map<String, Method> functions;

        DefaultFunctionMapper(Map<String, Method> map) {
            this.functions = null;
            this.functions = map == null ? new HashMap() : new HashMap(map);
        }

        @Override // javax.el.FunctionMapper
        public Method resolveFunction(String str, String str2) {
            return this.functions.get(str + ":" + str2);
        }

        @Override // javax.el.FunctionMapper
        public void mapFunction(String str, String str2, Method method) {
            this.functions.put(str + ":" + str2, method);
        }
    }

    private static class DefaultVariableMapper extends VariableMapper {
        private Map<String, ValueExpression> variables;

        private DefaultVariableMapper() {
            this.variables = null;
        }

        @Override // javax.el.VariableMapper
        public ValueExpression resolveVariable(String str) {
            Map<String, ValueExpression> map = this.variables;
            if (map == null) {
                return null;
            }
            return map.get(str);
        }

        @Override // javax.el.VariableMapper
        public ValueExpression setVariable(String str, ValueExpression valueExpression) {
            if (this.variables == null) {
                this.variables = new HashMap();
            }
            if (valueExpression == null) {
                return this.variables.remove(str);
            }
            return this.variables.put(str, valueExpression);
        }
    }

    private class LocalBeanNameResolver extends BeanNameResolver {
        @Override // javax.el.BeanNameResolver
        public boolean canCreateBean(String str) {
            return true;
        }

        @Override // javax.el.BeanNameResolver
        public boolean isReadOnly(String str) {
            return false;
        }

        private LocalBeanNameResolver() {
        }

        @Override // javax.el.BeanNameResolver
        public boolean isNameResolved(String str) {
            return StandardELContext.this.beans.containsKey(str);
        }

        @Override // javax.el.BeanNameResolver
        public Object getBean(String str) {
            return StandardELContext.this.beans.get(str);
        }

        @Override // javax.el.BeanNameResolver
        public void setBeanValue(String str, Object obj) {
            StandardELContext.this.beans.put(str, obj);
        }
    }
}
