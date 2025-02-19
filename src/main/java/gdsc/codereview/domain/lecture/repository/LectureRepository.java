package gdsc.codereview.domain.lecture.repository;

import gdsc.codereview.domain.lecture.entity.Category;
import gdsc.codereview.domain.lecture.entity.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LectureRepository extends JpaRepository<Lecture, Long> {
    List<Lecture> findByTitleContaining(String title);

    List<Lecture> findByCategory(Category category);

    List<Lecture> findByTitle(String title);

    List<Lecture> findByInstructorName(String name);

}
