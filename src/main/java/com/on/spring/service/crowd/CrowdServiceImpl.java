package com.on.spring.service.crowd;

import com.on.spring.entity.crowd.Crowd;
import com.on.spring.entity.crowd.CrowdRepository;
import com.on.spring.entity.user.User;
import com.on.spring.entity.user.UserRepository;
import com.on.spring.exception.CrowdNotFoundException;
import com.on.spring.exception.FileIsNotFoundException;
import com.on.spring.exception.FileUploadFailedException;
import com.on.spring.exception.UserNotFoundException;
import com.on.spring.payload.request.UploadCrowdRequest;
import com.on.spring.payload.response.CrowdListResponse;
import com.on.spring.payload.response.CrowdResponse;
import com.on.spring.security.auth.AuthenticationFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CrowdServiceImpl implements CrowdService {
    private final CrowdRepository crowdRepository;
    private final UserRepository userRepository;
    private final AuthenticationFacade authenticationFacade;

    @Value("${spring.file.path}")
    private String filePath;

    @Override
    public void uploadCrowd(UploadCrowdRequest request) {
        User user = userRepository.findByEmail(authenticationFacade.getUserEmail())
                .orElseThrow(UserNotFoundException::new);

        System.out.println(request.getCrowdTitle() + request.getDestinationAmount());

        Crowd crowd = crowdRepository.save(
                Crowd.builder().companyId(user.getCompany().getCompanyId())
                        .crowdName(request.getCrowdTitle())
                        .hashTag("##")
                .companyName(user.getCompany().getCompanyName())
                .destinationAmount(request.getDestinationAmount())
                .nowAmount(0)
                .build()
        );
    }

    @Override
    public CrowdResponse viewCrowd(Long crowdId) {
        return crowdRepository.findById(crowdId)
                .map(crowd -> {
                            return CrowdResponse.builder()
                                    .hashTag(crowd.getHashTag())
                                    .crowdTitle(crowd.getCrowdName())
                                    .companyName(crowd.getCompanyName())
                                    .destinationAmount(crowd.getDestinationAmount())
                                    .nowAmount(crowd.getNowAmount())
                                    .build();
                        }
                    )
                .orElseThrow(CrowdNotFoundException::new);
    }

    @Override
    public List<CrowdListResponse> viewCompanyCrowdList(Long companyId) {
        return null;
    }

    @Override
    public List<CrowdListResponse> viewCrowdList() {
        List<CrowdListResponse> responses = new ArrayList<>();
        List<Crowd> crowds = crowdRepository.findAll();

        for (Crowd crowd : crowds) {
                responses.add(CrowdListResponse.builder()
                        .companyName(crowd.getCompanyName())
                        .crowdId(crowd.getId())
                        .crowdName(crowd.getCrowdName())
                        .hashTag(crowd.getHashTag())
                        .destinationAmount(crowd.getDestinationAmount())
                        .nowAmount(crowd.getNowAmount())
                        .build()
                );
        }

        return responses;
    }

    @Override
    public void addCrowd(Long crowdId, Long crowdAmount) {
        crowdRepository.findById(crowdId)
                .map(crowd -> {
                    crowd.addAmount(crowdAmount);
                    return crowd;
                })
                .map(crowdRepository::save)
                .orElseThrow(CrowdNotFoundException::new);
    }
}
