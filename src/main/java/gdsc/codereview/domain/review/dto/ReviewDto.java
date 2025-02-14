package gdsc.codereview.domain.review.dto;

import gdsc.codereview.domain.lecture.entity.Lecture;
import gdsc.codereview.domain.review.entity.Review;
import gdsc.codereview.global.Platform;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ReviewDto {

    private Long id;
    private String username;
    private String content;
    private Platform platform;
    private Lecture lecture;
    private Long score;
    private Long liked;
    private Long hated;

    @Builder
    public ReviewDto(String username, String content, Platform platform, Long score,
                     Lecture lecture, Long liked, Long hated) {
        this.username = username;
        this.content = content;
        this.lecture = lecture;
        this.platform = platform;
        this.score = score;
        this.liked = liked;
        this.hated = hated;
    }

    public Review toEntity() {
        return Review.builder()
                .username(username)
                .content(content)
                .lecture(lecture)
                .platform(platform)
                .score(score)
                .liked(liked)
                .hated(hated)
                .build();
    }

}
