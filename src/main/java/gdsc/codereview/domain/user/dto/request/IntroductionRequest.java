package gdsc.codereview.domain.user.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class IntroductionRequest {

    private String email;
    private String introduction;

}
