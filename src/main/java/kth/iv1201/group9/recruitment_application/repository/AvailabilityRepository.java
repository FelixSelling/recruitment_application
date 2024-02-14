/**
 * This interface represents the repository for managing Availabilty entities.
 * It extends the JpaRepository interface, providing CRUD operations for Availability entities.
 */
package kth.iv1201.group9.recruitment_application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kth.iv1201.group9.recruitment_application.domain.entity.Availability;

/**
 * The AvailabilityRepository interface is responsible for handling database
 * operations related to the Availability entity.
 */
@Repository
public interface AvailabilityRepository extends JpaRepository<Availability, Integer> {

}