package rest_api_jwt_token.mapper.editMapper;

import rest_api_jwt_token.dto.request.TeacherRequest;
import rest_api_jwt_token.models.Teacher;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author Muhammed Toichubai
 */
@Component
public class TeacherEditMapper {

    public Teacher save(TeacherRequest request) {
        if (request == null) {
            return null;
        }
        Teacher teacher = new Teacher();
        teacher.setName(request.getName());
        teacher.setSurname(request.getSurname());
        teacher.setLocalDateTime(LocalDateTime.now());
        teacher.setActive(true);
        return teacher;
    }

    public void update(Teacher teacher, TeacherRequest request) {
        teacher.setName(request.getName());
        teacher.setSurname(request.getSurname());
        teacher.setLocalDateTime(LocalDateTime.now());
        teacher.setActive(true);
    }
}
