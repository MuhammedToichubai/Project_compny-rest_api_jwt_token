package rest_api_jwt_token.services;

import rest_api_jwt_token.dto.request.StudentRequest;
import rest_api_jwt_token.dto.request.TeacherRequest;
import rest_api_jwt_token.dto.response.ResponseDeleted;
import rest_api_jwt_token.dto.response.StudentResponse;
import rest_api_jwt_token.dto.response.TeacherResponse;
import rest_api_jwt_token.exceptions.BadRequest;
import rest_api_jwt_token.exceptions.ThisNotFoundException;
import rest_api_jwt_token.models.Course;
import rest_api_jwt_token.models.Student;
import rest_api_jwt_token.models.Teacher;
import rest_api_jwt_token.mapper.editMapper.TeacherEditMapper;
import rest_api_jwt_token.mapper.viewMapper.TeacherViewMapper;
import rest_api_jwt_token.repositories.CourseRepository;
import rest_api_jwt_token.repositories.TeacherRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author Muhammed Toichubai
 */
@Service

public class TeacherService {

    private final TeacherRepository teacherRepository;
    private final TeacherEditMapper editMapper;
    private final TeacherViewMapper viewMapper;
    private final CourseRepository courseRepository;

    public TeacherService(TeacherRepository repository, TeacherEditMapper editMapper, TeacherViewMapper viewMapper, CourseRepository courseRepository) {
        this.teacherRepository = repository;
        this.editMapper = editMapper;
        this.viewMapper = viewMapper;
        this.courseRepository = courseRepository;
    }

    public TeacherResponse save(TeacherRequest request) {
        Teacher teacher = editMapper.save(request);
        Course curse = getCourseToTeacher(request.getCourseId());
        Teacher courseTeacher = curse.getTeacher();
        if (courseTeacher == null){
            teacher.setCourse(curse);
            teacherRepository.save(teacher);
        }else {
            throw new BadRequest(
                    "This course has a teacher !" +
                            " Name teacher: "+ courseTeacher.getName()+"!"
            );
        }

        return viewMapper.viewTeacher(teacher);
    }

    private Course getCourseToTeacher(Long id){
        return courseRepository.findById(id)
                .orElseThrow( () -> new ThisNotFoundException(
                        "Course whit id = " +id +" not found!"
                ));
    }

    private Teacher getTeacherThroughId(Long id){
        return teacherRepository.findById(id)
                .orElseThrow( () -> new ThisNotFoundException(
                        "Teacher whit id = " +id +" not found!"
                ));
    }

    @Transactional
    public TeacherResponse update(Long id, TeacherRequest request) {
        Teacher teacher = getTeacherThroughId(id);
        editMapper.update(teacher, request);
        return viewMapper.viewTeacher(teacherRepository.save(teacher));
    }

    public TeacherResponse findById(Long id) {
        Teacher teacher = getTeacherThroughId(id);
        return viewMapper.viewTeacher(teacher);
    }

    public ResponseDeleted delete(Long teacherId) {

        boolean exists = teacherRepository.existsById(teacherId);
        if (!exists) {
            throw new ThisNotFoundException(
                    "Teacher whit id = " + teacherId + " not found!"
            );
        }
        teacherRepository.deleteById(teacherId);

        return new ResponseDeleted(
                "DELETED",
                "Successfully deleted teacher !"
        );
    }

    public List<TeacherResponse> findAll() {
        return viewMapper.view(teacherRepository.findAll());
    }
}
