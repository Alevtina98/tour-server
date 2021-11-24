package ru.krista.tour.controller.domains.webApp.user.session.tour.program.focus.target;

public class TargetBo {
    public String formName;
    public String formCaption;

    public TargetBo (String formName, String formCaption) {
        this.formName = formName;
        this.formCaption = formCaption;
    }
    public TargetBo () {
        this.formName = "";
        this.formCaption = "";
    }
}
