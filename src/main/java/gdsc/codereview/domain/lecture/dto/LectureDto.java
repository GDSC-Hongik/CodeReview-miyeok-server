package gdsc.codereview.domain.lecture.dto;

import gdsc.codereview.domain.lecture.entity.Category;
import gdsc.codereview.domain.lecture.entity.Lecture;
import gdsc.codereview.domain.lecture.entity.Platform;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
public class LectureDto {
    private String title;
    private String summary;
    private Platform platform;
    private Double score;
    private String thumbnail;
    private String link;
    private Category category;
    private Long students;

    // DTO -> Entity 변환 메서드
    public Lecture toEntity() {
        return Lecture.builder()
                .title(title)
                .thumbnail(thumbnail)
                .summary(summary)
                .score(score)
                .students(students)
                .lectureLink(link)
                .build();
    }
}
