package utils.validators.impl;

import net.sf.oval.Validator;
import net.sf.oval.configuration.annotation.AbstractAnnotationCheck;
import net.sf.oval.context.OValContext;
import net.sf.oval.exception.OValException;
import utils.validators.DateAfter;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

/**
 * Annotation {@link utils.validators.DateAfter} implementation
 * Checks if date is after certain date
 */
public class DateAfterCheck extends AbstractAnnotationCheck<DateAfter> {

    /** Error message key. */
    public final static String message = "validation.date-after";

    /** Date that should be before this date */
    private String before;

    /**
     * Reads input parameters
     * @param dateAfter annotation
     */
    @Override
    public void configure(final DateAfter dateAfter) {
        setMessage(dateAfter.message());
        this.before = dateAfter.before();
    }

    /***
     * Puts message variables in Map
     * @return map with message variable: before field name
     */
    @Override
    protected Map<String, ?> createMessageVariables() {
        final Map<String, String> variables = new TreeMap<String, String>();
        variables.put("2", before);
        return variables;
    }

    /**
     * Validates file type
     * @param validatedObject    Validated object
     * @param value              File
     * @param oValContext
     * @param validator
     * @return true if date passes validation
     * @throws net.sf.oval.exception.OValException
     */
    @Override
    public boolean isSatisfied(
            final Object validatedObject,
            final Object value,
            final OValContext oValContext,
            final Validator validator
    ) throws OValException {

        requireMessageVariablesRecreation();
        if (value == null || before == null) {
            return true;
        }

        final Date beforeDate;
        final Date thisDate = (Date) value;
        try {
            Class clazz = validatedObject.getClass();
            Field field = clazz.getField(before);
            beforeDate = (Date) field.get(validatedObject);
        } catch (Exception e) {
            return false;
        }

        if (thisDate != null && beforeDate != null) {
            return thisDate.after(beforeDate);
        } else {
            return false;
        }
    }
}
