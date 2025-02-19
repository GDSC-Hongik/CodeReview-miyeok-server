package gdsc.codereview.domain.favorite.repository;

import gdsc.codereview.domain.favorite.entity.Favorite;
import gdsc.codereview.domain.lecture.entity.Lecture;
import gdsc.codereview.domain.lecture.repository.LectureRepository;
import gdsc.codereview.domain.user.entity.User;
import gdsc.codereview.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class FavoriteService {

    private final FavoriteRepository favoriteRepository;
    private final UserRepository userRepository;
    private final LectureRepository lectureRepository;

    public void addFavorite(Long userId, Long lectureId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("등록되지 않은 사용자입니다."));

        Lecture lecture = lectureRepository.findById(lectureId)
                .orElseThrow(() -> new IllegalArgumentException("등록되지 않은 강좌입니다."));


        // 중복 방지
        if (favoriteRepository.findByUserAndLecture(user, lecture).isPresent()) {
            throw new IllegalArgumentException("이미 즐겨찾기에 추가된 강좌입니다.");
        }

        Favorite favorite = Favorite.builder()
                .user(user)
                .lecture(lecture)
                .build();

        favoriteRepository.save(favorite);
    }

    // 특정 유저가 즐겨찾기한 강좌 리스트 반환
    @Transactional(readOnly = true)
    public List<Lecture> getUserFavorites(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저를 찾을 수 없습니다."));

        return favoriteRepository.findAllByUser(user)
                .stream()
                .map(Favorite::getLecture)
                .collect(Collectors.toList());
    }
}
