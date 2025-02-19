package gdsc.codereview.domain.user.dto.request;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserInfoRequest {
    private String email;
}
