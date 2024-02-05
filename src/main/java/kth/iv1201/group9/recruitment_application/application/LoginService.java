package kth.iv1201.group9.recruitment_application.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kth.iv1201.group9.recruitment_application.domain.DTO.PersonDTO;
import kth.iv1201.group9.recruitment_application.domain.entity.Person;
import kth.iv1201.group9.recruitment_application.repository.PersonRepository;

@Service
public class LoginService {
    @Autowired
    private PersonRepository personRepo;

    public PersonDTO findUser(String username, String password) {
        Person person = personRepo.findByUsername(username);
        if (person != null && person.getPassword().equals(password)) {
            return new PersonDTO(person);
        }
        return null;
    }
}
