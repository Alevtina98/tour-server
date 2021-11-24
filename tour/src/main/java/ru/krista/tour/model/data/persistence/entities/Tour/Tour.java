package ru.krista.tour.model.data.persistence.entities.Tour;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import ru.krista.tour.model.data.persistence.entities.RootKey;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "tour")
public class Tour extends RootKey implements Serializable {
    @Column(name = "\"name\"")
    private String name;

    @Column(name = "desc", nullable = false, columnDefinition = "")
    private String desc;

    @Column(name = "\"code\"", nullable = false, columnDefinition = "")
    private String code;

    @Column(name = "\"code_js\"", nullable = false, columnDefinition = "")
    private String codeJS;

    @Column(name = "\"create_date\"")
    @CreationTimestamp
    private Date dateCreate;

    @Column(name = "\"change_date\"")
    @UpdateTimestamp
    private Date dateChange;

    @Column(name = "\"form_name\"")
    private String formName;

    @Column(name = "\"form_caption\"")
    private String formCaption;

    @Column(name = "\"is_general_user\"")
    private boolean isGeneralUser;

}
