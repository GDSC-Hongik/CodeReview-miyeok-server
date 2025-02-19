package gdsc.codereview.domain.user.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import gdsc.codereview.domain.user.entity.OAuthType;
import gdsc.codereview.domain.user.entity.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SignInResponse {

    @Schema(description = "Access Token")
    private String accessToken;

    @Schema(description = "Refresh Token")
    private String refreshToken;

    @Schema(description = "아이디")
    private Long id;

    @Schema(description = "이메일(아이디)")
    private String email;

    @Schema(description = "권한")
    private Role role;

    @Schema(description = "이름")
    private String name;

    @Schema(description = "로그인 타입")
    private OAuthType provider;

}