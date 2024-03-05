package kth.iv1201.group9.recruitment_application.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import kth.iv1201.group9.recruitment_application.application.LoginService;

@Configuration
@EnableWebSecurity
@EnableAsync
public class WebSecurityConfig {

    @Autowired
    private LoginService loginService;

    @Bean
    SecurityFilterChain customLoginSecurityFilterChain(HttpSecurity http) throws Exception {
        return http
                .formLogin(form -> form.loginPage("/login").successHandler(new AuthenticationSuccessHandlerConfig())
                        .permitAll())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login/**").permitAll() // Everyone is allowed
                        .requestMatchers("/registration/**").permitAll() // Everyone is allowed
                        .requestMatchers("/passwordRecovery/**").permitAll() // Everyone is allowed
                        .requestMatchers("/changePassword/**").permitAll() // Everyone is allowed
                        .requestMatchers("/css/**").permitAll() // Everyone is allowed
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
