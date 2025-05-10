package javax.portlet.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
/* loaded from: classes2.dex */
public @interface PortletListener {
    LocaleString[] description() default {};

    LocaleString[] displayName() default {};

    String listenerName() default "";

    int ordinal() default 0;
}
