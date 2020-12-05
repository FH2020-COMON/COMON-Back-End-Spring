package com.on.spring.service.user;

import com.on.spring.payload.request.RegisterRequest;
import com.on.spring.payload.response.GrassResponse;
import com.on.spring.payload.response.MyPageResponse;

import java.util.List;

public interface UserService {
    public GrassResponse[] viewUserGrass();
    public void switchOwner();
    public void switchExecutive();
    public void register(RegisterRequest request);
    public MyPageResponse viewMyPage();
}
