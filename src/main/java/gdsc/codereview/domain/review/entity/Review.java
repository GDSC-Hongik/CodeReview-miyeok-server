package gdsc.codereview.domain.review.entity;


import gdsc.codereview.domain.lecture.entity.Lecture;
import gdsc.codereview.global.Platform;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@RequiredArgsConstructor
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lecture_id")
    private Lecture lecture;

    // 유저 - 크롤링
    private String username;

    @Column(columnDefinition = "LONGTEXT")
    private String content; //리뷰 내용

    @Enumerated(EnumType.STRING)
    private Platform platform;

    private Long score; //등록평점

    private Long liked;

    private Long hated;

    @Builder
    public Review(String username, String content, Platform platform, Lecture lecture,
                  Long score, Long liked, Long hated) {
        this.username = username;
        this.content = content;
        this.platform = platform;
        this.lecture = lecture;
        this.score = score;
        this.liked = liked;
        this.hated = hated;
    }
}
