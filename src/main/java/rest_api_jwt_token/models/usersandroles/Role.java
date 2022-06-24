package rest_api_jwt_token.models.usersandroles;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * @author Muhammed Toichubai
 */
@Entity
@Table(name = "roles")
@Getter@Setter
@NoArgsConstructor
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String roleName;

    @ManyToMany(targetEntity = User.class, mappedBy = "roles",cascade = CascadeType.ALL)
    List<User> users;
}
