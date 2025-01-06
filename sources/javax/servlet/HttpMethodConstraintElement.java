package javax.servlet;

/* loaded from: classes2.dex */
public class HttpMethodConstraintElement extends HttpConstraintElement {
    private String methodName;

    public String getMethodName() {
        return this.methodName;
    }

    public HttpMethodConstraintElement(String str) {
        if (str == null || str.length() == 0) {
            throw new IllegalArgumentException("invalid HTTP method name");
        }
        this.methodName = str;
    }

    public HttpMethodConstraintElement(String str, HttpConstraintElement httpConstraintElement) {
        super(httpConstraintElement.getEmptyRoleSemantic(), httpConstraintElement.getTransportGuarantee(), httpConstraintElement.getRolesAllowed());
        if (str == null || str.length() == 0) {
            throw new IllegalArgumentException("invalid HTTP method name");
        }
        this.methodName = str;
    }
}
