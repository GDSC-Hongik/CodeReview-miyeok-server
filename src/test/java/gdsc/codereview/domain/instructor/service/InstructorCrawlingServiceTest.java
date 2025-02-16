package gdsc.codereview.domain.instructor.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class InstructorCrawlingServiceTest {

    @Autowired
    InstructorCrawlingService instructorCrawlingService;

    @Test
    public void 강사크롤링() throws Exception{
        instructorCrawlingService.crawlingAndSaveInflearnInstructor();
    }

}