package sourse.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sourse.crud.entity.Company;

public interface CompanyRepository  extends JpaRepository<Company, String> {
}
