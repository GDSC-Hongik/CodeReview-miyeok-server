package gdsc.codereview.domain.auth.service;

import gdsc.codereview.domain.user.entity.User;
import gdsc.codereview.domain.user.repository.UserRepository;
import gdsc.codereview.global.exception.GeneralException;
import gdsc.codereview.global.exception.status.ErrorStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AuthDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new GeneralException(ErrorStatus.USER_NOT_FOUND));

        return new AuthDetails(user);
    }
}