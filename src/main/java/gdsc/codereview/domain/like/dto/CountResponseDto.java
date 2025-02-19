package gdsc.codereview.domain.like.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CountResponseDto {

    private final int likedCount;
    private final int hatedCount;

}
