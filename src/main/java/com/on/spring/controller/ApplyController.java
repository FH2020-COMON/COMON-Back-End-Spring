package com.on.spring.controller;

import com.on.spring.service.apply.ApplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/apply")
@RequiredArgsConstructor
public class ApplyController {
    private final ApplyService applyService;

    @PostMapping("/image/preview")
    public void uploadApplyPreviewImage(MultipartFile file) {
        applyService.uploadApplyPreviewImage(file);
    }

    @PostMapping("/image/")
}
