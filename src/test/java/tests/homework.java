package tests;


import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.is;


class Homework extends TestBase {
    @Test
    public void statusTest() {
        given()
                .log().all()
                .auth().basic("user1", "1234")
                .when()
                .get("/wd/hub/status")
                .then()
                .log().all()
                .statusCode(200);
    }

    @Test
    public void unauthorizedStatusTest() {
        given()
                .log().all()
                .when()
                .get("/wd/hub/status")
                .then()
                .log().all()
                .statusCode(401);
    }


//-------------------Homework

    @Test
    public void checkingMassage() {
        given()
                .auth().basic("user1", "1234")
                .log().all()
                .when()
                .get("/wd/hub/status")
                .then()
                .log().body()
                .statusCode(200)
                .body("value.message", is("Selenoid 1.11.3 built at 2024-05-25_12:34:40PM"));
    }

    @Test
    public void checkingReady() {
        given()
                .auth().basic("user1", "1234")
                .log().all()
                .when()
                .get("/wd/hub/status")
                .then()
                .log().body()
                .statusCode(200)
                .body("value.ready", is(true));
    }

    @Test
    public void checkingJsonSchemaTest() {
        given()
                .auth().basic("user1", "1234")
                .log().all()
                .when()
                .get("/wd/hub/status")
                .then()
                .log().body()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("schemas/wd_hub_status_response_schema.json"));
    }


    @Test
    public void negativeTestLogin() {
        given()
                .auth().basic("", "1234")
                .log().all()
                .when()
                .get("/wd/hub/status")
                .then()
                .log().body()
                .statusCode(401);
    }

    @Test
    public void checkingAnotherPage() {
        given()
                .auth().basic("user1", "1234")
                .log().all()
                .when()
                .get("/wd/hub/status1")
                .then()
                .log().body()
                .statusCode(200)
                .body(is("You are using Selenoid 1.11.3!"));
    }
}

