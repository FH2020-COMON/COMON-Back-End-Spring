package com.on.spring.service.apply;

import com.on.spring.entity.apply.Apply;
import com.on.spring.entity.apply.ApplyRepository;
import com.on.spring.entity.company.Company;
import com.on.spring.entity.company.CompanyRepository;
import com.on.spring.entity.user.User;
import com.on.spring.entity.user.UserRepository;
import com.on.spring.entity.user.UserType;
import com.on.spring.exception.*;
import com.on.spring.payload.request.AddApplyRequest;
import com.on.spring.payload.response.ApplyResponse;
import com.on.spring.payload.response.ApplyListResponse;
import com.on.spring.payload.response.TopApplyResponse;
import com.on.spring.security.auth.AuthenticationFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ApplyServiceImpl implements ApplyService {
    private final ApplyRepository applyRepository;
    private final CompanyRepository companyRepository;
    private final UserRepository userRepository;
    private final AuthenticationFacade authenticationFacade;

    @Value("${spring.file.path}")
    private String filePath;

    @Override
    public void uploadApply(AddApplyRequest request) {
        User user = userRepository.findByEmail(authenticationFacade.getUserEmail())
                .orElseThrow(UserNotFoundException::new);

        if (!user.getUserType().equals(UserType.OWNER))
            throw new InvalidTokenException();

        Apply apply = applyRepository.save(
                Apply.builder()
                        .applyName(request.getApplyName())
                        .companyId(user.getCompany().getCompanyId())
                        .companyName(user.getCompany().getCompanyName())
                        .date(LocalDateTime.of(request.getYear(), request.getMonth(), request.getDay(), request.getHour(), request.getMinute()))
                        .imageNum(0)
                        .hashTag(request.getHashTag())
                        .build()
        );
    }

    @Override
    public ApplyResponse viewApply(Long applyId) {
        return applyRepository.findByApplyId(applyId)
                .map(apply -> {
                    Duration diffTime = Duration.between(apply.getDate(), LocalDateTime.now());
                    return ApplyResponse.builder()
                            .applyId(apply.getApplyId())
                            .applyName(apply.getApplyName())
                            .companyName(apply.getCompanyName())
                            .date(diffTime.toString())
                            .hashTag(apply.getHashTag())
                            .build();
                })
                .orElseThrow(ApplyNotFoundException::new);
    }

    @Override
    public List<TopApplyResponse> topApplyResponses() {
        List<TopApplyResponse> responses = new ArrayList<>();

        int cur = 0;

        for (Company company : companyRepository.findAllByOrderByLikes()) {
            if (cur > 3)
                break;

            List<Apply> applies = applyRepository.findAllByCompanyId(company.getCompanyId());

            if (applies.get(0) == null) {
                continue;
            }

            Apply apply = applies.get(0);

            responses.add(TopApplyResponse.builder()
                    .applyId(apply.getApplyId())
                    .applyName(apply.getApplyName())
                    .companyName(company.getCompanyName())
                    .dDay(Duration.between(apply.getDate(), LocalDateTime.now()).toDays())
                    .hashTag(apply.getHashTag())
                    .likes(company.getLikes())
                    .build());

            cur++;
        }

        return responses;
    }

    @Override
    public MultipartFile viewApplyPreview(Long applyId) {
        MultipartFile file;

        try {
            file = new MockMultipartFile("preview.png", new FileInputStream(filePath + applyId + "/" + "preview.png"));
        } catch (IOException e) {
            throw new FileIsNotFoundException();
        }

        return file;
    }

    @Override
    public List<String> viewApplyImages(Long applyId) {
        return applyRepository.findByApplyId(applyId)
                .map(apply -> {
                    List<String> filePath = new ArrayList<>();
                    int cur = apply.getImageNum();
                    for (int i = 1; i <= apply.getApplyId(); i++) {
                            filePath.add(filePath + applyId.toString() + "/" + cur + ".png");
                    }
                    return filePath;
                })
                .orElseThrow(ApplyNotFoundException::new);
    }

    @Override
    public List<ApplyListResponse> companyApplyView() {
        List<ApplyListResponse> responses = new ArrayList<>();
        applyRepository.findAll().forEach(apply ->  {
            Duration diffTime = Duration.between(apply.getDate(), LocalDateTime.now());
            Company company = companyRepository.findByCompanyId(apply.getCompanyId())
                    .orElseThrow(CompanyNotFoundException::new);
                responses.add(
                        ApplyListResponse.builder()
                                .applyName(apply.getApplyName())
                                .companyName(apply.getCompanyName())
                                .companyId(apply.getCompanyId())
                                .dDay(diffTime.toDays())
                                .hashTag(apply.getHashTag())
                                .applyId(apply.getApplyId())
                                .likes(company.getLikes())
                                .build()
                );
        });

        return responses;
    }
}
