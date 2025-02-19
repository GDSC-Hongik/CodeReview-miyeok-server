package gdsc.codereview.domain.lecture.service;

import gdsc.codereview.domain.lecture.dto.LectureDto;
import gdsc.codereview.domain.lecture.dto.LectureReviewDto;
import gdsc.codereview.domain.lecture.entity.Category;
import gdsc.codereview.domain.lecture.entity.Lecture;
import gdsc.codereview.domain.lecture.repository.LectureRepository;
import gdsc.codereview.domain.review.entity.Review;
import gdsc.codereview.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class LectureService {
    private final LectureRepository lectureRepository;
    private final ReviewRepository reviewRepository;

    public List<LectureDto> getAllLectures() {
        List<Lecture> lectures = lectureRepository.findAll();
        return lectures.stream().map(LectureDto::new).collect(Collectors.toList());
    }

    public List<LectureDto> getLecturesByCategory(Category category) {
        List<Lecture> lectures = lectureRepository.findByCategory(category);
        return lectures.stream().map(LectureDto::new).collect(Collectors.toList());
    }

    public List<LectureReviewDto> getLectureWithReview(String title) {
        List<Lecture> lectures = lectureRepository.findByTitle(title);

        // 강좌에 맞는 리뷰를 가져오기 위함
        List<LectureReviewDto> lectureReviewDtos = new ArrayList<>();
        for (Lecture lecture : lectures) {
            List<Review> reviews = reviewRepository.findByLectureId(lecture.getId());

            LectureReviewDto dto = new LectureReviewDto(lecture, reviews);
            lectureReviewDtos.add(dto);
        }

        return lectureReviewDtos;
    }

    
    // ID에 맞는 summary 가져오기 위함
    public String getLectureSummary(Long lectureId) {
        Optional<Lecture> lecture = lectureRepository.findById(lectureId);
        return lecture.map(Lecture::getSummary).orElseThrow(() -> new IllegalArgumentException("강좌를 찾을 수 없습니다."));
    }

    public Lecture getLecture(Long lectureId) {
        Optional<Lecture> lecture = lectureRepository.findById(lectureId);
        return lecture.orElseThrow(() -> new IllegalArgumentException("강좌를 찾을 수 없습니다."));
    }

}
