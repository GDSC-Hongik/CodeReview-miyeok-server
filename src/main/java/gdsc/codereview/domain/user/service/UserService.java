package gdsc.codereview.domain.user.service;

import gdsc.codereview.domain.user.converter.UserConverter;
import gdsc.codereview.domain.user.dto.response.UserResponse;
import gdsc.codereview.domain.user.entity.User;
import gdsc.codereview.domain.user.repository.UserRepository;
import gdsc.codereview.global.exception.GeneralException;
import gdsc.codereview.global.exception.status.ErrorStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;

    // 유저 정보 가져오기
    public User getUserByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        return user.orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));
    }

    public User updateUserIntroduction(User user, String introduction) {
        return user.updateUserInfo(introduction);
    }

    @Transactional
    public void withdraw(User user) {
        userRepository.delete(user);
    }

}