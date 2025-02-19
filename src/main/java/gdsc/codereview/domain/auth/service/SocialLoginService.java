package gdsc.codereview.domain.auth.service;

import gdsc.codereview.domain.auth.jwt.JwtProvider;
import gdsc.codereview.domain.auth.security.GoogleClient;
import gdsc.codereview.domain.auth.security.dto.GoogleToken;
import gdsc.codereview.domain.user.dto.response.SignInResponse;

import gdsc.codereview.domain.user.entity.OAuthType;
import gdsc.codereview.domain.user.entity.Role;
import gdsc.codereview.domain.user.entity.User;
import gdsc.codereview.domain.user.repository.UserRepository;
import gdsc.codereview.global.exception.GeneralException;
import gdsc.codereview.global.exception.status.ErrorStatus;
import gdsc.codereview.domain.user.dto.response.TokenResponse;
import gdsc.codereview.domain.auth.converter.AuthConverter;
import gdsc.codereview.domain.auth.security.dto.GoogleProfile;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SocialLoginService {

    @Value("${spring.security.oauth2.client.registration.google.redirect-uri}")
    private String googleRedirectUrl;

    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;
    private final GoogleClient googleClient;

    @Transactional
    public SignInResponse signInWithGoogle(String code) {

        GoogleToken googleAccessToken = googleClient.getGoogleAccessToken(code);

        GoogleProfile googleProfile = googleClient.getMemberInfo(googleAccessToken);

        // 반환된 정보의 이메일 기반으로 사용자 테이블에서 계정 정보 조회 진행
        String email = googleProfile.email();
        if (email == null) {
            throw new GeneralException(ErrorStatus.USER_NOT_FOUND);
        }

        // 사용자 정보를 DB에서 조회하거나, 없으면 새로 생성
        User user = userRepository.findByEmail(email)
                .orElseGet(() -> createUser(email, googleProfile, OAuthType.GOOGLE));

        // 이미 구글로 가입된 사용자가 아니면 예외 발생
        if (user.getProvider() != OAuthType.GOOGLE) {
            throw new GeneralException(ErrorStatus.ALREADY_EXIST_USER);
        }

        TokenResponse tokenResponse = jwtProvider.createToken(user);;

        return AuthConverter.toSignInResDto(user, tokenResponse);
    }


    @Transactional
    public User createUser(String email, GoogleProfile googleProfile, OAuthType provider) {
        User user = User.builder()
                .email(email)
                .role(Role.USER)
                .name(googleProfile.name()) // Google에서 받은 이름을 사용
                .build();
        return userRepository.save(user);
    }
}
