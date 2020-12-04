package com.on.spring.service.company;

import com.on.spring.entity.company.Company;
import com.on.spring.entity.company.CompanyRepository;
import com.on.spring.entity.companylike.CompanyLikeRepository;
import com.on.spring.entity.user.User;
import com.on.spring.entity.user.UserRepository;
import com.on.spring.entity.work.Work;
import com.on.spring.entity.work.WorkRepository;
import com.on.spring.exception.CompanyNotFoundException;
import com.on.spring.exception.InvalidTokenException;
import com.on.spring.exception.UserNotFoundException;
import com.on.spring.exception.UserNotOwnerException;
import com.on.spring.payload.request.AddWorkRequest;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
    public List<WorkResponse> viewWorks(String userEmail) {
        List<Work> works = workRepository.findAllByTargetUserEmail(userEmail);
        List<WorkResponse> workResponses = new ArrayList<>();

        for (var work : works) {
            workResponses.add(new WorkResponse(work.getWorkName(), work.getWorkContent(), work.getDate().toString()));
        }

        return workResponses;
    }

    @Override
    public void addWorks(AddWorkRequest request, String userId) throws ParseException {
        SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        workRepository.save(
                Work.builder()
                .requestId(request.getRequestId())
                .targetUserEmail(userId)
                .workName(request.getWorkName())
                .date(transFormat.parse(request.getDate()))
                .workContent(request.getWorkContent())
                .build()
        );
    }
}
