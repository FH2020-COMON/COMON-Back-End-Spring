package com.on.spring.payload.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @AllArgsConstructor @NoArgsConstructor @Builder
public class TopApplyResponse {
    private String companyName;
    private String applyName;
    private Long applyId;
    private Long dDay;
    private Long likes;
    private String hashTag;
}
