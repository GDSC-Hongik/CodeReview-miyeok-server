package gdsc.codereview.domain.lecture.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Lecture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lecture_id")
    private Long id;

    // fk instruction id

    private String title;

    private String summary; //간단한 소개

    @Enumerated(EnumType.STRING)
    private Platform platform;

    @Column(name = "lecture_score")
    private Double score;
    
    private String thumbnail; //이미지 경로 저장

    private String link; // 강좌 페이지

    @Enumerated(EnumType.STRING)
    private Category category;

    private Long students; //수강생 수

    @Builder
    public Lecture(String title, String thumbnail, Platform platform, Category category,
                   String summary, Double score, Long students, String link) {
        this.title = title;
        this.thumbnail = thumbnail;
        this.platform = platform;
        this.category = category;
        this.summary = summary;
        this.score = score;
        this.students = students;
        this.link= link;
    }

}
