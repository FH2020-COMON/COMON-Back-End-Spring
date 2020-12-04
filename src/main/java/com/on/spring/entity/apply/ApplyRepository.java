package com.on.spring.entity.apply;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ApplyRepository extends CrudRepository<Apply, Long> {
    public Optional<Apply> findByApplyId(Long applyId);
    public List<Apply> findAllByCompanyId(Long companyId);
}
