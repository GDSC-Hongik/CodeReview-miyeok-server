package gdsc.codereview.domain.user.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Entity
@Getter
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int id;

    @Column(nullable = false)
    private String name;

    private String introduction;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OAuthType provider;

    @Column(nullable = false, name = "social_id")
    private String socialId;

    @Builder
    public User(String name, String introduction, OAuthType provider, String socialId) {
        this.name = name;
        this.introduction = introduction;
        this.provider = provider;
        this.socialId = socialId;
    }
}
