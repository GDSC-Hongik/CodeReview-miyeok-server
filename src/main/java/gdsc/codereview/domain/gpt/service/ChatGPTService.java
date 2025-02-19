package gdsc.codereview.domain.gpt.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import gdsc.codereview.domain.lecture.service.LectureService;
import gdsc.codereview.global.config.ChatGPTConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;


@Slf4j
@RequiredArgsConstructor
@Service
public class ChatGPTService {

    @Value("${openai.url.prompt}")
    private String API_URL;

    @Value("${openai.secret-key}")
    private String API_KEY;

    private final ChatGPTConfig chatGPTConfig;
    private final RestTemplate restTemplate;
    private final LectureService lectureService;  // LectureService 추가

    public String compareLectureSummaries(String summary1, String summary2) {
        // prompt 구성
        String prompt = String.format(
                "Please compare the following two lecture summaries and describe the similarities and differences in detail. Analyze based on the main topics, key content, and the approaches covered in each lecture. Finally, give me answer in Korean:\n\nSummary 1: %s\n\nSummary 2: %s",
                summary1, summary2
        );

        // JSON 요청 본문 생성
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("model", "gpt-4");
        requestBody.put("messages", new Object[] {
                new HashMap<String, String>() {{
                    put("role", "system");
                    put("content", "You are a helpful assistant.");
                }},
                new HashMap<String, String>() {{
                    put("role", "user");
                    put("content", prompt);
                }}
        });
        requestBody.put("max_tokens", 1000);

        // ObjectMapper를 사용하여 요청 본문을 JSON으로 변환
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonRequestBody = "";
        try {
            jsonRequestBody = objectMapper.writeValueAsString(requestBody);
        } catch (Exception e) {
            log.error("Error while converting request body to JSON: {}", e.getMessage());
        }

        // HttpEntity 생성하여 HttpHeaders 설정
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + API_KEY);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(jsonRequestBody, headers);

        // OpenAI API 호출
        ResponseEntity<String> responseEntity = restTemplate.exchange(
                API_URL, HttpMethod.POST, entity, String.class
        );

        // 응답 처리
        String responseBody = responseEntity.getBody();
        //log.info("Response: {}", responseBody);

        return responseBody;
    }
}