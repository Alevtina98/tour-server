package ru.krista.tour.view.resources.presentations;

import ru.krista.tour.Dto;
import ru.krista.tour.view.resources.IPresenter;

import javax.faces.bean.ApplicationScoped;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON_TYPE;

@ApplicationScoped
public class Presenter implements IPresenter {
    private Response statusResponse(Integer code, Object data) {
        if (data == null) {
            return Response.status(code).build();
        }
        return Response.status(code).type(APPLICATION_JSON_TYPE).entity(data).build();
    }
    private Response errorResponse (Object info){
        return statusResponse(500, info);
    }
    private Response okResponse (Object info){
        return statusResponse(200, info);
    }

    @Override
    public <TInformationObject> Response response(Dto<TInformationObject> dto) {
        if (dto.status == Dto.Status.error) {
             return errorResponse(dto.data);
        }
        return okResponse(dto.data);
    }
}
