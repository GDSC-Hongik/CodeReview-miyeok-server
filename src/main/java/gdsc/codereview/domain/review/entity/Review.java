package gdsc.codereview.domain.review.entity;


import gdsc.codereview.domain.lecture.entity.Lecture;
import gdsc.codereview.domain.user.entity.User;
import gdsc.codereview.global.Platform;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
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

    @Column(columnDefinition = "LONGTEXT", nullable = false)
    private String content; //리뷰 내용

    @Enumerated(EnumType.STRING)
    private Platform platform;

    @Column(nullable = false)
    private Long score; //등록평점

    // 크롤링 한 데이터는 기본 null 값이 들어가므로 Integer로 설정
    @Column(nullable = false, columnDefinition = "INT DEFAULT 0")
    private Integer liked=0;

    @Column(nullable = false, columnDefinition = "INT DEFAULT 0")
    private Integer hated=0;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;


    public void increaseLike() {
        this.liked++;
    }

    public void decreaseLike() {
       if (this.liked > 0) {
           this.liked = Math.max(0, this.liked - 1);
       }
    }

    public void increaseDislike() {
        this.hated++;
    }

    public void decreaseDislike() {
        if (this.hated > 0) {
            this.hated = Math.max(0, this.hated - 1);
        }
    }

    @Builder
    public Review(String username, String content, Platform platform, Lecture lecture,
                  Long score, Integer liked, Integer hated) {
        this.username = username;
        this.content = content;
        this.platform = platform;
        this.lecture = lecture;
        this.score = score;
        this.liked = liked;
        this.hated = hated;
    }
}
