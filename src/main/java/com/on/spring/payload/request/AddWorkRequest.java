package com.on.spring.payload.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @AllArgsConstructor @NoArgsConstructor
public class AddWorkRequest {
    private String requestId;
    private String workName;
    private String workContent;
    private String date;
}
