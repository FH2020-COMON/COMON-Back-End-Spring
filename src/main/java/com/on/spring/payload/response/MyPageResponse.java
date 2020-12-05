package com.on.spring.payload.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor @NoArgsConstructor @Builder
public class MyPageResponse {
    private String name;
    private String companyName;
    private List<GrassResponse> grass;
}
