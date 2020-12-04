package com.on.spring.controller;

import com.on.spring.payload.response.GrassResponse;
import com.on.spring.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/grass")
    public List<GrassResponse> viewUserGrass(String userId) {
        return userService.viewUserGrass(userId);
    }
}
