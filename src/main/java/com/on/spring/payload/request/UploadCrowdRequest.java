package com.on.spring.payload.request;

import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
public class UploadCrowdRequest {
    private String companyName;
    private String crowdTitle;
    private int destinationAmount;
    private List<MultipartFile> files;
}
