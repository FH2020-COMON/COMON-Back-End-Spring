package com.on.spring.payload.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Getter @AllArgsConstructor @NoArgsConstructor
public class UploadBoardRequest {
    private String boardName;
    private MultipartFile file;
}
