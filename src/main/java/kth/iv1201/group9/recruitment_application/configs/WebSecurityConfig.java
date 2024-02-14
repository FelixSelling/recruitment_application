package kth.iv1201.group9.recruitment_application.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import kth.iv1201.group9.recruitment_application.application.LoginService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

	@Autowired
	private LoginService loginService;

	@Bean
	public SecurityFilterChain customLoginSecurityFilterChain(HttpSecurity http) throws Exception {
		return http.formLogin(form -> form.loginPage("/login").permitAll())
				.authorizeHttpRequests(auth -> auth
						.requestMatchers("/login/**").permitAll() // Everyone is allowed
						.requestMatchers("/registration/**").permitAll() // Everyone is allowed
						.requestMatchers("/recruiter/**").hasAnyAuthority("recruiter") // Only recruiters
						.requestMatchers("/applicant/**").hasAnyAuthority("applicant") // Only applicants
						.anyRequest().authenticated())
				.userDetailsService(loginService).build();
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(12); // Specification for which
	}
}

/*
 * TODO:
 * Authorization Token timeout?
 */
