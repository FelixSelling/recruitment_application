/**
 * This interface represents the repository for managing Competence entities.
 * It extends the JpaRepository interface, providing CRUD operations for Competence entities.
 */
package kth.iv1201.group9.recruitment_application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kth.iv1201.group9.recruitment_application.domain.entity.Competence;

/**
 * The CompetenceRepository interface is responsible for handling database
 * operations related to the Competence entity.
 */
@Repository
public interface CompetenceRepository extends JpaRepository<Competence, Integer> {

}