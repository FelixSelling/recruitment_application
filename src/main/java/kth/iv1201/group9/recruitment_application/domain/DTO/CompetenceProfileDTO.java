package kth.iv1201.group9.recruitment_application.domain.DTO;

import java.math.BigDecimal;

public interface CompetenceProfileDTO {
  Integer getCompetenceProfileId();

  BigDecimal getYearsOfExperience();

  CompetenceDTO getCompetence();

  PersonDTO getPerson();
}
