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
    private PasswordEncoder passwordEncoder;

    @SuppressWarnings("null")
    public void handleRegisteredUser(PersonDTO userDTO) {
        if (userDTO == null) {
            throw new IllegalArgumentException("UserDTO is null");
        }
        personRepo.save(casting(userDTO));
    }

    private Person casting(PersonDTO userDTO) {
        Person user = new Person();
        user.setName(userDTO.getName());
        user.setSurname(userDTO.getSurname());
        user.setEmail(userDTO.getEmail());
        user.setPnr(userDTO.getPnr());
        user.setUsername(userDTO.getUsername());
        user.setRole(new Role(2));
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        return user;
    }
}
