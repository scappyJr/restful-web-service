package com.example.restfulwebservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public InMemoryUserDetailsManager userDetailsManager() {
        UserDetails user = User.withUsername("username")
                .password("{noop}passw0rd") // {noop}는 "NoOpPasswordEncoder"를 사용하여 암호를 인코딩하지 않는다는 의미로 사용하는 접두사
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(user);
    }

}
