package com.on.spring.payload.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Getter @AllArgsConstructor @NoArgsConstructor @Builder
public class ApplyListResponse {
    private String companyName;
    private String applyName;
    private Long companyId;
    private Long applyId;
    private Long dDay;
    private Long likes;
}
