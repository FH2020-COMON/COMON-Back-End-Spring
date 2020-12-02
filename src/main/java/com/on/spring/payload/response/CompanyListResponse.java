package com.on.spring.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @AllArgsConstructor @NoArgsConstructor
public class CompanyListResponse {
    private String companyName;
    private Long companyId;
    private String pictureUrl;
}
