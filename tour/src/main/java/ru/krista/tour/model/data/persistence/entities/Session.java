package ru.krista.tour.model.data.persistence.entities;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Proxy;
import org.hibernate.annotations.UpdateTimestamp;
import ru.krista.tour.model.data.persistence.entities.RootKey;
import ru.krista.tour.model.data.persistence.entities.Tour;

import javax.faces.bean.ApplicationScoped;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Proxy(lazy = true)
@Table(name = "session")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
@ApplicationScoped
public class Session extends RootKey implements Serializable {
   @ManyToOne(optional = false, fetch = FetchType.LAZY)
   @JoinColumn( name="tour_id", referencedColumnName="id")
    private Tour tour;

    @Column(name= "user_id")
    private String userId;
    @Column(name= "status")
    private String status;

    @Column(name= "change_date")
    private Date dateChange;

    @PrePersist
    protected void onPersist() {
        dateChange = new Date();
    }
    @PreUpdate
    protected void onUpdate() {

        dateChange = new Date();
    }
    public Session(String user, Tour tour, String status){
        this.userId = user;
        this.tour = tour;
        this.status = status;
    }
    public Session(){ }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public Long getTourId() {
        return tour.getId();
    }
    public Tour getTour() {
        return tour;
    }
    public void setTour(Tour tour) {
        this.tour = tour;
    }
    public Date getDateChange() {
        return dateChange;
    }
    public void setDateChange(Date dateChange) {
        this.dateChange = dateChange;
    }
}

