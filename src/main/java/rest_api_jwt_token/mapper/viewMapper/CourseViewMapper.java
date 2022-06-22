package rest_api_jwt_token.mapper.viewMapper;

import rest_api_jwt_token.dto.response.CourseResponse;
import rest_api_jwt_token.models.Course;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Muhammed Toichubai
 */
@Component
public class CourseViewMapper {

    public CourseResponse viewCourse(Course course) {
        if (course == null) {
            return null;
        }
        CourseResponse courseResponse = new CourseResponse();
        courseResponse.setId(course.getId());
        courseResponse.setCourseName(course.getCourseName());
        courseResponse.setDuration(course.getDuration());
        courseResponse.setCompanyName(course.getCompany().getCompanyName());
        courseResponse.setLocalDateTime(course.getLocalDateTime());
        courseResponse.setActive(course.isActive());
        return courseResponse;
    }

    public List<CourseResponse> view(List<Course> courses) {
        List<CourseResponse> responses = new ArrayList<>();
        for (Course course : courses) {
            responses.add(viewCourse(course));
        }
        return responses;
    }
}
