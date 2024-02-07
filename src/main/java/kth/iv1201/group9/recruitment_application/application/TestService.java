package kth.iv1201.group9.recruitment_application.application;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kth.iv1201.group9.recruitment_application.domain.DTO.PersonDTO;
import kth.iv1201.group9.recruitment_application.repository.PersonRepository;

@Service
public class TestService {
    @Autowired
    private PersonRepository personRepo;

    public List<PersonDTO> getAllPersonList() {
        List<PersonDTO> personList = new ArrayList<>();
        personList.addAll(personRepo.findAll());
        return personList;
    }
}
