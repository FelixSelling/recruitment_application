
package kth.iv1201.group9.recruitment_application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kth.iv1201.group9.recruitment_application.domain.entity.Status;

/**
 * The StatusRepository interface is responsible for handling database
 * operations related to the Status entity.
 */
@Repository
public interface StatusRepository extends JpaRepository<Status, Integer> {

}