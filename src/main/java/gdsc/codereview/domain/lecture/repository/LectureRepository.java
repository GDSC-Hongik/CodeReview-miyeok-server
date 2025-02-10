package gdsc.codereview.domain.lecture.repository;

import gdsc.codereview.domain.lecture.domain.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LectureRepository extends JpaRepository<Lecture, Long> {
    List<Lecture> findByTitleContaining(String title);
}
