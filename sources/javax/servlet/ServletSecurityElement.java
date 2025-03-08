package javax.servlet;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import javax.servlet.annotation.HttpMethodConstraint;
import javax.servlet.annotation.ServletSecurity;

/* loaded from: classes2.dex */
public class ServletSecurityElement extends HttpConstraintElement {
    private Collection<HttpMethodConstraintElement> methodConstraints;
    private Collection<String> methodNames;

    public ServletSecurityElement() {
        this.methodConstraints = new HashSet();
        this.methodNames = Collections.emptySet();
    }

    public ServletSecurityElement(HttpConstraintElement httpConstraintElement) {
        super(httpConstraintElement.getEmptyRoleSemantic(), httpConstraintElement.getTransportGuarantee(), httpConstraintElement.getRolesAllowed());
        this.methodConstraints = new HashSet();
        this.methodNames = Collections.emptySet();
    }

    public ServletSecurityElement(Collection<HttpMethodConstraintElement> collection) {
        collection = collection == null ? new HashSet<>() : collection;
        this.methodConstraints = collection;
        this.methodNames = checkMethodNames(collection);
    }

    public ServletSecurityElement(HttpConstraintElement httpConstraintElement, Collection<HttpMethodConstraintElement> collection) {
        super(httpConstraintElement.getEmptyRoleSemantic(), httpConstraintElement.getTransportGuarantee(), httpConstraintElement.getRolesAllowed());
        collection = collection == null ? new HashSet<>() : collection;
        this.methodConstraints = collection;
        this.methodNames = checkMethodNames(collection);
    }

    public ServletSecurityElement(ServletSecurity servletSecurity) {
        super(servletSecurity.value().value(), servletSecurity.value().transportGuarantee(), servletSecurity.value().rolesAllowed());
        this.methodConstraints = new HashSet();
        for (HttpMethodConstraint httpMethodConstraint : servletSecurity.httpMethodConstraints()) {
            this.methodConstraints.add(new HttpMethodConstraintElement(httpMethodConstraint.value(), new HttpConstraintElement(httpMethodConstraint.emptyRoleSemantic(), httpMethodConstraint.transportGuarantee(), httpMethodConstraint.rolesAllowed())));
        }
        this.methodNames = checkMethodNames(this.methodConstraints);
    }

    public Collection<HttpMethodConstraintElement> getHttpMethodConstraints() {
        return Collections.unmodifiableCollection(this.methodConstraints);
    }

    public Collection<String> getMethodNames() {
        return Collections.unmodifiableCollection(this.methodNames);
    }

    private Collection<String> checkMethodNames(Collection<HttpMethodConstraintElement> collection) {
        HashSet hashSet = new HashSet();
        Iterator<HttpMethodConstraintElement> it = collection.iterator();
        while (it.hasNext()) {
            String methodName = it.next().getMethodName();
            if (!hashSet.add(methodName)) {
                throw new IllegalArgumentException("Duplicate HTTP method name: " + methodName);
            }
        }
        return hashSet;
    }
}
