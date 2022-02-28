package springboot.config.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import springboot.config.auth.dto.OAuthAttributes;
import springboot.config.auth.dto.SessionUser;
import springboot.domain.user.User;
import springboot.domain.user.UserRepository;

import javax.servlet.http.HttpSession;
import java.util.Collections;

@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    private final UserRepository userRepository;
    private final HttpSession httpSession;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        /*  registrationId
        *   현재 로그인 진행 중인 서비스를 구분하는 코드
        *   구글만 사용한다면 불필요한데, 이후 네이버 연동 시에 구글과에 구분을 위해 사용
        * */
        String registrationId = userRequest.getClientRegistration().getRegistrationId();

        /*  userNameAttributeName
        *   OAuth2 로그인 진행 시 키가 되는 필드값. Primary Key와 같은 의미
        *   구글은 기본적으로 코드를 지원하지만 네이버, 카카오는 기본 지원을 하지 않음
        *   이후 네이터 구글 로그인을 동시 시원할 때 사용됨.
        * */
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails()
                .getUserInfoEndpoint().getUserNameAttributeName();

        /*  OAuthAttributes
        *   OAuth2UserService를 통해 가져온 OAuth2User의 attribute를 담을 클래스
        * */
        OAuthAttributes attributes = OAuthAttributes.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());

        User user = saveOrUpdate(attributes);

        httpSession.setAttribute("user", new SessionUser(user));    //SessionUser : 세션에 사용자 정보를 저장하기위한 Dto

        return new DefaultOAuth2User(Collections.singleton(new SimpleGrantedAuthority( user.getRoleKey())),
                                    attributes.getAttributes(),
                                    attributes.getNameAttributeKey());
    }

    private User saveOrUpdate(OAuthAttributes attributes){
        User user = userRepository.findByEmail(attributes.getEmail())
                .map(entity -> entity.update(attributes.getName(), attributes.getPicture()))
                .orElse(attributes.toEntity());
        return userRepository.save(user);
    }
}
