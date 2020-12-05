package com.on.spring.payload.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

@Getter
public class UploadCrowdRequest {
    private String crowdTitle;
    private int destinationAmount;
}
