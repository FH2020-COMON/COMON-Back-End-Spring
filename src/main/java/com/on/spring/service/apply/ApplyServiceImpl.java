package com.on.spring.service.apply;

import com.on.spring.entity.apply.Apply;
import com.on.spring.entity.apply.ApplyRepository;
import com.on.spring.exception.AddApplyFailedException;
import com.on.spring.payload.request.AddApplyRequest;
import com.on.spring.payload.response.ApplyResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Service
@RequiredArgsConstructor
public class ApplyServiceImpl implements ApplyService {
    private final ApplyRepository applyRepository;

    @Value("${spring.file.path}")
    private String filePath;

    @Override
    public void uploadApply(AddApplyRequest request) {
        int cur = 1;
        SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Long applyId;

        try {
            applyId = applyRepository.save(
                    Apply.builder()
                            .companyId(request.getCompanyId())
                            .companyName(request.getCompanyName())
                            .date(transFormat.parse(request.getDate()))
                            .build()
            ).getApplyId();
        } catch (ParseException e) {
            throw new AddApplyFailedException();
        }

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

    public ApplyResponse viewApply(Long applyId) {
        applyRepository.findByApplyId(applyId)
                .map(apply -> {
                    Long time = 
                    return new ApplyResponse(apply.getCompanyId(), apply.getCompanyName(), apply.);
                })
    }
}
