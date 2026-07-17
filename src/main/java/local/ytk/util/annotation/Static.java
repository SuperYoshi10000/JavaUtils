package local.ytk.util.annotation;

import java.lang.annotation.*;

/**
 * All fields and methods are static; instances cannot be created
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Static {}
