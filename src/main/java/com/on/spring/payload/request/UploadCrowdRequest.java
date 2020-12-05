package com.on.spring.payload.request;

import lombok.Getter;

@Getter
public class UploadCrowdRequest {
    private String crowdTitle;
    private int destinationAmount;
}
