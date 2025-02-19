package gdsc.codereview.domain.review.service;

import gdsc.codereview.domain.lecture.dto.LectureDto;
import gdsc.codereview.domain.lecture.entity.Lecture;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/mypage")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    @GetMapping("/{userId}/list")
    @Operation(summary = "특정 유저가 리뷰를 작성한 강좌 목록 조회")
    public ResponseEntity<List<LectureDto>> getLecturesByUser(@PathVariable Long userId) {
        List<Lecture> lectures = reviewService.getLecturesByUser(userId);
        List<LectureDto> response = lectures.stream()
                .map(LectureDto::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }
}
