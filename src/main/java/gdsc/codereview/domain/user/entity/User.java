package gdsc.codereview.domain.user.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Entity
@Getter
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(nullable = false, name = "name")
    private String username;

    private String introduction;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OAuthType provider;

    @Column(nullable = false, name = "social_id")
    private String socialId;

    @Builder
    public User(String username, String introduction, OAuthType provider, String socialId) {
        this.username = username;
        this.introduction = introduction;
        this.provider = provider;
        this.socialId = socialId;
    }
}
