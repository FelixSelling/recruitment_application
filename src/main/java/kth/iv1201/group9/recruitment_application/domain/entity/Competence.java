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
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import kth.iv1201.group9.recruitment_application.domain.DTO.CompetenceDTO;
import kth.iv1201.group9.recruitment_application.domain.DTO.CompetenceProfileDTO;

/**
 * The Competence class represents a competence in CompetenceProfile.
 */
@Entity
@Table(name = "competence")
public class Competence implements CompetenceDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "competence_id")
    private Integer competenceId;

    @Column(name = "name")
    @NotBlank(message = "error.database.validation.competence.name.blank")
    private String name;

    @OneToMany(mappedBy = "competence")
    @Valid
    private List<CompetenceProfile> competenceProfileList;

    /**
     * Default constructor for the Competence class.
     */
    public Competence() {
    }

    /**
     * Constructor for the Competence class.
     * @param competenceId The ID of the competence.
     */
    public Competence(Integer competenceId) {
        this.competenceId = competenceId;
    }

    /**
     * Get the ID of the competence.
     * @return The ID of the competence.
     */
    @Override
    public Integer getCompetenceId() {
        return competenceId;
    }

    /**
     * Set the ID of the competence.
     * @param competenceId The ID of the competence.
     */
    public void setCompetenceId(Integer competenceId) {
        this.competenceId = competenceId;
    }

    /**
     * Get the name of the competence.
     * @return The name of the competence.
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Set the name of the competence.
     * @param name The name of the competence.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the list of competence profiles associated with this competence.
     * @return The list of competence profiles.
     */
    @Override
    public List<? extends CompetenceProfileDTO> getCompetenceProfileList() {
        return competenceProfileList;
    }

    /**
     * Set the list of competence profiles associated with this competence.
     * @param competenceProfileList The list of competence profiles.
     */
    public void setCompetenceProfileList(List<CompetenceProfile> competenceProfileList) {
        this.competenceProfileList = competenceProfileList;
    }
}
