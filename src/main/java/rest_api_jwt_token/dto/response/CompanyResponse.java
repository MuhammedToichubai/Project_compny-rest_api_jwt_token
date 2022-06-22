package rest_api_jwt_token.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import rest_api_jwt_token.models.Course;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Muhammed Toichubai
 */
@Getter
@Setter
public class CompanyResponse {

    private Long id;
    private String name;
    private String localCountry;
    @JsonIgnore
    private List<Course> courses;
    private LocalDateTime localDateTime;
    private boolean isActive;

}
