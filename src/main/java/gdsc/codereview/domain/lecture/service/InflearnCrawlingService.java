package gdsc.codereview.domain.lecture.service;

import gdsc.codereview.domain.lecture.entity.Lecture;
import gdsc.codereview.domain.lecture.repository.LectureRepository;
import lombok.RequiredArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class InflearnCrawlingService {

    private final LectureRepository lectureRepository;

    @Transactional
    public List<String> lectureListCrawling() throws Exception{

        System.setProperty("webdriver.chrome.driver","driver/chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        //인프런 웹 개발 카테고리 url
        String url = "https://www.inflearn.com/courses/it-programming/web-dev";

        driver.get(url);

        List<String> lecturesLink = new ArrayList<>();

        try{
            List<WebElement> ulElements = driver.findElements(By.xpath("//ul[contains(@class, 'mantine-1avyp1d')]"));

            for (WebElement ul : ulElements) {
                List<WebElement> liElements = ul.findElements(By.tagName("li"));

                for (WebElement li : liElements) {
                    WebElement aTag = li.findElement(By.tagName("a"));

                    String link = aTag.getAttribute("href");

                    lecturesLink.add(link);
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            // 웹 드라이버 종료
            driver.quit();
        }

        return lecturesLink;
    }
}
