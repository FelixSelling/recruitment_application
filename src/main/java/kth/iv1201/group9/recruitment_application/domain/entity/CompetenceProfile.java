package kth.iv1201.group9.recruitment_application.domain.entity;

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
import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import kth.iv1201.group9.recruitment_application.domain.DTO.CompetenceProfileDTO;

/**
 * The CompetenceProfile class represents a competence profile of a person.
 */
@Entity
@Table(name = "competence_profile")
public class CompetenceProfile implements CompetenceProfileDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "competence_profile_id")
    private Integer competenceProfileId;

    @Column(name = "years_of_experience")
    @DecimalMin(value = "0", inclusive = false, message = "error.database.validation.competence_profile.yearsOfExperience.invalid")
    @NotNull(message = "error.database.validation.competence_profile.yearsOfExperience.null")
    private BigDecimal yearsOfExperience;

    @JoinColumn(name = "competence_id", referencedColumnName = "competence_id")
    @ManyToOne
    @NotNull(message = "error.database.validation.competence_profile.competence.null")
    private Competence competence;

    @JoinColumn(name = "person_id", referencedColumnName = "person_id")
    @ManyToOne
    @NotNull(message = "error.database.validation.competence_profile.person.null")
    @Valid
    private Person person;

    public CompetenceProfile() {
    }

    public CompetenceProfile(Integer competenceProfileId) {
        this.competenceProfileId = competenceProfileId;
    }

    /**
     * Get the competence profile ID.
     *
     * @return The competence profile ID.
     */
    @Override
    public Integer getCompetenceProfileId() {
        return competenceProfileId;
    }

    /**
     * Set the competence profile ID.
     *
     * @param competenceProfileId The competence profile ID.
     */
    public void setCompetenceProfileId(Integer competenceProfileId) {
        this.competenceProfileId = competenceProfileId;
    }

    /**
     * Get the years of experience.
     *
     * @return The years of experience.
     */
    @Override
    public BigDecimal getYearsOfExperience() {
        return yearsOfExperience;
    }

    /**
     * Set the years of experience.
     *
     * @param yearsOfExperience The years of experience.
     */
    public void setYearsOfExperience(BigDecimal yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

    /**
     * Get the competence.
     *
     * @return The competence.
     */
    @Override
    public Competence getCompetence() {
        return competence;
    }

    /**
     * Set the competence.
     *
     * @param competence The competence.
     */
    public void setCompetence(Competence competence) {
        this.competence = competence;
    }

    /**
     * Get the person.
     *
     * @return The person.
     */
    @Override
    public Person getPerson() {
        return person;
    }

    /**
     * Set the person.
     *
     * @param person The person.
     */
    public void setPerson(Person person) {
        this.person = person;
    }
}
