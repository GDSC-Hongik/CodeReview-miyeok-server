package gdsc.codereview.domain.lecture.service;

import gdsc.codereview.domain.lecture.dto.LectureDto;
import gdsc.codereview.domain.lecture.entity.Category;
import gdsc.codereview.domain.lecture.entity.Lecture;
import gdsc.codereview.domain.lecture.repository.LectureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class LectureService {
    private final LectureRepository lectureRepository;

    public List<LectureDto> getAllLectures() {
        List<Lecture> lectures = lectureRepository.findAll();
        return lectures.stream().map(LectureDto::new).collect(Collectors.toList());
    }

    public List<LectureDto> getLecturesByCategory(Category category) {
        List<Lecture> lectures = lectureRepository.findByCategory(category);
        return lectures.stream().map(LectureDto::new).collect(Collectors.toList());
    }
}
