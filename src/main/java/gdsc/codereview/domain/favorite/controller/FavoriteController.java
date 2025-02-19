package gdsc.codereview.domain.favorite.controller;

import gdsc.codereview.domain.favorite.repository.FavoriteService;
import gdsc.codereview.domain.lecture.dto.LectureDto;
import gdsc.codereview.domain.lecture.entity.Lecture;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Tag(name="즐겨찾기", description = "즐겨찾기 추가 및 즐겨찾기 한 강좌 리스트 조회")
@RestController
@RequestMapping("/api/favorite")
@RequiredArgsConstructor
public class FavoriteController {

    private final FavoriteService favoriteService;


    @Operation(summary = "즐겨찾기 추가", description = "강좌를 즐겨찾기에 추가")
    @PostMapping("/{userId}/{lectureId}")
    public ResponseEntity<String> addFavorite(@PathVariable Long userId, @PathVariable Long lectureId) {
        favoriteService.addFavorite(userId, lectureId);
        return ResponseEntity.ok("즐겨찾기에 추가되었습니다.");
    }

    @Operation(summary = "즐겨찾기 목록 조회", description = "특정 유저가 즐겨찾기 한 강좌 목록 조회")
    @GetMapping("/{userId}")
    public ResponseEntity<List<LectureDto>> getUserFavorites(@PathVariable Long userId) {
        List<Lecture> favoriteLectures = favoriteService.getUserFavorites(userId);

        List<LectureDto> response = favoriteLectures.stream()
                .map(LectureDto::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }
}
