package gdsc.codereview.domain.gpt.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
@Service
public class ChatGPTService {

    @Value("${openai.url.prompt}")
    private String API_URL;

    @Value("${openai.secret-key}")
    private String API_KEY;


    private final RestTemplate restTemplate;

    public String compareLectureSummaries(String summary1, String summary2) {

        // prompt 구성
        String prompt = String.format(
                "Please compare the following two lecture summaries and describe the similarities and differences:\n\nSummary 1: %s\n\nSummary 2: %s",
                summary1, summary2
        );

        // OpenAI API 호출
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + API_KEY);
        headers.set("Content-Type", "application/json");

        String requestBody = String.format("{\"model\": \"text-davinci-003\", \"prompt\": \"%s\", \"max_tokens\": 1000}", prompt);

        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<String> response = restTemplate.exchange(API_URL, HttpMethod.POST, entity, String.class);

        // 응답에서 비교 결과 추출
        return response.getBody();
    }
}