package com.on.spring.service.apply;

import com.on.spring.payload.request.AddApplyRequest;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ApplyService {
    public void uploadApply(AddApplyRequest request);
}
