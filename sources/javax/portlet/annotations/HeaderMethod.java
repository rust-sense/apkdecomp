package javax.portlet.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
/* loaded from: classes2.dex */
public @interface HeaderMethod {
    String contentType() default "*/*";

    String include() default "";

    int ordinal() default 0;

    String portletMode() default "";

    String[] portletNames();
}
