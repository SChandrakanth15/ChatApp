package com.theelixrlabs.ChatApp.service;

import com.theelixrlabs.ChatApp.model.LoginUser;
import com.theelixrlabs.ChatApp.repository.LoginUserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final LoginUserRepository loginUserRepository;

    public CustomUserDetailsService(LoginUserRepository loginUserRepository) {
        this.loginUserRepository = loginUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<LoginUser> appUserOpt = loginUserRepository.findByUsername(username);
        LoginUser appUser = appUserOpt.orElseThrow(() -> new UsernameNotFoundException("User not found"));

        if (!appUser.isActive()) {
            throw new UsernameNotFoundException("User is not active");
        }

        return User.builder()
                .username(appUser.getUsername())
                .password(appUser.getPassword())
                .build();
    }
}
