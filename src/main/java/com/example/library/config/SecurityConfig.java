package com.example.library.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
	
	private final JwtAuthenticationFilter jwtAuthFilter;
	private final AuthenticationProvider authenticationProvider;
	private final LogoutHandler logoutHandler;
	
	public SecurityConfig (JwtAuthenticationFilter jwtAuthFilter, AuthenticationProvider authenticationProvider, LogoutHandler logoutHandler) {
		this.jwtAuthFilter = jwtAuthFilter;
		this.authenticationProvider = authenticationProvider;
		this.logoutHandler = logoutHandler;
	}
	
//	@Bean
//	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//		http.csrf().disable()
//		.cors()
//		.and()
//		.authorizeHttpRequests()
//		//.requestMatchers("/auth/**")
//		.requestMatchers("/**")
//		.permitAll()
//		.anyRequest()
//		.authenticated()
//		.and()
//		.sessionManagement()
//		.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//		.and()
//		.authenticationProvider(authenticationProvider)
//		.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
//		.logout()
//		.logoutUrl("/auth/logout")
//		//.logout((logout) -> logout.logoutUrl("/auth/logout"))
//		.addLogoutHandler(logoutHandler)
//		.logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext())
//		;
//		return http.build();
//	}

	@Bean public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf(AbstractHttpConfigurer::disable)  // Disables CSRF protection
				.cors(cors -> {})  // Enables CORS
				.authorizeHttpRequests(authz -> authz
						.requestMatchers("/**").permitAll()  // Allows all requests
						.anyRequest().authenticated()  // Requires authentication for any other requests
				)
				.sessionManagement(session -> session
						.sessionCreationPolicy(SessionCreationPolicy.STATELESS)  // Stateless session policy
				)
				.authenticationProvider(authenticationProvider)  // Sets the authentication provider
				.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)  // Adds JWT filter
				.logout(logout -> logout
						.logoutUrl("/auth/logout")  // Configures the logout URL
						.addLogoutHandler(logoutHandler)  // Adds a custom logout handler
						.logoutSuccessHandler((request, response, authentication) ->
								SecurityContextHolder.clearContext())  // Clears the security context after logout
				);

		return http.build();
	}

}
