package gdsc.codereview.domain.lecture.dto;

import gdsc.codereview.domain.instructor.entity.Instructor;
import gdsc.codereview.domain.lecture.entity.Category;
import gdsc.codereview.domain.lecture.entity.Lecture;
import gdsc.codereview.domain.review.dto.ReviewDto;
import gdsc.codereview.domain.review.entity.Review;
import gdsc.codereview.global.Platform;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class LectureReviewDto {
    private Long id;
    private String title;
    private String summary;
    private String instructorLink;
    private String instructorName;
    private Platform platform;
    private Double score;
    private String thumbnail;
    private String link;
    private Category category;
    private Long students;

    private List<ReviewDto> reviews;

    public LectureReviewDto(Lecture lecture, List<Review> reviews) {
        this.id = lecture.getId();
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
        this.reviews = reviews.stream().map(ReviewDto::fromEntity).collect(Collectors.toList());
    }

}
