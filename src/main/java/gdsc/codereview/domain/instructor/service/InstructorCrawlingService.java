package gdsc.codereview.domain.instructor.service;

import gdsc.codereview.domain.instructor.dto.InstructorDto;
import gdsc.codereview.domain.instructor.entity.Instructor;
import gdsc.codereview.domain.instructor.repository.InstructorRepository;
import gdsc.codereview.domain.lecture.entity.Lecture;
import gdsc.codereview.domain.lecture.repository.LectureRepository;
import gdsc.codereview.global.Platform;
import gdsc.codereview.global.SeleniumConfig;
import lombok.RequiredArgsConstructor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.stereotype.Service;
import org.openqa.selenium.By;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InstructorCrawlingService {

    private final LectureRepository lectureRepository;
    private final InstructorRepository instructorRepository;

    public void crawlingAndSaveInflearnInstructor(){
        List<Lecture> lectures = lectureRepository.findAll();

        for(Lecture lecture : lectures){

            Optional<Instructor> existingInstructor = instructorRepository.findByLink(lecture.getInstructionLink());

            if (existingInstructor.isPresent()) {
                // 이미 존재하는 강사라면 패스
                continue;
            }

            String url = lecture.getInstructionLink();

            WebDriver driver = new ChromeDriver(SeleniumConfig.getChromeOptions());
            driver.get(url);

            //크롤링
            WebElement webName = driver.findElement(By.xpath("//*[@id=\"__next\"]/main/div/aside/div/div/div[1]/div/div/div[2]/p"));
            WebElement webInstruction = driver.findElement(By.xpath("//*[@id=\"__next\"]/main/div/div[2]/div/section"));
            WebElement webScore = driver.findElement(By.xpath("//*[@id=\"__next\"]/main/div/aside/div/div/div[2]/div/div[3]/p[2]/span"));

            //크롤링 요소 맵핑
            String name = webName.getText();
            String instruction = webInstruction.getText();
            Double score = Double.parseDouble(webScore.getText());

            InstructorDto instructorDto = InstructorDto.builder()
                    .name(name)
                    .instruction(instruction)
                    .link(url)
                    .platform(Platform.INFLEARN)
                    .score(score).
                    build();

            instructorRepository.save(instructorDto.toEntity());

            driver.quit();
        }
    }


}
