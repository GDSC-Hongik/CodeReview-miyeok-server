package gdsc.codereview.domain.review.service;

import gdsc.codereview.domain.instructor.entity.Instructor;
import gdsc.codereview.domain.lecture.entity.Lecture;
import gdsc.codereview.domain.lecture.repository.LectureRepository;
import gdsc.codereview.domain.review.dto.ReviewDto;
import gdsc.codereview.domain.review.entity.Review;
import gdsc.codereview.domain.review.repository.ReviewRepository;
import gdsc.codereview.global.Platform;
import gdsc.codereview.global.SeleniumConfig;
import lombok.RequiredArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewCrawlingService {

    private final ReviewRepository reviewRepository;
    private final LectureRepository lectureRepository;

    public void crawlingAndSaveInflearnReview(){
        List<Lecture> lectures = lectureRepository.findAll();
        for(Lecture lecture: lectures){
            String lectureLink = lecture.getLink();

            WebDriver driver = new ChromeDriver(SeleniumConfig.getChromeOptions());
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

            driver.get(lectureLink);

            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[contains(@class, 'mantine-1413au')]")));

            // 리뷰 크롤링 - 5개
            List<WebElement> webNames = driver.findElements(By.xpath("//a[contains(@class, 'mantine-1413au')]"));
            List<WebElement> webContents = driver.findElements(By.xpath("//p[contains(@class,'css-2csv75')]"));
            List<WebElement> webScores = driver.findElements(By.xpath("//p[contains(@aria-label, '평점')]"));

            for(int i=0;i< webNames.size();i++){
                String name = webNames.get(i).getText();
                String content = webContents.get(i).getText();
                Long score = Long.parseLong(webScores.get(i).getText());

                if(content.isEmpty()){
                    content = "수강평점만 등록된 리뷰입니다.";
                }

                ReviewDto reviewDto = ReviewDto.builder()
                        .lecture(lecture)
                        .username(name)
                        .content(content)
                        .score(score)
                        .platform(Platform.INFLEARN)
                        .build();

                reviewRepository.save(reviewDto.toEntity());
            }

            driver.quit();

        }
    }

}
