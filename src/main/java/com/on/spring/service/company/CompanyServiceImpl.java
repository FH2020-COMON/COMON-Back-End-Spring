package com.on.spring.service.company;

import com.on.spring.entity.company.Company;
import com.on.spring.entity.company.CompanyRepository;
import com.on.spring.entity.companylike.CompanyLikeRepository;
import com.on.spring.entity.user.User;
import com.on.spring.entity.user.UserRepository;
import com.on.spring.entity.work.WorkRepository;
import com.on.spring.exception.CompanyNotFoundException;
import com.on.spring.exception.InvalidTokenException;
import com.on.spring.exception.UserNotFoundException;
import com.on.spring.exception.UserNotOwnerException;
import com.on.spring.payload.request.RegisterCompanyRequest;
import com.on.spring.payload.response.CompanyListResponse;
import com.on.spring.payload.response.WorkResponse;
import com.on.spring.security.auth.AuthenticationFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository companyRepository;
    private final UserRepository userRepository;
    private final CompanyLikeRepository likeRepository;
    private final AuthenticationFacade authenticationFacade;
    private final WorkRepository workRepository;

    @Value("${spring.file.path}")
    private String filePath;

    public void registerCompany(RegisterCompanyRequest request) {
        if (!userRepository.findByEmail(request.getUserEmail())
                .map(User::isOwner)
                .orElseThrow(UserNotFoundException::new)
        ) {
            throw new UserNotOwnerException();
        }

        companyRepository.save(
                Company.builder()
                .ceoName(request.getCeoName())
                .companyName(request.getCompanyName())
                .ceoName(request.getCeoName()).like(0L)
                .build()
        );
    }

/*
    @Override
    public void addUserToCompany(String email, Long companyId) {
        companyRepository.findByCompanyId(companyId)
                .map(company -> company.addUser(userRepository.findByEmail(email)
                            .orElseThrow(UserNotFoundException::new)))
                .map(companyRepository::save)
                .orElseThrow(CompanyNotFoundException::new);
    }

 */

    @Override
    public List<User> viewCompanyMember(Long companyId) {
        return companyRepository.findByCompanyId(companyId)
                .map(Company::getUsers)
                .orElseThrow(CompanyNotFoundException::new);
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
    public void uploadCompanyPreviewImage(MultipartFile file, Long companyId) {
        try {
            file.transferTo(new File(filePath + "company/" + companyId + "/" + "preview.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void companyLike(Long companyId) {
        userRepository.findByEmail(authenticationFacade.getUserEmail())
                .map(user -> {
                    likeRepository.findAllByCompanyIdAndUserId(companyId, user.getEmail());
                    return companyRepository.findByCompanyId(companyId)
                            .map(Company::addLike)
                            .map(companyRepository::save)
                            .orElseThrow(CompanyNotFoundException::new);
                })
                .orElseThrow(InvalidTokenException::new);
    }

    @Override
    public List<WorkResponse> viewWork(Long companyId, String userEmail) {
        return companyRepository.findByCompanyId(companyId)
                .map(userRepository::findByCompany)
                .map(user -> user.map(User::getEmail).orElseThrow(UserNotFoundException::new))
                .map(workRepository::findAllByTargetUserEmail)
                .map(works -> {
                    List<WorkResponse> workResponses = new ArrayList<>();

                    for (var work : works) {
                        workResponses.add(new WorkResponse(work.getWorkName(), work.getWorkContent(), work.getDate().toString()));
                    }

                    return workResponses;
                })
                .orElseThrow(CompanyNotFoundException::new);
    }
}
