package rest_api_jwt_token.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

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
    @NotEmpty
    private String groupName;

    @NotEmpty
    private LocalDate start;

    @NotEmpty
    private LocalDate finish;

    @Column(name = "local_date_time")
    @CreatedDate
    private LocalDateTime localDateTime;

    @Column(name = "is_active")
    private boolean isActive;


    @ManyToMany(cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    @JsonIgnore
    private List<Course> courses;

    @OneToMany(mappedBy = "group", fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Student> students;

    @ManyToOne
    @JsonIgnore
    private Company company;


}
