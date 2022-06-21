package rest_api_jwt_token.mapper.editMapper;

import rest_api_jwt_token.dto.request.CourseRequest;
import rest_api_jwt_token.exceptions.ThisNotFoundException;
import rest_api_jwt_token.models.Company;
import rest_api_jwt_token.models.Course;
import org.springframework.stereotype.Component;
import rest_api_jwt_token.repositories.CompanyRepository;
import rest_api_jwt_token.services.CompanyService;

import java.time.LocalDateTime;

/**
 * @author Muhammed Toichubai
 */
@Component
public class CourseEditMapper {

    private CompanyService companyService;
    private CompanyRepository companyRepository;

    private Company courseForTheCompany(Long id){
        Course course = new Course();
       return companyRepository.findById(id)
                .orElseThrow( () -> new ThisNotFoundException(
                        "Company whit id = " +id +" not found!"
                ));
    }


    public Course creat(CourseRequest courseRequest) {
        if (courseRequest == null) {
            return null;
        }
        Course course = new Course();
        course.setCourseName(courseRequest.getCourseName());
        course.setDuration(courseRequest.getDuration());
        course.setLocalDateTime(LocalDateTime.now());
        course.setActive(true);
        return course;
    }

    public void update(Course course, CourseRequest courseRequest) {
        course.setCourseName(courseRequest.getCourseName());
        course.setDuration(courseRequest.getDuration());
        course.setLocalDateTime(LocalDateTime.now());
        course.setActive(true);
    }
}
