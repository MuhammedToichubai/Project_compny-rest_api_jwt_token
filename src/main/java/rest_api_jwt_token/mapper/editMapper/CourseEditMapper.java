package rest_api_jwt_token.mapper.editMapper;

import org.springframework.stereotype.Component;
import rest_api_jwt_token.dto.request.CourseRequest;
import rest_api_jwt_token.models.Course;

import java.time.LocalDateTime;

/**
 * @author Muhammed Toichubai
 */
@Component
public class CourseEditMapper {

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
