package gdsc.codereview.domain.user.controller;

import gdsc.codereview.domain.user.dto.response.IntroductionRequest;
import gdsc.codereview.domain.user.entity.User;
import gdsc.codereview.global.exception.ApiResponse;
import gdsc.codereview.domain.user.dto.response.UserResponse;
import gdsc.codereview.domain.user.converter.UserConverter;
import gdsc.codereview.domain.auth.service.AuthDetails;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Tag(name = "User")
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("")
    @Operation(summary = "유저 정보 조회")
    public ApiResponse<UserResponse> getUser(@AuthenticationPrincipal AuthDetails authDetails) {
        User user = authDetails.user();
        return ApiResponse.onSuccess(UserConverter.toUserResDto(user));
    }

    @PostMapping("")
    @Operation(summary = "유저 정보 입력", description = "description 정보 입력")
    public ApiResponse<UserResponse> createUserInfo(@AuthenticationPrincipal AuthDetails authDetails, @RequestBody IntroductionRequest introductionRequest) {
        return ApiResponse.onSuccess(userService.createUserInfo(String.valueOf(authDetails.user().getId()), introductionRequest.getIntroduction()));
    }


}