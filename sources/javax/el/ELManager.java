package javax.el;

import java.lang.reflect.Method;

/* loaded from: classes2.dex */
public class ELManager {
    private StandardELContext elContext;

    public static ExpressionFactory getExpressionFactory() {
        return ELUtil.getExpressionFactory();
    }

    public StandardELContext getELContext() {
        if (this.elContext == null) {
            this.elContext = new StandardELContext(getExpressionFactory());
        }
        return this.elContext;
    }

    public ELContext setELContext(ELContext eLContext) {
        StandardELContext standardELContext = this.elContext;
        this.elContext = new StandardELContext(eLContext);
        return standardELContext;
    }

    public void addBeanNameResolver(BeanNameResolver beanNameResolver) {
        getELContext().addELResolver(new BeanNameELResolver(beanNameResolver));
    }

    public void addELResolver(ELResolver eLResolver) {
        getELContext().addELResolver(eLResolver);
    }

    public void mapFunction(String str, String str2, Method method) {
        getELContext().getFunctionMapper().mapFunction(str, str2, method);
    }

    public void setVariable(String str, ValueExpression valueExpression) {
        getELContext().getVariableMapper().setVariable(str, valueExpression);
    }

    public void importStatic(String str) throws ELException {
        getELContext().getImportHandler().importStatic(str);
    }

    public void importClass(String str) throws ELException {
        getELContext().getImportHandler().importClass(str);
    }

    public void importPackage(String str) {
        getELContext().getImportHandler().importPackage(str);
    }

    public Object defineBean(String str, Object obj) {
        Object obj2 = getELContext().getBeans().get(str);
        getELContext().getBeans().put(str, obj);
        return obj2;
    }

    public void addEvaluationListener(EvaluationListener evaluationListener) {
        getELContext().addEvaluationListener(evaluationListener);
    }
}
