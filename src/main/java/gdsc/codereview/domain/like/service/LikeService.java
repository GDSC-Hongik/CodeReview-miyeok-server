package gdsc.codereview.domain.like.service;

import gdsc.codereview.domain.like.dto.LikeResponseDto;
import gdsc.codereview.domain.like.entity.Like;
import gdsc.codereview.domain.like.entity.LikeType;
import gdsc.codereview.domain.like.repository.LikeRepository;
import gdsc.codereview.domain.review.entity.Review;
import gdsc.codereview.domain.review.repository.ReviewRepository;

import gdsc.codereview.domain.user.entity.User;
import gdsc.codereview.domain.user.repository.UserRepository;
import gdsc.codereview.global.exception.GeneralException;
import gdsc.codereview.global.exception.status.ErrorStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class LikeService {

    private final LikeRepository likeRepository;
    private final UserRepository userRepository;
    private final ReviewRepository reviewRepository;

    @Transactional
    public LikeResponseDto toggleLike(Long userId, Long reviewId, LikeType likeType) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("해당 유저를 찾을 수 없습니다."));
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new NoSuchElementException("해당 리뷰를 찾을 수 없습니다."));

        Optional<Like> existingLike = likeRepository.findByUserAndReview(user, review);

        if (existingLike.isPresent()) {
            Like like = existingLike.get();

            // 같은 타입이면 취소
            if (like.getLikeType() == likeType) {
                likeRepository.delete(like);
                if (likeType == LikeType.LIKE) {
                    review.setLiked(review.getLiked() - 1);
                } else {
                    review.setHated(review.getHated() - 1);
                }
            } else {
                // 반대 타입이면 변경 불가
                throw new GeneralException(ErrorStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            // 좋아요/싫어요 등록
            Like newLike = Like.builder()
                    .user(user)
                    .review(review)
                    .likeType(likeType)
                    .build();
            likeRepository.save(newLike);

            if (likeType == LikeType.LIKE) {
                review.setLiked(review.getLiked() + 1);
            } else {
                review.setHated(review.getHated() + 1);
            }
        }

        // 변경된 좋아요/싫어요 개수 저장
        reviewRepository.save(review);

        return new LikeResponseDto(review.getLiked(), review.getHated(), likeType + " 처리되었습니다.");
    }
}
