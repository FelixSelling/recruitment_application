/**
 * This interface represents the repository for managing Role entities.
 * It extends the JpaRepository interface, providing CRUD operations for Role entities.
 */
package kth.iv1201.group9.recruitment_application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kth.iv1201.group9.recruitment_application.domain.entity.Role;

/**
 * The RoleRepository interface is responsible for handling database
 * operations related to the Role entity.
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

}
