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


        if (user == null) {
            return ResponseEntity.notFound().build();
        } else {
            // 사용자가 존재
            user = user.updateUserInfo(introduction);
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


    @PostMapping("/update")
    @Operation(summary = "회원 소개글 수정", description = "introduction 수정")
    public ResponseEntity<UserResponse> updateIntroduction(@RequestBody IntroductionRequest introductionRequest) {

        String userEmail = introductionRequest.getEmail();
        String introduction = introductionRequest.getIntroduction();

        // 이메일로 사용자 정보 조회
        User user = userService.getUserByEmail(userEmail);

        if (user == null) {
            // 사용자가 존재하지 않으면 404 반환
            return ResponseEntity.notFound().build();
        } else {
            // 사용자가 존재하면 소개글만 수정
            user = user.updateUserInfo(introduction);
        }

        // 수정된 사용자 정보를 UserResponse로 변환
        UserResponse userResponse = UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .introduction(user.getIntroduction())
                .provider(user.getProvider())
                .socialId(user.getSocialId())
                .role(user.getRole())
                .build();

        // 수정된 사용자 정보를 응답으로 반환
        return ResponseEntity.ok(userResponse);
    }
}