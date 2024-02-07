package kth.iv1201.group9.recruitment_application.domain.DTO;

import java.util.List;

/**
 * The PersonDTO interface represents a data transfer object for a person.
 */
public interface PersonDTO {

    /**
     * Returns the ID of the person.
     */
    Integer getPersonId();

    /**
     * Returns the name of the person.
     */
    String getName();

    /**
     * Returns the surname of the person.
     */
    String getSurname();

    /**
     * Returns the personal identification number (pnr) of the person.
     */
    String getPnr();

    /**
     * Returns the email of the person.
     */
    String getEmail();

    /**
     * Returns the password of the person.
     */
    String getPassword();

    /**
     * Returns the username of the person.
     */
    String getUsername();

    /**
     * Returns the role of the person.
     */
    RoleDTO getRole();

    /**
     * Returns a list of competence profiles associated with the person.
     */
    List<? extends CompetenceProfileDTO> getCompetenceProfileList();

    /**
     * Returns the list of availabilities associated with this person.
     */
    List<? extends AvailabilityDTO> getAvailabilityList();

}
