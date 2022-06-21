package rest_api_jwt_token.services;

import rest_api_jwt_token.dto.request.CourseRequest;
import rest_api_jwt_token.dto.response.CourseResponse;
import rest_api_jwt_token.dto.response.ResponseDeleted;
import rest_api_jwt_token.exceptions.ThisNotFoundException;
import rest_api_jwt_token.models.Course;
import rest_api_jwt_token.mapper.editMapper.CourseEditMapper;
import rest_api_jwt_token.mapper.viewMapper.CourseViewMapper;
import rest_api_jwt_token.repositories.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Muhammed Toichubai
 */
@Service
public class CourseService {

    private final CourseRepository repository;
    private final CourseEditMapper editMapper;
    private final CourseViewMapper viewMapper;

    public CourseService(CourseRepository repository, CourseEditMapper editMapper, CourseViewMapper viewMapper) {
        this.repository = repository;
        this.editMapper = editMapper;
        this.viewMapper = viewMapper;
    }

    public CourseResponse save(CourseRequest request) {
        Course course = editMapper.creat(request);
        repository.save(course);
        return viewMapper.viewCourse(course);
    }

    private Course getCourseThroughId(Long courseId){
        return repository.findById(courseId)
                .orElseThrow( () -> new ThisNotFoundException(
                        "Course whit id = " +courseId +" not found!"
                ));
    }

    public CourseResponse update(Long id, CourseRequest request) {
        Course course = getCourseThroughId(id);
        editMapper.update(course, request);
        return viewMapper.viewCourse(repository.save(course));
    }

    public CourseResponse findById(Long id) {
        Course course = getCourseThroughId(id);
        return viewMapper.viewCourse(course);
    }

    public ResponseDeleted delete(Long courseId) {

        boolean exists = repository.existsById(courseId);
        if (!exists) {
            throw new ThisNotFoundException(
                    "Course whit id = " + courseId + " not found!"
            );
        }
        repository.deleteById(courseId);

        return new ResponseDeleted(
                "DELETED",
                "Successfully deleted course !"
        );

    }

    public List<CourseResponse> findAll() {
        return viewMapper.view(repository.findAll());
    }
}
