package gdsc.codereview.domain.like.controller;

import gdsc.codereview.domain.like.dto.CountResponseDto;
import gdsc.codereview.domain.like.dto.LikeResponseDto;
import gdsc.codereview.domain.like.entity.LikeType;
import gdsc.codereview.domain.like.service.LikeService;
import gdsc.codereview.domain.review.entity.Review;
import gdsc.codereview.domain.review.repository.ReviewRepository;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/review/likes")
@RequiredArgsConstructor
public class LikeController {

    private final LikeService likeService;
    private final ReviewRepository reviewRepository;

    @Operation(summary = "좋아요/싫어요 토글", description = "같은 타입 2번 요청 시 취소됨. 취소하지 않은 채로 다른 타입 요청 시 오류 반환")
    @PostMapping("/{reviewId}")
    public LikeResponseDto toggleLike(
            @PathVariable Long reviewId,
            @RequestParam Long userId,
            @RequestParam LikeType likeType
    ) {
        return likeService.toggleLike(userId, reviewId, likeType);
    }

    @Operation(summary = "좋아요/싫어요 개수")
    @GetMapping("/{reviewId}")
    public ResponseEntity<CountResponseDto> getLikeDislikeCount(@PathVariable Long reviewId) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 리뷰입니다."));

        CountResponseDto response = new CountResponseDto(review.getLiked(), review.getHated());
        return ResponseEntity.ok(response);
    }
}
