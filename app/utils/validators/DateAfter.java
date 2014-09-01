package utils.validators;

import net.sf.oval.configuration.annotation.Constraint;
import utils.validators.impl.DateAfterCheck;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Date is after another certain date in object checking validator annotation
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Constraint(checkWith = DateAfterCheck.class)
public @interface DateAfter {

    /** Validation message */
    String message() default DateAfterCheck.message;

    /** Name of date that should be before */
    String before();
}
