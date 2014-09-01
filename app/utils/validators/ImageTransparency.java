package utils.validators;

import net.sf.oval.configuration.annotation.Constraint;
import utils.validators.impl.ImageTransparencyCheck;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Image Transparency checking validator annotation
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Constraint(checkWith = ImageTransparencyCheck.class)
public @interface ImageTransparency {

    /** Validation message */
    String message() default ImageTransparencyCheck.message;
}
