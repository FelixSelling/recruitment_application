package kth.iv1201.group9.recruitment_application.domain.DTO;

import java.util.Set;

public interface PersonDTO {

    Integer getPersonId();

    String getName();

    String getSurname();

    String getPnr();

    String getEmail();

    String getPassword();

    String getUsername();

    RoleDTO getRole();

    Set<? extends CompetenceProfileDTO> getCompetenceProfileList();

    Set<? extends AvailabilityDTO> getAvailabilityList();

}
