package rest_api_jwt_token.apis;

import org.springframework.security.access.prepost.PreAuthorize;
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
@PreAuthorize("hasAnyAuthority('ADMIN','TEACHER')")
public class TeacherAPI {

    private final TeacherService service;

    public TeacherAPI(TeacherService service) {
        this.service = service;
    }

    @PostMapping("/save")
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

    @PreAuthorize("hasAuthority('STUDENT')")
    @GetMapping("/findAll")
    public List<TeacherResponse> findAll() {
        return service.findAll();
    }
}
