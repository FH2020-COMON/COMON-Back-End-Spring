package com.on.spring.payload.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@AllArgsConstructor @NoArgsConstructor @Builder
public class CrowdResponse {
    private String companyName;
    private String crowdTitle;
    private String hashTag;
    private Integer nowAmount;
    private Integer destinationAmount;
}
