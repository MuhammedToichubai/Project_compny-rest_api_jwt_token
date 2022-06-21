package rest_api_jwt_token.repositories;

import rest_api_jwt_token.models.Group;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Muhammed Toichubai
 */
public interface GroupRepository extends JpaRepository<Group,Long> {
}
