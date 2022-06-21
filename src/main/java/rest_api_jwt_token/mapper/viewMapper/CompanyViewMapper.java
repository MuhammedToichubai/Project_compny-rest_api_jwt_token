package rest_api_jwt_token.mapper.viewMapper;

import rest_api_jwt_token.dto.response.CompanyResponse;
import rest_api_jwt_token.models.Company;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Muhammed Toichubai
 */
@Component
public class CompanyViewMapper {
    public CompanyResponse viewCompany(Company company) {
        if (company == null) {
            return null;
        }
        CompanyResponse response = new CompanyResponse();
        response.setId(company.getId());
        response.setName(company.getCompanyName());
        response.setLocalCountry(company.getLocalCountry());
        response.setLocalDateTime(company.getLocalDateTime());
        response.setActive(company.isActive());
        return response;
    }

    public List<CompanyResponse> view(List<Company> companies) {
        List<CompanyResponse> responses = new ArrayList<>();
        for (Company response : companies) {
            responses.add(viewCompany(response));
        }
        return responses;
    }
}
