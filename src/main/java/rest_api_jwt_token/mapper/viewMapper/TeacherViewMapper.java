package rest_api_jwt_token.mapper.viewMapper;

import rest_api_jwt_token.dto.response.TeacherResponse;
import rest_api_jwt_token.models.Teacher;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Muhammed Toichubai
 */
@Component
public class TeacherViewMapper {

    public TeacherResponse viewTeacher(Teacher teacher) {

        if (teacher == null) {
            return null;
        }
        TeacherResponse response = new TeacherResponse();
        response.setId(teacher.getId());
        response.setName(teacher.getName());
        response.setSurname(teacher.getSurname());
        response.setLocalDateTime(teacher.getLocalDateTime());
        response.setActive(teacher.isActive());
        return response;
    }

    public List<TeacherResponse> view(List<Teacher> teachers) {
        List<TeacherResponse> responses = new ArrayList<>();
        for (Teacher teacher : teachers) {
            responses.add(viewTeacher(teacher));
        }
        return responses;
    }
}
