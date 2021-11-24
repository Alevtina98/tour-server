package ru.krista.tour.controller.domains.webApp.user.session.tour.program.focus;

import ru.krista.tour.controller.domains.webApp.user.session.tour.program.focus.target.TargetBo;

public class FocusBo {
    public boolean isGeneralUser;
    public TargetBo target;

    public FocusBo (boolean isGeneralUser, TargetBo target) {
        this.isGeneralUser = isGeneralUser;
        this.target = target;
    }
    public FocusBo () {
        this.isGeneralUser = true;
        this.target = null; // агрегация, т. к. можно выделить сущность Форма - может встречаться в различных версиях различных приложений - общее шаблонное решение (компонент)
    }
}
