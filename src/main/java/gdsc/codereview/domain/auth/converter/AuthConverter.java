package gdsc.codereview.domain.auth.converter;

import gdsc.codereview.domain.user.dto.response.SignInResponse;
import gdsc.codereview.domain.user.dto.response.TokenResponse;
import gdsc.codereview.domain.user.entity.User;

public class AuthConverter {
    public static SignInResponse toSignInResDto(User user, TokenResponse tokenResponse) {
        return SignInResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .role(user.getRole())
                .email(user.getEmail())
                .provider(user.getProvider())
                .accessToken(tokenResponse.getAccessToken())
                .refreshToken(tokenResponse.getRefreshToken())
                .build();
    }
}