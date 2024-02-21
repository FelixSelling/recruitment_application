package kth.iv1201.group9.recruitment_application.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import kth.iv1201.group9.recruitment_application.domain.DTO.PersonDTO;
import kth.iv1201.group9.recruitment_application.domain.entity.Person;
import kth.iv1201.group9.recruitment_application.domain.entity.Role;
import kth.iv1201.group9.recruitment_application.repository.PersonRepository;

@Service
public class RegistrationService {
    @Autowired
    private PersonRepository personRepo;

    @Autowired
    private ValidationService validationService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Handles the registration of a user.
     * 
     * @param userDTO the data transfer object containing the user information
     * @throws IllegalArgumentException if the userDTO is null
     */
    @SuppressWarnings("null")
    public void handleRegisteredUser(PersonDTO userDTO) {
        if (userDTO == null) {
            throw new IllegalArgumentException("UserDTO is null");
        }
        personRepo.save(validateInput(userDTO));
    }

    /**
     * Validated the input and returns a new user.
     * 
     * @param userDTO the data transfer object containing the user information
     * @throws IllegalArgumentException if any of the input is invalid
     */
    private Person validateInput(PersonDTO userDTO) {
        // Validate the input
        String name = validationService.validateName(userDTO.getName());
        String surname = validationService.validateSurname(userDTO.getSurname());
        String email = validationService.validateEmail(userDTO.getEmail());
        String pnr = validationService.validatePnr(userDTO.getPnr());
        String username = validationService.validateUsername(userDTO.getUsername());
        String password = validationService.validatePassword(userDTO.getPassword());

        // Create a new user and return it for saving
        Person user = new Person();
        user.setName(name);
        user.setSurname(surname);
        user.setEmail(email);
        user.setPnr(pnr);
        user.setUsername(username);
        user.setRole(new Role(2));
        user.setPassword(passwordEncoder.encode(password));
        return user;
    }
}
