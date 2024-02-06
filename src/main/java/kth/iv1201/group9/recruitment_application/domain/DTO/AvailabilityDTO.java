package kth.iv1201.group9.recruitment_application.domain.DTO;

import java.util.Date;

public interface AvailabilityDTO {
  Integer getAvailabilityId();

  Date getFromDate();

  Date getToDate();

  PersonDTO getPerson();
}