package utils.validators;

import play.data.validation.Check;

import java.lang.reflect.Field;
import java.util.Date;

/**
 * Checks if this date is after "startDate"
 */
public class DateAfterDateCheck extends Check {

    /**
     * Checks if date is after "startDate" in the same object,
     * otherwise makes validation error with code "validation.before"
     * @param object       Our object
     * @param date         End date
     * @return true if date is after start date, otherwise false
     */
    public boolean isSatisfied(final Object object, final Object date) {
        final Date startDate;
        final Date endDate = (Date) date;
        try {
            Class clazz = object.getClass();
            Field field = clazz.getField("startDate");
            startDate = (Date) field.get(object);
        } catch (Exception e) {
            return false;
        }
        setMessage("validation.before");
        if (endDate != null && startDate != null) {
            return endDate.after(startDate);
        } else {
            return false;
        }
    }
}
