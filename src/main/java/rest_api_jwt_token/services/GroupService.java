package rest_api_jwt_token.services;

import rest_api_jwt_token.dto.request.GroupRequest;
import rest_api_jwt_token.dto.response.GroupResponse;
import rest_api_jwt_token.dto.response.ResponseDeleted;
import rest_api_jwt_token.exceptions.BadRequest;
import rest_api_jwt_token.exceptions.ThisNotFoundException;
import rest_api_jwt_token.models.Course;
import rest_api_jwt_token.models.Group;
import rest_api_jwt_token.mapper.editMapper.GroupEditMapper;
import rest_api_jwt_token.mapper.viewMapper.GroupViewMapper;
import rest_api_jwt_token.repositories.CourseRepository;
import rest_api_jwt_token.repositories.GroupRepository;
import org.springframework.stereotype.Service;
import rest_api_jwt_token.repositories.StudentRepository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Muhammed Toichubai
 */
@Service
public class GroupService {

    private final GroupRepository groupRepository;
    private final GroupEditMapper editMapper;
    private final GroupViewMapper viewMapper;
    private final CourseRepository courseRepository;

    public GroupService(GroupRepository repository, GroupEditMapper editMapper, GroupViewMapper viewMapper, CourseRepository courseRepository, StudentRepository studentRepository) {
        this.groupRepository = repository;
        this.editMapper = editMapper;
        this.viewMapper = viewMapper;
        this.courseRepository = courseRepository;
    }

    public GroupResponse save(GroupRequest request) {
        Group group = editMapper.save(request);
        Group verifiedGroup = checkName(group);
        verifiedGroup.setCourses(getCoursesToGroup(request.getCourse()));
        groupRepository.save(verifiedGroup);
        return viewMapper.viewGroup(group);
    }

    private List<Course> getCoursesToGroup(List<Long> courseId ){
        List<Course> courses = new ArrayList<>();
        for (Long course : courseId) {
            courses.add( courseRepository.findById(course)
                    .orElseThrow(() -> new ThisNotFoundException(
                            "Course whit id = " + courseId + " not found!"
                    ))
            );
        }
        return courses;
    }

    private Group getGroupThroughId(Long id){
        return groupRepository.findById(id)
                .orElseThrow( () -> new ThisNotFoundException(
                        "Group whit id = " +id +" not found!"
                ));
    }

    @Transactional
    public GroupResponse update(Long id, GroupRequest request) {
        Group group = getGroupThroughId(id);
        editMapper.update(group, request);
        return viewMapper.viewGroup(group);
    }

    public GroupResponse findById(Long id) {
        Group group = getGroupThroughId(id);
        return viewMapper.viewGroup(group);
    }

    public ResponseDeleted delete(Long groupId) {

        boolean exists = groupRepository.existsById(groupId);
        if (!exists) {
            throw new ThisNotFoundException(
                    "Group whit id = " + groupId + " not found!"
            );
        }
        groupRepository.deleteById(groupId);

        return new ResponseDeleted(
                "DELETED",
                "Successfully deleted group !"
        );

    }

    public List<GroupResponse> findAll() {
        return viewMapper.view(groupRepository.findAll());
    }

    public Group checkName(Group group){
        List<Group> groups = groupRepository.findAll();
        for (Group gr : groups) {
            if (Objects.equals(gr.getGroupName(), group.getGroupName())){
                throw new BadRequest(
                        "There is a group with that name : "+group.getGroupName()
                                +"! Need to create with different names."
                );
            }
        }
        return group;
    }
}
