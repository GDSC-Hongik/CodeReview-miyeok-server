package gdsc.codereview.domain.instructor.dto;

import gdsc.codereview.domain.instructor.entity.Instructor;
import gdsc.codereview.domain.lecture.dto.LectureDto;
import gdsc.codereview.domain.lecture.entity.Lecture;
import gdsc.codereview.global.Platform;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class InstructorWithLectureDto {

    private String name;
    private String instruction;
    private Double score;
    private Platform platform;
    private String link;
    private List<LectureDto> lectures;

    public InstructorWithLectureDto(Instructor instructor, List<Lecture> lectures){
        this.name = instructor.getName();
        this.instruction = instructor.getInstruction();
        this.score = instructor.getScore();
        this.platform = instructor.getPlatform();
        this.link = instructor.getLink();
        this.lectures = lectures.stream().map(LectureDto::fromEntity).collect(Collectors.toList());
    }

}
