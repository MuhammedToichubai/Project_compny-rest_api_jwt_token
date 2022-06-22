package rest_api_jwt_token.dto.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

/**
 * @author Muhammed Toichubai
 */
@Getter
@Setter
public class TeacherRequest {

    private String name;
    private String surname;
    private Long courseId;

}
