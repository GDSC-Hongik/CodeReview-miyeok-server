package gdsc.codereview.domain.lecture.controller;

import gdsc.codereview.domain.lecture.dto.LectureDto;
import gdsc.codereview.domain.lecture.dto.LectureReviewDto;
import gdsc.codereview.domain.lecture.entity.Category;
import gdsc.codereview.domain.lecture.entity.Lecture;
import gdsc.codereview.domain.lecture.service.LectureService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lecture")
@RequiredArgsConstructor
public class LectureController {

    private final LectureService lectureService;

    @GetMapping
    @Operation(summary = "강좌 전체 리스트", description = "모든 강좌를 가져옵니다.")
    public ResponseEntity<List<LectureDto>> getLectureList(){
        List<LectureDto> lectureDtos = lectureService.getAllLectures();
        return ResponseEntity.ok(lectureDtos);
    }

    @GetMapping("/category")
    @Operation(summary = "카테고리별 강좌 리스트", description = "카테고리를 선택했을때 특정 카테고리의 강좌를 가져옵니다.")
    public ResponseEntity<List<LectureDto>> getCatagoryLectureList(@RequestParam("category") Category category){
        List<LectureDto> lectureDtos = lectureService.getLecturesByCategory(category);
        return ResponseEntity.ok(lectureDtos);
    }

    @GetMapping("/course")
    @Operation(summary = "특정 강좌", description = "특정 강좌 페이지를 가져옵니다(리뷰가 들어있는)")
    public ResponseEntity<List<LectureReviewDto>> getLectureWithReview(@RequestParam("title") String title){
        List<LectureReviewDto> lectureReviewDtos = lectureService.getLectureWithReview(title);
        return ResponseEntity.ok(lectureReviewDtos);
    }

    @GetMapping("/search")
    @Operation(summary = "검색", description = "제목이나 강사를 키워드로 검색할 수 있습니다.")
    public ResponseEntity<?> searchKeyword(@RequestParam("keyword") String keyword){
        List<LectureDto> lectureDtos = lectureService.searchLectureOrInstructor(keyword);

        if (lectureDtos.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK)  // 임의로 200 OK 상태코드 설정
                    .body("강좌나 강사가 검색되지 않습니다");
        }
        
        return ResponseEntity.ok(lectureDtos);
    }
}
