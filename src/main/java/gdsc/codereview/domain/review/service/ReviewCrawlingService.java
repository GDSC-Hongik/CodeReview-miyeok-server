package gdsc.codereview.domain.review.service;

import gdsc.codereview.domain.lecture.entity.Lecture;
import gdsc.codereview.domain.lecture.repository.LectureRepository;
import gdsc.codereview.domain.review.dto.ReviewDto;
import gdsc.codereview.domain.review.repository.ReviewRepository;
import gdsc.codereview.global.Platform;
import gdsc.codereview.global.SeleniumConfig;
import lombok.RequiredArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.stereotype.Service;

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
            driver.get(lectureLink);

            // 리뷰 크롤링 - 5개
            // 5개가 안되거나 없는 경우,, 나중에 고려하기
            List<WebElement> webNames = driver.findElements(By.xpath("//a[contains(@class, 'mantine-1413au')]"));
            List<WebElement> webContents = driver.findElements(By.xpath("//p[contains(@class,'css-2csv75')]"));
            List<WebElement> webScores = driver.findElements(By.xpath("//p[contains(@aria-label, '평점')]"));

            for(int i=0;i< webNames.size();i++){
                String name = webNames.get(i).getText();
                String content = webContents.get(i).getText();
                Long score = Long.parseLong(webScores.get(i).getText());

                ReviewDto reviewDto = ReviewDto.builder()
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
