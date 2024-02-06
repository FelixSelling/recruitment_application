/**
 * This interface represents the repository for managing CompetenceProfile entities.
 * It extends the JpaRepository interface, providing CRUD operations for CompetenceProfile entities.
 */
package kth.iv1201.group9.recruitment_application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kth.iv1201.group9.recruitment_application.domain.entity.CompetenceProfile;

/**
 * The CompetenceProfileRepository interface is responsible for handling
 * database operations related to the CompetenceProfile entity.
 */
@Repository
public interface CompetenceProfileRepository extends JpaRepository<CompetenceProfile, Integer> {

}