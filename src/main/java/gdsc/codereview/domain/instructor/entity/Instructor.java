package gdsc.codereview.domain.instructor.entity;

import gdsc.codereview.domain.lecture.entity.Lecture;
import gdsc.codereview.global.Platform;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Instructor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "instructor_id")
    private Long id;

    private String name; //강사

    private String instruction; //소개

    private Double score;

    @Enumerated(EnumType.STRING)
    private Platform platform;

    private String link;

    @OneToMany(mappedBy = "instructor", cascade = CascadeType.ALL)
    private List<Lecture> lectures;

    @Builder
    public Instructor(Long id, String name, String instruction, Double score,
                         Platform platform, String link) {
        this.id = id;
        this.name = name;
        this.instruction = instruction;
        this.score = score;
        this.platform = platform;
        this.link = link;
    }
}
