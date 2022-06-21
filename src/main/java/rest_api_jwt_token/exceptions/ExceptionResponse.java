package rest_api_jwt_token.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

/**
 * @author Muhammed Toichubai
 */
@Getter
@Setter
public class ExceptionResponse {

    private HttpStatus status;

    private String exceptionClassName;

    private String message;

    public ExceptionResponse() {
    }

    public ExceptionResponse(HttpStatus status, String exceptionClassName, String message) {
        this.status = status;
        this.exceptionClassName = exceptionClassName;
        this.message = message;
    }
}
