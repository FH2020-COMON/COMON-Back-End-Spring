package com.on.spring.controller;

import com.on.spring.payload.request.AddApplyRequest;
import com.on.spring.payload.response.ApplyListResponse;
import com.on.spring.payload.response.ApplyResponse;
import com.on.spring.service.apply.ApplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/apply")
@RequiredArgsConstructor
public class ApplyController {
    private final ApplyService applyService;

    @GetMapping
    public List<ApplyListResponse> companyApplyView() {
        return applyService.companyApplyView();
    }

    @GetMapping("/{applyId}")
    public ApplyResponse viewApply(@PathVariable Long applyId) {
        return applyService.viewApply(applyId);
    }

    @GetMapping("/image/{applyId}")
    public List<MultipartFile> viewApplyImages(@PathVariable Long applyId) {
        return applyService.viewApplyImages(applyId);
    }

    @GetMapping("/image/preview/{applyId}")
    public MultipartFile viewApplyPreview(@PathVariable Long applyId) {
        return applyService.viewApplyPreview(applyId);
    }

    @PostMapping
    public void addApply(@RequestBody AddApplyRequest request, List<MultipartFile> files) {
        applyService.uploadApply(request, files);
    }
}
