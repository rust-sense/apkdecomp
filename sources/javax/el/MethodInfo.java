package javax.el;

/* loaded from: classes2.dex */
public class MethodInfo {
    private String name;
    private Class<?>[] paramTypes;
    private Class<?> returnType;

    public String getName() {
        return this.name;
    }

    public Class<?>[] getParamTypes() {
        return this.paramTypes;
    }

    public Class<?> getReturnType() {
        return this.returnType;
    }

    public MethodInfo(String str, Class<?> cls, Class<?>[] clsArr) {
        this.name = str;
        this.returnType = cls;
        this.paramTypes = clsArr;
    }
}
