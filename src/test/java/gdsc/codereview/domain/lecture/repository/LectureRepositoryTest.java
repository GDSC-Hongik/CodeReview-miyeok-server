package gdsc.codereview.domain.lecture.repository;

import gdsc.codereview.domain.lecture.entity.Lecture;
import gdsc.codereview.domain.lecture.service.InflearnCrawlingService;
import gdsc.codereview.domain.lecture.service.LectureService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LectureRepositoryTest {

    @Autowired
    LectureRepository lectureRepository;

    @Autowired
    InflearnCrawlingService inflearnCrawlingService;

//    @Test
//    public void 세부강좌_크롤링_저장(){
//        inflearnCrawlingService.detailLectureCrawling("https://www.inflearn.com/course/ORM-JPA-Basic");
//
//        List<Lecture> list =  lectureRepository.findAll();
//        for(Lecture i:list){
//            System.out.println(i.getTitle());
//        }
//
//    }
}