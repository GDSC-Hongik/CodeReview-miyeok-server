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

    @Builder
    public LectureDto(String title, String summary, Platform platform, Double score, String thumbnail,
                      String link, Category category, Long students) {
        this.title = title;
        this.summary = summary;
        this.platform = platform;
        this.score = score;
        this.thumbnail = thumbnail;
        this.link = link;
        this.category = category;
        this.students = students;
    }

    // DTO -> Entity 변환 메서드
    public Lecture toEntity() {
        return Lecture.builder()
                .platform(platform)
                .category(category)
                .title(title)
                .thumbnail(thumbnail)
                .summary(summary)
                .score(score)
                .students(students)
                .link(link)
                .build();
    }
}
