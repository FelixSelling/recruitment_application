package kth.iv1201.group9.recruitment_application.domain.DTO;

import java.math.BigDecimal;

/**
 * The CompetenceProfileDTO interface represents a data transfer object for a
 * competence profile.
 */
public interface CompetenceProfileDTO {

  /**
   * Returns the competence profile ID.
   */
  Integer getCompetenceProfileId();

  /**
   * Returns the years of experience.
   */
  BigDecimal getYearsOfExperience();

  /**
   * Returns the competence associated with the profile.
   */
  CompetenceDTO getCompetence();

  /**
   * Returns the person associated with the profile.
   */
  PersonDTO getPerson();
}
