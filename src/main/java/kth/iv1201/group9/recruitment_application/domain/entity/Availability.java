package kth.iv1201.group9.recruitment_application.domain.entity;

import java.io.Serializable;
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

@Entity
@Table(name = "availability")
public class Availability implements Serializable {

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

    public Availability() {
    }

    public Availability(Integer availabilityId) {
        this.availabilityId = availabilityId;
    }

    public Integer getAvailabilityId() {
        return availabilityId;
    }

    public void setAvailabilityId(Integer availabilityId) {
        this.availabilityId = availabilityId;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
