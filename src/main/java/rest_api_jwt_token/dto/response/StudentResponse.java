package rest_api_jwt_token.dto.response;

import rest_api_jwt_token.models.enums.StudyFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * @author Muhammed Toichubai
 */
@Getter
@Setter
public class StudentResponse {

    private Long id;
    private String name;
    private String surname;
    private String email;
    private StudyFormat studyFormat;
    private LocalDateTime localDateTime;
    private boolean isActive;

}
