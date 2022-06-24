package rest_api_jwt_token.services;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import rest_api_jwt_token.dto.request.StudentRequest;
import rest_api_jwt_token.dto.response.ResponseDeleted;
import rest_api_jwt_token.dto.response.StudentResponse;
import rest_api_jwt_token.exceptions.BadRequest;
import rest_api_jwt_token.exceptions.ThisNotFoundException;
import rest_api_jwt_token.models.Group;
import rest_api_jwt_token.models.Student;
import rest_api_jwt_token.mapper.editMapper.StudentEditMapper;
import rest_api_jwt_token.mapper.viewMapper.StudentViewMapper;
import rest_api_jwt_token.repositories.GroupRepository;
import rest_api_jwt_token.repositories.StudentRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Muhammed Toichubai
 */
@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final StudentEditMapper editMapper;
    private final StudentViewMapper viewMapper;
    private final GroupRepository groupRepository;

    public StudentService(StudentRepository repository, StudentEditMapper editMapper, StudentViewMapper viewMapper, GroupRepository groupRepository) {
        this.studentRepository = repository;
        this.editMapper = editMapper;
        this.viewMapper = viewMapper;
        this.groupRepository = groupRepository;
    }

    public StudentResponse save(StudentRequest request) {
        Student student = editMapper.save(request);
        Student verifiedStudent = checkName(student);
        verifiedStudent.setGroup(getGroupToStudent(request.getGroupId()));
        studentRepository.save(verifiedStudent);
        return viewMapper.viewStudent(student);
    }

    private Group getGroupToStudent(Long id){
        return groupRepository.findById(id)
                .orElseThrow( () -> new ThisNotFoundException(
                        "Group whit id = " +id +" not found!"
                ));
    }

    private Student getStudentThroughId(Long id){
        return studentRepository.findById(id)
                .orElseThrow( () -> new ThisNotFoundException(
                        "Student whit id = " +id +" not found!"
                ));
    }

    @Transactional
    public StudentResponse update(Long id, StudentRequest request) {
        Student student = getStudentThroughId(id);
        editMapper.update(student, request);
        return viewMapper.viewStudent(studentRepository.save(student));
    }

    public StudentResponse findById(Long id) {
        Student student = getStudentThroughId(id);
        return viewMapper.viewStudent(student);
    }

    public ResponseDeleted delete(Long studentId) {

        boolean exists = studentRepository.existsById(studentId);
        if (!exists) {
            throw new ThisNotFoundException(
                    "Student whit id = " + studentId + " not found!"
            );
        }
        studentRepository.deleteById(studentId);

        return new ResponseDeleted(
                "DELETED",
                "Successfully deleted student !"
        );

    }
    public List<StudentResponse> findAll(){
        return viewMapper.view(studentRepository.findAll());
    }

    public Student checkName(Student student){
        List<Student> students = studentRepository.findAll();
        for (Student st : students) {
            if (Objects.equals(st.getEmail(), student.getEmail())){
                throw new BadRequest(
                        "Email must be unique !"
                );
            }
        }
        return student;
    }
}
