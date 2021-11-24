package ru.krista.tour.controller;

import ru.krista.tour.Dto;
import ru.krista.tour.view.resources.IController;
import ru.krista.tour.controller.domains.webApp.user.UserBo;
import ru.krista.tour.controller.domains.webApp.user.UserService;
import ru.krista.tour.controller.domains.webApp.user.session.SessionService;
import ru.krista.tour.controller.domains.webApp.user.session.tour.TourBo;
import ru.krista.tour.controller.domains.webApp.user.session.tour.TourService;
import ru.krista.tour.controller.domains.webApp.user.session.tour.program.LearningProgramBo;
import ru.krista.tour.controller.domains.webApp.user.session.tour.program.focus.FocusBo;
import ru.krista.tour.controller.domains.webApp.user.session.tour.program.focus.target.TargetBo;
import ru.krista.tour.view.resources.presentations.informationObjects.*;

import javax.inject.Inject;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Controller  implements IController {
    @Inject
    UserService userService;
    @Inject
    SessionService sessionService;
    @Inject
    TourService tourService;

    private <TServiceData, TPresentationData> Dto<TPresentationData> buildPresentationDto(Dto<TServiceData> serviceDto, Function<TServiceData, TPresentationData> builder){
        if (serviceDto.status.equals(Dto.Status.error))  {
            //ErrorIo errorIo = new ErrorIo("");
            return  new Dto<>(null, Dto.Status.error, "");
        }
        return new Dto<>(builder.apply(serviceDto.data));
    };;
    private TargetBo buildTargetBo(TourIo tourIo){
        return new TargetBo(tourIo.formName, tourIo.formCaption);
    };
    private FocusBo buildFocusBo(TourIo tourIo){
        return new FocusBo(tourIo.isGeneralUser, buildTargetBo(tourIo));
    };
    private LearningProgramBo buildLearningProgramBo(TourIo tourIo){
        return new LearningProgramBo(tourIo.code, tourIo.codeJS, buildFocusBo(tourIo));
    };
    private TourBo buildTourBo(TourIo tourIo){
        TourBo tourBo = new TourBo();
        tourBo.id = tourIo.id;
        tourBo.name = tourIo.name;
        tourBo.desc = tourIo.desc;
        tourBo.dateCreate = tourIo.dateCreate;
        tourBo.dateChange = tourIo.dateChange;
        tourBo.learningProgram = buildLearningProgramBo(tourIo);
        return tourBo;
    };
    private TourIo buildTourIo(TourBo tourBo){
        TourIo tourIo = new TourIo();
        tourIo.id = tourBo.id;
        tourIo.name = tourBo.name;
        tourIo.desc = tourBo.desc;
        tourIo.code = tourBo.learningProgram.codeXml;
        tourIo.codeJS = tourBo.learningProgram.codeJs;
        tourIo.isGeneralUser = tourBo.learningProgram.focus.isGeneralUser;
        if (tourBo.learningProgram.focus.target != null) {
            tourIo.formName = tourBo.learningProgram.focus.target.formName;
            tourIo.formCaption = tourBo.learningProgram.focus.target.formCaption;
        }
        return tourIo;
    };
    private UserBo buildUserBo(UserSessionIo userSessionIo){
        /*LessonBo lessonBo = new LessonBo();
        UserBo userBo = new UserBo();
        businessObject.status = lessonIo.name;
        businessObject.startDate = lessonIo.desc;
        businessObject.finishDate = lessonIo.code;

        return lessonIo;*/
        return null;
    };


    @Override
    public Dto<List<TourIo>> getTours() {
        Dto<List<TourBo>> serviceDto = tourService.getAllTours();
      //  buildPresentationDto<List<TourBo>, List<TourIo>>(serviceDto,);
        List<TourIo> tourIoList = serviceDto.data.stream().map(this::buildTourIo).collect(Collectors.toList());
        return new Dto<>(tourIoList);
    };
    @Override
    public Dto<TourIo> getTour (TourIdIo info){
        Dto<TourBo> serviceDto = tourService.getTour(info.id);
        TourIo tourIo = buildTourIo(serviceDto.data);
        return new Dto<>(tourIo);
    };
    @Override
    public Dto<TourIo> createTour(TourIo info){
        TourBo tourBo = buildTourBo(info);
        Dto<TourBo> serviceDto = tourService.createTour(tourBo);
        TourIo tourIo = buildTourIo(serviceDto.data);
        return  new Dto<>(tourIo);
    };
    @Override
    public Dto<TourIo> changeTour(TourIo info){
        TourBo tourBo = buildTourBo(info);
        Dto<TourBo> serviceDto = tourService.changeTour(tourBo);
        if (serviceDto.status.equals(Dto.Status.error))  {
            ErrorIo errorIo = new ErrorIo("");
            return  new Dto<>(null, Dto.Status.error, "");
        }
        TourIo tourIo = buildTourIo(serviceDto.data);
        return  new Dto<>(tourIo);
    }

    @Override
    public Dto<EmptyIo> deleteTour(TourIdIo info) {
        Dto<Object> serviceDto = tourService.deleteTour(info.id);
        if (serviceDto.status.equals(Dto.Status.error))  {
            ErrorIo errorIo = new ErrorIo("");
            return  new Dto<>(null, Dto.Status.error, "");
        }
        return new Dto<EmptyIo>(null);
    }

    ;
    @Override
    public Dto<List<TourIo>> getToursWithGeneralFocus(){
        Dto<List<TourBo>> serviceDto = tourService.getGeneralTours();
        List<TourIo> tourIoList = serviceDto.data.stream().map(this::buildTourIo).collect(Collectors.toList());
        return  new Dto<>(tourIoList);
    };
    @Override
    public Dto<List<TourIo>> getToursFromUserSessionWithStatus(StatusAndUserIo info){
        //Dto<List<TourBo>> serviceDto = sessionService.;
        //List<TourIo> tourIoList = serviceDto.data.stream().map(this::buildTourIo).collect(Collectors.toList());
        return null;
    };
    @Override
    public Dto<List<UserSessionIo>> getUserSessions(UserIdIo info){
        return null;
    };
    @Override
    public Dto<UserSessionIo> createUserSession(UserSessionIo info){
       /* Long tourId = lesson.tour.id;
        List<LessonIo> sameEntity = findUserTour(lesson.getUserId(), tourId);
        if (!sameEntity.isEmpty()) {
            return response(500, "Данная связь уже существует");
        }
        TourIo tour = findById(TourIo.class, tourId);
        if (tour==null) {
            return response(500, "Назначаемого тура не существует");
        }
        LessonIo insertedUserTour = save(lesson);
        if (insertedUserTour != null){
            return response(200, insertedUserTour);
        }else {
            return response(500);
        }*/
        return new Dto<UserSessionIo>(new UserSessionIo());
    };
    @Override
    public Dto<UserSessionIo> updateUserSession(UserSessionIo info){
        /*Long tourId = userTour.getTour().getId();
        List<LessonIo> sameEntity = findUserTour(userTour.getUserId(), tourId);
        if (sameEntity != null) {
            if (sameEntity.size() != 1) {
                return response(418);
            }
            userTour.setId(sameEntity.get(0).getId());
        }
        LessonIo updatedUserTour = save(userTour);
        if (updatedUserTour != null){
            return response(200, updatedUserTour);
        }else {
            return response(500);
        }*/
        return new Dto<UserSessionIo>(new UserSessionIo());
    };
}
