package ru.krista.tour.model.data.persistence.entities;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Proxy;
import org.hibernate.annotations.UpdateTimestamp;

import javax.faces.bean.ApplicationScoped;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Proxy(lazy = true)
@Table(name = "tour")
@ApplicationScoped
public class Tour extends RootKey implements Serializable {
    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String desc;

    @Column(name = "code")
    @Lob
    private String code;

    @Column(name = "code_js" )
    @Lob
    private String codeJS;

    @Column(name = "create_date", updatable = false)
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


  /*  @PrePersist
    protected void onCreate() {
        dateCreate = new Date();
        dateChange = new Date();
    }

    @PreUpdate
    protected void onUpdate() {

        dateChange = new Date();
    }*/

    @Override
    public Long getId() {
        return super.getId();
    }

    public Date getDateChange() {
        return dateChange;
    }

    public Date getDateCreate() {
        return dateCreate;
    }

    public String getCode() {
        return code;
    }

    public String getCodeJS() {
        return codeJS;
    }

    public String getDesc() {
        return desc;
    }

    public String getFormCaption() {
        return formCaption;
    }

    public String getFormName() {
        return formName;
    }

    public String getName() {
        return name;
    }

    public boolean getIsGeneral() {
        return isGeneralUser;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setDesc(String desc) {
        this.desc = desc;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public void setCodeJS(String codeJS) {
        this.codeJS = codeJS;
    }
    public void setGeneralUser(boolean generalUser) {
        isGeneralUser = generalUser;
    }
    public void setFormName(String formName) {
        this.formName = formName;
    }
    public void setFormCaption(String formCaption) {
        this.formCaption = formCaption;
    }

}
