package models;

import java.util.Map;

/**
 * POJO class for server json response representation.
 * Status - SUCCESS or FAIL.
 * Result - an Object with some errors information, for example a Map with form fields errors.
 */
public class JsonResponse {

    private String status;
    private String message;
    private Map<String, String> errors;

    public JsonResponse(final String status, final String message, final Map<String, String> result) {
        this.status = status;
        this.message = message;
        this.errors = result;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(final String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public void setErrors(final Map<String, String> errors) {
        this.errors = errors;
    }
}
