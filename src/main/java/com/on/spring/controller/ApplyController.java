package com.on.spring.controller;

import com.on.spring.payload.request.AddApplyRequest;
import com.on.spring.service.apply.ApplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/apply")
@RequiredArgsConstructor
public class ApplyController {
    private final ApplyService applyService;

    @PostMapping
    public void addApply(AddApplyRequest request) {
        applyService.uploadApply(request);
    }

    @PostMapping("/image/")
}
