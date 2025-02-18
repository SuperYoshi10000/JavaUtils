package local.ytk.util.annotation;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.CLASS)
@Target({ElementType.METHOD, ElementType.CONSTRUCTOR, ElementType.TYPE_PARAMETER})
public @interface AutoGeneric {
}
