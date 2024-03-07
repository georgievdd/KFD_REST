package com.example.kfd_hw.config

import com.example.kfd_hw.database.entity.Role
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.DefaultSecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
class SecurityConfiguration(
    private val authenticationProvider: AuthenticationProvider
) {

    @Bean
    fun securityFilterChain(
        http: HttpSecurity,
        jwtAuthenticationFilter: JwtAuthenticationFilter
    ): DefaultSecurityFilterChain =
        http
            .csrf { it.disable() }
            .authorizeHttpRequests {authorize ->
                authorize
                    .requestMatchers("/api/auth/**").permitAll()
                    .requestMatchers("/api/user/**").permitAll()
                    .requestMatchers(HttpMethod.POST, "/api/questionnaire/one_answer").hasRole(Role.ADMIN.name)
                    .requestMatchers(HttpMethod.POST, "/api/questionnaire/few_answers").hasRole(Role.ADMIN.name)
                    .requestMatchers(HttpMethod.POST, "/api/questionnaire/free_answer").hasRole(Role.ADMIN.name)
                    .anyRequest().fullyAuthenticated()
            }
            .sessionManagement {
                it.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            }
            .authenticationProvider(authenticationProvider)
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter::class.java)
            .build()
}