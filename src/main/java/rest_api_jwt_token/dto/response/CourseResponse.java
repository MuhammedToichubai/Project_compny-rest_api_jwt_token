package rest_api_jwt_token.dto.response;

import lombok.Getter;
import lombok.Setter;
import rest_api_jwt_token.models.Company;

import java.time.LocalDateTime;

/**
 * @author Muhammed Toichubai
 */
@Getter
@Setter
public class CourseResponse {

    private Long id;
    private String courseName;
    private String duration;
    private Company company;
    private LocalDateTime localDateTime;
    private boolean isActive;
}
