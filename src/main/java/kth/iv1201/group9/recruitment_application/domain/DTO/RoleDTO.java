package kth.iv1201.group9.recruitment_application.domain.DTO;

import java.util.List;

/**
 * The RoleDTO interface represents a data transfer object for a role.
 */
public interface RoleDTO {

  /**
   * Returns the role ID.
   */
  Integer getRoleId();

  /**
   * Returns the name of the role.
   */
  String getName();

  /**
   * Returns a list of persons associated with the role.
   */
  List<? extends PersonDTO> getPersonList();
}
