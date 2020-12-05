package com.on.spring.service.apply;

import com.on.spring.payload.request.AddApplyRequest;
import com.on.spring.payload.response.ApplyResponse;
import com.on.spring.payload.response.ApplyListResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ApplyService {
    public void uploadApply(AddApplyRequest request, List<MultipartFile> files);
    public ApplyResponse viewApply(Long applyId);
    public MultipartFile viewApplyPreview(Long applyId);
    public List<String> viewApplyImages(Long applyId);
    public List<ApplyListResponse> companyApplyView();
}
