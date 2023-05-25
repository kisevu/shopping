package com.amedakevin.app.webParent.admin.security.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class ShoppingSecurityConfig {
    private final AuthenticationProvider authenticationProvider;
    private final ShoppingJWTAuthenticationFilter customerJWTAuthenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws  Exception{
         http
                 .csrf()
                 .disable()
                 .authorizeHttpRequests()
                 .requestMatchers("/resources/**","/users/**","/webjars/**","/**")
                 .permitAll()
                 .anyRequest()
                 .authenticated()
                 .and()
                 .sessionManagement()
                 .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                 .and()
                 .authenticationProvider(authenticationProvider)
                 .addFilterBefore(customerJWTAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
