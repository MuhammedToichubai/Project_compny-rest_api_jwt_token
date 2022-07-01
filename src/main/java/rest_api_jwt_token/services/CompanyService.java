package rest_api_jwt_token.services;

import rest_api_jwt_token.dto.request.CompanyRequest;
import rest_api_jwt_token.dto.response.CompanyResponse;
import rest_api_jwt_token.dto.response.ResponseDeleted;
import rest_api_jwt_token.exceptions.BadRequest;
import rest_api_jwt_token.exceptions.ThisNotFoundException;
import rest_api_jwt_token.models.Company;
import rest_api_jwt_token.mapper.editMapper.CompanyEditMapper;
import rest_api_jwt_token.mapper.viewMapper.CompanyViewMapper;
import rest_api_jwt_token.repositories.CompanyRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

/**
 * @author Muhammed Toichubai
 */
@Service
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final CompanyEditMapper editMapper;
    private final CompanyViewMapper viewMapper;

    public CompanyService(CompanyRepository companyRepository, CompanyEditMapper editMapper, CompanyViewMapper viewMapper) {
        this.companyRepository = companyRepository;
        this.editMapper = editMapper;
        this.viewMapper = viewMapper;
    }

    public CompanyResponse save(CompanyRequest request) {
        Company company = editMapper.saveCompany(request);
        Company verifiedCompany = checkName(company);
        companyRepository.save(verifiedCompany);
        return viewMapper.viewCompany(company);
    }

    @Transactional
    public CompanyResponse update(Long companyId, CompanyRequest request) {
        Company company = companyRepository.findById(companyId)
                .orElseThrow( () -> new ThisNotFoundException(
                "Company whit id = " +companyId +" not found!"
        ));
        editMapper.updateCompany(company, request);
        return viewMapper.viewCompany(companyRepository.save(company));
    }

    public CompanyResponse findById(Long companyId) {
        Company company = companyRepository.findById(companyId)
                .orElseThrow( () -> new ThisNotFoundException(
                        "Company whit id = " +companyId +" not found!"
                ));
        return viewMapper.viewCompany(company);
    }

    public ResponseDeleted deleteBy(Long companyId) {

        boolean exists = companyRepository.existsById(companyId);
        if (!exists) {
            throw new ThisNotFoundException(
                    "Company whit id = " + companyId + " not found!"
            );
        }
        companyRepository.deleteById(companyId);

        return new ResponseDeleted(
                "DELETED",
                "Successfully deleted company !"
        );

    }

    public List<CompanyResponse> findAll() {
        return viewMapper.view(companyRepository.findAll());
    }

    public Company checkName(Company company){
     List<Company> companies = companyRepository.findAll();
        for (Company com : companies) {
            if (Objects.equals(com.getCompanyName(), company.getCompanyName())){
                throw new BadRequest(
                        "There is a company with that name: '"+company.getCompanyName()
                        +"'! Need to create with different names."
                );
            }
        }
      return company;
    }

}
