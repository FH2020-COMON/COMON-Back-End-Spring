package com.on.spring.controller;

import com.on.spring.payload.request.AddApplyRequest;
import com.on.spring.payload.response.ApplyResponse;
import com.on.spring.service.apply.ApplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/apply")
@RequiredArgsConstructor
public class ApplyController {
    private final ApplyService applyService;

    @GetMapping("/image/{applyId}")
    public ApplyResponse viewApply(@PathVariable Long applyId) {
        applyService.view;
    }

    @PostMapping
    public void addApply(AddApplyRequest request) {
        applyService.uploadApply(request);
    }

}
