package kth.iv1201.group9.recruitment_application.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.stereotype.Service;

import kth.iv1201.group9.recruitment_application.domain.entity.Person;
import kth.iv1201.group9.recruitment_application.repository.PersonRepository;

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

    public void handleEmail(String email) {
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
