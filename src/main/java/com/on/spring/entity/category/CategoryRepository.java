package com.on.spring.entity.category;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {
    public List<Category> findAllByCompanyId(Long companyId);
}
