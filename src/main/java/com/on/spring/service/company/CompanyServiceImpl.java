package com.on.spring.service.company;

import com.on.spring.entity.board.Board;
import com.on.spring.entity.board.BoardRepository;
import com.on.spring.entity.category.Category;
import com.on.spring.entity.category.CategoryRepository;
import com.on.spring.entity.company.Company;
import com.on.spring.entity.company.CompanyRepository;
import com.on.spring.entity.companylike.CompanyLikeRepository;
import com.on.spring.entity.grass.Grass;
import com.on.spring.entity.user.User;
import com.on.spring.entity.user.UserRepository;
import com.on.spring.entity.work.Work;
import com.on.spring.entity.work.WorkRepository;
import com.on.spring.exception.*;
import com.on.spring.payload.request.AddWorkRequest;
import com.on.spring.payload.request.RegisterCompanyRequest;
import com.on.spring.payload.request.UploadBoardRequest;
import com.on.spring.payload.response.BoardResponse;
import com.on.spring.payload.response.GrassResponse;
import com.on.spring.payload.response.MemberResponse;
import com.on.spring.payload.response.WorkResponse;
import com.on.spring.security.auth.AuthenticationFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository companyRepository;
    private final UserRepository userRepository;
    private final CompanyLikeRepository likeRepository;
    private final AuthenticationFacade authenticationFacade;
    private final WorkRepository workRepository;
    private final BoardRepository boardRepository;
    private final CategoryRepository categoryRepository;

    @Value("${spring.file.path}")
    private String filePath;

    public void registerCompany(RegisterCompanyRequest request) {
        if (!userRepository.findByEmail(request.getUserEmail())
                .map(User::isOwner)
                .orElseThrow(UserNotFoundException::new)
        ) {
            throw new UserNotOwnerException();
        }

        companyRepository.save(
                Company.builder()
                .ceoName(request.getCeoName())
                .companyName(request.getCompanyName())
                .ceoName(request.getCeoName()).like(0L)
                .build()
        );
    }

/*
    @Override
    public void addUserToCompany(String email, Long companyId) {
        companyRepository.findByCompanyId(companyId)
                .map(company -> company.addUser(userRepository.findByEmail(email)
                            .orElseThrow(UserNotFoundException::new)))
                .map(companyRepository::save)
                .orElseThrow(CompanyNotFoundException::new);
    }

 */

    @Override
    public List<MemberResponse> viewCompanyMember(Long companyId) {
        return companyRepository.findByCompanyId(companyId)
                .map(company -> {
                    List<MemberResponse> members = new ArrayList<>();

                    for (User user : company.getUsers()) {
                        List<GrassResponse> grasses = new ArrayList<>();

                        for (Grass grass : user.getGrasses()) {
                            grasses.add(new GrassResponse(grass.getCreatedDateAt().toString(), grass.getInformation()));
                        }

                        members.add(MemberResponse.builder()
                                .email(user.getEmail())
                                .grassResponse(grasses)
                                .name(user.getName())
                                .build());
                    }

                    return members;
                })
                .orElseThrow(CompanyNotFoundException::new);
    }

    @Override
    public void uploadCompanyPreviewImage(MultipartFile file, Long companyId) {
        try {
            file.transferTo(new File(filePath + "company/" + companyId + "/" + "preview.png"));
        } catch (IOException e) {
            throw new FileUploadFailedException();
        }
    }

    @Override
    public void companyLike(Long companyId) {
        userRepository.findByEmail(authenticationFacade.getUserEmail())
                .map(user -> {
                    likeRepository.findAllByCompanyIdAndUserId(companyId, user.getEmail());
                    return companyRepository.findByCompanyId(companyId)
                            .map(Company::addLike)
                            .map(companyRepository::save)
                            .orElseThrow(CompanyNotFoundException::new);
                })
                .orElseThrow(InvalidTokenException::new);
    }

    @Override
    public List<WorkResponse> viewWorks(String userEmail) {
        List<Work> works = workRepository.findAllByTargetUserEmail(userEmail);
        List<WorkResponse> workResponses = new ArrayList<>();

        for (Work work : works) {
            Duration duration = Duration.between(work.getDate(), LocalDateTime.now());
            workResponses.add(new WorkResponse(work.getWorkName(), work.getWorkContent(), duration.toString()));
        }

        return workResponses;
    }

    @Override
    public void addWorks(AddWorkRequest request, String userId) {
        workRepository.save(
                    Work.builder()
                            .requestId(request.getRequestId())
                            .targetUserEmail(userId)
                            .workName(request.getWorkName())
                            .date(LocalDateTime.of(request.getYear(), request.getMonth(), request.getDay(), request.getHour(), request.getMinute()))
                            .workContent(request.getWorkContent())
                            .build()
        );
    }

    @Override
    public List<BoardResponse> viewBoardList(long companyId) {
        List<BoardResponse> responses = new ArrayList<>();

        for (Board board : boardRepository.findAllByCompanyId(companyId)) {
            responses.add(new BoardResponse(board.getName(), board.getId()));
        }

        return responses;
    }

    @Override
    public void uploadBoard(UploadBoardRequest request, Long companyId) {
        Board board = null;
        Category targetCategory = null;

        for (Category category : categoryRepository.findAllByCompanyId(companyId)) {
            if (request.getBoardName().equals(category.getCategoryName()))
                targetCategory = category;
                break;
        }

        if (targetCategory != null) {
            board = boardRepository.save(
                    Board.builder()
                            .name(request.getBoardName())
                            .category(targetCategory)
                            .build()
            );
        }
        else {
            targetCategory = categoryRepository.save(
                    Category.builder()
                    .categoryName(request.getCategory())
                    .companyId(companyId)
                    .build()
            );

            board = boardRepository.save(
                    Board.builder()
                    .name(request.getBoardName())
                    .category(targetCategory)
                    .build()
            );
        }

        try {
            request.getFile().transferTo(new File(filePath + "board" + companyId + "/" + board.getId() + "/" + "read.md"));
        } catch (IOException e) {
            throw new FileUploadFailedException();
        }
    }

    @Override
    public MultipartFile viewBoard(Long companyId, Long boardId) {
        MultipartFile file;

        try {
            file = new MockMultipartFile("read.md", new FileInputStream(filePath + companyId + "/" + boardId + "/" + "read.md"));
        } catch (IOException e) {
            throw new FileIsNotFoundException();
        }

        return file;
    }
}
