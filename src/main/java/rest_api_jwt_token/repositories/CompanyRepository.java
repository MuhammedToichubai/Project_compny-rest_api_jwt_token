package rest_api_jwt_token.repositories;

import rest_api_jwt_token.models.Company;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Muhammed Toichubai
 */
public interface CompanyRepository extends JpaRepository<Company,Long> {
}
