package rest_api_jwt_token.repositories;

import rest_api_jwt_token.models.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Muhammed Toichubai
 */
public interface TeacherRepository extends JpaRepository<Teacher,Long> {
}
