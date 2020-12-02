package com.on.spring.service.company;

import com.on.spring.entity.company.Company;
import com.on.spring.entity.company.CompanyRepository;
import com.on.spring.entity.user.User;
import com.on.spring.entity.user.UserRepository;
import com.on.spring.exception.CompanyNotFoundException;
import com.on.spring.exception.UserNotFoundException;
import com.on.spring.payload.request.RegisterCompanyRequest;
import com.on.spring.payload.response.CompanyListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository companyRepository;
    private final UserRepository userRepository;

    @Value("${spring.file.path}")
    private String filePath;

    public void registerCompany(RegisterCompanyRequest request) {
        List<User> users = new ArrayList<>();

        for (String email : request.getUserEmails()) {
            users.add(userRepository.findByEmail(email).orElseThrow(UserNotFoundException::new));
        }

        companyRepository.save(
                Company.builder()
                .ceoName(request.getCeoName())
                .companyName(request.getCompanyName())
                .ceoName(request.getCeoName()).like(0L)
                .users(users)
                .build()
        );
    }

    @Override
    public void addUserToCompany(String email, Long companyId) {
        companyRepository.findByCompanyId(companyId)
                .map(company -> company.addUser(userRepository.findByEmail(email)
                            .orElseThrow(UserNotFoundException::new)))
                .map(companyRepository::save)
                .orElseThrow(CompanyNotFoundException::new);
    }

    @Override
    public List<User> viewCompanyMember(Long companyId) {
        return userRepository.findAllByCompanyId(companyId);
    }

    @Override
    public List<CompanyListResponse> companyList() {
        List<CompanyListResponse> responses = new ArrayList<>();

        for (Company company : companyRepository.findAll()) {
            responses.add(new CompanyListResponse(company.getCompanyName(), company.getCompanyId(), filePath + company.getCompanyId() + "/preview.png"));
        }

        return responses;
    }

    @Override
    public void uploadCompanyIntroduceImage(MultipartFile file) {
        
    }
}
