package rest_api_jwt_token.services;

import rest_api_jwt_token.dto.request.StudentRequest;
import rest_api_jwt_token.dto.response.ResponseDeleted;
import rest_api_jwt_token.dto.response.StudentResponse;
import rest_api_jwt_token.exceptions.ThisNotFoundException;
import rest_api_jwt_token.models.Student;
import rest_api_jwt_token.mapper.editMapper.StudentEditMapper;
import rest_api_jwt_token.mapper.viewMapper.StudentViewMapper;
import rest_api_jwt_token.repositories.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Muhammed Toichubai
 */
@Service
public class StudentService {

    private final StudentRepository repository;
    private final StudentEditMapper editMapper;
    private final StudentViewMapper viewMapper;

    public StudentService(StudentRepository repository, StudentEditMapper editMapper, StudentViewMapper viewMapper) {
        this.repository = repository;
        this.editMapper = editMapper;
        this.viewMapper = viewMapper;
    }

    public StudentResponse save(StudentRequest request) {
        Student student = editMapper.save(request);
        repository.save(student);
        return viewMapper.viewStudent(student);
    }

    private Student getStudentThroughId(Long id){
        return repository.findById(id)
                .orElseThrow( () -> new ThisNotFoundException(
                        "Student whit id = " +id +" not found!"
                ));
    }

    public StudentResponse update(Long id, StudentRequest request) {
        Student student = getStudentThroughId(id);
        editMapper.update(student, request);
        return viewMapper.viewStudent(repository.save(student));
    }

    public StudentResponse findById(Long id) {
        Student student = getStudentThroughId(id);
        return viewMapper.viewStudent(student);
    }

    public ResponseDeleted delete(Long studentId) {

        boolean exists = repository.existsById(studentId);
        if (!exists) {
            throw new ThisNotFoundException(
                    "Student whit id = " + studentId + " not found!"
            );
        }
        repository.deleteById(studentId);

        return new ResponseDeleted(
                "DELETED",
                "Successfully deleted student !"
        );

    }
    public List<StudentResponse> findAll(){
        return viewMapper.view(repository.findAll());
    }
}
