package javax.el;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Stack;

/* loaded from: classes2.dex */
public abstract class ELContext {
    private ImportHandler importHandler;
    private Stack<Map<String, Object>> lambdaArgs;
    private Locale locale;
    private boolean resolved;
    private HashMap<Class<?>, Object> map = new HashMap<>();
    private transient List<EvaluationListener> listeners = null;

    public abstract ELResolver getELResolver();

    public List<EvaluationListener> getEvaluationListeners() {
        return this.listeners;
    }

    public abstract FunctionMapper getFunctionMapper();

    public Locale getLocale() {
        return this.locale;
    }

    public abstract VariableMapper getVariableMapper();

    public boolean isPropertyResolved() {
        return this.resolved;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public void setPropertyResolved(boolean z) {
        this.resolved = z;
    }

    public void setPropertyResolved(Object obj, Object obj2) {
        setPropertyResolved(true);
        notifyPropertyResolved(obj, obj2);
    }

    public void putContext(Class cls, Object obj) {
        if (cls == null || obj == null) {
            throw null;
        }
        this.map.put(cls, obj);
    }

    public Object getContext(Class cls) {
        cls.getClass();
        return this.map.get(cls);
    }

    public ImportHandler getImportHandler() {
        if (this.importHandler == null) {
            this.importHandler = new ImportHandler();
        }
        return this.importHandler;
    }

    public void addEvaluationListener(EvaluationListener evaluationListener) {
        if (this.listeners == null) {
            this.listeners = new ArrayList();
        }
        this.listeners.add(evaluationListener);
    }

    public void notifyBeforeEvaluation(String str) {
        if (getEvaluationListeners() == null) {
            return;
        }
        Iterator<EvaluationListener> it = getEvaluationListeners().iterator();
        while (it.hasNext()) {
            it.next().beforeEvaluation(this, str);
        }
    }

    public void notifyAfterEvaluation(String str) {
        if (getEvaluationListeners() == null) {
            return;
        }
        Iterator<EvaluationListener> it = getEvaluationListeners().iterator();
        while (it.hasNext()) {
            it.next().afterEvaluation(this, str);
        }
    }

    public void notifyPropertyResolved(Object obj, Object obj2) {
        if (getEvaluationListeners() == null) {
            return;
        }
        Iterator<EvaluationListener> it = getEvaluationListeners().iterator();
        while (it.hasNext()) {
            it.next().propertyResolved(this, obj, obj2);
        }
    }

    public boolean isLambdaArgument(String str) {
        Stack<Map<String, Object>> stack = this.lambdaArgs;
        if (stack == null) {
            return false;
        }
        for (int size = stack.size() - 1; size >= 0; size--) {
            if (this.lambdaArgs.elementAt(size).containsKey(str)) {
                return true;
            }
        }
        return false;
    }

    public Object getLambdaArgument(String str) {
        Stack<Map<String, Object>> stack = this.lambdaArgs;
        if (stack == null) {
            return null;
        }
        for (int size = stack.size() - 1; size >= 0; size--) {
            Object obj = this.lambdaArgs.elementAt(size).get(str);
            if (obj != null) {
                return obj;
            }
        }
        return null;
    }

    public void enterLambdaScope(Map<String, Object> map) {
        if (this.lambdaArgs == null) {
            this.lambdaArgs = new Stack<>();
        }
        this.lambdaArgs.push(map);
    }

    public void exitLambdaScope() {
        Stack<Map<String, Object>> stack = this.lambdaArgs;
        if (stack != null) {
            stack.pop();
        }
    }

    public Object convertToType(Object obj, Class<?> cls) {
        boolean isPropertyResolved = isPropertyResolved();
        try {
            try {
                setPropertyResolved(false);
                ELResolver eLResolver = getELResolver();
                if (eLResolver != null) {
                    Object convertToType = eLResolver.convertToType(this, obj, cls);
                    if (isPropertyResolved()) {
                        return convertToType;
                    }
                }
                setPropertyResolved(isPropertyResolved);
                return ELUtil.getExpressionFactory().coerceToType(obj, cls);
            } catch (ELException e) {
                throw e;
            } catch (Exception e2) {
                throw new ELException(e2);
            }
        } finally {
            setPropertyResolved(isPropertyResolved);
        }
    }
}
