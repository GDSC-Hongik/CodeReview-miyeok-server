package gdsc.codereview.domain.review.controller;

import gdsc.codereview.domain.review.dto.ReviewCreateDto;
import gdsc.codereview.domain.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    @PostMapping("/review/create")
    public void createReview(@RequestBody ReviewCreateDto requestDto){
        reviewService.createReview(requestDto.getCourseId(), requestDto.getEmail(), requestDto.getContent(), requestDto.getScore());
    }


}
