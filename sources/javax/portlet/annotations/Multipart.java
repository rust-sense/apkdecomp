package javax.portlet.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
/* loaded from: classes2.dex */
public @interface Multipart {
    int fileSizeThreshold() default 0;

    String location() default "";

    long maxFileSize() default -1;

    long maxRequestSize() default -1;

    boolean supported();
}
