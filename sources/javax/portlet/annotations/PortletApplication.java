package javax.portlet.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
/* loaded from: classes2.dex */
public @interface PortletApplication {
    CustomPortletMode[] customPortletModes() default {};

    CustomWindowState[] customWindowStates() default {};

    String defaultNamespaceURI() default "";

    EventDefinition[] events() default {};

    PublicRenderParameterDefinition[] publicParams() default {};

    String resourceBundle() default "";

    RuntimeOption[] runtimeOptions() default {};

    UserAttribute[] userAttributes() default {};

    String version() default "3.0";
}
