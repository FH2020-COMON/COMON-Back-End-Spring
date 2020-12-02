package com.on.spring.service.company;

import com.on.spring.entity.company.Company;
import com.on.spring.entity.company.CompanyRepository;
import com.on.spring.entity.user.User;
import com.on.spring.entity.user.UserRepository;
import com.on.spring.exception.UserNotFoundException;
import com.on.spring.payload.request.RegisterCompanyRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository companyRepository;
    private final UserRepository userRepository;

    public void registerCompany(RegisterCompanyRequest request) {
        List<User> users = new ArrayList<>();

        for (String email : request.getUserEmails()) {
            users.add(userRepository.findByEmail(email).orElseThrow(UserNotFoundException::new));
        }

        companyRepository.save(
                Company.builder()
                .ceoName(request.getCeoName())
                .companyName(request.getCompanyName())
                .ceoName(request.getCeoName())
                .users(users)
                .build()
        );
    }
}
