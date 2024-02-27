/**
 * This interface represents the repository for managing Person entities.
 * It extends the JpaRepository interface, providing CRUD operations for Person entities.
 */
package kth.iv1201.group9.recruitment_application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kth.iv1201.group9.recruitment_application.domain.entity.Person;

/**
 * The PersonRepository interface is responsible for handling database
 * operations related to the Person entity.
 */
@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {

    /**
     * Finds a person by their username and password.
     *
     * @param username the username of the person
     * @param password the password of the person
     * @return the person with the given username and password, or null if not found
     */
    Person findByUsernameAndPassword(String username, String password);

    Person findByUsername(String username);

    Person findByPnr(String pnr);

    Person findByEmail(String email);
}
