package io.tpd.springbootcucumber.core.annotations;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PageAccessor {

    String name() default "";

    String url() default "";
}
