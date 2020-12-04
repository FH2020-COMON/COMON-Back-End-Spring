package com.on.spring.entity.companylike;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyLikeRepository extends CrudRepository<CompanyLike, Long> {
    public Optional<CompanyLike> findByCompanyIdAndUserId(Long companyId, String userId);
}
