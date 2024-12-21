package sourse.crud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sourse.crud.dto.request.CompanyCreationRequest;
import sourse.crud.entity.Company;
import sourse.crud.repository.CompanyRepository;

import java.util.List;
@Service
public class CompanyService {
    @Autowired
  CompanyRepository companyRepository;
    public Company createCompany(CompanyCreationRequest request ){
        System.out.println("request"+ request);
        Company company = new Company();
        company.setName(request.getName());
        company.setAddress(request.getAddress());
        company.setFieldOfActivity(request.getFieldOfActivity());
        return companyRepository.save(company);
    };
   public List<Company> getAllCompany(){
       return companyRepository.findAll();
   };
    public Company getCompanyID (String id) {
        return companyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException( "company not found"));
    };
    public Company   updateCompany (String id, CompanyCreationRequest request) {
        System.out.println("request"+ request);
        Company company = companyRepository.findById(id).orElseThrow(() -> new RuntimeException( "company not found"));
        company.setName(request.getName());
        company.setAddress(request.getAddress());
        company.setFieldOfActivity(request.getFieldOfActivity());
        return companyRepository.save(company);
    }
    public void deleteCompanyID(String id) {
         companyRepository.deleteById(id);
    }
}
