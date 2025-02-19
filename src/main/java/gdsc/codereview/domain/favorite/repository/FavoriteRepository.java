package gdsc.codereview.domain.favorite.repository;

import gdsc.codereview.domain.favorite.entity.Favorite;
import gdsc.codereview.domain.lecture.entity.Lecture;
import gdsc.codereview.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {

    // 특정 유저가 특정 강좌를 즐겨찾기 했는지 여부
    Optional<Favorite> findByUserAndLecture(User user, Lecture lecture);

    // 특정 유저가 즐겨찾기한 모든 강좌
    List<Favorite> findAllByUser(User user);

    // 즐겨찾기 삭제용
    Optional<Favorite> findByUserIdAndLectureId(Long userId, Long lectureId);
}