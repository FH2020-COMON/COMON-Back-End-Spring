package com.on.spring.controller;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.on.spring.exception.FileUploadFailedException;
import com.on.spring.payload.request.UploadCrowdRequest;
import com.on.spring.payload.response.CrowdListResponse;
import com.on.spring.payload.response.CrowdResponse;
import com.on.spring.service.crowd.CrowdService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public void uploadCrowd(@RequestPart("user") String user, @RequestParam("files") List<MultipartFile> files) {
        UploadCrowdRequest request = new UploadCrowdRequest();
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            request = objectMapper.readValue(user, UploadCrowdRequest.class);
        } catch (IOException err) {
            throw new FileUploadFailedException();
        }

        crowdService.uploadCrowd(request, files);
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
