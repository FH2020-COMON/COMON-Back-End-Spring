package com.on.spring.payload.request;

import lombok.Getter;

@Getter
public class UploadCrowdRequest {
    private String crowdName;
    private int destinationAmount;
}
