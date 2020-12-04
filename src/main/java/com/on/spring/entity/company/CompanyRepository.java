package com.on.spring.entity.company;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompanyRepository extends CrudRepository<Company, Long> {
    public Optional<Company> findByCompanyId(Long companyId);
    public List<Company> findAll(Pageable pageable);
}
