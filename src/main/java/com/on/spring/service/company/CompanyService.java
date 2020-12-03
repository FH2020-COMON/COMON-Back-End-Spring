package com.on.spring.service.company;

import com.on.spring.entity.user.User;
import com.on.spring.payload.request.RegisterCompanyRequest;
import com.on.spring.payload.response.CompanyListResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CompanyService {
    public void registerCompany(RegisterCompanyRequest request);
    // public void addUserToCompany(String email, Long companyId);
    public List<User> viewCompanyMember(Long companyId);
    public List<CompanyListResponse> companyList();
    public void uploadCompanyIntroduceImage(List<MultipartFile> file, Long companyId);
    public void companyLike(Long companyId);
}
