package com.on.spring.controller.company;

import com.on.spring.entity.user.User;
import com.on.spring.payload.request.RegisterCompanyRequest;
import com.on.spring.service.company.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/company")
@RequiredArgsConstructor
public class CompanyController {
    private final CompanyService companyService;

    @PostMapping("/")
    public void registerCompany(RegisterCompanyRequest request) {

    }

    @GetMapping("/")
    public List<User> userList() {
        return
    }
}
