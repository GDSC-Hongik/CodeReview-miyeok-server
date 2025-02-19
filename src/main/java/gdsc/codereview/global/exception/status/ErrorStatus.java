package gdsc.codereview.global.exception.status;

import gdsc.codereview.global.exception.BaseCode;
import gdsc.codereview.global.exception.ReasonDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorStatus implements BaseCode {

    // 로그인 관련
    LOGIN_UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "LOGIN_FAIL", "아이디 또는 비밀번호를 확인하세요"),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "USER_NOT_FOUND", "사용자를 찾을 수 없습니다."),
    FORBIDDEN(HttpStatus.FORBIDDEN, "FORBIDDEN", "금지된 접근입니다."),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "UNAUTHORIZED", "권한이 없습니다."),
    PASSWORD_NOT_MATCH(HttpStatus.NOT_FOUND, "PASSWORD_NOT_MATCH", "비밀번호가 틀렸습니다."),
    PASSWORD_INVALID(HttpStatus.NOT_FOUND, "PASSWORD_INVALID", "유효하지 않은 password입니다."),
    TOKEN_INVALID(HttpStatus.UNAUTHORIZED, "TOKEN_INVALID", "토큰이 유효하지 않습니다."),
    TOKEN_NOT_FOUND(HttpStatus.NOT_FOUND, "TOKEN_INVALID", "토큰을 찾을 수 없습니다."),
    ALREADY_EXIST_USER(HttpStatus.BAD_REQUEST, "ALREADY_EXIST_USER", "이미 존재하는 회원입니다."),

    // 세션
    SESSION_UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "UNAUTHORIZED", "유효하지 않은 세션입니다."),

    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "INTERNAL_SERVER_ERROR", "서버 에러");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public ReasonDto getReason() {
        return ReasonDto.builder()
                .isSuccess(false)
                .httpStatus(httpStatus)
                .code(code)
                .message(message)
                .build();
    }

}