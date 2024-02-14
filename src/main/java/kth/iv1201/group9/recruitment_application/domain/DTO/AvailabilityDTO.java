package kth.iv1201.group9.recruitment_application.domain.DTO;

import java.util.Date;

/**
 * This interface represents a data transfer object for availability.
 */
public interface AvailabilityDTO {

  /**
   * Returns the availability ID.
   */
  Integer getAvailabilityId();

  /**
   * Returns the start date of availability.
   */
  Date getFromDate();

  /**
   * Returns the end date of availability.
   */
  Date getToDate();

  /**
   * Returns the person associated with the availability.
   */
  PersonDTO getPerson();
}