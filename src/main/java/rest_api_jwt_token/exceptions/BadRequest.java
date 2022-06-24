package rest_api_jwt_token.exceptions;

/**
 * @author Muhammed Toichubai
 */
public class BadRequest extends RuntimeException {
    public BadRequest() {
    }

    public BadRequest(String message) {
        super(message);
    }
}