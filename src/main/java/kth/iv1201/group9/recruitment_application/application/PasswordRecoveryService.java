package kth.iv1201.group9.recruitment_application.application;

import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kth.iv1201.group9.recruitment_application.domain.DTO.PersonDTO;
import kth.iv1201.group9.recruitment_application.domain.entity.Person;
import kth.iv1201.group9.recruitment_application.exception.ValidationException;
import kth.iv1201.group9.recruitment_application.repository.PersonRepository;

/**
 * This class represents a service for handling password recovery functionality.
 * 
 */
@Transactional
@Service
public class PasswordRecoveryService {
    @Autowired
    private PersonRepository personRepo;

    @Autowired
    private ValidationService validationService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EmailService emailService;

    @Autowired
    private Map<String, String> tokenEmailMap;

    /**
     * Requests password recovery for the specified email address.
     * If the email address is valid and exists in the person repository,
     * a password recovery token is generated and stored in the tokenEmailMap.
     * An email containing a password reset link is then sent to the specified email
     * address.
     *
     * @param email the email address for which password recovery is requested
     * @throws ValidationException if the email address is invalid
     */
    public void requestPasswordRecovery(String email) throws ValidationException {

        if (validationService.validateEmail(email) && personRepo.findByEmail(email) != null) {
            String token = generateToken();
            tokenEmailMap.put(token, email);

            String baseUrl = System.getenv("BASE_URL");
            String passwordResetLink = baseUrl + "changePassword?token=" + token;

            emailService.sendEmail(email, "Password recovery",
                    "Do not share this email with unauthorized people." +
                            " Click the link below to reset your password: \n \"" + passwordResetLink + "\"");
        }
    }

    /**
     * Changes the password for a user identified by the provided token.
     *
     * @param token  The token used to identify the user.
     * @param person The PersonDTO object containing the new password.
     * @throws ValidationException If the new password fails validation.
     */
    public void changePassword(String token, PersonDTO person) throws ValidationException {
        validationService.validatePassword(person.getPassword());
        String email = tokenEmailMap.get(token);
        Person p = personRepo.findByEmail(email);
        p.setPassword(passwordEncoder.encode(person.getPassword()));
        personRepo.save(p);
        removeToken(token);
    }

    /**
     * Verifies if a given token exists in the token-email map.
     * 
     * @param token the token to be verified
     * @return true if the token exists in the map, false otherwise
     */
    public boolean verifyToken(String token) {
        return tokenEmailMap.containsKey(token);
    }

    /**
     * Removes the specified token from the tokenEmailMap.
     *
     * @param token the token to be removed
     */
    private void removeToken(String token) {
        tokenEmailMap.remove(token);
    }

    /**
     * Generates a random token as a string.
     *
     * @return a randomly generated token as a string.
     */
    private String generateToken() {
        return UUID.randomUUID().toString();
    }

}
