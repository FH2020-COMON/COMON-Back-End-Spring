package com.on.spring.entity.user;

import com.on.spring.entity.company.Company;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, String> {
    public Optional<User> findByEmail(String email);
    public Optional<User> findByCompany(Company company);
}
