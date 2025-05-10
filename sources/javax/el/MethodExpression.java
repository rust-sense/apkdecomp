package javax.el;

/* loaded from: classes2.dex */
public abstract class MethodExpression extends Expression {
    public abstract MethodInfo getMethodInfo(ELContext eLContext);

    public abstract Object invoke(ELContext eLContext, Object[] objArr);

    public boolean isParametersProvided() {
        return false;
    }

    @Deprecated
    public boolean isParmetersProvided() {
        return isParametersProvided();
    }
}
