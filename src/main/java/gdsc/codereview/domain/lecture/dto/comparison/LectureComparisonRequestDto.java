package gdsc.codereview.domain.lecture.dto.comparison;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LectureComparisonRequestDto {
    private Long lectureId1;
    private Long lectureId2;
}
