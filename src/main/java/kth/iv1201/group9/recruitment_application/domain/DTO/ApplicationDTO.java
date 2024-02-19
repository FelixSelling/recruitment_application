package kth.iv1201.group9.recruitment_application.domain.DTO;

import kth.iv1201.group9.recruitment_application.domain.entity.Status;

public interface ApplicationDTO {

    Integer getApplicationId();

    Status getStatus();

    PersonDTO getPerson();

}
