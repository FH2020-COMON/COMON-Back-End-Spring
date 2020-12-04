package com.on.spring.service.apply;

import com.on.spring.entity.apply.Apply;
import com.on.spring.entity.apply.ApplyRepository;
import com.on.spring.exception.ApplyNotFoundException;
import com.on.spring.payload.request.AddApplyRequest;
import com.on.spring.payload.response.ApplyResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ApplyServiceImpl implements ApplyService {
    private final ApplyRepository applyRepository;

    @Value("${spring.file.path}")
    private String filePath;

    @Override
    public void uploadApply(AddApplyRequest request) {
        int cur = 1;
        Long applyId;

        applyId = applyRepository.save(
                Apply.builder()
                        .companyId(request.getCompanyId())
                        .companyName(request.getCompanyName())
                        .date(LocalDateTime.of(request.getYear(), request.getMonth(), request.getDay(), request.getHour(), request.getMinute()))
                        .build()
        ).getApplyId();

        try {
            request.getFiles().get(0).transferTo(new File(filePath + "apply/" + applyId + "/" + "preview.png"));
            request.getFiles().remove(0);
            for (MultipartFile file : request.getFiles()) {
                file.transferTo(new File(filePath + "apply/"+ applyId + "/" + cur + ".png"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ApplyResponse viewApply(Long applyId) {
        return applyRepository.findByApplyId(applyId)
                .map(apply -> {
                    Duration diffTime = Duration.between(apply.getDate(), LocalDateTime.now());
                    return new ApplyResponse(apply.getCompanyId(), apply.getCompanyName(), diffTime.toString());
                })
                .orElseThrow(ApplyNotFoundException::new);
    }

    public MultipartFile view
}
