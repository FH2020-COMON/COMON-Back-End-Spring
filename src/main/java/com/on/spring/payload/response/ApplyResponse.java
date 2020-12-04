package com.on.spring.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @NoArgsConstructor @AllArgsConstructor
public class ApplyResponse {
    private Long companyId;
    private String companyName;
    private Long time;
}
