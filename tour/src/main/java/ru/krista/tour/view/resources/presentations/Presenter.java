package ru.krista.tour.view.resources.presentations;

import ru.krista.tour.Dto;
import ru.krista.tour.view.resources.IPresenter;
import ru.krista.tour.view.resources.presentations.informationObjects.ErrorIo;

import javax.faces.bean.ApplicationScoped;
import javax.ws.rs.core.Response;

import java.util.logging.Logger;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON_TYPE;

@ApplicationScoped
public class Presenter implements IPresenter {
    private Logger logger = Logger.getLogger("Presenter LOGGER");

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
            dto.errorMsgList.forEach(msg-> logger.info(msg));
            // return errorResponse(null);
            return errorResponse(new ErrorIo(dto.errorMsgList));
        }
        return okResponse(dto.data);
    }
}
