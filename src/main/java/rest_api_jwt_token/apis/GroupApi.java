package rest_api_jwt_token.apis;

import rest_api_jwt_token.dto.request.GroupRequest;
import rest_api_jwt_token.dto.response.GroupResponse;
import rest_api_jwt_token.dto.response.ResponseDeleted;
import rest_api_jwt_token.services.GroupService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Muhammed Toichubai
 */
@RestController
@RequestMapping("/api/group")
public class GroupApi {

    public final GroupService service;

    public GroupApi(GroupService service) {
        this.service = service;
    }

    @PostMapping("/save")
    public GroupResponse save(@RequestBody GroupRequest request) {
        return service.save(request);
    }

    @PutMapping("/update/{id}")
    public GroupResponse update(@PathVariable Long id, @RequestBody GroupRequest request) {
        return service.update(id, request);
    }

    @GetMapping("/find/{id}")
    public GroupResponse findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseDeleted deleteGroupById(@PathVariable Long id){
        return service.delete(id);
    }

    @GetMapping("/findAll")
    public List<GroupResponse> findAll() {
        return service.findAll();
    }
}

