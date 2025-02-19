package gdsc.codereview.domain.instructor.service;

import gdsc.codereview.domain.instructor.dto.InstructorDto;
import gdsc.codereview.domain.instructor.dto.InstructorWithLectureDto;
import gdsc.codereview.domain.instructor.entity.Instructor;
import gdsc.codereview.domain.instructor.repository.InstructorRepository;
import gdsc.codereview.domain.lecture.dto.LectureDto;
import gdsc.codereview.domain.lecture.entity.Lecture;
import gdsc.codereview.domain.lecture.repository.LectureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class InstructorService {
    private final InstructorRepository instructorRepository;
    private final LectureRepository lectureRepository;

    public InstructorWithLectureDto getInstructor(String name){
        Instructor instructor = instructorRepository.findByName(name);
        List<Lecture> lectures = lectureRepository.findByInstructorName(name);

        return new InstructorWithLectureDto(instructor,lectures);

    }
}
