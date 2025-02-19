package gdsc.codereview.domain.lecture.service;


import gdsc.codereview.domain.lecture.dto.comparison.LectureComparisonRequestDto;
import gdsc.codereview.domain.lecture.dto.comparison.LectureComparisonResponseDto;

public interface LectureComparisonService {
    LectureComparisonResponseDto compareLectures(LectureComparisonRequestDto requestDto);
}