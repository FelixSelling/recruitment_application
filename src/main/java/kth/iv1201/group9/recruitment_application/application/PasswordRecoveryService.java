package kth.iv1201.group9.recruitment_application.application;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.stereotype.Service;

import kth.iv1201.group9.recruitment_application.domain.DTO.PersonDTO;
import kth.iv1201.group9.recruitment_application.domain.entity.Person;
import kth.iv1201.group9.recruitment_application.repository.PersonRepository;

/**
 * This class represents a service for handling password recovery functionality.
 * 
 */
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
     */
    public void requestPasswordRecovery(String email) {

        try {
            validationService.validateEmail(email);
            String token = generateToken();
            tokenEmailMap.put(token, email);

            emailService.sendEmail(email, "Password recovery",
                    "Do not share this email with unauthorized people." +
                            " Click the link below to reset your password: \n \"http://localhost:8080/changePassword?token="
                            + token + "\"",
                    token);

        } catch (Exception e) {
            e.printStackTrace();
            // TODO: handle exception
        }

    }

    public void changePassword(String token, PersonDTO person) {
        validationService.validatePassword(person.getPassword());
        String email = tokenEmailMap.get(token);
        Person p = personRepo.findByEmail(email);
        p.setPassword(passwordEncoder.encode(person.getPassword()));
        personRepo.save(p);
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
