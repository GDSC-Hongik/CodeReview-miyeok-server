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
    public List<String> lectureListCrawling(WebDriver driver) throws Exception{ //한 페이지 크롤링

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
        }

        return lecturesLink;
    }

    @Transactional
    public void allPageCrolling() throws Exception{
        System.setProperty("webdriver.chrome.driver","driver/chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        List<String> allLecturesLink = new ArrayList<>();

        for(int i=1;i<=11;i++){ // 임시 하드코딩

            //인프런 웹 개발 카테고리 url - 최신순
            String url = "https://www.inflearn.com/courses/it-programming/web-dev?sort=RECENT";

            //페이지네이션
            if(i!=1) url+="&page_number="+i;

            driver.get(url);

            allLecturesLink.addAll(lectureListCrawling(driver));
        }

        // 웹 드라이버 종료
        driver.quit();

    }

}
