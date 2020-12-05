package com.on.spring.service.apply;

import com.on.spring.entity.apply.Apply;
import com.on.spring.entity.apply.ApplyRepository;
import com.on.spring.entity.company.Company;
import com.on.spring.entity.company.CompanyRepository;
import com.on.spring.exception.ApplyNotFoundException;
import com.on.spring.exception.CompanyNotFoundException;
import com.on.spring.exception.FileIsNotFoundException;
import com.on.spring.payload.request.AddApplyRequest;
import com.on.spring.payload.response.ApplyResponse;
import com.on.spring.payload.response.ApplyListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
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

    @Value("${spring.file.path}")
    private String filePath;

    @Override
    public void uploadApply(AddApplyRequest request, List<MultipartFile> files) {
        int cur = 1;

        Apply apply = applyRepository.save(
                Apply.builder()
                        .companyId(request.getCompanyId())
                        .companyName(request.getCompanyName())
                        .date(LocalDateTime.of(request.getYear(), request.getMonth(), request.getDay(), request.getHour(), request.getMinute()))
                        .imageNum(0)
                        .hashTag(request.getHashTag())
                        .build()
        );

        Long applyId = apply.getApplyId();

        try {
            files.get(0).transferTo(new File(filePath + "apply/" + applyId + "/" + "preview.png"));
            files.remove(0);
            for (MultipartFile file : files) {
                file.transferTo(new File(filePath + "apply/"+ applyId + "/" + cur + ".png"));
                cur++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        applyRepository.save(
            apply.addImageNum(cur)
        );
    }

    @Override
    public ApplyResponse viewApply(Long applyId) {
        return applyRepository.findByApplyId(applyId)
                .map(apply -> {
                    Duration diffTime = Duration.between(apply.getDate(), LocalDateTime.now());
                    return ApplyResponse.builder()
                            .applyName(apply.getApplyName())
                            .companyName(apply.getCompanyName())
                            .date(diffTime.toString())
                            .hashTag(apply.getHashTag())
                            .build();
                })
                .orElseThrow(ApplyNotFoundException::new);
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
                                .likes(company.getLikes())
                                // .previewPath(filePath + apply.getApplyId() + "/" + "preview.png")
                                .build()
                );
        });

        return responses;
    }
}
