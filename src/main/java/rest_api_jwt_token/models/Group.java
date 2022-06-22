package rest_api_jwt_token.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static javax.persistence.CascadeType.*;
import static javax.persistence.FetchType.EAGER;

/**
 * @author Muhammed Toichubai
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "groups")
public class Group {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "id_generator_group")

    @SequenceGenerator(
            name = "id_generator_group",
            sequenceName = "group_seq",
            allocationSize = 1
    )
    private Long id;

    @Column(name = "group_name")
    private String groupName;

    private LocalDate start;

    private LocalDate finish;

    @Column(name = "local_date_time")
    @CreatedDate
    private LocalDateTime localDateTime;

    @Column(name = "is_active")
    private boolean isActive;

    @ManyToMany(cascade = {MERGE, PERSIST})
    private List<Course> courses;

    @OneToMany(cascade = ALL, mappedBy = "group")
    private List<Student> students;

}
