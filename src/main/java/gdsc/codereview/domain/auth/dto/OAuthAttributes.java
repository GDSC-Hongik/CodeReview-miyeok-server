package gdsc.codereview.domain.auth.dto;

import gdsc.codereview.domain.user.entity.OAuthType;
import gdsc.codereview.domain.user.entity.Role;
import gdsc.codereview.domain.user.entity.User;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
public class OAuthAttributes {
    private Map<String, Object> attributes;
    private String nameAttributeKey;
    private String name;
    private String email;
    private OAuthType provider;
    private String socialId;

    @Builder
    public OAuthAttributes(Map<String, Object> attributes, String nameAttributeKey, String name, String email, OAuthType provider, String socialId) {
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.name = name;
        this.email = email;
        this.provider = provider;
        this.socialId = socialId;
    }

    public static OAuthAttributes of(String  registrationId, String userNameAttributeName, Map<String, Object> attributes){

        return ofGoogle(userNameAttributeName, attributes, registrationId);
    }

    private static OAuthAttributes ofGoogle(String userNameAttributeName, Map<String, Object> attributes, String registrationId){
        // registrationId에 따라 provider 값 설정
        OAuthType provider = OAuthType.valueOf(registrationId.toUpperCase());  // "google" -> OAuthType.GOOGLE, "kakao" -> OAuthType.KAKAO


        return OAuthAttributes.builder()
                .name((String) attributes.get("name"))
                .email((String) attributes.get("email"))
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .provider(provider)
                .socialId((String) attributes.get("sub"))
                .build();
    }

    // OAuthAttributes -> User 엔티티로 변환
    public User toEntity(){
        return User.builder()
                .name(name)
                .email(email)
                .role(Role.USER)
                .provider(provider)
                .socialId(socialId)
                .build();
    }
}
