package kth.iv1201.group9.recruitment_application.domain.entity;

import java.util.ArrayList;
import java.util.List;
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

@Entity
@Table(name = "person")
public class Person implements PersonDTO {

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

    public Person() {
    }

    public Person(Integer personId) {
        this.personId = personId;
    }

    @Override
    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public String getPnr() {
        return pnr;
    }

    public void setPnr(String pnr) {
        this.pnr = pnr;
    }

    @Override
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public List<CompetenceProfileDTO> getCompetenceProfileList() {
        List<CompetenceProfileDTO> competenceProfileDTOList = new ArrayList<>();
        for (CompetenceProfileDTO competenceProfile : competenceProfileList) {
            competenceProfileDTOList.add(competenceProfile);
        }
        return competenceProfileDTOList;
    }

    public void setCompetenceProfileList(List<CompetenceProfile> competenceProfileList) {
        this.competenceProfileList = competenceProfileList;
    }

    @Override
    public List<AvailabilityDTO> getAvailabilityList() {
        List<AvailabilityDTO> availabilityDTOList = new ArrayList<>();
        for (AvailabilityDTO availability : availabilityList) {
            availabilityDTOList.add(availability);
        }
        return availabilityDTOList;
    }

    public void setAvailabilityList(List<Availability> availabilityList) {
        this.availabilityList = availabilityList;
    }
}
