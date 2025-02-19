package gdsc.codereview.domain.review.controller;

import gdsc.codereview.domain.lecture.dto.LectureDto;
import gdsc.codereview.domain.lecture.entity.Lecture;
import gdsc.codereview.domain.review.dto.ReviewCreateDto;
import gdsc.codereview.domain.review.dto.ReviewResponseDto;
import gdsc.codereview.domain.review.service.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Tag(name="리뷰", description = "리뷰 등록 및 조회 관련")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    @PostMapping("/review/create")
    @Operation(summary = "리뷰등록", description = "특정 강좌의 리뷰를 등록합니다(CODEREVIEW 사이트 내에서)")
    public void createReview(@RequestBody ReviewCreateDto requestDto){
        reviewService.createReview(requestDto.getCourseId(), requestDto.getEmail(), requestDto.getContent(), requestDto.getScore());
    }

    @GetMapping("/{userId}/mypage/course/list")
    @Operation(summary = "특정 유저가 리뷰를 작성한 강좌 목록 조회 (userId 1로 고정)")
    public ResponseEntity<List<LectureDto>> getLecturesByUser(@PathVariable Long userId) {
        List<Lecture> lectures = reviewService.getLecturesByUser(userId);
        List<LectureDto> response = lectures.stream()
                .map(LectureDto::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{userId}/mypage/review/list")
    @Operation(summary = "특정 유저가 작성한 리뷰 목록 조회 (userId 1로 고정)")
    public ResponseEntity<List<ReviewResponseDto>> getReviewsByUser(@PathVariable Long userId) {
        List<ReviewResponseDto> reviews = reviewService.getReviewsByUser(userId);
        return ResponseEntity.ok(reviews);
    }
}
