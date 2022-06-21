package rest_api_jwt_token.apis;

import rest_api_jwt_token.dto.request.TeacherRequest;
import rest_api_jwt_token.dto.response.ResponseDeleted;
import rest_api_jwt_token.dto.response.TeacherResponse;
import rest_api_jwt_token.services.TeacherService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Muhammed Toichubai
 */
@RestController
@RequestMapping("/api/teacher")
public class TeacherApi {

    private final TeacherService service;

    public TeacherApi(TeacherService service) {
        this.service = service;
    }

    @PostMapping("/creat")
    public TeacherResponse creat(@RequestBody TeacherRequest request) {
        return service.save(request);
    }

    @PutMapping("/update/{id}")
    public TeacherResponse update(@PathVariable Long id, @RequestBody TeacherRequest request) {
        return service.update(id, request);
    }

    @GetMapping("/find/{id}")
    public TeacherResponse findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseDeleted deleteGroupById(@PathVariable Long id){
        return service.delete(id);
    }

    @GetMapping("/findAll")
    public List<TeacherResponse> findAll() {
        return service.findAll();
    }
}
