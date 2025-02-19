package gdsc.codereview.domain.review.service;

import gdsc.codereview.domain.lecture.entity.Lecture;
import gdsc.codereview.domain.review.entity.Review;
import gdsc.codereview.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public List<Lecture> getLecturesByUser(Long userId) {
        List<Review> reviews = reviewRepository.findAllByUserId(userId);
        return reviews.stream()
                .map(Review::getLecture)
                .collect(Collectors.toList());
    }
}
