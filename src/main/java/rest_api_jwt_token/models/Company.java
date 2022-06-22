package rest_api_jwt_token.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

import static javax.persistence.CascadeType.ALL;

/**
 * @author Muhammed Toichubai
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "companies")
public class Company {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "id_generator_company")

    @SequenceGenerator(
            name = "id_generator_company",
            sequenceName = "company_seq",
            allocationSize = 1
    )
    private Long id;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "company_country")
    private String localCountry;

    @OneToMany(mappedBy = "company", orphanRemoval = true,cascade = ALL)
    private List<Course> courses;

    @Column(name = "local_date_time")
    @CreatedDate
    private LocalDateTime localDateTime;

    @Column(name = "is_active")
    private boolean isActive;

}
