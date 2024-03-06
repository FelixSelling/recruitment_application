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

    /**
     * A bean that represents the security filter chain configuration for the
     * application.
     * The security filter chain is responsible for handling and processing incoming
     * HTTP requests
     * and applying the necessary security measures based on the defined rules.
     *
     * This bean is used in the {@link WebSecurityConfig} class to configure the
     * security settings
     * for the application, such as login page, access permissions, and
     * authentication handlers.
     *
     * @param http the {@link HttpSecurity} object used to configure the security
     *             filter chain.
     * @return the configured {@link SecurityFilterChain} object.
     * @throws Exception if an error occurs during the configuration process.
     */
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

    /**
     * Returns a new instance of the PasswordEncoder interface.
     * The returned instance is an implementation of the BCryptPasswordEncoder
     * class,
     * which is used to encode passwords using the BCrypt hashing algorithm.
     * The strength of the hashing algorithm is specified by the parameter passed to
     * the constructor.
     * 
     * @return a new instance of the PasswordEncoder interface.
     */
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12); // Specification for which
    }
}
