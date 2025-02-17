package gdsc.codereview.domain.user.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(nullable = false, name = "name")
    private String name;

    @Column(nullable = false, columnDefinition = "varchar(255)")
    private String email;

    private String introduction;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OAuthType provider;

    @Column(nullable = false, name = "social_id")
    private String socialId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;


    @Builder
    public User(String name, String email, String introduction, OAuthType provider, String socialId, Role role) {
        this.name = name;
        this.email = email;
        this.introduction = introduction;
        this.provider = provider;
        this.socialId = socialId;
        this.role = role;
    }

    public User update(String name, OAuthType provider, String socialId) {
        this.name = name;
        this.provider = provider;
        this.socialId = socialId;
        return this;
    }

    public User updateIntroduction(String introduction) {
        this.introduction = introduction;
        return this;
    }

    public String getRoleKey() {
        return this.role.getKey();
    }
}
