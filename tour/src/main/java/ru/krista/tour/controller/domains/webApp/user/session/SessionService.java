package ru.krista.tour.controller.domains.webApp.user.session;

public class SessionService {
    public enum StatusVariant {
        APPOINTED ("назначен"),
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
  /*  public Dto<List<TourBo>> getTourListByStatus(List<SessionBo> sessionBoList, SessionBo.StatusVariant status) {
        List<SessionBo> filterList = sessionBoList.stream().filter(sessionBo -> sessionBo.status.equals(status)).collect(Collectors.toList());
        List<TourBo>  tourBoList = filterList.stream().map((sessionBo -> sessionBo.tour)).collect(Collectors.toList());
        return new Dto<>(tourBoList);// new ArrayList<TourBo>();
    };*/

}
