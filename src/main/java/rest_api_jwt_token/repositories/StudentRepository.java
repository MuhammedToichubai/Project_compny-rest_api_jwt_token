package rest_api_jwt_token.repositories;

import rest_api_jwt_token.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Muhammed Toichubai
 */
public interface StudentRepository extends JpaRepository<Student,Long> {
}
