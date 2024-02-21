
package kth.iv1201.group9.recruitment_application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kth.iv1201.group9.recruitment_application.domain.entity.Application;

/**
 * The ApplicationRepository interface is responsible for handling database
 * operations related to the Application entity.
 */
@Repository
public interface ApplicationRepository extends JpaRepository<Application, Integer> {

}