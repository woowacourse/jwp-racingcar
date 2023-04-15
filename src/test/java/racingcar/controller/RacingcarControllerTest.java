package racingcar.controller;

import static org.hamcrest.core.Is.*;
import static org.hamcrest.core.IsNull.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;

import io.restassured.RestAssured;

@SpringBootTest
@Transactional
class RacingcarControllerTest {

    @DisplayName("/plays post 요청 정상동작 확인")
    @Test
    void playsPostMapping(){

        RacingGameRequest request = new RacingGameRequest("name1, name2", "10");

        RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(request)
                .when().post("/plays")
                .then().log().all()
                .statusCode(HttpStatus.OK.value())
                .body("winners", notNullValue())
                .body("racingCars", notNullValue())
                .body("racingCars.size()", is(2));
    }

    @DisplayName("/plays get 요청 정상동작 확인")
    @Test
    void playsGetMapping(){

        RacingGameRequest request = new RacingGameRequest("name1, name2", "10");

        RestAssured.given().log().all()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .body(request)
            .when().get("/plays")
            .then().log().all()
            .statusCode(HttpStatus.OK.value())
            .body("winners", notNullValue())
            .body("racingCars", notNullValue())
            .body("racingCars", notNullValue());
    }
}
