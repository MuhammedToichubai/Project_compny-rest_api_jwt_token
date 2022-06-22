package rest_api_jwt_token.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

import static javax.persistence.CascadeType.*;

/**
 * @author Muhammed Toichubai
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "teachers")
public class Teacher {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "id_generator_teacher")

    @SequenceGenerator(
            name = "id_generator_teacher",
            sequenceName = "teacher_seq",
            allocationSize = 1
    )
    private Long id;

    private String name;

    private String surname;

    @Column(name = "local_date_time")
    @CreatedDate
    private LocalDateTime localDateTime;

    @Column(name = "is_active")
    private boolean isActive;

    @OneToOne(cascade = {MERGE, DETACH, REFRESH})
    private Course course;

}
