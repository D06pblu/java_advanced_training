package com.epam.jatstartup.configuration;

import com.epam.jatstartup.service.impl.JpaUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class SecurityConfig {

    private final JpaUserDetailsService jpaUserDetailsService;

    public SecurityConfig(JpaUserDetailsService jpaUserDetailsService) {
        this.jpaUserDetailsService = jpaUserDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
//                .authorizeRequests(auth -> auth
//                        .anyRequest().authenticated()
//                )
//                .userDetailsService(jpaUserDetailsService)
//                .formLogin(Customizer.withDefaults())
//                .logout(Customizer.withDefaults())
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails admin = User.builder()
                .username("root")
                .password("RootPass")
                .roles("ADMIN", "USER")
                .build();
        return new InMemoryUserDetailsManager(admin);
    }

    //TODO всем новым юзерам назначать пермишн Spectator
    // соответственно сделать форму регистрации
}
