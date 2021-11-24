package ru.krista.tour.controller.domains.webApp.user.session.tour.program;

import ru.krista.tour.controller.domains.webApp.user.session.tour.program.focus.FocusBo;

public class LearningProgramBo {
    public String codeXml;
    public String codeJs;
    public FocusBo focus;

    public LearningProgramBo (String codeJs, String codeXml, FocusBo focus) {
        this.codeXml = codeXml;
        this.codeJs = codeJs;
        this.focus = focus;
    }
    public LearningProgramBo () {
        this.codeXml = "";
        this.codeJs = "";
        this.focus = null;  // агрегация, т. к. можно выделить сущность Категория пользователей, группирующую пользователей, работающих с определенным функционалом
    }
}
