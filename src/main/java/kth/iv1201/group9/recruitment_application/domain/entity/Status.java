package kth.iv1201.group9.recruitment_application.domain.entity;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import kth.iv1201.group9.recruitment_application.domain.DTO.StatusDTO;

/**
 * The Status class represents the status of an persons application.
 */
@Entity
@Table(name = "status")
public class Status implements StatusDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "status_id")
    private Integer statusId;

    @Column(name = "status_number")
    @NotNull(message = "error.database.validation.Status.statusNumber.null")
    private Integer statusNumber;

    /**
     * Default constructor for the Status class.
     */
    public Status() {
    }

    /**
     * Constructor for the Status class with a specified status ID.
     *
     * @param statusId the status ID
     */
    public Status(Integer statusId) {
        this.statusId = statusId;
    }

    /**
     * Get the status ID.
     *
     * @return the status ID
     */
    @Override
    public Integer getStatusId() {
        return statusId;
    }

    /**
     * Set the status ID.
     *
     * @param statusId the status ID to set
     */
    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

    /**
     * Get the status number.
     *
     * @return the status number
     */
    @Override
    public Integer getStatusNumber() {
        return statusNumber;
    }

    /**
     * Set the status number.
     *
     * @param status the statusNumber to set
     */
    public void setStatusNumber(Integer statusNumber) {
        this.statusNumber = statusNumber;
    }

}
