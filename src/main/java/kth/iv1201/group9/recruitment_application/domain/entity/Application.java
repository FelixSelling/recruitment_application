package kth.iv1201.group9.recruitment_application.domain.entity;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import kth.iv1201.group9.recruitment_application.domain.DTO.ApplicationDTO;

@Entity
@Table(name = "application")
public class Application implements ApplicationDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "application_id")
    private Integer applicationId;

    @JoinColumn(name = "status_id", referencedColumnName = "status_id")
    @ManyToOne
    private Status status;

    @JoinColumn(name = "person_id", referencedColumnName = "person_id")
    @ManyToOne
    private Person person;

    public Application() {
    }

    public Application(Integer applicationId) {
        this.applicationId = applicationId;
    }

    @Override
    public Integer getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Integer applicationId) {
        this.applicationId = applicationId;
    }

    @Override
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public Person getPerson() {
        return person;
    }

    public void setPersonList(Person person) {
        this.person = person;
    }
}
