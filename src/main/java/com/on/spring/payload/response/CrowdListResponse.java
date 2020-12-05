package com.on.spring.payload.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Getter @AllArgsConstructor @NoArgsConstructor @Builder
public class CrowdListResponse {
    private String crowdName;
    private Long crowdId;
    private String companyName;
    private String hashTag;
    private int nowAmount;
    private int destinationAmount;
}
