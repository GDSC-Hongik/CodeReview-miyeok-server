package gdsc.codereview.domain.lecture.dto.comparison;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LectureSimpleResponseDto {

    private LectureInfo[] lectures;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class LectureInfo {
        private String title;
        private String thumbnail;
        private Long students;
        private Double score;
    }
}