package com.on.spring.controller.company;

import com.on.spring.entity.company.Company;
import com.on.spring.entity.user.User;
import com.on.spring.payload.request.RegisterCompanyRequest;
import com.on.spring.payload.response.CompanyListResponse;
import com.on.spring.payload.response.CompanyViewResponse;
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
    public CompanyViewResponse companyView(@PathVariable Long companyId) {
        companyService.
    }

    @PostMapping("/{companyId}")
    public void companyIntroduce(List<MultipartFile> files, @PathVariable long companyId) {
        companyService.uploadCompanyIntroduceImage(files, companyId);
    }

    @PatchMapping("/{companyId}")
    public void companyLike(@PathVariable long companyId) {
        companyService.companyLike(companyId);
    }

    @GetMapping("/{companyId}/user")
    public List<User> userList(@PathVariable long companyId) {
        return companyService.viewCompanyMember(companyId);
    }
}
