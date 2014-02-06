package models;

/**
 * POJO class for server json response representation.
 * Status - SUCCESS or FAIL.
 * Result - an Object with some result information, for example a Map with form fields errors.
 */
public class JsonResponse {

    private String status;
    private String message;
    private Object result;

    public JsonResponse(final String status, final String message, final Object result) {
        this.status = status;
        this.message = message;
        this.result = result;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}
