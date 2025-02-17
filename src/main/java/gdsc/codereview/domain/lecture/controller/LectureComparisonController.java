package gdsc.codereview.domain.lecture.controller;

import gdsc.codereview.domain.lecture.dto.comparison.LectureComparisonRequestDto;
import gdsc.codereview.domain.lecture.dto.comparison.LectureComparisonResponseDto;
import gdsc.codereview.domain.lecture.service.LectureComparisonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/comparison")
public class LectureComparisonController {

    private final LectureComparisonService lectureComparisonService;

    @PostMapping("")
    public ResponseEntity<LectureComparisonResponseDto> compareLectures(@RequestBody LectureComparisonRequestDto requestDto) {
        LectureComparisonResponseDto responseDto = lectureComparisonService.compareLectures(requestDto);
        return ResponseEntity.ok(responseDto);
    }
}
