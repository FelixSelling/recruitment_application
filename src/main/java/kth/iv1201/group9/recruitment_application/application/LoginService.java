package kth.iv1201.group9.recruitment_application.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kth.iv1201.group9.recruitment_application.domain.DTO.PersonDTO;
import kth.iv1201.group9.recruitment_application.repository.PersonRepository;

@Service
public class LoginService {
    @Autowired
    private PersonRepository personRepo;

    /**
     * Finds a user with the given username and password.
     * 
     * @param username the username of the user
     * @param password the password of the user
     * @return the PersonDTO object representing the user if found, null otherwise
     */
    public PersonDTO findUser(String username, String password) {
        PersonDTO person = personRepo.findByUsernameAndPassword(username, password);
        if (person != null) {
            return person;
        }
        return null;
    }
}
