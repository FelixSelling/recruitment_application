package kth.iv1201.group9.recruitment_application.application;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kth.iv1201.group9.recruitment_application.domain.DTO.ApplicationDTO;
import kth.iv1201.group9.recruitment_application.repository.ApplicationRepository;

/**
 * This class represents the service for handling recruiter-related operations.
 * 
 */
@Transactional
@Service
public class RecruiterService {

    @Autowired
    private ApplicationRepository applicationRepository;

    /**
     * Retrieves a list of all applications.
     * 
     * @return the list of all applications
     */
    public List<ApplicationDTO> getApplicationsList() {
        return new ArrayList<>(applicationRepository.findAll());
    }
}
