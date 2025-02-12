package gdsc.codereview.domain.lecture.service;

import gdsc.codereview.domain.lecture.entity.Lecture;
import gdsc.codereview.domain.lecture.repository.LectureRepository;
import lombok.RequiredArgsConstructor;
import org.openqa.selenium.By;
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
import java.util.NoSuchElementException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class InflearnCrawlingService {

    private final LectureRepository lectureRepository;

    public void onePageLectureCrawling(WebDriver driver) throws Exception{ //한 페이지 크롤링

        try{
            List<WebElement> ulElements = driver.findElements(By.xpath("//ul[contains(@class, 'mantine-1avyp1d')]"));

            for (WebElement ul : ulElements) {
                List<WebElement> liElements = ul.findElements(By.tagName("li"));

                for (WebElement li : liElements) {
                    WebElement aTag = li.findElement(By.tagName("a"));

                    String link = aTag.getAttribute("href");

                    System.out.println("link#############"+link);

                    detailLectureCrawling(link,driver);
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void allPageLectureCrolling() throws Exception{

        System.setProperty("webdriver.chrome.driver","driver/chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("window-size=1920,1000");
        options.addArguments("--headless=new");
        options.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.20 Safari/537.36");

        WebDriver driver = new ChromeDriver(options);

//        List<String> allLecturesLink = new ArrayList<>();

        for(int i=1;i<=1;i++){ // 임시 하드코딩

            //인프런 웹 개발 카테고리 url - 최신순
            String url = "https://www.inflearn.com/courses/it-programming/web-dev?sort=RECENT";

            //페이지네이션
            if(i!=1) url+="&page_number="+i;

            driver.get(url);

            onePageLectureCrawling(driver);
//            allLecturesLink.addAll(onePageLectureCrawling(driver));
        }

        // 웹 드라이버 종료
        driver.quit();

    }

    public void detailLectureCrawling(String link,WebDriver driver) {

        driver.get(link);

        // 각 요소 크롤링
        WebElement webThumbnail = driver.findElement(By.xpath("//*[@id=\"__next\"]/div[1]/div[3]/div/section[1]/div/div/div[2]/div/div[2]/div[1]/figure/div/img"));
        WebElement webTitle = driver.findElement(By.xpath("//*[@id=\"__next\"]/div[1]/div[3]/div/section[1]/div/div/div[1]/div/div[1]/h1"));
//        try{
//            //평점등록한 학생이 없는 강좌의 경우 평점이 없음
//            WebElement webScore = driver.findElement(By.xpath("//*[@id=\"__next\"]/div[1]/div[3]/div/section[1]/div/div/div[1]/div/div[2]/div[1]/div[2]/a"));
//        }catch(NoSuchElementException e){
//
//        }

        //href 값 가져와서 리스트에 저장해서 강사 크롤링하기
        WebElement webInstructor = driver.findElement(By.xpath("//*[@id=\"__next\"]/div[1]/div[3]/div/section[1]/div/div/div[1]/div/div[2]/div[2]/ul/li/a"));
        WebElement webSummary = driver.findElement(By.xpath("//*[@id=\"__next\"]/div[1]/div[3]/div/section[1]/div/div/div[1]/div/div[1]/p"));
        //List<WebElement> webContent = driver.findElements(By.xpath("//*[@id=\"__next\"]/div[1]/div[3]/div/section[3]/div/div/section/div/div[2]/div/ul/li"));
       // WebElement webStudents = driver.findElement(By.xpath("//*[@id=\"__next\"]/div[1]/div[3]/div/section[1]/div/div/div[1]/div/div[2]/div[1]/div[2]/p/strong"));


        // 크롤링된 요소 맵핑
        String thumbnail = webThumbnail.getAttribute("src"); //link를 저장
        String title = webTitle.getText();

        // 수강평점 추출
//        Double score = 0.0;
//        Pattern pattern = Pattern.compile("\\((\\d+\\.\\d+)\\)"); // 괄호 안의 소수점 숫자
//        Matcher matcher = pattern.matcher(webScore.getText());
//        if (matcher.find()) {
//            String numberStr = matcher.group(1);  // 첫 번째 그룹: 소수점 숫자
//            score = Double.parseDouble(numberStr);  // float로 변환
//            System.out.println("추출된 숫자 (float): " + score);
//        }

        String instructor = webInstructor.getText();
        String instructorLink = webInstructor.getAttribute("href");
        String summary = webSummary.getText();
        //Long students = Long.parseLong(webStuendts.getText());

        System.out.println("thumbnail:"+thumbnail);
        System.out.println("title:"+title);
       // System.out.println("score"+ score);
        System.out.println("instructor"+instructor);
        System.out.println("link:"+instructorLink);
        System.out.println("summary:"+summary);
       // System.out.println("students:"+students);


    }
}


