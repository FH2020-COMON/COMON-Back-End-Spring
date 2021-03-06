package com.on.spring.controller;

import com.on.spring.payload.request.UploadCrowdRequest;
import com.on.spring.payload.response.CrowdListResponse;
import com.on.spring.payload.response.CrowdResponse;
import com.on.spring.service.crowd.CrowdService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public void uploadCrowd(@RequestBody UploadCrowdRequest request) {
        crowdService.uploadCrowd(request);
    }

    @GetMapping("/{crowdId}")
    public CrowdListResponse viewCrowd(@PathVariable Long crowdId) {
        return crowdService.viewCrowd(crowdId);
    }

    @GetMapping("/addition/{crowdId}")
    public void addCrowd(@PathVariable Long crowdId, @RequestParam(name = "amount") Long amount) {
        crowdService.addCrowd(crowdId, amount);
    }
}
