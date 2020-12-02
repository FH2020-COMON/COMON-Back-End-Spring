package com.on.spring.entity.glass;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GlassRepository extends CrudRepository<Glass, Long> {
}
