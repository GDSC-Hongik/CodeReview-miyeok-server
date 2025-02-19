package gdsc.codereview.domain.review.dto;

import gdsc.codereview.domain.review.entity.Review;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import lombok.Getter;

@Getter
public class ReviewResponseDto {
    private final Long reviewId;
    private final String content;
    private final Long score;

    public ReviewResponseDto(Review review) {
        this.reviewId = review.getId();
        this.content = review.getContent();
        this.score = review.getScore();
    }
}