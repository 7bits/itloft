package utils;

import play.data.validation.Error;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Some support methods for Play validation
 */
public class ValidationUtils {

    /**
     * Extracts map with field validation errors <field, localised error message>
     * @param errorsMap    Play validation errors map
     * @param formName     Form name
     * @return map with field validation errors
     */
    public static Map<String, String> extractFieldValidationErrors(final Map<String, List<Error>> errorsMap, final String formName) {

        if (errorsMap == null || errorsMap.size() == 0) {
            return null;
        }

        Map<String, String> errors = new HashMap<String, String>();
        for (Map.Entry<String, List<Error>> entry : errorsMap.entrySet()) {
            String key = entry.getKey();
            if (!key.equals(formName)) {
                List<Error> errorList = entry.getValue();
                if (errorList != null && errorList.size() > 0) {
                    int formPosition = key.indexOf(formName);
                    if (formPosition > -1) {
                        // adding 1 for "."
                        String inputName = key.substring(formName.length() + 1);
                        errors.put(inputName, errorList.get(0).toString());
                    }
                }
            }
        }

        return errors;
    }

    /**
     * Extracts main form error from Play errors validation map
     * @param errorsMap    errors map
     * @param formName     form name
     * @return localized error message for form formName
     */
    public static String extractMainValidationError(final Map<String, List<Error>> errorsMap, final String formName) {
        if (errorsMap == null || errorsMap.size() == 0) {
            return null;
        }
        List<Error> mainError = errorsMap.get(formName);
        return mainError.get(0).toString();
    }
}
