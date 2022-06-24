package rest_api_jwt_token.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import rest_api_jwt_token.models.usersandroles.User;

import javax.validation.ValidationException;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

//    @Query("select o from User o where o.email = :email")
//    Optional<User> getByEmail(String email);
//
//    default User finByEmail(String email){
//        Optional<User> optionalUser = getByEmail(email);
//        if (optionalUser.isEmpty()){
//            throw new ValidationException("not found by email : "+email);
//        }
//        return optionalUser.get();
//    }
}