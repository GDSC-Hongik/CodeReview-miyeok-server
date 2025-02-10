package gdsc.codereview.domain.lecture.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Lecture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lecture_id")
    private Long id;

    // fk instruction id

    private String title;

    private String summary; //간단한 소개

    private String content; //전체 내용

    private String platform;

    @Column(name = "lecture_score")
    private Float score;

}
