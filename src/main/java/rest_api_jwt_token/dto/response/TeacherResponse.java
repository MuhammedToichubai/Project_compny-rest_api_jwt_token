package rest_api_jwt_token.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * @author Muhammed Toichubai
 */
@Getter
@Setter
public class TeacherResponse {

    private Long id;
    private String name;
    private String surname;
    private LocalDateTime localDateTime;
    private boolean isActive;
}
