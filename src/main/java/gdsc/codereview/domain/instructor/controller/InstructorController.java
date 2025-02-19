package gdsc.codereview.domain.instructor.controller;

import gdsc.codereview.domain.instructor.dto.InstructorDto;
import gdsc.codereview.domain.instructor.dto.InstructorWithLectureDto;
import gdsc.codereview.domain.instructor.service.InstructorService;
import io.swagger.v3.oas.annotations.Operation;
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
    @Operation(summary = "강사 페이지 ", description = "강사의 세부정보를 가져옵니다.")
    public ResponseEntity<InstructorWithLectureDto> getInstructor(@RequestParam("name") String name){
        InstructorWithLectureDto instructorWithLectureDto =  instructorService.getInstructor(name);
        return ResponseEntity.ok(instructorWithLectureDto);
    }
}
