package com.on.spring.service.apply;

import com.on.spring.payload.request.AddApplyRequest;
import com.on.spring.payload.response.ApplyResponse;
import com.on.spring.payload.response.ApplyListResponse;
import com.on.spring.payload.response.TopApplyResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ApplyService {
    public void uploadApply(AddApplyRequest request);
    public ApplyResponse viewApply(Long applyId);
    public MultipartFile viewApplyPreview(Long applyId);
    public List<String> viewApplyImages(Long applyId);
    public List<ApplyListResponse> companyApplyView();
    public List<TopApplyResponse> topApplyResponses();
}
