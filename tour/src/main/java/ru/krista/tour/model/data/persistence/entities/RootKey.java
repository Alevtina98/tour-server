package ru.krista.tour.model.data.persistence.entities;

import javax.persistence.*;
import java.io.Serializable;

@MappedSuperclass
public abstract class RootKey implements Serializable {
    @Id
    //@SequenceGenerator(name = "root", sequenceName = "seq_root", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    protected Long id;
    public Long getId() {return id;}
    // public void setId (Long key) {this.id = key;}

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }
    public RootKey() {
        super();
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        RootKey other = (RootKey) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        String result;
        if (id == null) {
            result = this.getClass().getName() + " - [ NEW ]";
        }
        else {
            result = this.getClass().getName() + " - [ID=" + id + "]";
        }
        return result;
    }

}
