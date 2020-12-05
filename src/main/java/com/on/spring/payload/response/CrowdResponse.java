package com.on.spring.payload.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@AllArgsConstructor @NoArgsConstructor @Builder
public class CrowdResponse {
    private String companyName;
    private String crowdTitle;
    private int nowAmount;
    private int destinationAmount;
    private String hashTag;
}
