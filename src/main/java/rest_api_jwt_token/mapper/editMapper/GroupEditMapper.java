package rest_api_jwt_token.mapper.editMapper;

import rest_api_jwt_token.dto.request.GroupRequest;
import rest_api_jwt_token.models.Group;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Muhammed Toichubai
 */
@Component
public class GroupEditMapper {

    public Group save(GroupRequest request) {
        if (request == null) {
            return null;
        }
        Group group = new Group();
        creatingGroup(group, request);
        return group;
    }

    public void update(Group group, GroupRequest request) {
        creatingGroup(group, request);
    }

    private void creatingGroup(Group group, GroupRequest request) {
        group.setGroupName(request.getGroupName());
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        group.setStart(LocalDate.parse(request.getStart(), dateTimeFormatter));
        group.setFinish(LocalDate.parse(request.getFinish(), dateTimeFormatter));
        group.setLocalDateTime(LocalDateTime.now());
        group.setActive(true);
    }
}
