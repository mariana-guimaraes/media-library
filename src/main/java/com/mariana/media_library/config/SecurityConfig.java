package com.mariana.media_library.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		http.csrf(csrf -> csrf.disable())

				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

				.authorizeHttpRequests(auth -> auth
						// get
						.requestMatchers(HttpMethod.GET, "/api/titles/**").permitAll()
						// H2
						.requestMatchers("/h2-console/**", "/actuator/**").permitAll()
						// admin
						.requestMatchers("/api/admin/**").hasAuthority("SCOPE_movies.admin")
						// others
						.anyRequest().authenticated())

				.oauth2ResourceServer(oauth2 -> oauth2.jwt(jwt -> {
				}))

				.headers(headers -> headers.frameOptions(frame -> frame.sameOrigin()));

		return http.build();
	}
}
