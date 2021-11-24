package ru.krista.tour.controller.domains.webApp;

import ru.krista.tour.controller.domains.webApp.user.UserBo;

import java.util.List;

/*
 ** ПЛАТФОРМА ОБУЧЕНИЯ (веб-приложение)
 */
public abstract class WebAppBo {
        public List<UserBo> userList;
        public String name;
        public String version;

        public WebAppBo (String name, String version,  List<UserBo> userList) {
                this.name  =name;
                this.version = version;
                this.userList = userList;
        }
}
