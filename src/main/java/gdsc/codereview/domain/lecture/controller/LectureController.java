package gdsc.codereview.domain.lecture.controller;

import gdsc.codereview.domain.lecture.dto.LectureDto;
import gdsc.codereview.domain.lecture.dto.LectureReviewDto;
import gdsc.codereview.domain.lecture.entity.Category;
import gdsc.codereview.domain.lecture.entity.Lecture;
import gdsc.codereview.domain.lecture.service.LectureService;
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
    public ResponseEntity<List<LectureDto>> getLectureList(){
        List<LectureDto> lectureDtos = lectureService.getAllLectures();
        return ResponseEntity.ok(lectureDtos);
    }

    @GetMapping("/category")
    public ResponseEntity<List<LectureDto>> getCatagoryLectureList(@RequestParam("category") Category category){
        List<LectureDto> lectureDtos = lectureService.getLecturesByCategory(category);
        return ResponseEntity.ok(lectureDtos);
    }

    @GetMapping("/course")
    public ResponseEntity<List<LectureReviewDto>> getLectureWithReview(@RequestParam("title") String title){
        List<LectureReviewDto> lectureReviewDtos = lectureService.getLectureWithReview(title);
        return ResponseEntity.ok(lectureReviewDtos);
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchKeyword(@RequestParam("keyword") String keyword){
        List<LectureDto> lectureDtos = lectureService.searchLectureOrInstructor(keyword);

        if (lectureDtos.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK)  // 임의로 200 OK 상태코드 설정
                    .body("강좌나 강사가 검색되지 않습니다");
        }
        
        return ResponseEntity.ok(lectureDtos);
    }
}
