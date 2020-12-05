package com.on.spring.payload.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor @NoArgsConstructor @Builder @Getter
public class MyPageResponse {
    private String name;
    private String companyName;
    private List<GrassResponse> grass;
}
