package gdsc.codereview.domain.lecture.repository;

import gdsc.codereview.domain.lecture.domain.Lecture;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@SpringBootTest
@Transactional
class LectureRepositoryTest {

    @Autowired
    LectureRepository lectureRepository;
    @Test
    public void 제목에_특정_키워드가_들어간_강좌를_찾는다() throws Exception{

        // given
        Lecture lecture = new Lecture();
        lecture.setTitle("자바 Spring 강의");
        lectureRepository.save(lecture);

        // when
        List<Lecture> foundLectures = lectureRepository.findByTitleContaining("Spring");

        // then
        assertThat(foundLectures.get(0).getTitle()).isEqualTo("자바 Spring 강의");
    }
}