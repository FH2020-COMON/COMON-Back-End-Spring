package com.on.spring.payload.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @NoArgsConstructor @AllArgsConstructor @Builder
public class ApplyResponse {
    private String applyName;
    private String companyName;
    private String date;
}
