package kth.iv1201.group9.recruitment_application.domain.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import kth.iv1201.group9.recruitment_application.domain.DTO.AvailabilityDTO;
import kth.iv1201.group9.recruitment_application.domain.DTO.CompetenceProfileDTO;
import kth.iv1201.group9.recruitment_application.domain.DTO.PersonDTO;

/**
 * The Person class represents a person.
 */
@Entity
@Table(name = "person")
public class Person implements PersonDTO, UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "person_id")
    private Integer personId;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "pnr")
    private String pnr;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "username")
    private String username;

    @JoinColumn(name = "role_id", referencedColumnName = "role_id")
    @ManyToOne
    private Role role;

    @OneToMany(mappedBy = "person")
    private List<CompetenceProfile> competenceProfileList;

    @OneToMany(mappedBy = "person")
    private List<Availability> availabilityList;

    /**
     * Default constructor for the Person class.
     */
    public Person() {
    }

    /**
     * Constructor for the Person class with the personId parameter.
     * 
     * @param personId The ID of the person.
     */
    public Person(Integer personId) {
        this.personId = personId;
    }

    /**
     * Get the ID of the person.
     * 
     * @return The ID of the person.
     */
    @Override
    public Integer getPersonId() {
        return personId;
    }

    /**
     * Set the ID of the person.
     * 
     * @param personId The ID of the person.
     */
    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    /**
     * Get the name of the person.
     * 
     * @return The name of the person.
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Set the name of the person.
     * 
     * @param name The name of the person.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the surname of the person.
     * 
     * @return The surname of the person.
     */
    @Override
    public String getSurname() {
        return surname;
    }

    /**
     * Set the surname of the person.
     * 
     * @param surname The surname of the person.
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * Get the personal identification number (pnr) of the person.
     * 
     * @return The personal identification number (pnr) of the person.
     */
    @Override
    public String getPnr() {
        return pnr;
    }

    /**
     * Set the personal identification number (pnr) of the person.
     * 
     * @param pnr The personal identification number (pnr) of the person.
     */
    public void setPnr(String pnr) {
        this.pnr = pnr;
    }

    /**
     * Get the email address of the person.
     * 
     * @return The email address of the person.
     */
    @Override
    public String getEmail() {
        return email;
    }

    /**
     * Set the email address of the person.
     * 
     * @param email The email address of the person.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Get the password of the person.
     * 
     * @return The password of the person.
     */
    @Override
    public String getPassword() {
        return password;
    }

    /**
     * Set the password of the person.
     * 
     * @param password The password of the person.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Get the username of the person.
     * 
     * @return The username of the person.
     */
    @Override
    public String getUsername() {
        return username;
    }

    /**
     * Set the username of the person.
     * 
     * @param username The username of the person.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Get the role of the person.
     * 
     * @return The role of the person.
     */
    @Override
    public Role getRole() {
        return role;
    }

    /**
     * Set the role of the person.
     * 
     * @param role The role of the person.
     */
    public void setRole(Role role) {
        this.role = role;
    }

    /**
     * Get the list of competence profiles associated with the person.
     * 
     * @return The list of competence profiles associated with the person.
     */
    @Override
    public List<? extends CompetenceProfileDTO> getCompetenceProfileList() {
        return competenceProfileList;
    }

    /**
     * Set the list of competence profiles associated with the person.
     * 
     * @param competenceProfileList The list of competence profiles associated with
     *                              the person.
     */
    public void setCompetenceProfileList(List<CompetenceProfile> competenceProfileList) {
        this.competenceProfileList = competenceProfileList;
    }

    /**
     * Get the list of availabilities associated with the person.
     * 
     * @return The list of availabilities associated with the person.
     */
    @Override
    public List<? extends AvailabilityDTO> getAvailabilityList() {
        return availabilityList;
    }

    /**
     * Set the list of availabilities associated with the person.
     * 
     * @param availabilityList The list of availabilities associated with the
     *                         person.
     */
    public void setAvailabilityList(List<Availability> availabilityList) {
        this.availabilityList = availabilityList;
    }

    // Below is the user details implementation
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorityList = new ArrayList<>();
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.getName());
        authorityList.add(authority);
        return authorityList;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;

    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;

    }

    @Override
    public boolean isEnabled() {
        return true;

    }
}
