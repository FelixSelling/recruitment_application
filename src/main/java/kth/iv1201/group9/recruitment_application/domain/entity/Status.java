package kth.iv1201.group9.recruitment_application.domain.entity;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import kth.iv1201.group9.recruitment_application.domain.DTO.StatusDTO;

@Entity
@Table(name = "status")
public class Status implements StatusDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "status_id")
    private Integer statusId;

    @Column(name = "name")
    private String name;

    public Status() {
    }

    public Status(Integer statusId) {
        this.statusId = statusId;
    }

    @Override
    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
