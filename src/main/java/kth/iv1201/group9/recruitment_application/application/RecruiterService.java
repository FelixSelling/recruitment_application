package kth.iv1201.group9.recruitment_application.application;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kth.iv1201.group9.recruitment_application.domain.DTO.ApplicationDTO;
import kth.iv1201.group9.recruitment_application.repository.ApplicationRepository;

@Transactional
@Service
public class RecruiterService {
    /**
     * Retrieves a list of all applicantions.
     * 
     * @return the list of all applicantions
     */
    @Autowired
    private ApplicationRepository applicationRepository;

    public List<ApplicationDTO> getApplicantionsList() {
        return new ArrayList<>(applicationRepository.findAll());
    }
}
