package com.example.restfulwebservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /**
     * Spring Boot Security 적용 후 별도의 인증 없이 h2-console 접속 허용
     * @param http
     * @return
     * @throws Exception
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/h2-console").permitAll();
        http.csrf().disable();
        http.headers().frameOptions().disable();

        return http.build();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsManager() {
        UserDetails user = User.withUsername("username")
                .password("{noop}passw0rd") // {noop}는 "NoOpPasswordEncoder"를 사용하여 암호를 인코딩하지 않는다는 의미로 사용하는 접두사
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(user);
    }

}
