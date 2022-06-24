package rest_api_jwt_token.repositories;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import rest_api_jwt_token.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Muhammed Toichubai
 */
public interface StudentRepository extends JpaRepository<Student,Long> {

    @Query("select s from Student s where upper(s.name) like concat('%',:text,'%') " +
            "or upper(s.surname) like concat('%',:text,'%') or upper(s.email) like concat('%',:text,'%')")
    List<Student> searchAndPagination(@Param("text") String text, Pageable pageable);
}
