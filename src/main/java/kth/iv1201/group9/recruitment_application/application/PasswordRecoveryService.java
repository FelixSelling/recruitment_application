package kth.iv1201.group9.recruitment_application.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.stereotype.Service;

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
    public void handlePasswordRecovery(String email) {
        try {
            validationService.validateEmail(email);

            Person person = personRepo.findByEmail(email);
            person.setPassword(passwordEncoder.encode("Test1234"));
            personRepo.save(person);

            emailService.sendEmail(email, "Password recovery", " \n Your new password is: Test1234");

        } catch (Exception e) {
            e.printStackTrace();
            // TODO: handle exception
        }

    }

}
