package ru.krista.tour.model.data.persistence.entities;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Proxy;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Proxy(lazy = true)
@Table(name = "tour")
public class Tour extends RootKey implements Serializable {
    @Column(name = "name")
    private String name;

    @Column(name = "description", nullable = true, columnDefinition = "")
    private String desc;

    @Column(name = "code", nullable = true, columnDefinition = "")
    private String code;

    @Column(name = "code_js", nullable = true, columnDefinition = "")
    private String codeJS;

    @Column(name = "create_date")
    @CreationTimestamp
    private Date dateCreate;

    @Column(name = "change_date")
    @UpdateTimestamp
    private Date dateChange;

    @Column(name = "form_name")
    private String formName;

    @Column(name = "form_caption")
    private String formCaption;

    @Column(name = "is_general_user")
    private boolean isGeneralUser;

    public Tour (String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    public Tour() {

    }
}
