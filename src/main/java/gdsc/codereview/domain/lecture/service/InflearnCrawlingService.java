package gdsc.codereview.domain.lecture.service;

import gdsc.codereview.domain.lecture.dto.LectureDto;
import gdsc.codereview.domain.lecture.entity.Category;
import gdsc.codereview.domain.lecture.entity.Lecture;
import gdsc.codereview.domain.lecture.entity.Platform;
import gdsc.codereview.domain.lecture.repository.LectureRepository;
import lombok.RequiredArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class InflearnCrawlingService {

    private final LectureRepository lectureRepository;

    public void onePageLectureCrawling(String url, WebDriver driver) throws Exception{ //한 페이지 크롤링

        driver.get(url);

        try{
            List<WebElement> ulElements = driver.findElements(By.xpath("//ul[contains(@class, 'mantine-1avyp1d')]"));

            for (WebElement ul : ulElements) {
                List<WebElement> liElements = ul.findElements(By.tagName("li"));

                for (WebElement li : liElements) {
                    WebElement aTag = li.findElement(By.tagName("a"));

                    String link = aTag.getAttribute("href");

                    System.out.println("link#############"+link);

                    detailLectureCrawling(link);
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void allPageLectureCrolling() throws Exception{

        System.setProperty("webdriver.chrome.driver","driver/chromedriver.exe");

        WebDriver driver = new ChromeDriver(SeleniumConfig.getChromeOptions());

//        List<String> allLecturesLink = new ArrayList<>();

        for(int i=1;i<=1;i++){ // 임시 하드코딩

            //인프런 웹 개발 카테고리 url - 최신순
            String url = "https://www.inflearn.com/courses/it-programming/web-dev?sort=RECENT";

            //페이지네이션
            if(i!=1) url+="&page_number="+i;

            onePageLectureCrawling(url, driver);
//            allLecturesLink.addAll(onePageLectureCrawling(driver));
        }

        // 웹 드라이버 종료
        driver.quit();

    }

    public void detailLectureCrawling(String link) {

        WebDriver driver = new ChromeDriver(SeleniumConfig.getChromeOptions());

        driver.get(link);

        // 각 요소 크롤링
        WebElement webThumbnail = driver.findElement(By.xpath("//*[@id=\"__next\"]/div[1]/div[3]/div/section[1]/div/div/div[2]/div/div[2]/div[1]/figure/div/img"));
        WebElement webTitle = driver.findElement(By.xpath("//*[@id=\"__next\"]/div[1]/div[3]/div/section[1]/div/div/div[1]/div/div[1]/h1"));
        WebElement webInstructor = driver.findElement(By.xpath("//*[@id=\"__next\"]/div[1]/div[3]/div/section[1]/div/div/div[1]/div/div[2]/div[2]/ul/li/a"));
        WebElement webSummary = driver.findElement(By.xpath("//*[@id=\"__next\"]/div[1]/div[3]/div/section[1]/div/div/div[1]/div/div[1]/p"));

        // 크롤링된 요소 맵핑
        String thumbnail = webThumbnail.getAttribute("src"); //link를 저장
        String title = webTitle.getText();
        String instructor = webInstructor.getText();
        String instructorLink = webInstructor.getAttribute("href");
        String summary = webSummary.getText();

        // 수강평점 크롤링 및 추출
        Double score = 0.0;
        try {
            // 평점 요소 찾기 (수강생이 없을 경우 예외 발생)
            WebElement webScore = driver.findElement(By.xpath("//*[@id=\"__next\"]/div[1]/div[3]/div/section[1]/div/div/div[1]/div/div[2]/div[1]/div[2]/a"));
            String text = webScore.getText();  // '(5.0) 수강평 1,763개' 꼴로 추출됨

            // 수강평점 추출
            Pattern pattern = Pattern.compile("\\((\\d+\\.\\d+)\\)");
            Matcher matcher = pattern.matcher(text);

            if (matcher.find()) {
                score = Double.parseDouble(matcher.group(1)); // 소수점 변환
            }
        } catch (NoSuchElementException e) {
            System.out.println("평점이 없는 강좌입니다");
        }

        // 수강생 수 크롤링 및 추출
        Long students = 0L;
        try{
            // 수강생 수 찾기 (들은 수강생이 없을 경우 예외 발생)
            WebElement webStudents = driver.findElement(By.xpath("//*[@id=\"__next\"]/div[1]/div[3]/div/section[1]/div/div/div[1]/div/div[2]/div[1]/div[2]/p/strong"));
            String text = webStudents.getText(); // '352명' 꼴로 추출됨

            students = Long.parseLong(text.replaceAll("[^0-9]", ""));
        }catch(NoSuchElementException e){
            System.out.println("수강생 수가 없는 강좌입니다.");
        }

        LectureDto lectureDto = LectureDto.builder()
                .title(title)
                .summary(summary)
                .platform(Platform.INFLEARN)
                .score(score)
                .thumbnail(thumbnail)
                .link(link)
                .students(students)
                .category(Category.WEB)
                .build();

        lectureRepository.save(lectureDto.toEntity());

        driver.quit();

    }
}


