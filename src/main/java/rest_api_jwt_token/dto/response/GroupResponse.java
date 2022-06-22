package rest_api_jwt_token.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Muhammed Toichubai
 */
@Getter
@Setter
public class GroupResponse {

    private Long id;
    private String groupName;
    private String start;
    private String finish;
    private List<String> coursesName;
    private LocalDateTime localDateTime;
    private boolean isActive;

}
