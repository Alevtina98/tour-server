package ru.krista.tour.model.data.persistence.entities.userTour;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.Proxy;
import ru.krista.tour.model.data.persistence.entities.RootKey;
import ru.krista.tour.model.data.persistence.entities.Tour;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Proxy(lazy = true)
@Table(name = "user_tour")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class UserTour extends RootKey implements Serializable {
   /* @EmbeddedId
    @AttributeOverrides({
            @AttributeOverride(name="userId",
                    column=@Column(name="user_id")),
            @AttributeOverride(name="tour",
                    column=@Column(name="tour"))
    })
    private UserTourKey id;*/
   @ManyToOne(optional = false, fetch = FetchType.LAZY)
   @JoinColumn( name="tour_id", referencedColumnName="id")
    private Tour tour;

    @Column(name= "user_id")
    private String userId;
    @Column(name= "status")
    private String status;

    @Column(name= "change_date",insertable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateChange;

    public UserTour(String user, Tour tour, String status){
        this.userId = user;
        this.tour = tour;
        this.status = status;
    }
    public UserTour(){ }
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

