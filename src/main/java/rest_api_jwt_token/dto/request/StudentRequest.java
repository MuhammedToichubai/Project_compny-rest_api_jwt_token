package rest_api_jwt_token.dto.request;

import rest_api_jwt_token.models.enums.StudyFormat;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Muhammed Toichubai
 */
@Getter
@Setter
public class StudentRequest {
    private String name;
    private String surname;
    private String email;
    private StudyFormat studyFormat;
    private Long groupId;


}
