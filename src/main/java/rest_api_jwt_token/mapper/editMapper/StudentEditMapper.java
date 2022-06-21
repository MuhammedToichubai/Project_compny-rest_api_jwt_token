package rest_api_jwt_token.mapper.editMapper;

import rest_api_jwt_token.dto.request.StudentRequest;
import rest_api_jwt_token.models.Student;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author Muhammed Toichubai
 */
@Component
public class StudentEditMapper {
    public Student save(StudentRequest request){
        if (request ==null){
            return  null;
        }
        Student student = new Student();
        student.setName(request.getName());
        student.setSurname(request.getSurname());
        student.setEmail(request.getEmail());
        student.setStudyFormat(request.getStudyFormat());
        student.setLocalDateTime(LocalDateTime.now());
        student.setActive(true);
        return student;
    }
    public  void update(Student student, StudentRequest request){
        student.setName(request.getName());
        student.setSurname(request.getSurname());
        student.setEmail(request.getEmail());
        student.setStudyFormat(request.getStudyFormat());
        student.setLocalDateTime(LocalDateTime.now());
        student.setActive(true);
    }
}
