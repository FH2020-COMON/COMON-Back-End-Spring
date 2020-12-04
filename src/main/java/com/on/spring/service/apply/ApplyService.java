package com.on.spring.service.apply;

import com.on.spring.payload.request.AddApplyRequest;
import com.on.spring.payload.response.ApplyResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ApplyService {
    public void uploadApply(AddApplyRequest request);
    public ApplyResponse viewApply(Long applyId);
}
