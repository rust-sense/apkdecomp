package javax.el;

/* loaded from: classes2.dex */
public abstract class VariableMapper {
    public abstract ValueExpression resolveVariable(String str);

    public abstract ValueExpression setVariable(String str, ValueExpression valueExpression);
}
