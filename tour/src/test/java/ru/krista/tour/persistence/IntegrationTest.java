package ru.krista.tour.persistence;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSender;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import ru.krista.tour.persistence.utils.DbTest;
import ru.krista.tour.view.View;

import static io.restassured.RestAssured.*;

import javax.inject.Named;
import java.io.File;


@Named("integration-test")
public class IntegrationTest extends DbTest {

    public static final class EndPoints {
        public static final String creation = "/creation";
        public static final String education = "/education";
        public static final String test = "/test";
    }

    RequestSpecification requestSpec = new RequestSpecBuilder()
          .setBaseUri("http://localhost/tour-server/resources")
            .setPort(8080)
            .build();
    ResponseSpecification responseSpec = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .build();

    @Before
    public void initRequest() {
        RestAssured.requestSpecification = requestSpec;

        //  given().baseUri("http://localhost/rest").port(8080).contentType(ContentType.JSON);
        //RestAssured.responseSpecification = responseSpec;

    }


    @Test
    public void creationResources() {
        RequestSpecification requestSpecification = given().log().all().when();
        Response response = requestSpecification.get(EndPoints.creation);
        System.out.println(response);
        response.then()
                .log().all().statusCode(200);
    }
}
