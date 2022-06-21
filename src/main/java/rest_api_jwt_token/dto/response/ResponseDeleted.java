package rest_api_jwt_token.dto.response;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Muhammed Toichubai
 */
@Getter
@Setter
public class ResponseDeleted {

    private String status;

    private String message;

    public ResponseDeleted(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public ResponseDeleted() {
    }
}