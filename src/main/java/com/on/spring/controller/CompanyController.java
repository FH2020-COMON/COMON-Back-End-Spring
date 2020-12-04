package com.on.spring.controller;

import com.on.spring.entity.user.User;
import com.on.spring.payload.request.RegisterCompanyRequest;
import com.on.spring.payload.response.BoardResponse;
import com.on.spring.payload.response.CompanyListResponse;
import com.on.spring.payload.response.CompanyApplyViewResponse;
import com.on.spring.payload.response.WorkResponse;
import com.on.spring.service.company.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/company")
@RequiredArgsConstructor
public class CompanyController {
    private final CompanyService companyService;

    @GetMapping("/")
    public List<CompanyListResponse> companyList() {
        return companyService.companyList();
    }

    @PostMapping("/")
    public void registerCompany(RegisterCompanyRequest request) {
        companyService.registerCompany(request);
    }

    @GetMapping("/{companyId}")
    public CompanyApplyViewResponse companyApplyView(@PathVariable Long companyId) {
        companyService.view
    }

    @PostMapping("/{companyId}")
    public void companyLogo(MultipartFile file, @PathVariable long companyId) {
        companyService.uploadCompanyPreviewImage(file, companyId);
    }

    @PatchMapping("/{companyId}")
    public void companyLike(@PathVariable long companyId) {
        companyService.companyLike(companyId);
    }

    @GetMapping("/{companyId}/user")
    public List<User> userList(@PathVariable long companyId) {
        return companyService.viewCompanyMember(companyId);
    }

    @GetMapping("/{companyId}/board")
    public List<BoardResponse> viewBoard(@PathVariable long companyId) {

    }

    @GetMapping("/work/{userId}")
    public List<WorkResponse> viewWorks(@PathVariable String userId) {
        return companyService.viewWorks(userId);
    }

    @PostMapping("/{companyId}/work/{userId}")
    public void addWork(@PathVariable Long companyId, @PathVariable String userId) {

    }
}
