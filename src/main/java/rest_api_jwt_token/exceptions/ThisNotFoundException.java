package rest_api_jwt_token.exceptions;

/**
 * @author Muhammed Toichubai
 */
public class ThisNotFoundException extends RuntimeException {
    public ThisNotFoundException() {
    }

    public ThisNotFoundException(String message) {
        super(message);
    }
}