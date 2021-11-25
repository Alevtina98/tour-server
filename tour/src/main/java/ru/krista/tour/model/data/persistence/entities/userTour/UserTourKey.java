package ru.krista.tour.model.data.persistence.entities.userTour;

import ru.krista.tour.model.data.persistence.entities.Tour;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class UserTourKey implements Serializable {
    @JoinColumn(referencedColumnName = "\"user_id\"")
    @ManyToOne(optional = false)
    private String userId;
    @JoinColumn(referencedColumnName = "tour")
    @ManyToOne(optional = false)
    private Tour tour;

    public UserTourKey () {}
    public UserTourKey (String userId, Tour tour) {
        this.tour= tour;
        this.userId = userId;
    }

    public void setTour(Tour tour) {
        this.tour = tour;
    }
    public void setUserId(String userId){
        this.userId = userId;
    }

    public Tour getTour() {
        return tour;
    }

    public String getUserId() {
        return userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if(!(o instanceof UserTourKey)) {
            return false;
        }
        UserTourKey bk_info = (UserTourKey) o;
        return Objects.equals(userId, bk_info.userId) && Objects.equals(userId, bk_info.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, tour);
    }
}
