package rest_api_jwt_token.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.List;

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
    @NotEmpty
    private String courseName;

    @NotEmpty
    @Column(name = "course_duration")
    private String duration;

    @ManyToMany(mappedBy = "courses", fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Group> group;

    @OneToOne(mappedBy = "course", cascade = {CascadeType.REMOVE, CascadeType.MERGE})
    private Teacher teacher;

    @ManyToOne
    @JsonIgnore
    private Company company;

    @Column(name = "local_date_time")
    @CreatedDate
    private LocalDateTime localDateTime;

    @Column(name = "is_active")
    private boolean isActive;

}
