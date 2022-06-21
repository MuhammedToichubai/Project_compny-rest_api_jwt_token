package rest_api_jwt_token.services;

import rest_api_jwt_token.dto.request.GroupRequest;
import rest_api_jwt_token.dto.response.GroupResponse;
import rest_api_jwt_token.dto.response.ResponseDeleted;
import rest_api_jwt_token.exceptions.ThisNotFoundException;
import rest_api_jwt_token.models.Group;
import rest_api_jwt_token.mapper.editMapper.GroupEditMapper;
import rest_api_jwt_token.mapper.viewMapper.GroupViewMapper;
import rest_api_jwt_token.repositories.GroupRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Muhammed Toichubai
 */
@Service
public class GroupService {

    private final GroupRepository repository;
    private final GroupEditMapper editMapper;
    private final GroupViewMapper viewMapper;

    public GroupService(GroupRepository repository, GroupEditMapper editMapper, GroupViewMapper viewMapper) {
        this.repository = repository;
        this.editMapper = editMapper;
        this.viewMapper = viewMapper;
    }

    public GroupResponse creat(GroupRequest request) {
        Group group = editMapper.save(request);
        repository.save(group);
        return viewMapper.viewGroup(group);
    }

    private Group getGroupThroughId(Long id){
        return repository.findById(id)
                .orElseThrow( () -> new ThisNotFoundException(
                        "Group whit id = " +id +" not found!"
                ));
    }

    public GroupResponse update(Long id, GroupRequest request) {
        Group group = getGroupThroughId(id);
        editMapper.update(group, request);
        return viewMapper.viewGroup(group);
    }

    public GroupResponse findById(Long id) {
        Group group = getGroupThroughId(id);
        return viewMapper.viewGroup(group);
    }

    public ResponseDeleted delete(Long groupId) {

        boolean exists = repository.existsById(groupId);
        if (!exists) {
            throw new ThisNotFoundException(
                    "Group whit id = " + groupId + " not found!"
            );
        }
        repository.deleteById(groupId);

        return new ResponseDeleted(
                "DELETED",
                "Successfully deleted group !"
        );

    }

    public List<GroupResponse> findAll() {
        return viewMapper.view(repository.findAll());
    }
}
