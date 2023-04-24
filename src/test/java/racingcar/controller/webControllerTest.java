package racingcar.controller;

import static org.hamcrest.core.Is.*;
import static org.hamcrest.core.IsNull.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import io.restassured.RestAssured;
import racingcar.dto.RacingCarRequestDto;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class webControllerTest {

    @LocalServerPort
    int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    @DisplayName("/plays post 요청 정상동작 확인")
    @Test
    void playsPostMapping() {

        RacingCarRequestDto request = new RacingCarRequestDto("name1, name2", "10");

        RestAssured.given().log().all()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .body(request)
            .when().post("/plays")
            .then().log().all()
            .statusCode(HttpStatus.OK.value())
            .body("winners", notNullValue())
            .body("racingCars",notNullValue())
            .body("racingCars.size()", is(2));
    }

    @DisplayName("/plays get 요청 정상동작 확인")
    @Test
    void playsGetMapping() {

        RacingCarRequestDto request = new RacingCarRequestDto("name1, name2", "10");

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

    @DisplayName("/plays post 잘못된 count 요청시, 핸들러 동작 확인")
    @Test
    void playsPostMappingCountFail() {

        RacingCarRequestDto request = new RacingCarRequestDto("name1, name2", "0");

        RestAssured.given().log().all()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .body(request)
            .when().post("/plays")
            .then().log().all()
            .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @DisplayName("/plays post 잘못된 names 요청시, 핸들러 동작 확인")
    @Test
    void playsPostMappingNamesFail() {

        RacingCarRequestDto request = new RacingCarRequestDto("", "10");

        RestAssured.given().log().all()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .body(request)
            .when().post("/plays")
            .then().log().all()
            .statusCode(HttpStatus.BAD_REQUEST.value());
    }
}
