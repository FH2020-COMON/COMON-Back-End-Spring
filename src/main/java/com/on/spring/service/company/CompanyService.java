package com.on.spring.service.company;

import com.on.spring.entity.user.User;
import com.on.spring.payload.request.AddWorkRequest;
import com.on.spring.payload.request.RegisterCompanyRequest;
import com.on.spring.payload.request.UploadBoardRequest;
import com.on.spring.payload.response.BoardResponse;
import com.on.spring.payload.response.CompanyListResponse;
import com.on.spring.payload.response.WorkResponse;
import org.springframework.web.multipart.MultipartFile;

import java.text.ParseException;
import java.util.List;

public interface CompanyService {
    public void registerCompany(RegisterCompanyRequest request);
    // public void addUserToCompany(String email, Long companyId);
    public List<User> viewCompanyMember(Long companyId);
    public List<CompanyListResponse> companyList();
    public void uploadCompanyPreviewImage(MultipartFile file, Long companyId);
    public void companyLike(Long companyId);
    public List<WorkResponse> viewWorks(String userEmail);
    public void addWorks(AddWorkRequest request, String userId);
    public List<BoardResponse> viewBoardList(long companyId);
    public void uploadBoard(UploadBoardRequest request, Long companyId);
}
