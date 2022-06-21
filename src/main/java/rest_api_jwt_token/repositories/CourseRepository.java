package rest_api_jwt_token.repositories;

import rest_api_jwt_token.models.Course;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Muhammed Toichubai
 */
public interface CourseRepository extends JpaRepository<Course,Long> {
}
