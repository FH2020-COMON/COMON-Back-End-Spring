package com.on.spring.controller;

import com.on.spring.payload.response.CrowdListResponse;
import com.on.spring.payload.response.CrowdResponse;
import com.on.spring.service.crowd.CrowdService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.List;

@RestController
@RequestMapping("/crowd")
@RequiredArgsConstructor
public class CrowdController {
    private final CrowdService crowdService;

    @GetMapping
    public List<CrowdListResponse> crowdList() {
        return crowdService.viewCrowdList();
    }

    @PostMapping("/{crowdTitle}/{destinationAmount}")
    public void uploadCrowd(MultipartFile request, @PathVariable String crowdTitle, @PathVariable String destinationAmount) {
        crowdService.uploadCrowd(request, crowdTitle, Integer.parseInt(destinationAmount));
    }

    @GetMapping("/{crowdId}")
    public CrowdResponse viewCrowd(@PathVariable Long crowdId) {
        return crowdService.viewCrowd(crowdId);
    }

    @GetMapping("/{crowdId}/addition")
    public void addCrowd(@PathVariable Long crowdId, Long amount) {
        crowdService.addCrowd(crowdId, amount);
    }
}
