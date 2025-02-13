package gdsc.codereview.domain.lecture.service;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@SpringBootTest
class InflearnCrawlingServiceTest {

    @Autowired
    InflearnCrawlingService inflearnCrawlingService;

//    @Test
//    public void 한페이지_강좌_리스트_크롤링() throws Exception{
//        inflearnCrawlingService.lectureListCrawling(driver);
//    }

    @Test
    public void 모든_강좌_세부_크롤링() throws Exception{
        inflearnCrawlingService.allPageLectureCrolling();
    }
}