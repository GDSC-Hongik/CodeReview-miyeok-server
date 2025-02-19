package gdsc.codereview.domain.instructor.controller;

import gdsc.codereview.domain.instructor.dto.InstructorDto;
import gdsc.codereview.domain.instructor.dto.InstructorWithLectureDto;
import gdsc.codereview.domain.instructor.service.InstructorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/instructor")
@RequiredArgsConstructor
public class InstructorController {

    private final InstructorService instructorService;

    @GetMapping
    public ResponseEntity<InstructorWithLectureDto> getInstructor(@RequestParam("name") String name){
        InstructorWithLectureDto instructorWithLectureDto =  instructorService.getInstructor(name);
        return ResponseEntity.ok(instructorWithLectureDto);
    }
}
