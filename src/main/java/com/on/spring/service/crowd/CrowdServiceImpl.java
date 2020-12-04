package com.on.spring.service.crowd;

import com.on.spring.entity.crowd.Crowd;
import com.on.spring.entity.crowd.CrowdRepository;
import com.on.spring.exception.FileUploadFailedException;
import com.on.spring.payload.request.UploadCrowdRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CrowdServiceImpl implements CrowdService {
    private final CrowdRepository crowdRepository;

    @Value("${spring.file.path}")
    private String filePath;

    @Override
    public void uploadCrowd(UploadCrowdRequest request) {
        Crowd crowd = crowdRepository.save(
                Crowd.builder()
                .companyName(request.getCompanyName())
                .destinationAmount(request.getDestinationAmount())
                .nowAmount(0)
                .build()
        );

        try {
            int cur = 1;
            List<MultipartFile> files = request.getFiles();
            files.get(0).transferTo(new File(filePath + "crowd/" + crowd.getId() + "/preview.png"));
            files.remove(0);
            for (MultipartFile file : files) {
                try {
                    file.transferTo(new File(filePath + "crowd/" + crowd.getId() + "/" + cur + ".png"));
                } catch (IOException e) {
                    throw new FileUploadFailedException();
                }
                cur++;
            }
        } catch (IOException e) {
            throw new FileUploadFailedException();
        }
    }
}
