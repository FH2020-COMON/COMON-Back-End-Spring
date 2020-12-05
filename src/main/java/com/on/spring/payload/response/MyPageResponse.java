package com.on.spring.payload.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@AllArgsConstructor @NoArgsConstructor @Builder
public class MyPageResponse {
    private String name;
    private String companyName;
    private GrassResponse[] grass;
}
