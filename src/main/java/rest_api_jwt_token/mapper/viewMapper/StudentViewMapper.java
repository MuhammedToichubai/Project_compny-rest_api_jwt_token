package rest_api_jwt_token.mapper.viewMapper;

import rest_api_jwt_token.dto.response.StudentResponse;
import rest_api_jwt_token.models.Student;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Muhammed Toichubai
 */
@Component
public class StudentViewMapper {

    public StudentResponse viewStudent(Student student) {
        if (student == null) {
            return null;
        }
        StudentResponse response = new StudentResponse();
        response.setId(student.getId());
        response.setName(student.getName());
        response.setSurname(student.getSurname());
        response.setEmail(student.getEmail());
        response.setStudyFormat(student.getStudyFormat());
        response.setGroupName(student.getGroup().getGroupName());
        response.setLocalDateTime(student.getLocalDateTime());
        response.setActive(student.isActive());
        return response;
    }

    public List<StudentResponse> view(List<Student> students) {
        List<StudentResponse> responses = new ArrayList<>();
        for (Student student : students) {
            responses.add(viewStudent(student));
        }
        return responses;
    }
}
