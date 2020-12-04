package com.on.spring.service.user;

import com.on.spring.entity.grass.Grass;
import com.on.spring.entity.user.User;
import com.on.spring.entity.user.UserRepository;
import com.on.spring.entity.user.UserType;
import com.on.spring.exception.UserNotFoundException;
import com.on.spring.payload.request.RegisterRequest;
import com.on.spring.payload.response.GrassResponse;
import com.on.spring.security.auth.AuthenticationFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final AuthenticationFacade authenticationFacade;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<GrassResponse> viewUserGrass(String userId) {
        return userRepository.findByEmail(userId)
                .map(user -> {
                    List<GrassResponse> response = new ArrayList<>();

                    for (Grass grass : user.getGrasses()) {
                        response.add(new GrassResponse(grass.getCreatedDateAt().toString(), grass.getInformation()));
                    }

                    return response;
                })
                .orElseThrow(UserNotFoundException::new);
    }

    @Override
    public void switchOwner() {
        userRepository.findByEmail(authenticationFacade.getUserEmail())
                .map(User::switchOwner)
                .orElseThrow(UserNotFoundException::new);
    }

    @Override
    public void switchExecutive() {
        userRepository.findByEmail(authenticationFacade.getUserEmail())
                .map(User::switchExecutive)
                .orElseThrow(UserNotFoundException::new);
    }

    @Override
    public void register(RegisterRequest request) {
        userRepository.save(
                User.builder()
                .email(request.getEmail())
                .name(request.getName())
                .password(passwordEncoder.encode(request.getPassword()))
                .userType(UserType.APPLICANT)
                .build()
        );
    }
}
