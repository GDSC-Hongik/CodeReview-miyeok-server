package gdsc.codereview.domain.user.service;

import gdsc.codereview.domain.user.entity.User;
import gdsc.codereview.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;

    // 유저 정보 가져오기
    public User getUserByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        return user.orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));
    }

    public User updateUserIntroduction(User user, String introduction) {
        user = user.updateUserInfo(introduction);
        return userRepository.save(user);

    }

    @Transactional
    public void withdraw(User user) {
        userRepository.delete(user);
    }

}