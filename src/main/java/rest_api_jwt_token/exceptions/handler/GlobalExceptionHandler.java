package rest_api_jwt_token.exceptions.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import rest_api_jwt_token.exceptions.BadRequest;
import rest_api_jwt_token.exceptions.ExceptionResponse;
import rest_api_jwt_token.exceptions.ThisNotFoundException;

/**
 * @author Muhammed Toichubai
 */
//Bul tak exception handler ekenin bilish uchun
// jana bul front end_ke joop bere alysh uchun  bul annotation_dy koiobuz
@RestControllerAdvice
public class GlobalExceptionHandler {

    //404
    @ExceptionHandler(ThisNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionResponse handlerNotFoundException(ThisNotFoundException e){
        return new ExceptionResponse(
                HttpStatus.NOT_FOUND,
                e.getClass().getSimpleName(),
                e.getMessage()
        );

    }

    //400
    @ExceptionHandler(BadRequest.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponse handlerNotFoundException(BadRequest e){
        return new ExceptionResponse(
                HttpStatus.NOT_FOUND,
                e.getClass().getSimpleName(),
                e.getMessage()
        );

    }

}
