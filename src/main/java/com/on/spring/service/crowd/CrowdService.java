package com.on.spring.service.crowd;

import com.on.spring.payload.response.CrowdListResponse;
import com.on.spring.payload.response.CrowdResponse;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.List;

public interface CrowdService {
    public void uploadCrowd(MultipartFile file, String crowdTitle, int destinationAmount);
    public List<CrowdListResponse> viewCrowdList();
    public CrowdResponse viewCrowd(Long crowdId);
    public List<CrowdListResponse> viewCompanyCrowdList(Long companyId);
    public void addCrowd(Long crowdId, Long crowdAmount);
}
