package com.on.spring.payload.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor @NoArgsConstructor @Builder
public class MemberResponse {
    private String name;
    private String email;
    private List<GrassResponse> grassResponse;
}
