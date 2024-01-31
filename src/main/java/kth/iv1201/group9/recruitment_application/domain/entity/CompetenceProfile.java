package kth.iv1201.group9.recruitment_application.domain.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "competence_profile")
public class CompetenceProfile implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "competence_profile_id")
    private Integer competenceProfileId;

    // @Max(value=?)  @Min(value=?)
    @Column(name = "years_of_experience")
    private BigDecimal yearsOfExperience;

    @JoinColumn(name = "competence_id", referencedColumnName = "competence_id")
    @ManyToOne
    private Competence competence;

    @JoinColumn(name = "person_id", referencedColumnName = "person_id")
    @ManyToOne
    private Person person;

    public CompetenceProfile() {
    }

    public CompetenceProfile(Integer competenceProfileId) {
        this.competenceProfileId = competenceProfileId;
    }

    public Integer getCompetenceProfileId() {
        return competenceProfileId;
    }

    public void setCompetenceProfileId(Integer competenceProfileId) {
        this.competenceProfileId = competenceProfileId;
    }

    public BigDecimal getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setYearsOfExperience(BigDecimal yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

    public Competence getCompetence() {
        return competence;
    }

    public void setCompetence(Competence competence) {
        this.competence = competence;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
