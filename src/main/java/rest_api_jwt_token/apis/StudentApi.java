package rest_api_jwt_token.apis;

import rest_api_jwt_token.dto.request.StudentRequest;
import rest_api_jwt_token.dto.response.ResponseDeleted;
import rest_api_jwt_token.dto.response.StudentResponse;
import rest_api_jwt_token.services.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Muhammed Toichubai
 */
@RestController
@RequestMapping("/api/student")
public class StudentApi {

    private final StudentService service;

    public StudentApi(StudentService service) {
        this.service = service;
    }

    @PostMapping("/save")
    public StudentResponse save(@RequestBody StudentRequest request) {
        return service.save(request);
    }

    @PutMapping("/update/{id}")
    public StudentResponse update(@PathVariable Long id, @RequestBody StudentRequest request) {
        return service.update(id, request);
    }

    @GetMapping("/find/{id}")
    public StudentResponse findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseDeleted deleteGroupById(@PathVariable Long id){
        return service.delete(id);
    }

    @GetMapping("/findAll")
    public List<StudentResponse> findAll() {
        return service.findAll();
    }

}
