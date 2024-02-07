package kth.iv1201.group9.recruitment_application.domain.DTO;

import java.util.List;

public interface CompetenceDTO {
  Integer getCompetenceId();

  String getName();

  List<? extends CompetenceProfileDTO> getCompetenceProfileList();
}
