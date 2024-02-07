package kth.iv1201.group9.recruitment_application.application;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kth.iv1201.group9.recruitment_application.domain.DTO.PersonDTO;

@Service
public class RecruiterService {
    @Autowired

    /**
     * Retrieves a list of all applicants.
     * The returned list is sorted by person ID.
     * 
     * @return the sorted list of all applicants
     */
    public List<PersonDTO> getAllApplicationsList() {
        List<PersonDTO> personList = new ArrayList<>();
        return personList;
    }
}
