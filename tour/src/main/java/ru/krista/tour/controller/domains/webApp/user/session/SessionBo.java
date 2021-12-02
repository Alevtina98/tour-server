package ru.krista.tour.controller.domains.webApp.user.session;

import ru.krista.tour.controller.domains.webApp.user.session.tour.TourBo;

import java.util.Date;

/*
 ** ПРОЦЕСС ОБУЧЕНИЯ (прохождение тура)
 */

public class SessionBo {
    public Long id;
    public TourBo tour;
    public Date dateChange;
    public String status;

    public SessionBo(TourBo tour, String status, Date date) {
        this.tour = tour;
        this.status = status;
       this.dateChange = date;
    }
    public SessionBo() {
    }
}
