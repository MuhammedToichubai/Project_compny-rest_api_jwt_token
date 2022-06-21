package rest_api_jwt_token.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import rest_api_jwt_token.models.enums.StudyFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author Muhammed Toichubai
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "id_generator_student")

    @SequenceGenerator(
            name = "id_generator_student",
            sequenceName = "student_seq",
            allocationSize = 1
    )
    private Long id;

    private String name;

    private String surname;

    private String email;

    @Column(name = "study_format")
    private StudyFormat studyFormat;

    @Column(name = "local_date_time")
    @CreatedDate
    private LocalDateTime localDateTime;

    @Column(name = "is_active")
    private boolean isActive;

    @ManyToOne()
    @JsonIgnore
    private Group group;

}
