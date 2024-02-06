package kth.iv1201.group9.recruitment_application.domain.DTO;

import java.util.Set;

public interface RoleDTO {
  Integer getRoleId();

  String getName();

  Set<? extends PersonDTO> getPersonList();
}
