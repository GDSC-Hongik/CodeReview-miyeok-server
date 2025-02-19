package gdsc.codereview.domain.lecture.dto.comparison;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LectureComparisonResponseDto {

    private Long lectureId1;
    private Long lectureId2;
    private String comparisonResult; // 강의 비교 결과
}