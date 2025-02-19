package gdsc.codereview.domain.user.controller;

import gdsc.codereview.domain.user.dto.request.IntroductionRequest;
import gdsc.codereview.domain.user.dto.request.UserInfoRequest;
import gdsc.codereview.domain.user.entity.User;
import gdsc.codereview.domain.user.service.UserService;
import gdsc.codereview.domain.user.dto.response.UserResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "회원")
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("")
    @Operation(summary = "회원 정보 조회")
    public ResponseEntity<UserResponse> getUser(@RequestBody UserInfoRequest request) {

        String userEmail = request.getEmail();

        User user = userService.getUserByEmail(userEmail);

        // 사용자 정보가 없으면 404
        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        UserResponse userResponse = UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .introduction(user.getIntroduction())
                .provider(user.getProvider())
                .socialId(user.getSocialId())
                .role(user.getRole())
                .build();

        return ResponseEntity.ok(userResponse);
    }

    @PostMapping("")
    @Operation(summary = "회원 소개글 등록", description = "introduction 등록")
    public ResponseEntity<UserResponse> createUserInfo( @RequestBody IntroductionRequest introductionRequest) {

        String userEmail = introductionRequest.getEmail();
        String introduction = introductionRequest.getIntroduction();

        User user = userService.getUserByEmail(userEmail);


        user = userService.updateUserIntroduction(user, introduction);

        UserResponse userResponse = buildUserResponse(user);

        return ResponseEntity.ok(userResponse);
    }


    @PutMapping("/update")
    @Operation(summary = "회원 소개글 수정", description = "introduction 수정")
    public ResponseEntity<UserResponse> updateIntroduction(@RequestBody IntroductionRequest introductionRequest) {

        String userEmail = introductionRequest.getEmail();
        String introduction = introductionRequest.getIntroduction();

        User user = userService.getUserByEmail(userEmail);

        user = userService.updateUserIntroduction(user, introduction);

        UserResponse userResponse = buildUserResponse(user);

        return ResponseEntity.ok(userResponse);
    }

    private UserResponse buildUserResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .introduction(user.getIntroduction())
                .provider(user.getProvider())
                .socialId(user.getSocialId())
                .role(user.getRole())
                .build();
    }
}