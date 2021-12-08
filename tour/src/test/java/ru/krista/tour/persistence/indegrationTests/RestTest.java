package ru.krista.tour.persistence.indegrationTests;

import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.Test;
import ru.krista.tour.model.data.persistence.entities.Tour;
import ru.krista.tour.persistence.persistence.TestData;


import static io.restassured.RestAssured.*;

import javax.inject.Named;


@Named("integration-test")
public class RestTest  extends TestData {

    public static final class EndPoints {
        public static final String creation = "/creation/{id}";
        public static final String education = "/education/";
    }

    RequestSpecification tourResources = given()
            .baseUri("http://localhost:8080/tour-server/resource");
    ResponseSpecification okResponse = expect()
            .statusCode(200);


    @Test
    public void creationResources() {
        tourResources
                .expect().spec(okResponse)
                .when()
                .get(EndPoints.creation)
                .then()
        ;

        tourResources
                .expect().spec(okResponse)
                .when()
                .get(EndPoints.creation, pTour1.getId().toString())
                .then()
        ;

        Tour tour = new Tour();
        tour.setName("Tour1");
        tour.setGeneralUser(true);

        tourResources
                .header("Content-Type", "application/json")
                .body(tour)
                .expect().spec(okResponse)
                .when()
                .put()
                .then().log().all()
        ;
    }
}
