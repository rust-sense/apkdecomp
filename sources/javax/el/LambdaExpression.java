package javax.el;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes2.dex */
public class LambdaExpression {
    private ELContext context;
    private Map<String, Object> envirArgs;
    private ValueExpression expression;
    private List<String> formalParameters;

    public void setELContext(ELContext eLContext) {
        this.context = eLContext;
    }

    public LambdaExpression(List<String> list, ValueExpression valueExpression) {
        new ArrayList();
        this.envirArgs = null;
        this.formalParameters = list;
        this.expression = valueExpression;
        this.envirArgs = new HashMap();
    }

    public Object invoke(ELContext eLContext, Object... objArr) throws ELException {
        HashMap hashMap = new HashMap();
        hashMap.putAll(this.envirArgs);
        int i = 0;
        for (String str : this.formalParameters) {
            if (i >= objArr.length) {
                throw new ELException("Expected Argument " + str + " missing in Lambda Expression");
            }
            hashMap.put(str, objArr[i]);
            i++;
        }
        eLContext.enterLambdaScope(hashMap);
        Object value = this.expression.getValue(eLContext);
        if (value instanceof LambdaExpression) {
            ((LambdaExpression) value).envirArgs.putAll(hashMap);
        }
        eLContext.exitLambdaScope();
        return value;
    }

    public Object invoke(Object... objArr) {
        return invoke(this.context, objArr);
    }
}
