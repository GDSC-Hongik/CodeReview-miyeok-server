package gdsc.codereview.domain.auth.controller;

import gdsc.codereview.domain.auth.jwt.JwtProvider;
import gdsc.codereview.domain.auth.service.AuthDetails;
import gdsc.codereview.domain.auth.service.AuthService;
import gdsc.codereview.domain.auth.service.SocialLoginService;
import gdsc.codereview.domain.user.dto.request.SignInRequest;
import gdsc.codereview.domain.user.dto.response.SignInResponse;
import gdsc.codereview.domain.user.dto.response.TokenResponse;
import gdsc.codereview.global.exception.ApiResponse;
import gdsc.codereview.global.exception.GeneralException;
import gdsc.codereview.global.exception.status.ErrorStatus;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@Tag(name = "Authentication", description = "로그인/로그아웃/회원탈퇴/토큰 재발급 등")
@Slf4j
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final SocialLoginService socialLoginService;
    private final JwtProvider jwtProvider;

    @PostMapping("/google/sign-in")
    @Operation(summary = "구글 로그인")
    public ResponseEntity<SignInResponse> signInWithGoogle(@RequestBody SignInRequest request) {
        log.info("Received code: " + request.getCode());

        return ResponseEntity.ok(socialLoginService.signInWithGoogle(request.getCode()));
    }


    @PostMapping("/sign-out")
    @Operation(summary = "로그아웃",  security = @SecurityRequirement(name = "JWT Authentication"))
    public ResponseEntity<ApiResponse<String>> signOut(HttpServletRequest request) {
        String refreshToken = jwtProvider.resolveRefreshToken(request);
        String accessToken = jwtProvider.resolveAccessToken(request);

        if(refreshToken==null || accessToken==null){
            throw new GeneralException(ErrorStatus.TOKEN_NOT_FOUND);
        }

        authService.signOut(refreshToken, accessToken);
        return ResponseEntity.ok(ApiResponse.onSuccess("로그아웃이 성공적으로 처리되었습니다."));
    }

    @Operation(summary = "토큰재발급", security = @SecurityRequirement(name = "JWT Authentication"))
    @GetMapping("/recreate")
    public ResponseEntity<TokenResponse> recreate(HttpServletRequest request, @AuthenticationPrincipal AuthDetails authDetails) {
        String token = request.getHeader("Authorization");
        if(token ==null){
            throw new GeneralException(ErrorStatus.TOKEN_NOT_FOUND);
        }
        return ResponseEntity.ok().body(authService.recreate(token, authDetails.user()));
    }

    @Operation(summary = "회원탈퇴", security = @SecurityRequirement(name = "JWT Authentication"))
    @DeleteMapping("/withdraw")
    public ResponseEntity<ApiResponse<String>> withdraw(HttpServletRequest request, @AuthenticationPrincipal AuthDetails authDetails) {
        String refreshToken = jwtProvider.resolveRefreshToken(request);
        String accessToken = jwtProvider.resolveAccessToken(request);

        if(refreshToken==null || accessToken==null){
            throw new GeneralException(ErrorStatus.TOKEN_NOT_FOUND);
        }

        authService.withdraw(authDetails.user(), refreshToken, accessToken);
        return ResponseEntity.ok(ApiResponse.onSuccess("회원탈퇴가 성공적으로 처리되었습니다."));
    }
}