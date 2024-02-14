package kth.iv1201.group9.recruitment_application.domain.entity;

import java.util.List;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import kth.iv1201.group9.recruitment_application.domain.DTO.PersonDTO;
import kth.iv1201.group9.recruitment_application.domain.DTO.RoleDTO;

/**
 * The Role class represents the role of a person.
 */
@Entity
@Table(name = "role")
public class Role implements RoleDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "role_id")
    private Integer roleId;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "role")
    private List<Person> personList;

    /**
     * Default constructor for the Role class.
     */
    public Role() {
    }

    /**
     * Constructor for the Role class with the roleId parameter.
     * @param roleId The ID of the role.
     */
    public Role(Integer roleId) {
        this.roleId = roleId;
    }

    /**
     * Get the ID of the role.
     * @return The ID of the role.
     */
    @Override
    public Integer getRoleId() {
        return roleId;
    }

    /**
     * Set the ID of the role.
     * @param roleId The ID of the role.
     */
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    /**
     * Get the name of the role.
     * @return The name of the role.
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Set the name of the role.
     * @param name The name of the role.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the list of persons associated with the role.
     * @return The list of persons associated with the role.
     */
    @Override
    public List<? extends PersonDTO> getPersonList() {
        return personList;
    }

    /**
     * Set the list of persons associated with the role.
     * @param personList The list of persons associated with the role.
     */
    public void setPersonList(List<Person> personList) {
        this.personList = personList;
    }
}
