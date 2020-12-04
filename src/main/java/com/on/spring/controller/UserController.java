package com.on.spring.controller;

import com.on.spring.payload.request.RegisterRequest;
import com.on.spring.payload.response.GrassResponse;
import com.on.spring.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @PostMapping
    public void register(@RequestBody @Valid RegisterRequest request) {
        userService.register(request);
    }
}
