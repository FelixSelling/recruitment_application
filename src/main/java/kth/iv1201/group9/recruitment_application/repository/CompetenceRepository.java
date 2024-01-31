package kth.iv1201.group9.recruitment_application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kth.iv1201.group9.recruitment_application.domain.entity.Competence;

@Repository
public interface CompetenceRepository extends JpaRepository<Competence, Integer>{
    
}