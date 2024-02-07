package kth.iv1201.group9.recruitment_application.domain.DTO;

import java.util.List;

/**
 * The CompetenceDTO interface represents the data transfer object for a
 * competence.
 */
public interface CompetenceDTO {
  /**
   * Returns the competence ID.
   */
  Integer getCompetenceId();

  /**
   * Returns the name of the competence.
   */
  String getName();

  /**
   * Returns a list of competence profiles associated with this competence.
   */
  List<? extends CompetenceProfileDTO> getCompetenceProfileList();
}
