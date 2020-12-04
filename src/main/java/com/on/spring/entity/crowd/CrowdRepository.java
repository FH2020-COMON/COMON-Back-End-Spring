package com.on.spring.entity.crowd;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CrowdRepository extends CrudRepository<Crowd, Long> {
    public Optional<Crowd> findById(Long id);
    public List<Crowd> findAll();
}
