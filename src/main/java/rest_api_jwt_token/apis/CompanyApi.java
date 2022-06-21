package rest_api_jwt_token.apis;

import org.springframework.web.bind.annotation.*;
import rest_api_jwt_token.dto.request.CompanyRequest;
import rest_api_jwt_token.dto.response.CompanyResponse;
import rest_api_jwt_token.dto.response.ResponseDeleted;
import rest_api_jwt_token.services.CompanyService;

import java.util.List;

/**
 * @author Muhammed Toichubai
 */
@RestController
@RequestMapping("/api/company")
public class CompanyApi {

    private final CompanyService companyService;

    public CompanyApi(CompanyService service) {
        this.companyService = service;
    }


    @PostMapping("/save")
    public CompanyResponse save(@RequestBody CompanyRequest request) {
        return companyService.save(request);
    }

    @PutMapping("/update/{id}")
    public CompanyResponse update(@PathVariable Long id, @RequestBody CompanyRequest request) {
        return companyService.update(id, request);
    }

    @GetMapping("/findById/{id}")
    public CompanyResponse findById(@PathVariable Long id) {
        return companyService.findById(id);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseDeleted deleteCompanyById(@PathVariable Long id) {

        return companyService.deleteBy(id);
    }

    @GetMapping("/findAll")
    public List<CompanyResponse> findAll() {
        return companyService.findAll();
    }
}
