package rest_api_jwt_token.services;

import rest_api_jwt_token.dto.request.CourseRequest;
import rest_api_jwt_token.dto.response.CourseResponse;
import rest_api_jwt_token.dto.response.ResponseDeleted;
import rest_api_jwt_token.exceptions.BadRequest;
import rest_api_jwt_token.exceptions.ThisNotFoundException;
import rest_api_jwt_token.models.Company;
import rest_api_jwt_token.models.Course;
import rest_api_jwt_token.mapper.editMapper.CourseEditMapper;
import rest_api_jwt_token.mapper.viewMapper.CourseViewMapper;
import rest_api_jwt_token.repositories.CompanyRepository;
import rest_api_jwt_token.repositories.CourseRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

/**
 * @author Muhammed Toichubai
 */
@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final CourseEditMapper editMapper;
    private final CourseViewMapper viewMapper;
    private final CompanyRepository companyRepository;

    public CourseService(CourseRepository repository, CourseEditMapper editMapper, CourseViewMapper viewMapper, CompanyRepository companyRepository) {
        this.courseRepository = repository;
        this.editMapper = editMapper;
        this.viewMapper = viewMapper;
        this.companyRepository = companyRepository;
    }

    public CourseResponse save(CourseRequest request) {
        Course course = editMapper.creat(request);
        Course verifiedCourse = checkName(course);
        verifiedCourse.setCompany(getCompanyToCourse(request.getCompanyId()));
        courseRepository.save(verifiedCourse);
        return viewMapper.viewCourse(course);
    }

    private Company getCompanyToCourse(Long id){
        return companyRepository.findById(id)
                .orElseThrow( () -> new ThisNotFoundException(
                        "Company whit id = " +id +" not found!"
                ));
    }

    private Course getCourseThroughId(Long courseId){
        return courseRepository.findById(courseId)
                .orElseThrow( () -> new ThisNotFoundException(
                        "Course whit id = " +courseId +" not found!"
                ));
    }

    @Transactional
    public CourseResponse update(Long id, CourseRequest request) {
        Course course = getCourseThroughId(id);
        editMapper.update(course, request);
        return viewMapper.viewCourse(courseRepository.save(course));
    }

    public CourseResponse findById(Long id) {
        Course course = getCourseThroughId(id);
        return viewMapper.viewCourse(course);
    }

    public ResponseDeleted delete(Long courseId) {

        boolean exists = courseRepository.existsById(courseId);
        if (!exists) {
            throw new ThisNotFoundException(
                    "Course whit id = " + courseId + " not found!"
            );
        }
        courseRepository.deleteById(courseId);

        return new ResponseDeleted(
                "DELETED",
                "Successfully deleted course !"
        );

    }

    public List<CourseResponse> findAll() {
        return viewMapper.view(courseRepository.findAll());
    }

    public Course checkName(Course course){
        List<Course> courses = courseRepository.findAll();
        for (Course cor : courses) {
            if (Objects.equals(cor.getCourseName(), course.getCourseName())){
                throw new BadRequest(
                        "There is a course with that name : "+course.getCourseName()
                                +"! Need to create with different names."
                );
            }
        }
        return course;
    }
}
