package javax.el;

/* loaded from: classes2.dex */
public abstract class ValueExpression extends Expression {
    public abstract Class<?> getExpectedType();

    public abstract Class<?> getType(ELContext eLContext);

    public abstract Object getValue(ELContext eLContext);

    public ValueReference getValueReference(ELContext eLContext) {
        return null;
    }

    public abstract boolean isReadOnly(ELContext eLContext);

    public abstract void setValue(ELContext eLContext, Object obj);
}
