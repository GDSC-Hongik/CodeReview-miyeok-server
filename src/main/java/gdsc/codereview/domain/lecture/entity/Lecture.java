package gdsc.codereview.domain.lecture.entity;

import gdsc.codereview.domain.instructor.entity.Instructor;
import gdsc.codereview.domain.review.entity.Review;
import gdsc.codereview.global.Platform;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Lecture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lecture_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "instructor_id")
    private Instructor instructor; //FK

    @Column(nullable = false, name = "instructor_link")
    private String instructorLink;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String instructorName;

    @Column(columnDefinition = "LONGTEXT",nullable = false)
    private String summary; //간단한 소개

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Platform platform;

    @Column(name = "lecture_score", nullable = false)
    private Double score;

    @Column(nullable = false)
    private String thumbnail; //이미지 경로 저장

    @Column(nullable = false)
    private String link; // 강좌 페이지

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Category category;

    @Column(nullable = false)
    private Long students; //수강생 수

    @OneToMany(mappedBy = "lecture", cascade = CascadeType.ALL)
    private List<Review> reviews;

    @Builder
    public Lecture(String title, String thumbnail, Platform platform, Category category, String instructorLink, String instructorName,
                   Instructor instructor, String summary, Double score, Long students, String link) {
        this.title = title;
        this.thumbnail = thumbnail;
        this.platform = platform;
        this.instructorLink = instructorLink;
        this.instructorName=instructorName;
        this.instructor=instructor;
        this.category = category;
        this.summary = summary;
        this.score = score;
        this.students = students;
        this.link= link;
    }

}
