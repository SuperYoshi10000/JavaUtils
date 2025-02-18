package local.ytk.util.annotation;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.TYPE_USE})
public @interface Immutable {
    boolean canCreate() default true;
    boolean canChange() default false;
    boolean canRead() default true;
    boolean deep() default false;
}
