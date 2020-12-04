package com.on.spring.payload.response;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class CompanyApplyViewResponse {
    private String applyName;
    private String companyName;
    private String date;
    private String hashTag;
    private List<MultipartFile> files;
}
