package gdsc.codereview.domain.lecture.service.impl;

import gdsc.codereview.domain.gpt.service.ChatGPTService;
import gdsc.codereview.domain.lecture.dto.comparison.LectureComparisonRequestDto;
import gdsc.codereview.domain.lecture.dto.comparison.LectureComparisonResponseDto;
import gdsc.codereview.domain.lecture.entity.Lecture;
import gdsc.codereview.domain.lecture.repository.LectureRepository;
import gdsc.codereview.domain.lecture.service.LectureComparisonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LectureComparisonServiceImpl implements LectureComparisonService {

    private final LectureRepository lectureRepository;
    private final ChatGPTService chatGPTService;

    @Override
    public LectureComparisonResponseDto compareLectures(LectureComparisonRequestDto requestDto) {
        // 강의 ID를 사용해 각각의 강의 데이터를 가져옴
        Lecture lecture1 = lectureRepository.findById(requestDto.getLectureId1())
                .orElseThrow(() -> new IllegalArgumentException("강의를 찾을 수 없습니다."));
        Lecture lecture2 = lectureRepository.findById(requestDto.getLectureId2())
                .orElseThrow(() -> new IllegalArgumentException("강의를 찾을 수 없습니다."));

        // 강의 summary 값을 가져와 OpenAI API에 전달
        String lecture1Summary = lecture1.getSummary();
        String lecture2Summary = lecture2.getSummary();

        // OpenAI API를 사용하여 강의 내용 비교
        String comparisonResult = chatGPTService.compareLectureSummaries(lecture1Summary, lecture2Summary);

        return LectureComparisonResponseDto.builder()
                .lectureId1(requestDto.getLectureId1())
                .lectureId2(requestDto.getLectureId2())
                .comparisonResult(comparisonResult)
                .build();
    }
}
