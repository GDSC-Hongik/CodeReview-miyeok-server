package gdsc.codereview.domain.user.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class IntroductionRequest {

    @Schema(description = "소개글")
    private String introduction;

}
