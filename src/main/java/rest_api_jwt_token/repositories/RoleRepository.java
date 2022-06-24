package rest_api_jwt_token.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import rest_api_jwt_token.models.usersandroles.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByRoleName(String roleName);
}