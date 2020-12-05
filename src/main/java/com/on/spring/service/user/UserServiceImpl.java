package com.on.spring.service.user;

import com.on.spring.entity.grass.Grass;
import com.on.spring.entity.user.User;
import com.on.spring.entity.user.UserRepository;
import com.on.spring.entity.user.UserType;
import com.on.spring.exception.UserAlreadyRegisteredException;
import com.on.spring.exception.UserNotFoundException;
import com.on.spring.payload.request.RegisterRequest;
import com.on.spring.payload.response.GrassResponse;
import com.on.spring.payload.response.MyPageResponse;
import com.on.spring.security.auth.AuthenticationFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final AuthenticationFacade authenticationFacade;
    private final PasswordEncoder passwordEncoder;

    @Override
    public GrassResponse[] viewUserGrass() {
        return userRepository.findByEmail(authenticationFacade.getUserEmail())
                .map(user -> {
                    GrassResponse [] response = new GrassResponse[12];

                    for (Grass grass : user.getGrasses()) {
                        switch (grass.getCreatedDateAt().getMonth()) {
                            case JANUARY:
                                response[0].sizeUp();
                            case FEBRUARY:
                                response[1].sizeUp();
                            case MARCH:
                                response[2].sizeUp();
                            case APRIL:
                                response[3].sizeUp();
                            case MAY:
                                response[4].sizeUp();
                            case JUNE:
                                response[5].sizeUp();
                            case JULY:
                                response[6].sizeUp();
                            case AUGUST:
                                response[7].sizeUp();
                            case SEPTEMBER:
                                response[8].sizeUp();
                            case OCTOBER:
                                response[9].sizeUp();
                            case NOVEMBER:
                                response[10].sizeUp();
                            case DECEMBER:
                                response[11].sizeUp();
                        }
                        return response;
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
        if (userRepository.findByEmail(request.getEmail()).isPresent())
            throw new UserAlreadyRegisteredException();

        userRepository.save(
                User.builder()
                .email(request.getEmail())
                .name(request.getName())
                .password(passwordEncoder.encode(request.getPassword()))
                .userType(UserType.APPLICANT)
                .build()
        );
    }

    @Override
    public MyPageResponse viewMyPage() {
        System.out.println(authenticationFacade.getUserEmail());
        return userRepository.findByEmail(authenticationFacade.getUserEmail())
                .map(user -> {
                    String companyName;
                    if (user.getCompany() == null)
                        companyName = "등록된 회사 없음";
                    else {
                        companyName = user.getCompany().getCompanyName();
                    }
                    return MyPageResponse.builder()
                            .companyName(companyName)
                            .name(user.getName())
                            // .grass(Arrays.asList(viewUserGrass()))
                            .build();
                })
                .orElseThrow(UserNotFoundException::new);
    }
}
