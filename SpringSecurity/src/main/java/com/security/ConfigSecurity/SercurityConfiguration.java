package com.security.ConfigSecurity;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SercurityConfiguration {

	@Bean
	public UserDetailsService userDetailsService() {
		
		UserDetails normalUser = User.withUsername("sandeep")
				.password(passwordEncoder().encode("user@123"))
				.roles("NORMAL")
				.build();
		
		UserDetails adminUser = User.withUsername("Deepu")
				.password(passwordEncoder().encode("admin@123"))
				.roles("ADMIN")
				.build();
		
		return new InMemoryUserDetailsManager(normalUser,adminUser);
	}
	
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	@SuppressWarnings("removal")
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.csrf().disable()
		.authorizeHttpRequests()
		
//		We will use the preAutrize annotaion for the Method based security
//		.requestMatchers("/welcome/admin")
//		.hasRole("ADMIN")
//		.requestMatchers("/welcome/normal")
//		.hasRole("NORMAL")
		.requestMatchers("/welcome/public")
		.permitAll()
		.anyRequest()
		.authenticated()
		.and()
		.formLogin();
		return httpSecurity.build();
	}
}
