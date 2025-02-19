package gdsc.codereview.domain.like.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LikeResponseDto {
    private Integer likeCount;
    private Integer dislikeCount;
    private String message;
}
