package com.theelixrlabs.ChatApp.config;

import com.theelixrlabs.ChatApp.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/*
 * SecurityConfig class provides the security configuration for the web application.
 * It configures authentication, authorization, password encoding, and security filters.
 */
@Configuration
public class SecurityConfig {
    private final CustomUserDetailsService userDetailsService;

    /**
     * Constructor that injects the custom UserDetailsService implementation
     *
     * @param userDetailsService Custom service to load user-specific data for authentication.
     */
    public SecurityConfig(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    /**
     * Bean definition for PasswordEncoder using BCrypt hashing algorithm.
     * BCrypt is used to encode and validate user passwords securely.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Bean definition for the SecurityFilterChain, configuring how requests are secured.
     * This method sets up authentication, login behavior, CSRF protection, and other security-related details.
     *
     * @param httpSecurity HttpSecurity object to configure web security.
     * @return a SecurityFilterChain that configures HTTP security for the application.
     * @throws Exception if there is a problem building the security chain.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login.html", "/api/auth/**").permitAll()
                        .anyRequest().authenticated())
                .formLogin(form -> form
                        .loginPage("/login.html")
                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/index.html", true)
                        .failureUrl("/login.html?error=true")
                        .permitAll())
                .csrf(csrf -> csrf.disable())
                .userDetailsService(userDetailsService)
                .build();
    }
}
