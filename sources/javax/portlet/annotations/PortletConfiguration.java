package javax.portlet.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
/* loaded from: classes2.dex */
public @interface PortletConfiguration {
    boolean asyncSupported() default false;

    int cacheExpirationTime() default 0;

    boolean cacheScopePublic() default false;

    Dependency[] dependencies() default {};

    LocaleString[] description() default {};

    LocaleString[] displayName() default {};

    InitParameter[] initParams() default {};

    LocaleString[] keywords() default {};

    Multipart multipart() default @Multipart(supported = false);

    String portletName();

    Preference[] prefs() default {};

    String[] publicParams() default {};

    String resourceBundle() default "";

    SecurityRoleRef[] roleRefs() default {};

    RuntimeOption[] runtimeOptions() default {};

    LocaleString[] shortTitle() default {};

    String[] supportedLocales() default {"en"};

    Supports[] supports() default {@Supports(mimeType = "text/html")};

    LocaleString[] title() default {};
}
