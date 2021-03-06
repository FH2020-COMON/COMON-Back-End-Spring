package com.on.spring.service.crowd;

import com.on.spring.payload.request.UploadCrowdRequest;
import com.on.spring.payload.response.CrowdListResponse;
import com.on.spring.payload.response.CrowdResponse;

import java.util.List;

public interface CrowdService {
    public void uploadCrowd(UploadCrowdRequest request);
    public List<CrowdListResponse> viewCrowdList();
    public CrowdListResponse viewCrowd(Long crowdId);
    public List<CrowdListResponse> viewCompanyCrowdList(Long companyId);
    public void addCrowd(Long crowdId, Long crowdAmount);
}
