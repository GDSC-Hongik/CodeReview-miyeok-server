package gdsc.codereview.domain.instructor.entity;

import gdsc.codereview.global.Platform;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Instructor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "instructor_id")
    private Long id;

    private String name;

    private String instruction;

    private Double score;

    private Platform platform;

    private String link;

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
