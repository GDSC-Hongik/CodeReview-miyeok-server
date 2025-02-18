package gdsc.codereview.domain.like.entity;

import gdsc.codereview.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
@Getter
@Builder
@Table(name = "like")
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "like_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    // 리뷰 ID foreign key

    @Enumerated(EnumType.STRING)
    @Column(name = "liked_or_hated")
    private LikeType likeType;
}
