package ru.krista.tour.view;

import ru.krista.tour.view.resources.restApi.Creation;
import ru.krista.tour.view.resources.restApi.Education;

import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

public class View extends Application {

    private Set<Object> singletons = new HashSet<Object>();

    public View() {
        singletons.add(new Creation());
        singletons.add(new Education());
    }

    @Override
    public Set<Object> getSingletons() {
        return singletons;
    }
}