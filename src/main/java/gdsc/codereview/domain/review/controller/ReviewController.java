package gdsc.codereview.domain.review.controller;

import gdsc.codereview.domain.lecture.dto.LectureDto;
import gdsc.codereview.domain.lecture.entity.Lecture;
import gdsc.codereview.domain.review.dto.ReviewCreateDto;
import gdsc.codereview.domain.review.service.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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

    @GetMapping("/{userId}/mypage/list")
    @Operation(summary = "특정 유저가 리뷰를 작성한 강좌 목록 조회")
    public ResponseEntity<List<LectureDto>> getLecturesByUser(@PathVariable Long userId) {
        List<Lecture> lectures = reviewService.getLecturesByUser(userId);
        List<LectureDto> response = lectures.stream()
                .map(LectureDto::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

}
