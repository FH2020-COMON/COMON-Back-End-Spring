package com.on.spring.service.apply;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ApplyService {
    public void uploadApply(List<MultipartFile> files, Long applyId);
}
