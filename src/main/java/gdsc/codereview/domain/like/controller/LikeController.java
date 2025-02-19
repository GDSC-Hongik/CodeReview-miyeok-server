package gdsc.codereview.domain.like.controller;

import gdsc.codereview.domain.like.dto.CountResponseDto;
import gdsc.codereview.domain.like.dto.LikeResponseDto;
import gdsc.codereview.domain.like.entity.LikeType;
import gdsc.codereview.domain.like.service.LikeService;
import gdsc.codereview.domain.review.entity.Review;
import gdsc.codereview.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/review/likes")
@RequiredArgsConstructor
public class LikeController {

    private final LikeService likeService;
    private final ReviewRepository reviewRepository;

    @PostMapping("/{reviewId}")
    public LikeResponseDto toggleLike(
            @PathVariable Long reviewId,
            @RequestParam Long userId,
            @RequestParam LikeType likeType
    ) {
        return likeService.toggleLike(userId, reviewId, likeType);
    }

    @GetMapping("/{reviewId}")
    public ResponseEntity<CountResponseDto> getLikeDislikeCount(@PathVariable Long reviewId) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 리뷰입니다."));

        CountResponseDto response = new CountResponseDto(review.getLiked(), review.getHated());
        return ResponseEntity.ok(response);
    }
}
