package gdsc.codereview.domain.review.controller;

import gdsc.codereview.domain.review.dto.ReviewCreateDto;
import gdsc.codereview.domain.review.service.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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


}
