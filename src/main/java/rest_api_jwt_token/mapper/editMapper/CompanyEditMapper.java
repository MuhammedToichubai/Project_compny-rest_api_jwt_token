package rest_api_jwt_token.mapper.editMapper;

import rest_api_jwt_token.dto.request.CompanyRequest;
import rest_api_jwt_token.models.Company;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author Muhammed Toichubai
 */


@Component
public class CompanyEditMapper {

    public Company saveCompany(CompanyRequest request){
        if (request == null){
            return  null;
        }
        Company company = new Company();
        company.setCompanyName(request.getName());
        company.setLocalCountry(request.getLocalCountry());
        company.setLocalDateTime(LocalDateTime.now());
        company.setActive(true);
        return company;
    }
    public void updateCompany(Company company,CompanyRequest request){
        company.setCompanyName(request.getName() );
        company.setLocalCountry(request.getLocalCountry());
        company.setLocalDateTime(LocalDateTime.now());
        company.setActive(true);
    }
}
