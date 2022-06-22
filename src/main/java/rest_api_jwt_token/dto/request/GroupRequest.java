package rest_api_jwt_token.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author Muhammed Toichubai
 */
@Getter
@Setter
public class GroupRequest {

    private String GroupName;
    private String Start;
    private String finish;
    private List<Long> course;
}
