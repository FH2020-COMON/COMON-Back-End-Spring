package com.on.spring.service.user;

import com.on.spring.payload.request.RegisterRequest;
import com.on.spring.payload.response.GrassResponse;

import java.util.List;

public interface UserService {
    public List<GrassResponse> viewUserGrass(String userId);
    public void switchOwner();
    public void switchExecutive();
    public void register(RegisterRequest request);
}
