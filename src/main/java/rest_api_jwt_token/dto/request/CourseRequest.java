package rest_api_jwt_token.dto.request;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Muhammed Toichubai
 */
@Getter
@Setter
public class CourseRequest {

    private String courseName;
    private String duration;
    private Long companyId;


}
