package gdsc.codereview.domain.lecture.dto;

import gdsc.codereview.domain.instructor.entity.Instructor;
import gdsc.codereview.domain.lecture.entity.Category;
import gdsc.codereview.domain.lecture.entity.Lecture;
import gdsc.codereview.global.Platform;
import lombok.Builder;
import lombok.Getter;

@Getter
public class LectureDto {

    private Long id;
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
    private Long price;

    // Entity -> Dto
    public LectureDto(Lecture lecture) {
        this.id = lecture.getId();
        this.title = lecture.getTitle();
        this.summary = lecture.getSummary();
        this.price = lecture.getPrice();
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
    public LectureDto(Long id,String title, String summary, Platform platform, Double score, String thumbnail, Long price,
                      String instructorName, String instructorLink, String link, Category category, Long students) {
        this.id = id;
        this.title = title;
        this.summary = summary;
        this.platform = platform;
        this.price = price;
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
                .id(id)
                .platform(platform)
                .category(category)
                .title(title)
                .price(price)
                .instructorName(instructorName)
                .thumbnail(thumbnail)
                .instructorLink(instructorLink)
                .summary(summary)
                .score(score)
                .students(students)
                .link(link)
                .build();
    }

    // 강사 페이지에서 사용하기 위함
    public static LectureDto fromEntity(Lecture lecture){
        return LectureDto.builder()
                .id(lecture.getId())
                .title(lecture.getTitle())
                .thumbnail(lecture.getThumbnail())
                .summary(lecture.getSummary())
                .build();
    }
}
