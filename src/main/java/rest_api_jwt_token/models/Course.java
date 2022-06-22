package rest_api_jwt_token.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.List;

import static javax.persistence.CascadeType.*;
import static javax.persistence.FetchType.EAGER;

/**
 * @author Muhammed Toichubai
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "id_generator_course")

    @SequenceGenerator(
            name = "id_generator_course",
            sequenceName = "course_seq",
            allocationSize = 1
    )
    private Long id;

    @Column(name = "course_Name")
    private String courseName;

    @Column(name = "course_duration")
    private String duration;

    @ManyToMany(fetch = EAGER, mappedBy = "courses", cascade = {MERGE, REFRESH,DETACH,REMOVE})
    private List<Group> groups;

    @OneToOne(cascade = ALL, mappedBy = "course")
    private Teacher teacher;

    @ManyToOne(cascade = {MERGE, DETACH, REFRESH})
    @JoinColumn(name = "company_id")
    private Company company;

    @Column(name = "local_date_time")
    @CreatedDate
    private LocalDateTime localDateTime;

    @Column(name = "is_active")
    private boolean isActive;

}
