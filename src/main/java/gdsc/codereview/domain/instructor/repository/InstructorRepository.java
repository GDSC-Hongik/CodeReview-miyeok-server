package gdsc.codereview.domain.instructor.repository;

import gdsc.codereview.domain.instructor.entity.Instructor;
import gdsc.codereview.domain.lecture.entity.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InstructorRepository extends JpaRepository<Instructor, Long> {

    Optional<Instructor> findByLink(String link);
}
