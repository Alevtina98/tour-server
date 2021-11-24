package ru.krista.tour.controller.domains.webApp.user.session;

import ru.krista.tour.controller.domains.webApp.user.session.tour.TourBo;

import java.util.Date;

/*
 ** ПРОЦЕСС ОБУЧЕНИЯ (прохождение тура)
 */

public class SessionBo {
    public TourBo tour;
    public Date startDate;
    public Date finishDate;
    public StatusVariant status;

    public enum StatusVariant {
        DELAYED ("отложен"),
        INTERRUPTED("прерван"),
        PASSED("пройден");

        private final String title;
        StatusVariant(String title) {
            this.title = title;
        }
        public String getTitle() {
            return title;
        }
    }

    public SessionBo ( TourBo tour, StatusVariant status, Date startDate, Date finishDate) {
        this.tour = tour;
        this.status = status;
        this.startDate = startDate;
        this.finishDate = finishDate;
    }
}
