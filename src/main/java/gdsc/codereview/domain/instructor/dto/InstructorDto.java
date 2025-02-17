package gdsc.codereview.domain.instructor.dto;

import gdsc.codereview.domain.instructor.entity.Instructor;
import gdsc.codereview.global.Platform;
import lombok.Builder;
import lombok.Getter;

@Getter
public class InstructorDto {
    private Long id;
    private String name;
    private String instruction;
    private Double score;
    private Platform platform;
    private String link;

    @Builder
    public InstructorDto(Long id, String name, String instruction, Double score,
                         Platform platform, String link) {
        this.id = id;
        this.name = name;
        this.instruction = instruction;
        this.score = score;
        this.platform = platform;
        this.link = link;
    }

    public Instructor toEntity(){
        return Instructor.builder()
                .name(name)
                .instruction(instruction)
                .score(score)
                .platform(platform)
                .link(link)
                .build();
    }
}
