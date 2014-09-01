package utils.validators;

import net.sf.oval.configuration.annotation.Constraint;
import utils.validators.impl.FileTypeCheck;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * File Type checking validator annotation
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Constraint(checkWith = FileTypeCheck.class)
public @interface FileType {

    /** Validation message */
    String message() default FileTypeCheck.message;

    /** Possible file types */
    String[] types();
}
