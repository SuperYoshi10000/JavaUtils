package local.ytk.util.annotation;

import java.lang.annotation.*;

/**
 * Only one instance of this class exists; no other instances can be created
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.TYPE})
public @interface Singleton {
    Class<?> value() default Object.class;
}
