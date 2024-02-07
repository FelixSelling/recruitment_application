package kth.iv1201.group9.recruitment_application.domain.entity;

import java.util.Date;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import kth.iv1201.group9.recruitment_application.domain.DTO.AvailabilityDTO;

/**
 * The Availability class represents the availability of a person for a certain period of time.
 */
@Entity
@Table(name = "availability")
public class Availability implements AvailabilityDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "availability_id")
    private Integer availabilityId;

    @Column(name = "from_date")
    @Temporal(TemporalType.DATE)
    private Date fromDate;

    @Column(name = "to_date")
    @Temporal(TemporalType.DATE)
    private Date toDate;

    @JoinColumn(name = "person_id", referencedColumnName = "person_id")
    @ManyToOne
    private Person person;

    /**
     * Default constructor for the Availability class.
     */
    public Availability() {
    }

    /**
     * Constructor for the Availability class that sets the availability ID.
     * @param availabilityId The availability ID to set.
     */
    public Availability(Integer availabilityId) {
        this.availabilityId = availabilityId;
    }

    /**
     * Get the availability ID.
     * @return The availability ID.
     */
    @Override
    public Integer getAvailabilityId() {
        return availabilityId;
    }

    /**
     * Set the availability ID.
     * @param availabilityId The availability ID to set.
     */
    public void setAvailabilityId(Integer availabilityId) {
        this.availabilityId = availabilityId;
    }

    /**
     * Get the from date.
     * @return The from date.
     */
    @Override
    public Date getFromDate() {
        return fromDate;
    }

    /**
     * Set the from date.
     * @param fromDate The from date to set.
     */
    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    /**
     * Get the to date.
     * @return The to date.
     */
    @Override
    public Date getToDate() {
        return toDate;
    }

    /**
     * Set the to date.
     * @param toDate The to date to set.
     */
    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    /**
     * Get the person associated with the availability.
     * @return The person associated with the availability.
     */
    @Override
    public Person getPerson() {
        return person;
    }

    /**
     * Set the person associated with the availability.
     * @param person The person to set.
     */
    public void setPerson(Person person) {
        this.person = person;
    }
}
