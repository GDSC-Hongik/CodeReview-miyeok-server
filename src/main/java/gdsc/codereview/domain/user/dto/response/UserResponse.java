package gdsc.codereview.domain.user.dto.response;

import gdsc.codereview.domain.user.entity.OAuthType;
import gdsc.codereview.domain.user.entity.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UserResponse {

    @Schema(description = "아이디")
    private Long id;

    @Schema(description = "이름")
    private String name;

    @Schema(description = "이메일(아이디)")
    private String email;

    @Schema(description = "소개글")
    private String introduction;

    @Schema(description = "로그인 정보")
    private OAuthType provider;

    @Schema(description = "소셜 로그인 아이디")
    private String socialId;

    @Schema(description = "권한")
    private Role role;



}