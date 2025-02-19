package gdsc.codereview.domain.lecture.controller;

import gdsc.codereview.domain.lecture.dto.comparison.LectureComparisonRequestDto;
import gdsc.codereview.domain.lecture.dto.comparison.LectureComparisonResponseDto;
import gdsc.codereview.domain.lecture.dto.comparison.LectureSimpleResponseDto;
import gdsc.codereview.domain.lecture.entity.Lecture;
import gdsc.codereview.domain.lecture.service.LectureComparisonService;
import gdsc.codereview.domain.lecture.service.LectureService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "비교", description = "강좌 단순 비교 및 ChatGPT를 이용한 비교 내용")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/comparison")
public class LectureComparisonController {

    private final LectureComparisonService lectureComparisonService;
    private final LectureService lectureService;

    @Operation(summary = "단순 비교", description = "강좌명/이미지 url/학생수/가격/평점")
    @PostMapping("/simple")
    public ResponseEntity<LectureSimpleResponseDto> compareSimple(@RequestBody LectureComparisonRequestDto requestDto) {
        // 요청 바디에서 강좌 ID 가져오기
        Long lectureId1 = requestDto.getLectureId1();
        Long lectureId2 = requestDto.getLectureId2();

        // 강좌 데이터를 가져오는 서비스 호출
        Lecture lecture1 = lectureService.getLecture(lectureId1);
        Lecture lecture2 = lectureService.getLecture(lectureId2);

        // LectureSimpleResponseDto에 데이터를 채움
        LectureSimpleResponseDto dto = new LectureSimpleResponseDto();

        // 첫 번째 강좌 정보
        LectureSimpleResponseDto.LectureInfo info1 = new LectureSimpleResponseDto.LectureInfo(
                lecture1.getTitle(),
                lecture1.getThumbnail(),
                lecture1.getStudents(),
                lecture1.getScore()
        );

        // 두 번째 강좌 정보
        LectureSimpleResponseDto.LectureInfo info2 = new LectureSimpleResponseDto.LectureInfo(
                lecture2.getTitle(),
                lecture2.getThumbnail(),
                lecture2.getStudents(),
                lecture2.getScore()
        );

        // lectures 배열에 두 개의 강좌 정보를 설정
        dto.setLectures(new LectureSimpleResponseDto.LectureInfo[]{info1, info2});

        // ResponseEntity로 반환
        return ResponseEntity.ok(dto);
    }

    @Operation(summary = "분석 내용", description = "prompt를 호출해 비교")
    @PostMapping("/detail")
    public ResponseEntity<LectureComparisonResponseDto> compareLectures(@RequestBody LectureComparisonRequestDto requestDto) {
        LectureComparisonResponseDto responseDto = lectureComparisonService.compareLectures(requestDto);
        return ResponseEntity.ok(responseDto);
    }
}
