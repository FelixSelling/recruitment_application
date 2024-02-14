package kth.iv1201.group9.recruitment_application.application;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kth.iv1201.group9.recruitment_application.domain.DTO.PersonDTO;
import kth.iv1201.group9.recruitment_application.domain.entity.Role;
import kth.iv1201.group9.recruitment_application.repository.RoleRepository;

@Service
public class RecruiterService {
    @Autowired
    private RoleRepository roleRepo;

    /**
     * Retrieves a list of all applicants.
     * The returned list is sorted by person ID.
     * 
     * @return the sorted list of all applicants
     */
    public List<PersonDTO> getAllApplicantsList() {
        List<PersonDTO> personList = new ArrayList<>();
        // ID 2 is the ID for the role "applicant"
        Role role = roleRepo.findById(2).get();
        for (PersonDTO person : role.getPersonList()) {
            personList.add(person);
        }
        personList.sort((p1, p2) -> p1.getPersonId() - p2.getPersonId());
        return personList;
    }
}
