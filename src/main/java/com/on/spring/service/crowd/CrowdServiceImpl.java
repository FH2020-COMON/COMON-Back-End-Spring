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

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
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

        Crowd crowd = crowdRepository.save(
                Crowd.builder().companyId(user.getCompany().getCompanyId())
                .companyName(user.getCompany().getCompanyName())
                .destinationAmount(request.getDestinationAmount())
                .nowAmount(0)
                .build()
        );

        try {
            int cur = 1;
            request.getFiles().get(0).transferTo(new File(filePath + "crowd/" + crowd.getId() + "/preview.png"));
            request.getFiles().remove(0);
            for (MultipartFile file : request.getFiles()) {
                try {
                    file.transferTo(new File(filePath + "crowd/" + crowd.getId() + "/" + cur + ".png"));
                } catch (IOException e) {
                    System.out.println("File Upload Failed!!");
                    throw new FileUploadFailedException();
                }
                cur++;
            }
        } catch (IOException e) {
            throw new FileUploadFailedException();
        }
    }

    @Override
    public CrowdResponse viewCrowd(Long crowdId) {
        return crowdRepository.findById(crowdId)
                .map(crowd -> {
                            List<MultipartFile> files = new ArrayList<>();

                            int cur = crowd.getImageNum();
                            try {
                                for (int i = 1; i <= crowd.getId(); i++) {
                                    MultipartFile file = new MockMultipartFile(cur + ".png", new FileInputStream(filePath + crowd.getId() + "/" + cur + ".png"));
                                    files.add(file);
                                }
                            } catch (IOException e) {
                                throw new FileIsNotFoundException();
                            }

                            return CrowdResponse.builder()
                                    .hashTag(crowd.getHashTag())
                                    .crowdTitle(crowd.getCrowdName())
                                    .companyName(crowd.getCompanyName())
                                    .destinationAmount(crowd.getDestinationAmount())
                                    .nowAmount(crowd.getNowAmount())
                                    .images(files)
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
            try {
                responses.add(CrowdListResponse.builder()
                        .companyName(crowd.getCompanyName())
                        .crowdId(crowd.getId())
                        .crowdName(crowd.getCrowdName())
                        .hashTag(crowd.getHashTag())
                        .previewImage(new MockMultipartFile("preview.png", new FileInputStream(filePath + crowd.getId() + "/" + "preview.png")))
                        .build()
                );
            } catch (IOException e) {
                throw new FileIsNotFoundException();
            }
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
