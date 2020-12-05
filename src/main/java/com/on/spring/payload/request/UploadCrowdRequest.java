package com.on.spring.payload.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class UploadCrowdRequest {
    private String crowdTitle;
    private int destinationAmount;
}
