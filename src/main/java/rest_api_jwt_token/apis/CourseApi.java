package rest_api_jwt_token.apis;

import rest_api_jwt_token.dto.request.CourseRequest;
import rest_api_jwt_token.dto.response.CourseResponse;
import rest_api_jwt_token.dto.response.ResponseDeleted;
import rest_api_jwt_token.services.CourseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Muhammed Toichubai
 */
@RestController
@RequestMapping("/api/course")
public class CourseApi {

    private final CourseService courseService;

    public CourseApi(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping("/save")
    public CourseResponse save(@RequestBody CourseRequest request) {

        return courseService.save(request);
    }

    @PutMapping("/update/{id}")
    public CourseResponse update(@PathVariable Long id, @RequestBody CourseRequest request) {
        return courseService.update(id, request);
    }

    @GetMapping("/find/{id}")
    public CourseResponse findById(@PathVariable Long id) {
        return courseService.findById(id);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseDeleted deleteCourseById(@PathVariable Long id){
        return courseService.delete(id);
    }

    @GetMapping("/findAll")
    public List<CourseResponse> findAll() {
        return courseService.findAll();
    }
}
