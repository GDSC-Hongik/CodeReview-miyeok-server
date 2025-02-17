package gdsc.codereview.domain.lecture.controller;

import gdsc.codereview.domain.lecture.dto.LectureDto;
import gdsc.codereview.domain.lecture.dto.LectureReviewDto;
import gdsc.codereview.domain.lecture.entity.Category;
import gdsc.codereview.domain.lecture.entity.Lecture;
import gdsc.codereview.domain.lecture.service.LectureService;
import lombok.RequiredArgsConstructor;
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

    @GetMapping("course")
    public ResponseEntity<List<LectureReviewDto>> getLectureWithReview(@RequestParam("title") String title){
        List<LectureReviewDto> lectureReviewDtos = lectureService.getLectureWithReview(title);
        return ResponseEntity.ok(lectureReviewDtos);
    }
}
