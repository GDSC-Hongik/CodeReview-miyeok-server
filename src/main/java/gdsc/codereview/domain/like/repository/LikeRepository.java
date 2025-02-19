package gdsc.codereview.domain.like.repository;

import gdsc.codereview.domain.like.entity.Like;
import gdsc.codereview.domain.review.entity.Review;
import gdsc.codereview.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {

    // 특정 유저가 특정 리뷰에 남긴 좋아요/싫어요를 조회
    Optional<Like> findByUserAndReview(User user, Review review);

}