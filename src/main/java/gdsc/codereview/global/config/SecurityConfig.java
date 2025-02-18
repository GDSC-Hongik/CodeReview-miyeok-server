package gdsc.codereview.global.config;

import gdsc.codereview.domain.auth.jwt.JwtAuthenticationFilter;
import gdsc.codereview.domain.auth.jwt.JwtTokenProvider;
import gdsc.codereview.domain.auth.service.CustomOAuth2UserService;
import gdsc.codereview.domain.user.entity.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.CorsFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomOAuth2UserService customOAuth2UserService;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final CorsFilter corsFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .headers(headers -> headers.frameOptions(frame -> frame.disable()))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**", "/index").permitAll()
                        .requestMatchers("/oauth2/**").permitAll() // OAuth2 인증 경로 허용
                        .requestMatchers("/api/**").hasRole(Role.USER.name()) // /api/**는 USER 권한만
                        .requestMatchers("/api/auth/**").permitAll() // 인증 관련 API는 모두 허용
                        .anyRequest().authenticated() // 나머지 모든 요청은 인증 필요
                )
                .addFilterBefore(corsFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .logout(logout -> logout.logoutSuccessUrl("/"))
                .oauth2Login(oauth2 -> oauth2
                        .authorizationEndpoint(endpointConfig ->
                                endpointConfig.baseUri("/oauth2/authorization/google") // 구글 OAuth2 인증 경로
                        )
                        .redirectionEndpoint(endpointConfig ->
                                endpointConfig.baseUri("/login/oauth2/code/google")) // 구글 로그인 후 리디렉션 경로
                        .userInfoEndpoint(userInfo ->
                                userInfo.userService(customOAuth2UserService)) // 로그인 성공 후 사용자 정보를 처리
                );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}