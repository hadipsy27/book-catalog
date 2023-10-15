package com.labs.catalog.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.labs.catalog.security.filter.UsernamePasswordAuthProcessingFilter;
import com.labs.catalog.security.handler.UsernamePasswordAuthFailureHandler;
import com.labs.catalog.security.handler.UsernamePasswordAuthSuccessHandler;
import com.labs.catalog.security.provider.UsernamePasswordAuthProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    private final static String AUTH_URL = "/v1/login";

    @Autowired
    private UsernamePasswordAuthProvider usernamePasswordAuthProvider;

    @Bean
    public AuthenticationSuccessHandler successHandler(ObjectMapper objectMapper){
        return new UsernamePasswordAuthSuccessHandler(objectMapper);
    }

    @Bean
    public AuthenticationFailureHandler failureHandler(ObjectMapper objectMapper){
        return new UsernamePasswordAuthFailureHandler(objectMapper);
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public UsernamePasswordAuthProcessingFilter UsernamePasswordAuthProcessingFilter(ObjectMapper objectMapper,
                                                                                     UsernamePasswordAuthSuccessHandler successHandler,
                                                                                     UsernamePasswordAuthFailureHandler failureHandler,
                                                                                     AuthenticationManager authenticationManager){

        UsernamePasswordAuthProcessingFilter filter = new UsernamePasswordAuthProcessingFilter(
                AUTH_URL, objectMapper, successHandler, failureHandler);
        filter.setAuthenticationManager(authenticationManager);
        return filter;
    }

    @Autowired
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(usernamePasswordAuthProvider);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, UsernamePasswordAuthProcessingFilter usernamePasswordAuthProcessingFilter) throws Exception {
        http.authorizeRequests().anyRequest().authenticated()
                .and()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .httpBasic();

        http.addFilterBefore(usernamePasswordAuthProcessingFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
