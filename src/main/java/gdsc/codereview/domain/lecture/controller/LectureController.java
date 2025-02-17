package gdsc.codereview.domain.lecture.controller;

import gdsc.codereview.domain.lecture.dto.LectureDto;
import gdsc.codereview.domain.lecture.entity.Lecture;
import gdsc.codereview.domain.lecture.service.LectureService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/lecture")
@RequiredArgsConstructor
public class LectureController {

    private final LectureService lectureService;

    @GetMapping
    public List<LectureDto> getLectureList(){
        return lectureService.getAllLectures();
    }

}
