package kth.iv1201.group9.recruitment_application.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Set;

/**
 * This class is responsible for handling successful authentication events.
 * It implements the Spring Security AuthenticationSuccessHandler interface.
 * The class redirects the user to different pages based on their role after successful authentication.
 */
public class AuthenticationSuccessHandlerConfig implements AuthenticationSuccessHandler {

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    /**
     * This method is called when the user is successfully authenticated.
     * It determines the user's role and redirects them to the appropriate page.
     *
     * @param request        the HTTP servlet request
     * @param response       the HTTP servlet response
     * @param authentication the authentication object containing the user's details
     * @throws IOException if an I/O error occurs during the redirect
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException {
        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());

        if (roles.contains("recruiter")) {
            redirectStrategy.sendRedirect(request, response, "/recruiter");
        } else if (roles.contains("applicant")) {
            redirectStrategy.sendRedirect(request, response, "/applicant");
        } else {
            throw new IllegalStateException("error.login.role.notfound");
        }
    }
}