package gdsc.codereview.domain.user.controller;

import gdsc.codereview.domain.user.converter.UserConverter;
import gdsc.codereview.domain.user.dto.response.UserResponse;
import gdsc.codereview.domain.user.entity.User;
import gdsc.codereview.domain.user.repository.UserRepository;
import gdsc.codereview.global.exception.GeneralException;
import gdsc.codereview.global.exception.status.ErrorStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;

    // 구글 로그인으로 유저 정보 가져오기
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new GeneralException(ErrorStatus.USER_NOT_FOUND));
    }

    @Transactional
    public void withdraw(User user) {
        userRepository.delete(user);
    }


    // 구글 로그인 후 정보 업데이트
    @Transactional
    public UserResponse createUserInfo(String email, String introduction) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new GeneralException(ErrorStatus.USER_NOT_FOUND));

        user.updateUserInfo(introduction);

        return UserConverter.toUserResDto(user);
    }
}