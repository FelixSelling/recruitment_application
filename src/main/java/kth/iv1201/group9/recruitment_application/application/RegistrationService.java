package kth.iv1201.group9.recruitment_application.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kth.iv1201.group9.recruitment_application.domain.DTO.PersonDTO;
import kth.iv1201.group9.recruitment_application.domain.entity.Person;
import kth.iv1201.group9.recruitment_application.domain.entity.Role;
import kth.iv1201.group9.recruitment_application.exception.RegistrationException;
import kth.iv1201.group9.recruitment_application.exception.ValidationException;
import kth.iv1201.group9.recruitment_application.repository.PersonRepository;

/**
 * Service class responsible for handling user registration.
 * 
 */
@Transactional
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
     * @throws RegistrationException    if any of the input is invalid
     */
    @SuppressWarnings("null")
    public void handleRegisteredUser(PersonDTO userDTO) throws RegistrationException {
        if (userDTO == null) {
            throw new IllegalArgumentException("error.registration.userDTO.null");
        }
        if (validateInput(userDTO) && checkUnique(userDTO)) {
            personRepo.save(createUser(userDTO, 2));
        }
    }

    /**
     * Validates the input provided in the PersonDTO object.
     *
     * @param userDTO the PersonDTO object containing the user's information
     * @return true if the input is valid
     * @throws RegistrationException if there is an error during the registration
     *                               process
     */
    private boolean validateInput(PersonDTO userDTO) throws RegistrationException {

        // Validate the input
        try {
            validationService.validateName(userDTO.getName());
            validationService.validateSurname(userDTO.getSurname());
            validationService.validateEmail(userDTO.getEmail());
            validationService.validatePnr(userDTO.getPnr());
            validationService.validateUsername(userDTO.getUsername());
            validationService.validatePassword(userDTO.getPassword());
        } catch (ValidationException ex) {
            throw new RegistrationException(ex.getMessage());
        }

        return true;
    }

    /**
     * Checks if the provided userDTO has unique email, pnr, and username.
     * 
     * @param userDTO the PersonDTO object containing the user information
     * @return true if the email, pnr, and username are unique
     * @throws RegistrationException if the email, pnr, or username is already taken
     */
    private boolean checkUnique(PersonDTO userDTO) throws RegistrationException {
        // Check if the email is already taken
        if (personRepo.findByEmail(userDTO.getEmail()) != null) {
            throw new RegistrationException("error.registration.email.taken");
        }

        // Check if the pnr is already taken
        if (personRepo.findByPnr(userDTO.getPnr()) != null) {
            throw new RegistrationException("error.registration.pnr.taken");
        }

        // Check if the username is already taken
        if (personRepo.findByUsername(userDTO.getUsername()) != null) {
            throw new RegistrationException("error.registration.username.taken");
        }

        return true;
    }

    /**
     * Creates a Person object from the provided PersonDTO object.
     * 
     * @param userDTO the PersonDTO object containing the user information
     * @param roleID  the ID of the role to be assigned to the user
     * @return the Person object created from the PersonDTO object
     */
    private Person createUser(PersonDTO userDTO, int roleID) {
        Person user = new Person();
        user.setName(userDTO.getName());
        user.setSurname(userDTO.getSurname());
        user.setEmail(userDTO.getEmail());
        user.setPnr(userDTO.getPnr());
        user.setUsername(userDTO.getUsername());
        user.setRole(new Role(roleID));
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        return user;
    }
}
