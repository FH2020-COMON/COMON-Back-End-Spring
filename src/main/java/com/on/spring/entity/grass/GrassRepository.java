package com.on.spring.entity.grass;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GrassRepository extends CrudRepository<Grass, Long> {
}
