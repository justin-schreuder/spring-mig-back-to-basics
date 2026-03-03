package org.example.migbacktobasics.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth ->
                auth.requestMatchers("/api/public/**").permitAll()
                    .anyRequest().authenticated()
            )
            .httpBasic(Customizer.withDefaults())
            .build();
    }

    @Bean
    public UserDetailsService users() {
        var viewer = User.withUsername("viewer")
            .password("{noop}viewer123")
            .roles("VIEW")
            .build();
        var editor = User.withUsername("editor")
            .password("{noop}editor123")
            .roles("VIEW",  "EDIT")
            .build();
        var admin = User.withUsername("admin")
            .password("{noop}admin123")
            .roles("VIEW", "EDIT", "ADMIN")
            .build();
        return new InMemoryUserDetailsManager(viewer, editor, admin);
    }

}
