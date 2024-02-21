package kth.iv1201.group9.recruitment_application.domain.DTO;

import kth.iv1201.group9.recruitment_application.domain.entity.Status;

/**
 * This interface represents a data transfer object for an application.
 */
public interface ApplicationDTO {

    /**
     * Returns the application ID.
     *
     */
    Integer getApplicationId();

    /**
     * Returns the status of the application.
     *
     */
    Status getStatus();

    /**
     * Returns the person associated with the application.
     *
     */
    PersonDTO getPerson();

}
