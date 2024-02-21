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

/**
 * The Application class represents a job application made by a person.
 */
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

    /**
     * Default constructor for the Application class.
     */
    public Application() {
    }

    /**
     * Constructor for the Application class with the specified application ID.
     *
     * @param applicationId The application ID.
     */
    public Application(Integer applicationId) {
        this.applicationId = applicationId;
    }

    /**
     * Get the application ID.
     *
     * @return The application ID.
     */
    @Override
    public Integer getApplicationId() {
        return applicationId;
    }

    /**
     * Set the application ID.
     *
     * @param applicationId The application ID.
     */
    public void setApplicationId(Integer applicationId) {
        this.applicationId = applicationId;
    }

    /**
     * Get the status of the application.
     *
     * @return The status of the application.
     */
    @Override
    public Status getStatus() {
        return status;
    }

    /**
     * Set the status of the application.
     *
     * @param status The status of the application.
     */
    public void setStatus(Status status) {
        this.status = status;
    }

    /**
     * Get the person associated with the application.
     *
     * @return The person associated with the application.
     */
    @Override
    public Person getPerson() {
        return person;
    }

    /**
     * Set the person associated with the application.
     *
     * @param person The person associated with the application.
     */
    public void setPersonList(Person person) {
        this.person = person;
    }
}
