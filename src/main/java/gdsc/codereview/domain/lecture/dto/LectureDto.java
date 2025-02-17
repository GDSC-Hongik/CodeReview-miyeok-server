package gdsc.codereview.domain.lecture.dto;

import gdsc.codereview.domain.instructor.entity.Instructor;
import gdsc.codereview.domain.lecture.entity.Category;
import gdsc.codereview.domain.lecture.entity.Lecture;
import gdsc.codereview.global.Platform;
import lombok.Builder;
import lombok.Getter;

@Getter
public class LectureDto {
    private String title;
    private String summary;
    private Instructor instructor;
    private String instructorLink;
    private String instructorName;
    private Platform platform;
    private Double score;
    private String thumbnail;
    private String link;
    private Category category;
    private Long students;

    // Entity -> Dto
    public LectureDto(Lecture lecture) {
        this.title = lecture.getTitle();
        this.summary = lecture.getSummary();
        this.instructorName = lecture.getInstructor().getName(); // Lazy Loading 문제 해결용
        this.instructorLink = lecture.getInstructorLink();
        this.platform = lecture.getPlatform();
        this.score = lecture.getScore();
        this.thumbnail = lecture.getThumbnail();
        this.link = lecture.getLink();
        this.category = lecture.getCategory();
        this.students = lecture.getStudents();
    }

    @Builder
    public LectureDto(String title, String summary, Platform platform, Double score, String thumbnail,
                      String instructorName, String instructorLink, String link, Category category, Long students) {
        this.title = title;
        this.summary = summary;
        this.platform = platform;
        this.instructorName = instructorName;
        this.instructorLink = instructorLink;
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
                .instructorName(instructorName)
                .thumbnail(thumbnail)
                .instructorLink(instructorLink)
                .summary(summary)
                .score(score)
                .students(students)
                .link(link)
                .build();
    }
}
