package javax.servlet;

import javax.servlet.annotation.ServletSecurity;

/* loaded from: classes2.dex */
public class HttpConstraintElement {
    private ServletSecurity.EmptyRoleSemantic emptyRoleSemantic;
    private String[] rolesAllowed;
    private ServletSecurity.TransportGuarantee transportGuarantee;

    public ServletSecurity.EmptyRoleSemantic getEmptyRoleSemantic() {
        return this.emptyRoleSemantic;
    }

    public ServletSecurity.TransportGuarantee getTransportGuarantee() {
        return this.transportGuarantee;
    }

    public HttpConstraintElement() {
        this(ServletSecurity.EmptyRoleSemantic.PERMIT);
    }

    public HttpConstraintElement(ServletSecurity.EmptyRoleSemantic emptyRoleSemantic) {
        this(emptyRoleSemantic, ServletSecurity.TransportGuarantee.NONE, new String[0]);
    }

    public HttpConstraintElement(ServletSecurity.TransportGuarantee transportGuarantee, String... strArr) {
        this(ServletSecurity.EmptyRoleSemantic.PERMIT, transportGuarantee, strArr);
    }

    public HttpConstraintElement(ServletSecurity.EmptyRoleSemantic emptyRoleSemantic, ServletSecurity.TransportGuarantee transportGuarantee, String... strArr) {
        if (emptyRoleSemantic == ServletSecurity.EmptyRoleSemantic.DENY && strArr.length > 0) {
            throw new IllegalArgumentException("Deny semantic with rolesAllowed");
        }
        this.emptyRoleSemantic = emptyRoleSemantic;
        this.transportGuarantee = transportGuarantee;
        this.rolesAllowed = copyStrings(strArr);
    }

    public String[] getRolesAllowed() {
        return copyStrings(this.rolesAllowed);
    }

    private String[] copyStrings(String[] strArr) {
        String[] strArr2;
        if (strArr != null) {
            int length = strArr.length;
            strArr2 = new String[length];
            if (length > 0) {
                System.arraycopy(strArr, 0, strArr2, 0, length);
            }
        } else {
            strArr2 = null;
        }
        return strArr2 != null ? strArr2 : new String[0];
    }
}
