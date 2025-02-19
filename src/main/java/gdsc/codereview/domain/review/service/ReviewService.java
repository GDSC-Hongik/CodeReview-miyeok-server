package gdsc.codereview.domain.review.service;

import gdsc.codereview.domain.lecture.entity.Lecture;
import gdsc.codereview.domain.lecture.repository.LectureRepository;
import gdsc.codereview.domain.review.entity.Review;
import gdsc.codereview.domain.review.repository.ReviewRepository;
import gdsc.codereview.domain.user.entity.User;
import gdsc.codereview.domain.user.repository.UserRepository;
import gdsc.codereview.global.Platform;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final LectureRepository lectureRepository;


    //임시토큰 - 이메일
    public void createReview(Long courseId, String email, String content, Long score) {
        Optional<User> user = userRepository.findByEmail(email);

        Review review = new Review();
        review.setUser(user.orElse(null));
        review.setContent(content);
        review.setLecture(lectureRepository.findById(courseId).orElse(null));
        review.setScore(score);

        reviewRepository.save(review);
    }

    public List<Lecture> getLecturesByUser(Long userId) {
        List<Review> reviews = reviewRepository.findAllByUserId(userId);
        return reviews.stream()
                .map(Review::getLecture)
                .collect(Collectors.toList());
    }
}
