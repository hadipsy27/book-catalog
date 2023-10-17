package com.labs.catalog.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.labs.catalog.security.filter.JWTAuthProcessingFilter;
import com.labs.catalog.security.filter.UsernamePasswordAuthProcessingFilter;
import com.labs.catalog.security.handler.UsernamePasswordAuthFailureHandler;
import com.labs.catalog.security.handler.UsernamePasswordAuthSuccessHandler;
import com.labs.catalog.security.provider.JWTAuthenticationProvider;
import com.labs.catalog.security.provider.UsernamePasswordAuthProvider;
import com.labs.catalog.security.util.JwtTokenFactory;
import com.labs.catalog.security.util.SkipPathRequestMatcher;
import com.labs.catalog.util.TokenExtractor;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    private final static String AUTH_URL = "/v1/login";
    private final static String V1_URL = "/v1/*";
    private final static String V2_URL = "/v2/*";
    private final static List<String> PERMIT_ENDPOINT_LIST = Arrays.asList(AUTH_URL);
    private final static List<String> AUTHENTICATED_ENDPOINT_LIST = Arrays.asList(V1_URL, V2_URL);

    @Autowired
    private UsernamePasswordAuthProvider usernamePasswordAuthProvider;

    @Autowired
    private JWTAuthenticationProvider jwtAuthenticationProvider;

    @Bean
    public AuthenticationSuccessHandler successHandler(ObjectMapper objectMapper, JwtTokenFactory jwtTokenFactory){
        return new UsernamePasswordAuthSuccessHandler(objectMapper, jwtTokenFactory);
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

    @Bean
    public JWTAuthProcessingFilter jwtAuthProcessingFilter(TokenExtractor tokenExtractor,
                                                           AuthenticationFailureHandler failureHandler,
                                                           AuthenticationManager authenticationManager){
        SkipPathRequestMatcher matcher = new SkipPathRequestMatcher(PERMIT_ENDPOINT_LIST, AUTHENTICATED_ENDPOINT_LIST);
        JWTAuthProcessingFilter filter = new JWTAuthProcessingFilter(matcher, tokenExtractor, failureHandler);
        filter.setAuthenticationManager(authenticationManager);
        return filter;
    }

    @Autowired
    protected void registerProvider(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(usernamePasswordAuthProvider)
                .authenticationProvider(jwtAuthenticationProvider);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http,
                                                   UsernamePasswordAuthProcessingFilter usernamePasswordAuthProcessingFilter,
                                                   JWTAuthProcessingFilter jwtAuthProcessingFilter) throws Exception {
        http.authorizeHttpRequests()
                .antMatchers(V1_URL, V2_URL).authenticated()
                .and()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .httpBasic();

        http.addFilterBefore(usernamePasswordAuthProcessingFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(jwtAuthProcessingFilter, UsernamePasswordAuthProcessingFilter.class);
        return http.build();
    }
}
