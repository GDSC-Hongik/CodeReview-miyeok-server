package gdsc.codereview.domain.auth.service;

import gdsc.codereview.domain.auth.converter.AuthConverter;
import gdsc.codereview.domain.auth.jwt.JwtProvider;
import gdsc.codereview.domain.user.controller.UserService;
import gdsc.codereview.domain.user.converter.UserConverter;
import gdsc.codereview.domain.user.dto.response.SignInResponse;
import gdsc.codereview.domain.user.dto.response.TokenResponse;
import gdsc.codereview.domain.user.dto.response.UserResponse;
import gdsc.codereview.domain.user.entity.User;
import gdsc.codereview.global.exception.GeneralException;
import gdsc.codereview.global.exception.status.ErrorStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AuthService {

    private final UserService userService;
    private final JwtProvider jwtProvider;


    @Transactional
    public void signOut(String refreshToken, String accessToken) {
        if (!jwtProvider.validateToken(accessToken, "access")) {
            throw new GeneralException(ErrorStatus.TOKEN_INVALID);
        }

        jwtProvider.invalidateTokens(refreshToken, accessToken);
    }

    public TokenResponse recreate(String token, User user) {
        if (user == null) {
            throw new GeneralException(ErrorStatus.USER_NOT_FOUND);
        }

        String refreshToken = token.substring(7);
        boolean isValid = jwtProvider.validateToken(refreshToken, "refresh");

        if (!isValid) {
            throw new GeneralException(ErrorStatus.TOKEN_INVALID);
        }

        String email = jwtProvider.getEmail(refreshToken);

        return jwtProvider.recreate(user, refreshToken);
    }

    @Transactional
    public void withdraw(User user, String refreshToken, String accessToken) {
        if (user == null) {
            throw new GeneralException(ErrorStatus.USER_NOT_FOUND);
        }

        if (!jwtProvider.validateToken(accessToken, "access")) {
            throw new GeneralException(ErrorStatus.TOKEN_INVALID);
        }

        userService.withdraw(user);
        jwtProvider.invalidateTokens(refreshToken, accessToken);
    }
}