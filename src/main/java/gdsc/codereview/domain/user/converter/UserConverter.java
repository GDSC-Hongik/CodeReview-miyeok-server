package gdsc.codereview.domain.user.converter;

import gdsc.codereview.domain.user.dto.response.UserResponse;
import gdsc.codereview.domain.user.entity.User;

public class UserConverter {

    public static UserResponse toUserResDto(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .role(user.getRole())
                .name(user.getName())
                .introduction(user.getIntroduction())
                .provider(user.getProvider())  // 로그인 정보 (OAuthType)
                .socialId(user.getSocialId())
                .build();
    }
}