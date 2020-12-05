package com.on.spring.payload.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class UploadCrowdRequest {
    private String crowdTitle;
    private int destinationAmount;
    private List<MultipartFile> files;
}
