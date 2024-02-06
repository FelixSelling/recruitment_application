package kth.iv1201.group9.recruitment_application.domain.DTO;

import java.util.Set;

public interface CompetenceDTO {
  Integer getCompetenceId();

  String getName();

  Set<? extends CompetenceProfileDTO> getCompetenceProfileList();
}
