package gdsc.codereview.domain.lecture.dto;

import gdsc.codereview.domain.lecture.entity.Category;
import gdsc.codereview.domain.lecture.entity.Platform;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LectureDto {
    private String title;
    private String summary;
    private String content;
    private Platform platform;
    private Float lectureScore;
    private String thumbnail;
    private String link;
    private Category category;
}
