package uol.collective.offer.commons.test.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;



@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface SequenceName
{

    public abstract String name() default "";

    public abstract String schema() default "";
    
}
