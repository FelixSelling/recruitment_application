package kth.iv1201.group9.recruitment_application.domain.DTO;

import java.util.List;

public interface RoleDTO {
  Integer getRoleId();

  String getName();

  List<PersonDTO> getPersonList();
}
