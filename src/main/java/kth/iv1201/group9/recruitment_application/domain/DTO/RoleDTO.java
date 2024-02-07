package kth.iv1201.group9.recruitment_application.domain.DTO;

import java.util.List;

/**
 * The RoleDTO interface represents a data transfer object for a role.
 */
public interface RoleDTO {

  /**
   * Retrieves the role ID.
   */
  Integer getRoleId();

  /**
   * Retrieves the name of the role.
   */
  String getName();

  /**
   * Retrieves a list of persons associated with the role.
   */
  List<? extends PersonDTO> getPersonList();
}
