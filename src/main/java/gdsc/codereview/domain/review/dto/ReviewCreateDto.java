package gdsc.codereview.domain.review.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ReviewCreateDto {
    private Long courseId;
    private String email;
    private String content;
    private Long score;
}
