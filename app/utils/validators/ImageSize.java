package utils.validators;

import net.sf.oval.configuration.annotation.Constraint;
import utils.validators.impl.ImageSizeCheck;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Image Size checking validator annotation
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Constraint(checkWith = ImageSizeCheck.class)
public @interface ImageSize {

    /** Validation message */
    String message() default ImageSizeCheck.message;

    /** Image width */
    int width();

    /** Image height */
    int height();
}
