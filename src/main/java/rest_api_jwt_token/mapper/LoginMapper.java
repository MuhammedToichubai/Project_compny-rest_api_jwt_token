package rest_api_jwt_token.mapper;

import org.springframework.stereotype.Component;
import rest_api_jwt_token.dto.response.LoginResponse;
import rest_api_jwt_token.models.usersandroles.Role;
import rest_api_jwt_token.models.usersandroles.User;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Muhammed Toichubai
 */
@Component
public class LoginMapper {

    public LoginResponse loginView(String token, String message, User user){
        var loginResponse = new LoginResponse();
        if (user != null){
            setAuthority(loginResponse,user.getRoles());
        }

        loginResponse.setJwtToken(token);
        loginResponse.setMessages(message);
        return loginResponse;
    }

    private void setAuthority(LoginResponse loginResponse, List<Role> roles) {
        Set<String> authorities = new HashSet<>();
        for (Role role : roles){
            authorities.add(role.getRoleName());
        }
        loginResponse.setAuthorities(authorities);
    }
}
