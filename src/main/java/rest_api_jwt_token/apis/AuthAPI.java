package rest_api_jwt_token.apis;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rest_api_jwt_token.dto.request.RegisterRequest;
import rest_api_jwt_token.dto.request.ValidationExceptionType;
import rest_api_jwt_token.dto.response.LoginResponse;
import rest_api_jwt_token.dto.response.RegisterResponse;
import rest_api_jwt_token.mapper.LoginMapper;
import rest_api_jwt_token.models.usersandroles.User;
import rest_api_jwt_token.repositories.UserRepository;
import rest_api_jwt_token.security.jwt.JwtTokenUtil;
import rest_api_jwt_token.services.UserService;

/**
 * @author Muhammed Toichubai
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("api/jwt")
public class AuthAPI {

    private final UserService userService;
    private final UserRepository repository;
    private final JwtTokenUtil jwtTokenUtil;
    private final LoginMapper loginMapper;

    @PostMapping("login")
    public ResponseEntity<LoginResponse> getLogin(@RequestBody RegisterRequest request) {
        try {
            
            UsernamePasswordAuthenticationToken token =
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());
            User user = repository.findByEmail(token.getName()).get();
            return ResponseEntity.ok()
                    .body(loginMapper.loginView(jwtTokenUtil.generateToken(user), ValidationExceptionType.SUCCESSFUL, user));
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(loginMapper.loginView("",
                    ValidationExceptionType.LOGIN_FAILED, null));
        }
    }

    @PostMapping("registration")
    public RegisterResponse create(@RequestBody RegisterRequest request){
        return userService.create(request);
    }
}
