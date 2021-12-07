package ru.krista.tour.controller.domains.webApp.user.session.tour;

import ru.krista.tour.controller.domains.webApp.user.session.tour.program.LearningProgramBo;
import ru.krista.tour.model.data.persistence.entities.Tour;

import java.util.Date;

/*
 ** ОБУЧАЮЩИЙ ТУР
 */
public class TourBo {
    public Long id;
    public String name;
    public String desc;
    public Date dateCreate;
    public Date dateChange;
    public LearningProgramBo learningProgram;

    public TourBo (String name, String desc, Date dateCreate, Date dateChange, LearningProgramBo learningProgram) {
        this.name = name;
        this.desc = desc;
        this.dateCreate = dateCreate;
        this.dateChange = dateChange;
        this.learningProgram = learningProgram;
    }

    public TourBo () {
        this.name = "";
        this.desc = "";
        this.dateChange = new Date();
        this.dateCreate = new Date();
        this.learningProgram = new LearningProgramBo(); //является агрегатором (в будующем код может быть библиотекой?)
    }
}
