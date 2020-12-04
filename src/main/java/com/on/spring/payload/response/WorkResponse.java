package com.on.spring.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @AllArgsConstructor @NoArgsConstructor
public class WorkResponse {
    private String workName;
    private String workContent;
    private String date;
}
