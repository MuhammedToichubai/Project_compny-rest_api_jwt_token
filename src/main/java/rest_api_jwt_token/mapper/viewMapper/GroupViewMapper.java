package rest_api_jwt_token.mapper.viewMapper;

import rest_api_jwt_token.dto.response.GroupResponse;
import rest_api_jwt_token.models.Group;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * @author Muhammed Toichubai
 */
@Component
public class GroupViewMapper {

    public GroupResponse viewGroup(Group group) {
        if (group == null) {
            return null;
        }
        GroupResponse response = new GroupResponse();
        response.setId(group.getId());
        response.setGroupName(group.getGroupName());
        response.setStart(group.getStart().format( DateTimeFormatter.ISO_LOCAL_DATE ));
        response.setFinish(group.getFinish().format( DateTimeFormatter.ISO_LOCAL_DATE));
        response.setLocalDateTime(group.getLocalDateTime());
        response.setActive(group.isActive());
        return response;
    }

    public List<GroupResponse> view(List<Group> groups) {
        List<GroupResponse> responses = new ArrayList<>();
        for (Group group : groups) {
            responses.add(viewGroup(group));
        }
        return responses;
    }
}
