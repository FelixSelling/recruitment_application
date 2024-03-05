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
     * Handles the password recovery process for a given email.
     * This method validates the email, retrieves the corresponding person from the
     * repository,
     * updates the person's password, and sends an email with the new password.
     * If any exception occurs during the process, it will be printed to the
     * console.
     *
     * @param email the email address for password recovery
     * @throws ValidationException
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

    public void changePassword(String token, PersonDTO person) throws ValidationException {
        validationService.validatePassword(person.getPassword());
        String email = tokenEmailMap.get(token);
        Person p = personRepo.findByEmail(email);
        p.setPassword(passwordEncoder.encode(person.getPassword()));
        personRepo.save(p);
        removeToken(token);
    }

    public void removeToken(String token) {
        tokenEmailMap.remove(token);
    }

    public boolean verifyToken(String token) {
        return tokenEmailMap.containsKey(token);
    }

    private String generateToken() {
        return UUID.randomUUID().toString();
    }

}
