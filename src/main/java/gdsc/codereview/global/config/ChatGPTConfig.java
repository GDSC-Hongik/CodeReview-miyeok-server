package gdsc.codereview.global.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;


@Configuration
public class ChatGPTConfig {

    @Value("${openai.secret-key}")
    private String secretKey;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public HttpHeaders httpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(secretKey);  // Authorization 헤더에 API 키 설정
        headers.setContentType(org.springframework.http.MediaType.APPLICATION_JSON);
        return headers;
    }

    public HttpEntity<String> createHttpEntity(String requestBody) {
        HttpHeaders headers = httpHeaders();
        return new HttpEntity<>(requestBody, headers);  // HttpEntity에 requestBody와 headers 설정
    }
}