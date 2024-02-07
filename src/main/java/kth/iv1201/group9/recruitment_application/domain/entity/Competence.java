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
import kth.iv1201.group9.recruitment_application.domain.DTO.CompetenceDTO;
import kth.iv1201.group9.recruitment_application.domain.DTO.CompetenceProfileDTO;

@Entity
@Table(name = "competence")
public class Competence implements CompetenceDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "competence_id")
    private Integer competenceId;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "competence")
    private List<CompetenceProfile> competenceProfileList;

    public Competence() {
    }

    public Competence(Integer competenceId) {
        this.competenceId = competenceId;
    }

    @Override
    public Integer getCompetenceId() {
        return competenceId;
    }

    public void setCompetenceId(Integer competenceId) {
        this.competenceId = competenceId;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public List<? extends CompetenceProfileDTO> getCompetenceProfileList() {
        return competenceProfileList;
    }

    public void setCompetenceProfileList(List<CompetenceProfile> competenceProfileList) {
        this.competenceProfileList = competenceProfileList;
    }
}
